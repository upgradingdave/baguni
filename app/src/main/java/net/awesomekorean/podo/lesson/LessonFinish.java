package net.awesomekorean.podo.lesson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.AdsLoad;
import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.PlaySoundPool;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;

import java.util.Arrays;
import java.util.List;

public class LessonFinish extends AppCompatActivity implements View.OnClickListener {

    LinearLayout selectBox;
    ConstraintLayout selectResult;

    ImageView box1;
    ImageView box2;
    ImageView box3;

    TextView tvPoints;

    Button btnGetPoint;

    int reward;

    boolean isFromProfile;

    PlaySoundPool playSoundPool;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_finish);

        context = getApplicationContext();

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

        playSoundPool = new PlaySoundPool(context);

        isFromProfile = getIntent().getBooleanExtra("isReward", false);

    }





    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnGetPoint :

                // 포인트 합산하기
                UserInformation userInformation = SharedPreferencesInfo.getUserInfo(context);
                int oldPoints = userInformation.getPoints();
                int newPoints = oldPoints + reward;
                userInformation.setPoints(newPoints);

                if(isFromProfile) {
                    Intent intent = new Intent();
                    intent.putExtra("newPoint", newPoints);
                    setResult(RESULT_OK, intent);
                    finish();

                } else {
                    FirebaseCrashlytics.getInstance().log("lessonId : " + LessonAdapterChild.lessonItem.getLessonId());
                    String lessonId = LessonAdapterChild.lessonItem.getLessonId();


                    // analytics 로그 이벤트 얻기
                    FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
                    Bundle bundle = new Bundle();
                    bundle.putString("lessonId", lessonId);
                    firebaseAnalytics.logEvent("lesson_finish", bundle);


                    // 레슨완료 정보 업데이트 하기
                    userInformation.updateCompleteList(context, lessonId, 100, false);

                    // 애드몹 광고 보여주기
                    if(!isFromProfile) {

                        AdsLoad.getInstance().playAds(this);
                    }
                }
                break;


            default:
                // 포인트 랜덤으로 가져오기
                reward = RandomRewards.randomRewards();
                tvPoints.setText(String.valueOf(reward));
                selectBox.setVisibility(View.GONE);
                selectResult.setVisibility(View.VISIBLE);
                playSoundPool.playSoundLesson(2);
                break;
        }
    }
}
