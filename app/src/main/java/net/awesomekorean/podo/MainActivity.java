package net.awesomekorean.podo;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import net.awesomekorean.podo.lesson.MainLesson;
import net.awesomekorean.podo.message.Message;
import net.awesomekorean.podo.profile.Profile;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    FragmentPagerAdapter adapterViewPager;

    ViewPager viewPager;

    TextView tvTitle;

    ImageView btnProfile;
    ImageView btnMessage;
    ImageView redDot;

    LinearLayout layoutLesson;
    LinearLayout layoutReading;
    LinearLayout layoutWriting;
    LinearLayout layoutCollection;

    public static ImageView btnLesson;
    public static ImageView btnReading;
    public static ImageView btnWriting;
    public static ImageView btnCollection;
    public static TextView textLesson;
    public static TextView textReading;
    public static TextView textWriting;
    public static TextView textCollection;


    Intent intent;

    public static String userEmail;
    public static String userName;
    public static Uri userImage;

    UserInformation userInformation;

    LinearLayout confirmQuit;
    Button btnYes;
    Button btnNo;

    Animation animation;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainLesson.lessonUnit = null;

        SettingStatusBar.setStatusBar(this);

        viewPager = findViewById(R.id.viewPager);
        adapterViewPager = new ViewPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapterViewPager);

        tvTitle = findViewById(R.id.tvTitle);
        btnProfile = findViewById(R.id.btnProfile);
        btnMessage = findViewById(R.id.btnMessage);
        redDot = findViewById(R.id.redDot);
        layoutLesson = findViewById(R.id.layoutLesson);
        layoutReading = findViewById(R.id.layoutReading);
        layoutWriting = findViewById(R.id.layoutWriting);
        layoutCollection = findViewById(R.id.layoutCollection);
        btnLesson = findViewById(R.id.btnLesson);
        btnReading = findViewById(R.id.btnReading);
        btnWriting = findViewById(R.id.btnWriting);
        btnCollection = findViewById(R.id.btnCollection);
        textLesson = findViewById(R.id.textLesson);
        textReading = findViewById(R.id.textReading);
        textWriting = findViewById(R.id.textWriting);
        textCollection = findViewById(R.id.textCollection);
        confirmQuit = findViewById(R.id.confirmQuit);
        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);
        btnProfile.setOnClickListener(this);
        btnMessage.setOnClickListener(this);
        layoutLesson.setOnClickListener(this);
        layoutReading.setOnClickListener(this);
        layoutWriting.setOnClickListener(this);
        layoutCollection.setOnClickListener(this);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.click_scale);


        // 유저정보 가져오기 (Email, Name, Image)
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            userEmail = user.getEmail();
            if (user.getDisplayName() != null) {
                userName = user.getDisplayName();
            } else {
                userName = userEmail.substring(0, userEmail.lastIndexOf("@"));
            }
            userImage = user.getPhotoUrl();
        }


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


        // 앱에서 유저 데이터 가져오기
        System.out.println("앱에서 유저 데이터를 가져옵니다.");
        userInformation = SharedPreferencesInfo.getUserInfo(getApplicationContext());

        final Calendar cal = Calendar.getInstance();
        final int today = cal.get(Calendar.DAY_OF_WEEK) - 1; // 0:일요일 ~ 6:토요일


        // 오늘 출석체크 안했으면 출석부 업데이트 (버그: 요일이 같으면 일주일만에 접속해도 초기화 안됨)
        if (!userInformation.getAttendance().get(today)) {
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
            DocumentReference reference = db.collection(getString(R.string.DB_USERS)).document(userEmail).collection(getString(R.string.DB_INFORMATION)).document(getString(R.string.DB_INFORMATION));
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
        } else {
            System.out.println("오늘의 출석체크가 이미 끝났습니다.");
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnProfile:
                intent = new Intent(this, Profile.class);
                startActivity(intent);
                break;

            case R.id.btnMessage:
                redDot.setVisibility(View.GONE);
                intent = new Intent(this, Message.class);
                startActivity(intent);
                break;

            case R.id.layoutLesson:
                setMainBtns(btnLesson, textLesson, R.drawable.lesson_active, R.string.LESSON);
                viewPager.setCurrentItem(0);
                break;

            case R.id.layoutReading:
                setMainBtns(btnReading, textReading, R.drawable.reading_active, R.string.READING);
                viewPager.setCurrentItem(1);
                break;

            case R.id.layoutWriting:
                setMainBtns(btnWriting, textWriting, R.drawable.writing_active, R.string.WRITING);
                viewPager.setCurrentItem(2);
                break;

            case R.id.layoutCollection:
                setMainBtns(btnCollection, textCollection, R.drawable.collection_active, R.string.COLLECTION);
                viewPager.setCurrentItem(3);
                break;

            case R.id.btnYes :
                finishAffinity();
                System.runFinalization();
                System.exit(0);
                break;

            case R.id.btnNo :
                confirmQuit.setVisibility(View.GONE);
                break;

        }
    }

    public void setMainBtns(ImageView btn, TextView text, int active, int title) {
        btnLesson.setImageResource(R.drawable.lesson);
        btnReading.setImageResource(R.drawable.reading);
        btnWriting.setImageResource(R.drawable.writing);
        btnCollection.setImageResource(R.drawable.collection);
        textLesson.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.GREY_DARK));
        textReading.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.GREY_DARK));
        textWriting.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.GREY_DARK));
        textCollection.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.GREY_DARK));

        btn.setImageResource(active);
        text.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.PURPLE));
        btn.startAnimation(animation);
        tvTitle.setText(getString(title));
    }

    @Override
    public void onBackPressed() {
        confirmQuit.setVisibility(View.VISIBLE);
    }
}



