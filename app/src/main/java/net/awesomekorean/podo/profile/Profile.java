package net.awesomekorean.podo.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.LessonFinish;
import net.awesomekorean.podo.login.SignIn;
import net.awesomekorean.podo.message.MessageAdapter;
import net.awesomekorean.podo.purchase.Subscribe;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ImageView btnBack;
    ImageView userImage;
    TextView userName;
    TextView userPoint;

    LinearLayout btnGetPoint;

    LinearLayout layoutEditName;
    LinearLayout layoutEditNameOpen;
    EditText editName;
    Button btnSave;

    LinearLayout purchase;

    LinearLayout layoutLanguage;
    ImageView ivFlag;
    TextView tvLanguage;

    LinearLayout layoutLanguageOpen;
    LinearLayout layoutEnglish;
    LinearLayout layoutKorean;
    LinearLayout layoutChinese;
    LinearLayout layoutJapanese;
    LinearLayout layoutThai;

    LinearLayout evaluation;
    LinearLayout recommend;
    LinearLayout logout;

    ImageView arrowEditProfile;
    ImageView arrowLanguage;

    Intent intent;

    AttendanceAdapter adapter;
    List<DayOfWeekItem> list = new ArrayList<>();

    int attendanceCount = 0; // 출석체크 카운트 (연속출석 7번 했는지 확인)

    boolean btnExtendClicked = false;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnBack = findViewById(R.id.btnBack);
        userImage = findViewById(R.id.userImage);
        userName = findViewById(R.id.userName);
        userPoint = findViewById(R.id.userPoint);

        btnGetPoint = findViewById(R.id.btnGetPoint);

        layoutEditName = findViewById(R.id.layoutEditName);
        layoutEditNameOpen = findViewById(R.id.layoutEditNameOpen);
        editName = findViewById(R.id.editName);
        btnSave = findViewById(R.id.btnSave);

        purchase = findViewById(R.id.purchase);

        layoutLanguage = findViewById(R.id.layoutLanguage);
        ivFlag = findViewById(R.id.ivFlag);
        tvLanguage = findViewById(R.id.tvLanguage);

        layoutLanguageOpen = findViewById(R.id.layoutLanguageOpen);
        layoutEnglish = findViewById(R.id.layoutEnglish);
        layoutKorean = findViewById(R.id.layoutKorean);
        layoutChinese = findViewById(R.id.layoutChinese);
        layoutJapanese = findViewById(R.id.layoutJapanese);
        layoutThai = findViewById(R.id.layoutThai);

        evaluation = findViewById(R.id.evaluation);
        recommend = findViewById(R.id.recommend);
        logout = findViewById(R.id.logout);

        arrowEditProfile = findViewById(R.id.arrowEditProfile);
        arrowLanguage = findViewById(R.id.arrowLanguage);

        btnBack.setOnClickListener(this);
        layoutEditName.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        purchase.setOnClickListener(this);
        layoutLanguage.setOnClickListener(this);
        layoutEnglish.setOnClickListener(this);
        layoutKorean.setOnClickListener(this);
        layoutChinese.setOnClickListener(this);
        layoutJapanese.setOnClickListener(this);
        layoutThai.setOnClickListener(this);
        evaluation.setOnClickListener(this);
        recommend.setOnClickListener(this);
        logout.setOnClickListener(this);

        userName.setText(MainActivity.userName);
        userPoint.setText(String.valueOf(MainActivity.userInformation.getPoints()));

        // 유저프로필사진 가져오기
        final Uri profileImage = MainActivity.userImage;
        if(profileImage != null) {
            // 프로필이미지 불러오기
            Thread mThread = new Thread() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(profileImage.toString());
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true);
                        conn.connect();

                        InputStream is = conn.getInputStream();
                        bitmap = BitmapFactory.decodeStream(is);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            mThread.start();

            try {
                mThread.join();
                userImage.setImageBitmap(bitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        setAttendance(MainActivity.userInformation.getAttendance());
    }


    // 출석체크 리사이클러뷰 세팅
    private void setAttendance(List<Boolean> attendance) {

        for(int i=0; i<7; i++) {
            boolean isChecked = attendance.get(i);

            DayOfWeekItem item = new DayOfWeekItem();
            switch (i) {
                case 0 :
                    setItems(item, "S", isChecked);
                    break;
                case 1 :
                    setItems(item, "M", isChecked);
                    break;
                case 2 :
                    setItems(item, "T", isChecked);
                    break;
                case 3 :
                    setItems(item, "W", isChecked);
                    break;
                case 4 :
                    setItems(item, "TH", isChecked);
                    break;
                case 5 :
                    setItems(item, "F", isChecked);
                    break;
                case 6 :
                    setItems(item, "S", isChecked);
                    break;
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AttendanceAdapter(list);
        recyclerView.setAdapter(adapter);

    }

    private void setItems(DayOfWeekItem item, String day, boolean isChecked) {
        item.setDay(day);
        item.setChecked(isChecked);
        list.add(item);

        if(isChecked){
            attendanceCount++;
        }

        // 일주일 출석 개근일 때 Get 버튼 활성화
        if(attendanceCount == 7) {
            btnGetPoint.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnBack :
                finish();
                break;

            case R.id.btnGetPoint :
                // 일주일 모두 출석했으면 오늘 출석만 남기고 다 초기화
                Calendar cal = Calendar.getInstance();
                int today = cal.get(Calendar.DAY_OF_WEEK); // 1:일요일 ~ 7:토요일
                MainActivity.userInformation.resetDays(today);
                db.collection(getString(R.string.DB_USERINFO)).document(MainActivity.userEmail).set(MainActivity.userInformation);
                System.out.println("일주일 모두 출석! DB의 출석부를 초기화 했습니다");

                intent = new Intent(this, LessonFinish.class);
                startActivity(intent);
                break;

            case R.id.layoutEditName :
                setExtendableButton(arrowEditProfile, layoutEditNameOpen);
                break;

            case R.id.btnSave :
                final String newName = editName.getText().toString();

                if(newName != null) {
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(newName)
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        System.out.println("userName을 업데이트 했습니다");
                                        Toast.makeText(getApplicationContext(), getString(R.string.UPDATE_USERNAME), Toast.LENGTH_SHORT).show();
                                        MainActivity.userName = user.getDisplayName();
                                        userName.setText(newName);
                                    }
                                }
                            });
                }

                setExtendableButton(arrowEditProfile, layoutEditNameOpen);
                break;

            case R.id.purchase :
                intent = new Intent(this, Subscribe.class);
                startActivity(intent);
                break;

            case R.id.layoutLanguage :
                setExtendableButton(arrowLanguage, layoutLanguageOpen);
                break;

            case R.id.layoutEnglish :
                setLanguage(R.drawable.en, R.string.ENGLISH);
                break;

            case R.id.layoutKorean :
                setLanguage(R.drawable.kr, R.string.KOREAN);
                break;

            case R.id.layoutChinese :
                setLanguage(R.drawable.cn, R.string.CHINESE);
                break;

            case R.id.layoutJapanese :
                setLanguage(R.drawable.jp, R.string.JAPANESE);
                break;

            case R.id.layoutThai :
                setLanguage(R.drawable.th, R.string.THAI);
                break;

            case R.id.evaluation :
                // 다운로드 링크로 연결
                break;

            case R.id.recommend :
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String title = "앱 다운 링크"; // <-- 앱 다운 링크 넣기
                intent.putExtra(Intent.EXTRA_TEXT, title);

                Intent chooser = Intent.createChooser(intent, "친구에게 공유!");
                startActivity(chooser);
                break;

            case R.id.logout :
                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.SIGN_OUT)).setMessage(getString(R.string.SIGN_OUT_MESSAGE))
                        .setPositiveButton(getString(R.string.SIGN_OUT), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                intent = new Intent(getApplicationContext(), SignIn.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent);
                                FirebaseAuth.getInstance().signOut();
                            }
                        })
                        .setNegativeButton(getString(R.string.CANCEL), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();
                break;
        }
    }

    // editProfile 이랑 language 펼치기/접기 메소드
    private void setExtendableButton(ImageView arrow, LinearLayout closedLayout) {
        if(!btnExtendClicked) {
            arrow.setImageResource(R.drawable.arrow_down_grey);
            closedLayout.setVisibility(View.VISIBLE);
            btnExtendClicked = true;
        } else {
            arrow.setImageResource(R.drawable.arrow_right_grey);
            closedLayout.setVisibility(View.GONE);
            btnExtendClicked = false;
        }
    }

    // 언어 선택 메소드
    private void setLanguage(int flag, int language) {
        ivFlag.setImageResource(flag);
        tvLanguage.setText(getString(language));
        layoutLanguageOpen.setVisibility(View.GONE);
        setExtendableButton(arrowLanguage, layoutLanguageOpen);
    }

}
