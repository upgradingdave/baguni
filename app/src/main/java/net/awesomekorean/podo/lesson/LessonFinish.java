package net.awesomekorean.podo.lesson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.profile.Profile;

public class LessonFinish extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    InterstitialAd interstitialAd;

    LinearLayout selectBox;
    ConstraintLayout selectResult;

    ImageView box1;
    ImageView box2;
    ImageView box3;

    TextView tvPoints;

    Button btnGetPoint;

    int reward;

    boolean isFromProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_finish);

        selectBox = findViewById(R.id.selectBox);
        selectResult = findViewById(R.id.selectResult);
        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        tvPoints = findViewById(R.id.tvPoints);
        btnGetPoint = findViewById(R.id.btnGetPoint);
        box1.setOnClickListener(this);
        box2.setOnClickListener(this);
        box3.setOnClickListener(this);
        btnGetPoint.setOnClickListener(this);

        isFromProfile = getIntent().getBooleanExtra("isReward", false);

        if(!isFromProfile) {
            // 애드몹 전면광고 로드하기
            MobileAds.initialize(getApplicationContext(), getString(R.string.ADMOB_APP_ID));
            interstitialAd = new InterstitialAd(getApplicationContext());
            interstitialAd.setAdUnitId(getString(R.string.ADMOB_TEST_ID_FULL_SCREEN));
            interstitialAd.loadAd(new AdRequest.Builder().build());
            interstitialAd.setAdListener(new AdListener() {

                @Override
                public void onAdLoaded() {
                    System.out.println("광고가 로드되었습니다");
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    System.out.println("광고 로드에 실패했습니다.");
                }

                @Override
                public void onAdClosed() {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }





    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnGetPoint :

                // 포인트 합산하기
                UserInformation userInformation = SharedPreferencesInfo.getUserInfo(getApplicationContext());
                int oldPoints = userInformation.getPoints();
                int newPoints = oldPoints + reward;
                userInformation.setPoints(newPoints);

                if(isFromProfile) {
                    Intent intent = new Intent();
                    intent.putExtra("newPoint", newPoints);
                    setResult(RESULT_OK, intent);
                    finish();

                } else {
                    // 레슨완료 정보 업데이트 하기
                    String lessonId = MainLesson.lessonUnit.getLessonId();
                    if (!userInformation.getLessonComplete().contains(lessonId)) {
                        userInformation.addLessonComplete(lessonId);
                    } else {
                        System.out.println("이미 완료된 Lesson 입니다.");
                    }

                    // DB 에 유저 정보 업데이드 하기
                    SharedPreferencesInfo.setUserInfo(getApplicationContext(), userInformation);
                    db.collection(getString(R.string.DB_USERS)).document(MainActivity.userEmail).collection(getString(R.string.DB_INFORMATION)).document(getString(R.string.DB_INFORMATION))
                            .set(userInformation)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    System.out.println("DB에 유저 정보를 업데이트 했습니다.");

                                    // 애드몹 광고 보여주기
                                    if(interstitialAd.isLoaded()) {
                                        interstitialAd.show();
                                    } else {
                                        System.out.println("The interstitial ads wasn't loaded yet.");
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                }

                break;



            default:
                // 포인트 랜덤으로 가져오기
                reward = RandomRewards.randomRewards();
                tvPoints.setText(String.valueOf(reward));
                selectBox.setVisibility(View.GONE);
                selectResult.setVisibility(View.VISIBLE);
                break;
        }
    }
}
