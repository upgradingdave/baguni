package net.awesomekorean.podo;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;

import net.awesomekorean.podo.message.Message;
import net.awesomekorean.podo.profile.AttendanceItem;
import net.awesomekorean.podo.profile.Profile;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    FragmentPagerAdapter adapterViewPager;

    ViewPager viewPager;

    TextView tvTitle;

    ImageView btnProfile;
    ImageView btnMessage;
    ImageView redDot;

    public static ImageView btnLesson;
    public static ImageView btnReading;
    public static ImageView btnWriting;
    public static ImageView btnCollection;
    public static ImageView btnQnA;

    Intent intent;

    public static String userEmail = "danny@gmail.com"; // 개발용
    public static String userName = "danny";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SettingStatusBar.setStatusBar(this);


        viewPager = findViewById(R.id.viewPager);
        adapterViewPager = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);

        tvTitle = findViewById(R.id.tvTitle);
        btnProfile = findViewById(R.id.btnProfile);
        btnMessage = findViewById(R.id.btnMessage);
        redDot = findViewById(R.id.redDot);
        btnLesson = findViewById(R.id.btnLesson);
        btnReading = findViewById(R.id.btnReading);
        btnWriting = findViewById(R.id.btnWriting);
        btnCollection = findViewById(R.id.btnCollection);
        btnQnA = findViewById(R.id.btnQnA);
        btnProfile.setOnClickListener(this);
        btnMessage.setOnClickListener(this);
        btnLesson.setOnClickListener(this);
        btnReading.setOnClickListener(this);
        btnWriting.setOnClickListener(this);
        btnCollection.setOnClickListener(this);
        btnQnA.setOnClickListener(this);

        // 프로필 이미지 동그랗게 만들기
        btnProfile.setBackground(new ShapeDrawable(new OvalShape()));
        if (Build.VERSION.SDK_INT >= 21) {
            btnProfile.setClipToOutline(true);
        }


        // 알림 메시지 실시간 리스너
        db.collection(getString(R.string.DB_MESSAGES))
                .whereEqualTo("userEmail", userEmail)
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

        final Calendar cal = Calendar.getInstance();

        final String today = "day"+cal.get(Calendar.DAY_OF_WEEK); // day1:일요일 ~ day7:토요일

        // DB에서 출석체크 가져오고 업데이트하기
        final DocumentReference sfDocRef = db.collection(getString(R.string.DB_ATTENDANCE)).document(userEmail);

        db.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(sfDocRef);
                AttendanceItem attendanceItem = snapshot.toObject(AttendanceItem.class);

                // 어제 출석 했는지 확인하기
                String yesterday;
                if(cal.get(Calendar.DAY_OF_WEEK) == 1) {
                    yesterday = "day7";
                } else {
                    yesterday = "day" + cal.get(Calendar.DAY_OF_WEEK-1);
                }

                // 어제 출석 안했으면 출석부 초기화 (오늘만 출석표시)
                if(!attendanceItem.getDay(yesterday)) {
                    System.out.println("어제 출석하지 않았습니다");

                    attendanceItem.resetDays(today);

                    // 어제도 출석했으면 오늘 출석 표시
                } else {
                    System.out.println("어제도 출석했습니다");
                    attendanceItem.setDay(today, true);
                }

                transaction.set(sfDocRef, attendanceItem);
                System.out.println("DB에 출석부를 업데이트 했습니다");
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                System.out.println("출석부 업데이트를 성공했습니다");
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("출석부 업데이트를 실패했습니다:"+e);
            }
        });
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

            case R.id.btnLesson:
                setMainBtns(btnLesson, R.drawable.lesson_active, R.string.LESSON);
                viewPager.setCurrentItem(0);
                break;

            case R.id.btnReading:
                setMainBtns(btnReading, R.drawable.reading_active, R.string.READING);
                viewPager.setCurrentItem(1);
                break;

            case R.id.btnWriting:
                setMainBtns(btnWriting, R.drawable.writting_active, R.string.WRITING);
                viewPager.setCurrentItem(2);
                break;

            case R.id.btnCollection:
                setMainBtns(btnCollection, R.drawable.collection_active, R.string.COLLECTION);
                viewPager.setCurrentItem(3);
                break;

            case R.id.btnQnA:
                setMainBtns(btnQnA, R.drawable.qna_active, R.string.QNA);
                viewPager.setCurrentItem(4);
                break;

        }
    }

    public void setMainBtns(ImageView btn, int active, int title) {
        btnLesson.setImageResource(R.drawable.lesson);
        btnReading.setImageResource(R.drawable.reading);
        btnWriting.setImageResource(R.drawable.writting);
        btnCollection.setImageResource(R.drawable.collection);
        btnQnA.setImageResource(R.drawable.qna);

        btn.setImageResource(active);
        tvTitle.setText(getString(title));
    }
}



