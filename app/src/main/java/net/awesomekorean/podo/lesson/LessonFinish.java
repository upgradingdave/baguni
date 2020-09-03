package net.awesomekorean.podo.lesson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import net.awesomekorean.podo.AdsManager;
import net.awesomekorean.podo.DailyMissionInfo;
import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.PlaySoundPool;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.lesson.lessons.Lesson;
import net.awesomekorean.podo.profile.Profile;

public class LessonFinish extends AppCompatActivity {

    ProgressBar progressBar;
    TextView tvMessage;
    Button btnComplete;
    PlaySoundPool playSoundPool;
    Context context;
    AdsManager adsManager;

    Lesson lesson = LessonFrame.lesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_finish);

        context = getApplicationContext();
        adsManager = AdsManager.getInstance();

        if(adsManager.interstitialAd == null || !adsManager.interstitialAd.isLoaded()) {
            adsManager.loadFullAds(context);
        }

        progressBar = findViewById(R.id.progressBar);
        tvMessage = findViewById(R.id.tvMessage);
        btnComplete = findViewById(R.id.btnComplete);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAds();
            }
        });

        playSoundPool = new PlaySoundPool(context);
        playSoundPool.playSoundYay();

        ObjectAnimator animator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        animator.setDuration(1500);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();

        Animation aniScale = AnimationUtils.loadAnimation(context, R.anim.scale_1000);
        tvMessage.startAnimation(aniScale);

        setLessonFinish();
    }


    private void setLessonFinish() {

        String lessonId = lesson.getLessonId();
        FirebaseCrashlytics.getInstance().log("lessonId : " + lessonId);

        System.out.println("레슨아이디 : " + lessonId);

        // analytics 로그 이벤트 얻기
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
        Bundle bundle = new Bundle();
        bundle.putString("lessonId", lessonId);
        firebaseAnalytics.logEvent("lesson_finish", bundle);

        // 레슨완료 정보 업데이트 하기
        UserInformation userInformation = SharedPreferencesInfo.getUserInfo(context);
        userInformation.updateCompleteList(context, lessonId, false);
    }


    // 애드몹 광고 보여주고 종료
    private void playAds() {
        if(adsManager.interstitialAd.isLoaded()) {
            adsManager.playFullAds(this);
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        playAds();
    }
}
