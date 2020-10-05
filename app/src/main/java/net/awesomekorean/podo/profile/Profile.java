package net.awesomekorean.podo.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import net.awesomekorean.podo.AdsManager;
import net.awesomekorean.podo.GetRandomPoint;
import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.PlaySoundPool;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UnixTimeStamp;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.lesson.LessonFinish;
import net.awesomekorean.podo.login.SignIn;
import net.awesomekorean.podo.purchase.TopUp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ImageView btnBack;
    ImageView userImage;
    TextView userName;
    TextView userPoint;

    ImageView btnGetPoint;

    LinearLayout layoutEditName;
    LinearLayout layoutEditNameOpen;
    EditText editName;
    Button btnSave;

    LinearLayout reportBug;
    LinearLayout reportBugOpen;
    EditText reportBugText;
    Button btnSend;

    LinearLayout evaluation;
    LinearLayout recommend;
    LinearLayout getPointByAd;
    LinearLayout getPointByPurchasing;
    LinearLayout logout;

    LinearLayout reportResult;

    ImageView arrowEditProfile;
    ImageView arrowReportBug;

    Intent intent;

    AttendanceAdapter adapter;
    List<DayOfWeekItem> list;

    int attendanceCount = 0; // 출석체크 카운트 (연속출석 7번 했는지 확인)

    Bitmap bitmap;

    UserInformation userInformation;

    RecyclerView recyclerView;

    AdsManager adsManager;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        context = getApplicationContext();

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        adsManager = AdsManager.getInstance();

        if(adsManager.rewardedAd == null || !adsManager.rewardedAd.isLoaded()) {

            adsManager.loadRewardAds(context);
        }

        btnBack = findViewById(R.id.btnBack);
        userImage = findViewById(R.id.userImage);
        userName = findViewById(R.id.userName);
        userPoint = findViewById(R.id.userPoint);

        btnGetPoint = findViewById(R.id.btnGetPoint);

        layoutEditName = findViewById(R.id.layoutEditName);
        layoutEditNameOpen = findViewById(R.id.layoutEditNameOpen);
        editName = findViewById(R.id.editName);
        btnSave = findViewById(R.id.btnSave);

        reportBug = findViewById(R.id.reportBug);
        reportBugOpen = findViewById(R.id.reportBugOpen);
        reportBugText = findViewById(R.id.reportBugText);
        btnSend = findViewById(R.id.btnSend);

        evaluation = findViewById(R.id.evaluation);
        recommend = findViewById(R.id.recommend);
        getPointByAd = findViewById(R.id.getPointsByAd);
        getPointByPurchasing = findViewById(R.id.getPointsByPurchasing);
        logout = findViewById(R.id.logout);
        reportResult = findViewById(R.id.reportResult);

        arrowEditProfile = findViewById(R.id.arrowEditProfile);
        arrowReportBug = findViewById(R.id.arrowReportBug);

        btnBack.setOnClickListener(this);
        btnGetPoint.setOnClickListener(this);
        layoutEditName.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        reportBug.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        evaluation.setOnClickListener(this);
        recommend.setOnClickListener(this);
        getPointByAd.setOnClickListener(this);
        getPointByPurchasing.setOnClickListener(this);
        logout.setOnClickListener(this);

        userInformation = SharedPreferencesInfo.getUserInfo(context);

        userName.setText(SharedPreferencesInfo.getUserName(context));
        userPoint.setText(String.valueOf(userInformation.getPoints()));

        // 유저프로필사진 가져오기
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final Uri profileImage = user.getPhotoUrl();

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
                mThread.join(3000);
                userImage.setImageBitmap(bitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        setAttendance(userInformation.getAttendance());
    }


    // 출석체크 리사이클러뷰 세팅
    private void setAttendance(List<Boolean> attendance) {

        list = new ArrayList<>();

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

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AttendanceAdapter(list);
        recyclerView.setAdapter(adapter);
    }


    // 출석부 세팅하기
    private void setItems(DayOfWeekItem item, String day, boolean isChecked) {
        item.setDay(day);
        item.setChecked(isChecked);
        list.add(item);

        if(isChecked){
            attendanceCount++;
        }

        // 일주일 출석 개근일 때 Get 버튼 활성화
        if(attendanceCount == 7) {
            btnGetPoint.setImageResource(R.drawable.getpodo);
            btnGetPoint.setEnabled(true);
        } else {
            btnGetPoint.setImageResource(R.drawable.getpodo_grey);
            btnGetPoint.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnBack :
                finish();
                break;

            case R.id.btnGetPoint :
                PlaySoundPool playSoundPool = new PlaySoundPool(context);
                playSoundPool.playSoundLesson(2);

                // 오늘 출석만 남기고 다 초기화
                UserInformation userInformation = SharedPreferencesInfo.getUserInfo(context);
                Calendar cal = Calendar.getInstance();
                int today = cal.get(Calendar.DAY_OF_WEEK) - 1; // 1:일요일 ~ 7:토요일
                userInformation.resetDays(today);
                setAttendance(userInformation.getAttendance());
                System.out.println("출석부를 초기화 했습니다");

                userInformation.addRewardPoints(context, 20);
                userPoint.setText(String.valueOf(userInformation.getPoints()));
                break;

            case R.id.layoutEditName :
                setExtendableButton(arrowEditProfile, layoutEditNameOpen);
                break;

            case R.id.btnSave :
                final String newName = editName.getText().toString();

                if(newName.getBytes().length > 0) {
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(newName)
                            .build();

                    if(user != null) {
                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            System.out.println("userName을 업데이트 했습니다");
                                            Toast.makeText(context, getString(R.string.UPDATED_USERNAME), Toast.LENGTH_SHORT).show();
                                            MainActivity.userName = user.getDisplayName();
                                            userName.setText(newName);
                                            SharedPreferencesInfo.setUserName(context, newName);
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println("userName을 업데이트를 실패 했습니다" + e);
                                Toast.makeText(context, getString(R.string.UPDATED_USERNAME_FAILED), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                setExtendableButton(arrowEditProfile, layoutEditNameOpen);
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(editName.getWindowToken(), 0);
                break;

            case R.id.reportBug :
                setExtendableButton(arrowReportBug, reportBugOpen);
                break;

            case R.id.btnSend :
                String token = SharedPreferencesInfo.getUserToken(context);
                String comments = reportBugText.getText().toString();
                if (comments.getBytes().length > 0) {
                    Map<String, Object> report = new HashMap<>();
                    report.put("date", UnixTimeStamp.getTimeNow());
                    report.put("userEmail", SharedPreferencesInfo.getUserEmail(context));
                    report.put("userName", SharedPreferencesInfo.getUserName(context));
                    report.put("comments", comments);
                    report.put("userToken", token);
                    report.put("status", 0);  // 0:신규, 1:읽음, 2:답변함
                    report.put("answer", "");

                    db.collection(getString(R.string.DB_REPORTS)).document(UUID.randomUUID().toString())
                            .set(report).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            reportResult.setVisibility(View.VISIBLE);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    reportResult.setVisibility(View.GONE);
                                }
                            }, 3000);
                        }
                    });
                }

                setExtendableButton(arrowReportBug, reportBugOpen);
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(reportBugText.getWindowToken(), 0);
                break;

            case R.id.evaluation :
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=net.awesomekorean.podo");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            case R.id.recommend :
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String title = "https://awesomekorean.page.link/Sohr";
                intent.putExtra(Intent.EXTRA_TEXT, title);

                Intent chooser = Intent.createChooser(intent, "Recommend podo to your friends");
                startActivity(chooser);
                break;


            case R.id.getPointsByAd :
                adsManager.playRewardAds(this);
                break;

            case R.id.getPointsByPurchasing :
                intent = new Intent(Profile.this, TopUp.class);
                startActivityForResult(intent, 300);
                break;

            case R.id.logout :
                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.SIGN_OUT)).setMessage(getString(R.string.SIGN_OUT_MESSAGE))
                        .setPositiveButton(getString(R.string.SIGN_OUT), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                SharedPreferencesInfo.setSignIn(context, false);
                                intent = new Intent(context, SignIn.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent);
                                FirebaseAuth.getInstance().signOut();
                                finish();
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


    // editProfile 이랑 reportBug 펼치기/접기 메소드
    private void setExtendableButton(ImageView arrow, LinearLayout clickedLayout) {
        boolean isClicked = false;
        if(clickedLayout.getVisibility() == View.VISIBLE) {
            isClicked = true;
        }
        layoutEditNameOpen.setVisibility(View.GONE);
        reportBugOpen.setVisibility(View.GONE);
        arrowEditProfile.setImageResource(R.drawable.arrow_right_grey);
        arrowReportBug.setImageResource(R.drawable.arrow_right_grey);

        arrow.setImageResource(R.drawable.arrow_down_grey);
        clickedLayout.setVisibility(View.VISIBLE);

        if(isClicked) {
            arrow.setImageResource(R.drawable.arrow_right_grey);
            clickedLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(userInformation != null) {
            userInformation = SharedPreferencesInfo.getUserInfo(getApplicationContext());
            userPoint.setText(String.valueOf(userInformation.getPoints()));
        }
    }
}
