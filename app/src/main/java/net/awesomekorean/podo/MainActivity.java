package net.awesomekorean.podo;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.collection.MainCollection;
import net.awesomekorean.podo.lesson.MainLesson;
import net.awesomekorean.podo.login.SignIn;
import net.awesomekorean.podo.profile.Profile;
import net.awesomekorean.podo.purchase.TopUp;
import net.awesomekorean.podo.qna.MainQnA;
import net.awesomekorean.podo.reading.MainReading;
import net.awesomekorean.podo.writing.MainWriting;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseCrashlytics crashlytics = FirebaseCrashlytics.getInstance();

    FragmentManager fm;
    FragmentTransaction tran;
    Fragment mainLesson;
    Fragment mainReading;
    Fragment mainWriting;
    Fragment mainCollection;
    Fragment mainQnA;

    TextView tvTitle;

    ImageView btnProfile;
    //ImageView btnDailyMission;
    TextView userPoint;
    LinearLayout layoutPoint;

    ConstraintLayout layoutPointDetail;
    ConstraintLayout layoutGetPoint;
    ConstraintLayout layoutPointInfo;
    ImageView btnPointInfo;
    ImageView btnClosePointDetail;
    Button btnWatchAds;
    Button btnPurchasePoints;
    Button btnClosePointInfo;

    LinearLayout layoutLesson;
    LinearLayout layoutReading;
    LinearLayout layoutWriting;
    LinearLayout layoutCollection;
    LinearLayout layoutQnA;


    public static ImageView btnLesson;
    public static ImageView btnReading;
    public static ImageView btnWriting;
    public static ImageView btnCollection;
    public static ImageView btnQnA;
    public static TextView textLesson;
    public static TextView textReading;
    public static TextView textWriting;
    public static TextView textCollection;
    public static TextView textQnA;

    Intent intent;

    public static String userEmail;
    public static String userName;
    public static Uri userImage;

    UserInformation userInformation;

    Animation animation;
/*
    DailyMissionInfo dailyMissionInfo;

    CountDownTimer timer;

    public static HandleBackground handleBackground;

    // 5분 이상 백그라운드 상태일 때 앱 종료하는 핸들러
    public static class HandleBackground extends Handler {

        private Activity activity;

        public HandleBackground(Activity activity) {
            super();
            this.activity = activity;
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            if(msg.what == 100) {
                activity.finishAffinity();
                System.runFinalization();
                System.exit(0);
            }
        }
    }
 */

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SettingStatusBar.setStatusBar(this);

        tvTitle = findViewById(R.id.tvTitle);
        btnProfile = findViewById(R.id.btnProfile);
        //btnDailyMission = findViewById(R.id.btnDailyMission);
        userPoint = findViewById(R.id.userPoint);
        layoutPoint = findViewById(R.id.layoutPoint);
        layoutPointDetail = findViewById(R.id.layoutPointDetail);
        layoutGetPoint = findViewById(R.id.layoutGetPoint);
        layoutPointInfo = findViewById(R.id.layoutPointInfo);
        btnPointInfo = findViewById(R.id.btnPointInfo);
        btnClosePointDetail = findViewById(R.id.btnClosePointDetail);
        btnWatchAds = findViewById(R.id.btnWatchAds);
        btnPurchasePoints = findViewById(R.id.btnPurchasePoints);
        btnClosePointInfo = findViewById(R.id.btnClosePointInfo);
        layoutLesson = findViewById(R.id.layoutLesson);
        layoutReading = findViewById(R.id.layoutReading);
        layoutWriting = findViewById(R.id.layoutWriting);
        layoutCollection = findViewById(R.id.layoutCollection);
        layoutQnA = findViewById(R.id.layoutQnA);
        btnLesson = findViewById(R.id.btnLesson);
        btnReading = findViewById(R.id.btnReading);
        btnWriting = findViewById(R.id.btnWriting);
        btnCollection = findViewById(R.id.btnCollection);
        btnQnA = findViewById(R.id.btnQnA);
        textLesson = findViewById(R.id.textLesson);
        textReading = findViewById(R.id.textReading);
        textWriting = findViewById(R.id.textWriting);
        textCollection = findViewById(R.id.textCollection);
        textQnA = findViewById(R.id.textQnA);
        btnProfile.setOnClickListener(this);
        //btnDailyMission.setOnClickListener(this);
        layoutPoint.setOnClickListener(this);
        btnPointInfo.setOnClickListener(this);
        btnClosePointDetail.setOnClickListener(this);
        btnWatchAds.setOnClickListener(this);
        btnPurchasePoints.setOnClickListener(this);
        btnClosePointInfo.setOnClickListener(this);
        layoutLesson.setOnClickListener(this);
        layoutReading.setOnClickListener(this);
        layoutWriting.setOnClickListener(this);
        layoutCollection.setOnClickListener(this);
        layoutQnA.setOnClickListener(this);


        mainLesson = new MainLesson();
        mainReading = new MainReading();
        mainWriting = new MainWriting();
        mainCollection = new MainCollection();
        mainQnA = new MainQnA();
        setFrag(mainLesson);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_200);

        // 유저정보 가져오기 (Email, Name, Image)
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            userEmail = user.getEmail();
            if (user.getDisplayName() != null) {
                userName = user.getDisplayName();
            } else {
                userName = userEmail.substring(0, userEmail.lastIndexOf("@"));
            }
            userImage = user.getPhotoUrl();
        }

        SharedPreferencesInfo.setUserEmail(getApplicationContext(), userEmail);
        SharedPreferencesInfo.setUserName(getApplicationContext(), userName);

        crashlytics.log("setting CustomKey");
        crashlytics.setCustomKey("userEmail", userEmail);
        crashlytics.setCustomKey("userName", userName);

/*
        // 알림 메시지 실시간 리스너
        db.collection(getString(R.string.DB_USERS)).document(userEmail).collection(getString(R.string.DB_MESSAGES))
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            System.out.println("Listening 실패: " + e);
                            return;
                        }

                        for (QueryDocumentSnapshot doc : value) {
                            if (doc.get("isNew").equals(true)) {
                                System.out.println("새로운 메시지가 있습니다");
                                redDot.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });


 */
        getAttendanceInfo();
/*
        // 일일미션 정보 가져오기 (기기에 dailyMissionInfo 가 없는 경우 문제발생 방지용.)
        dailyMissionInfo = SharedPreferencesInfo.getDailyMissionInfo(getApplicationContext());
        if(dailyMissionInfo == null) {
            initDailyMission();
        }

        // 일일미션 타이머 작동하기
        long leftTime = dailyMissionInfo.getMissionTime() - dailyMissionInfo.getRunningTime() + 1;
        System.out.println("레프트타임 : " + leftTime);
        if(leftTime >= 0) {
            timer = new DailyMissionTimer(getApplicationContext(), 3600 * 1000, 1000, dailyMissionInfo.getRunningTime());
            timer.start();
        }
 */

        checkPlayService();

        Intent intent = new Intent(this, FirebaseCloudMessage.class);
        startService(intent);
    }


    // 앱에서 유저 데이터 가져오기
    public void getAttendanceInfo() {
        System.out.println("앱에서 유저 데이터를 가져옵니다.");
        userInformation = SharedPreferencesInfo.getUserInfo(getApplicationContext());

        final Calendar cal = Calendar.getInstance();
        final int today = cal.get(Calendar.DAY_OF_WEEK) - 1; // 0:일요일 ~ 6:토요일

        // 오늘 출석체크 안했으면 출석부 업데이트 (버그: 요일이 같으면 일주일만에 접속해도 초기화 안됨)
        if (!userInformation.getAttendance().get(today)) {

            //initDailyMission();

            System.out.println("출석체크를 시작합니다.");
            int yesterday;
            if (today == 0) {
                yesterday = 6;
            } else {
                yesterday = today - 1;
            }

            // 어제 출석 했는지 확인하고 안했으면 출석부 초기화 (오늘만 출석표시)
            if (!userInformation.getAttendance().get(yesterday)) {
                System.out.println("어제 출석하지 않았습니다");
                userInformation.resetDays(today);

                // 어제도 출석했으면 오늘 출석 표시
            } else {
                System.out.println("어제도 출석했습니다");
                userInformation.setDay(today);
            }

            SharedPreferencesInfo.setUserInfo(getApplicationContext(), userInformation);
            System.out.println("앱에 출석부를 업데이트 했습니다");

            // DB 에 출석부 업데이트하기
            DocumentReference reference = db.collection(getString(R.string.DB_USERS)).document(userEmail);
            reference.update("attendance", userInformation.getAttendance()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    System.out.println("DB에 출석부를 업데이트 했습니다");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    System.out.println("출석부 업데이트를 실패했습니다:" + e);
                }
            });

            reference.update("lastVisit", UnixTimeStamp.getTimeNow()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    System.out.println("DB에 마지막 방문시간을 업데이트 했습니다");
                }
            });


        } else {
            System.out.println("오늘의 출석체크가 이미 끝났습니다.");
        }
    }

/*
    // 일일미션 초기화하기
    public void initDailyMission() {
        dailyMissionInfo = new DailyMissionInfo();
        SharedPreferencesInfo.setDailyMissionInfo(getApplicationContext(), dailyMissionInfo);
    }

 */


    public void checkPlayService() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int result = googleAPI.isGooglePlayServicesAvailable(this);

        if(result != ConnectionResult.SUCCESS) {
            googleAPI.makeGooglePlayServicesAvailable(this);
        }
    }
/*
    // 완료한 일일 미션이 있는지 확인
    public void checkMissionComplete() {
        dailyMissionInfo = SharedPreferencesInfo.getDailyMissionInfo(getApplicationContext());
        System.out.println("미션완료_레슨 : " + dailyMissionInfo.getLessonComplete());
        System.out.println("미션완료_단어리뷰 : " + dailyMissionInfo.getWordReviewComplete());
        int rewardPoints = dailyMissionInfo.isThereMissionCompleted();
        SharedPreferencesInfo.setDailyMissionInfo(getApplicationContext(), dailyMissionInfo);
        System.out.println("미션1 : " + dailyMissionInfo.getIsCompleted()[0]);
        System.out.println("미션2 : " + dailyMissionInfo.getIsCompleted()[1]);
        System.out.println("미션3 : " + dailyMissionInfo.getIsCompleted()[2]);
        System.out.println("미션완료_보너스 : " + rewardPoints);
        if(rewardPoints > 0) {
            Intent intent = new Intent(this, DailyMission.class);
            intent.putExtra("rewardPoints", rewardPoints);
            startActivity(intent);
        }
    }
 */

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnProfile:
                intent = new Intent(this, Profile.class);
                startActivity(intent);
                break;
/*
            case R.id.btnDailyMission :
                intent = new Intent(this, DailyMission.class);
                intent.putExtra("rewardPoints", 0);
                startActivity(intent);
                break;

            case R.id.btnMessage:
                redDot.setVisibility(View.GONE);
                intent = new Intent(this, Message.class);
                intent.putExtra(getResources().getString(R.string.EMAIL), userEmail);
                startActivity(intent);
                break;

 */
            case R.id.layoutPoint :
                layoutPointDetail.setVisibility(View.VISIBLE);
                layoutGetPoint.setVisibility(View.VISIBLE);
                layoutPointInfo.setVisibility(View.GONE);
                break;

            case R.id.btnWatchAds :
                AdsManager.getInstance().playRewardAds(this);
                break;

            case R.id.btnPurchasePoints :
                intent = new Intent(getApplicationContext(), TopUp.class);
                startActivityForResult(intent, 300);
                break;

            case R.id.btnPointInfo :
                layoutGetPoint.setVisibility(View.GONE);
                layoutPointInfo.setVisibility(View.VISIBLE);
                break;

            case R.id.btnClosePointDetail :
                layoutPointDetail.setVisibility(View.GONE);
                break;

            case R.id.btnClosePointInfo :
                layoutGetPoint.setVisibility(View.VISIBLE);
                layoutPointInfo.setVisibility(View.GONE);
                break;


            case R.id.layoutLesson:
                setFrag(mainLesson);
                setMainBtns(btnLesson, textLesson, R.drawable.lesson_active, R.string.LESSON);
                break;

            case R.id.layoutReading:
                setFrag(mainReading);
                setMainBtns(btnReading, textReading, R.drawable.reading_active, R.string.READING);
                break;

            case R.id.layoutWriting:
                setFrag(mainWriting);
                setMainBtns(btnWriting, textWriting, R.drawable.writing_active, R.string.WRITING);
                break;

            case R.id.layoutCollection:
                setFrag(mainCollection);
                setMainBtns(btnCollection, textCollection, R.drawable.collection_active, R.string.COLLECTION);
                break;

            case R.id.layoutQnA:
                setFrag(mainQnA);
                setMainBtns(btnQnA, textQnA, R.drawable.qna_active, R.string.QNA);
                break;
        }
    }


    public void setFrag(Fragment frag) {
        fm = getSupportFragmentManager();
        tran = fm.beginTransaction();
        tran.replace(R.id.frameLayout, frag);
        tran.commit();
    }


    public void setMainBtns(ImageView btn, TextView text, int active, int title) {
        btnLesson.setImageResource(R.drawable.lesson);
        btnReading.setImageResource(R.drawable.reading);
        btnWriting.setImageResource(R.drawable.writing);
        btnCollection.setImageResource(R.drawable.collection);
        btnQnA.setImageResource(R.drawable.qna);

        textLesson.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.GREY_DARK));
        textReading.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.GREY_DARK));
        textWriting.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.GREY_DARK));
        textCollection.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.GREY_DARK));
        textQnA.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.GREY_DARK));

        btn.setImageResource(active);
        text.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.PURPLE));
        btn.startAnimation(animation);
        tvTitle.setText(getString(title));
    }


    public void openConfirmQuit() {
        Intent intent = new Intent(this, ConfirmQuit.class);
        intent.putExtra(getResources().getString(R.string.IS_MAIN), true);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(final int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            userInformation = SharedPreferencesInfo.getUserInfo(getApplicationContext());
            userPoint.setText(String.valueOf(userInformation.getPoints()));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        checkPlayService();
        System.out.println("메인 보임!");
        if(userInformation != null) {
            userInformation = SharedPreferencesInfo.getUserInfo(getApplicationContext());
            userPoint.setText(String.valueOf(userInformation.getPoints()));
        }
        //checkMissionComplete();

        if(!IsOnline.isOnline(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "Internet connection required.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, SignIn.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    public void onBackPressed() {
        openConfirmQuit();
    }


    @Override
    protected void onPause() {
        super.onPause();
        crashlytics.log("메인액티비티 Pause!!");
        System.out.println("메인액티비티 Pause!!");
    }


    @Override
    protected void onStop() {
        super.onStop();
        crashlytics.log("메인액티비티 Stop!!");
        System.out.println("메인액티비티 Stop!!");
/*
        // 5분 이상 백그라운드 상태이면 앱 종료
        if(IsAppInBackground.isAppInBackground(getApplicationContext())) {
            handleBackground = new HandleBackground(this);
            CountDownTimer backgroundTimer = new BackgroundTimer(this, 300000, 1000);
            backgroundTimer.start();
        }
 */
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        crashlytics.log("메인액티비티 Destroy!!");
        System.out.println("메인액티비티 Destroy!!");
    }
}



