package net.awesomekorean.podo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.lesson.LessonAdapterChild;
import net.awesomekorean.podo.lesson.LessonFinish;
import net.awesomekorean.podo.lesson.LessonSpecialFrame;
import net.awesomekorean.podo.lesson.lessonHangul.LessonHangul;
import net.awesomekorean.podo.lesson.lessonHangul.LessonHangulAssembly;
import net.awesomekorean.podo.lesson.lessonNumber.LessonNumber;
import net.awesomekorean.podo.lesson.lessonNumber.LessonNumberMenu;
import net.awesomekorean.podo.lesson.lessons.Lesson;
import net.awesomekorean.podo.lesson.lessons.LessonSpecial;
import net.awesomekorean.podo.purchase.TopUp;
import net.awesomekorean.podo.reading.MainReading;
import net.awesomekorean.podo.reading.Reading;
import net.awesomekorean.podo.reading.ReadingFrame;

import java.io.Serializable;

public class UnlockActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    LinearLayout unlockFirst;
    LinearLayout unlockSecond;
    TextView pointHave;
    TextView pointNeed;
    Button btnYes;
    Button btnNo;
    Button btnPurchasePoints;
    Button btnWatchAds;
    ImageView btnClose;

    UserInformation userInformation;
    int userPoint; // 유저 포인트

    int specialLessonPrice = 15;
    int readingPrice = 15;
    int unlockPrice;

    String extra;

    Intent intent;

    Context context;

    AdsManager adsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_unlock);

        context = getApplicationContext();

        adsManager = AdsManager.getInstance();

        if(adsManager.rewardedAd == null || !adsManager.rewardedAd.isLoaded()) {

            adsManager.loadRewardAds(context);
        }

        unlockFirst = findViewById(R.id.unlockFirst);
        unlockSecond = findViewById(R.id.unlockSecond);
        pointHave = findViewById(R.id.pointHave);
        pointNeed = findViewById(R.id.pointNeed);
        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);
        btnPurchasePoints = findViewById(R.id.btnPurchasePoints);
        btnWatchAds = findViewById(R.id.btnWatchAds);
        btnClose = findViewById(R.id.btnClose);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
        btnPurchasePoints.setOnClickListener(this);
        btnWatchAds.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        userInformation = SharedPreferencesInfo.getUserInfo(context);
        userPoint = userInformation.getPoints();

        extra = getIntent().getStringExtra(getResources().getString(R.string.EXTRA_ID));
        if(extra.equals(getResources().getString(R.string.SPECIAL_LESSON))) {
            unlockPrice = specialLessonPrice;
        } else {
            unlockPrice = readingPrice;
        }

        pointNeed.setText(String.valueOf(unlockPrice));
    }


    // 리워드 보상 포인트 반영
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            userInformation = SharedPreferencesInfo.getUserInfo(context);
            userPoint = userInformation.getPoints();

            if(userPoint >= unlockPrice) {
                unlockFirst.setVisibility(View.VISIBLE);
                unlockSecond.setVisibility(View.GONE);
                btnYes.performClick();

            } else {
                unlockFirst.setVisibility(View.GONE);
                unlockSecond.setVisibility(View.VISIBLE);
                pointHave.setText(String.valueOf(userPoint));
            }
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnYes :
                if(userPoint >= unlockPrice) {
                    unlockFirst.setVisibility(View.GONE);

                    // 포인트 차감하고 specialLessonUnlock 에 레슨아이디 추가, 해당 레슨에 unlock = true 세팅
                    int newPoint = userPoint - unlockPrice;
                    userInformation.setPoints(newPoint);
                    if(extra.equals(getResources().getString(R.string.SPECIAL_LESSON))) {

                        String lessonId = getIntent().getStringExtra(getResources().getString(R.string.LESSON_ID));

                        switch (lessonId) {

                            case "H_assembly" :

                                intent = new Intent(context, LessonHangulAssembly.class);

                                break;

                            case "N_practice" :

                                intent = new Intent(context, LessonNumberMenu.class);

                                break;

                            default :

                                LessonSpecial lesson = (LessonSpecial) getIntent().getSerializableExtra(getResources().getString(R.string.LESSON));

                                intent = new Intent(context, LessonSpecialFrame.class);

                                intent.putExtra(getResources().getString(R.string.LESSON), (Serializable) lesson);

                                break;
                        }

                        userInformation.addSpecialLessonUnlock(lessonId);

                        startActivity(intent);


                    } else {

                        Reading reading = (Reading) getIntent().getSerializableExtra(getResources().getString(R.string.READING));

                        userInformation.addReadingUnlock(reading.getReadingId());

                        intent = new Intent(context, ReadingFrame.class);

                        intent.putExtra(getResources().getString(R.string.READING), (Serializable) reading);

                        startActivity(intent);
                    }

                    SharedPreferencesInfo.setUserInfo(context, userInformation);

                    DocumentReference informationRef = db.collection(getString(R.string.DB_USERS)).document(MainActivity.userEmail).collection(getString(R.string.DB_INFORMATION)).document(getString(R.string.DB_INFORMATION));
                    informationRef.set(userInformation).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            finish();
                            System.out.println("레슨/읽기를 포인트로 구매했습니다.");
                            Toast.makeText(context, getString(R.string.UNLOCK_SUCCEEDED), Toast.LENGTH_LONG).show();

                            // analytics 로그 이벤트 얻기
                            FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                            Bundle bundle = new Bundle();
                            bundle.putString("type", extra);
                            bundle.putInt("points", specialLessonPrice);
                            firebaseAnalytics.logEvent("point_use", bundle);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, getString(R.string.UNLOCK_FAILED), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    unlockFirst.setVisibility(View.GONE);
                    unlockSecond.setVisibility(View.VISIBLE);
                    pointHave.setText(String.valueOf(userPoint));
                }
                break;

            case R.id.btnPurchasePoints :
                intent = new Intent(context, TopUp.class);
                startActivityForResult(intent, 300);
                break;


            case R.id.btnWatchAds :
                RewardedAdCallback adCallback = new RewardedAdCallback() {

                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        System.out.println("보상을 받습니다.");
                        Intent intent = new Intent(context, LessonFinish.class);
                        intent.putExtra("isReward", true);
                        startActivityForResult(intent, 200);

                        // analytics 로그 이벤트 얻기
                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                        Bundle bundle = new Bundle();
                        firebaseAnalytics.logEvent("reward_watch", bundle);
                    }

                    @Override
                    public void onRewardedAdFailedToShow(int i) {
                        Toast.makeText(context, getString(R.string.AD_LOAD_FAILED), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onRewardedAdClosed() {
                        adsManager.loadRewardAds(context);
                    }
                };
                adsManager.rewardedAd.show(UnlockActivity.this, adCallback);
                break;

            default: // btnNo, btnClose
                finish();
                break;
        }
    }
}
