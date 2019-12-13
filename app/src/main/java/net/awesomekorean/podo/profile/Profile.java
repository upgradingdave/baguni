package net.awesomekorean.podo.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.LessonFinish;
import net.awesomekorean.podo.message.MessageAdapter;
import net.awesomekorean.podo.purchase.Subscribe;

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
        btnGetPoint.setOnClickListener(this);
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

        DocumentReference docRef = db.collection(getString(R.string.DB_ATTENDANCE)).document(MainActivity.userEmail);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        AttendanceItem attendance = document.toObject(AttendanceItem.class);
                        System.out.println("DB에서 출석부를 가져왔습니다");
                        setAttendance(attendance);
                    } else {
                        System.out.println("출석부가 없습니다");
                    }
                } else {
                    System.out.println("출석부 가져오기를 실패했습니다: "+task.getException());
                }
            }
        });
    }


    // 출석체크 리사이클러뷰 세팅
    private void setAttendance(AttendanceItem attendance) {

        for(int i=1; i<8; i++) {
            String dayOfWeek = "day"+i;
            boolean isChecked = attendance.getDay(dayOfWeek);

            DayOfWeekItem item = new DayOfWeekItem();
            switch (dayOfWeek) {
                case "day1" :
                    setItems(item, "S", isChecked);
                    break;
                case "day2" :
                    setItems(item, "M", isChecked);
                    break;
                case "day3" :
                    setItems(item, "T", isChecked);
                    break;
                case "day4" :
                    setItems(item, "W", isChecked);
                    break;
                case "day5" :
                    setItems(item, "TH", isChecked);
                    break;
                case "day6" :
                    setItems(item, "F", isChecked);
                    break;
                case "day7" :
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
        attendanceCount++;

        // 일주일 출석 개근일 때
        if(attendanceCount == 7) {
            // Get 버튼 활성화
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
                String today = "day" + cal.get(Calendar.DAY_OF_WEEK); // day1:일요일 ~ day7:토요일
                AttendanceItem attendanceItem = new AttendanceItem();
                attendanceItem.resetDays(today);
                db.collection(getString(R.string.DB_ATTENDANCE)).document(MainActivity.userEmail).set(attendanceItem);
                System.out.println("일주일 모두 출석! DB의 출석부를 초기화 했습니다");

                intent = new Intent(this, LessonFinish.class);
                startActivity(intent);
                break;

            case R.id.layoutEditName :
                setExtendableButton(arrowEditProfile, layoutEditNameOpen);
                break;

            case R.id.btnSave :
                String newName = editName.getText().toString();
                DocumentReference reference = db.collection(getString(R.string.DB_USERS)).document(MainActivity.userEmail);
                reference.update("name", newName).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), getString(R.string.SAVE_NEW_NAME), Toast.LENGTH_LONG).show();
                        System.out.println("이름을 변경했습니다");
                    }
                });

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
                break;

            case R.id.recommend :
                break;

            case R.id.logout :
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
    }

}
