package net.awesomekorean.podo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import net.awesomekorean.podo.lesson.LessonFinish;
import net.awesomekorean.podo.lesson.LessonFrame;
import net.awesomekorean.podo.lesson.RandomRewards;
import net.awesomekorean.podo.lesson.lessons.Lesson;

public class GetRandomPoint extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout selectResult;

    ImageView box1;
    ImageView box2;
    ImageView box3;

    ImageView finishPodo;
    TextView tvPoint;
    ImageView imageCoin;

    Button btnGetPoint;

    int reward;
    PlaySoundPool playSoundPool;
    Context context;
    AdsManager adsManager;
    Animation animation;

    boolean isFromProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_random_point);

        context = getApplicationContext();

        isFromProfile = getIntent().getBooleanExtra("isFromProfile", true);

        adsManager = AdsManager.getInstance();

        if(adsManager.rewardedAd == null || !adsManager.rewardedAd.isLoaded()) {
            adsManager.loadRewardAds(context);
        }

        selectResult = findViewById(R.id.selectResult);
        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        finishPodo = findViewById(R.id.finishPodo);
        tvPoint = findViewById(R.id.tvPoint);
        imageCoin = findViewById(R.id.imageCoin);
        btnGetPoint = findViewById(R.id.btnGetPoint);
        box1.setOnClickListener(this);
        box2.setOnClickListener(this);
        box3.setOnClickListener(this);
        btnGetPoint.setOnClickListener(this);

        playSoundPool = new PlaySoundPool(context);
        playSoundPool.playSoundYay();

        Animation aniScale = AnimationUtils.loadAnimation(context, R.anim.scale_1000);
        finishPodo.startAnimation(aniScale);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_200);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnGetPoint :

                // 포인트 합산하기
                UserInformation userInformation = SharedPreferencesInfo.getUserInfo(context);
                userInformation.addRewardPoints(context, reward);

                // analytics 로그 이벤트 얻기
                FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                Bundle bundle = new Bundle();
                firebaseAnalytics.logEvent("reward_watch", bundle);

                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                setResult(RESULT_OK, intent);
                adsManager.loadRewardAds(context);
                finish();


                /*

                // 애드몹 광고 보여주기
                if(adsManager.rewardedAd.isLoaded()) {
                    RewardedAdCallback adCallback = new RewardedAdCallback() {

                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            System.out.println("보상을 받습니다.");

                            // 포인트 합산하기
                            UserInformation userInformation = SharedPreferencesInfo.getUserInfo(context);
                            userInformation.addRewardPoints(context, reward);

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
                            Intent intent = new Intent();
                            setResult(RESULT_OK, intent);
                            adsManager.loadRewardAds(context);
                            finish();
                        }
                    };
                    adsManager.rewardedAd.show(this, adCallback);
                }

                 */
                break;

            default:
                v.startAnimation(animation);
                // 포인트 랜덤으로 가져오기
                reward = RandomRewards.randomRewards();
                tvPoint.setText("+ " + String.valueOf(reward));
                selectResult.setVisibility(View.VISIBLE);
                playSoundPool.playSoundLesson(2);

                Animation aniSelectResult = AnimationUtils.loadAnimation(context, R.anim.move_up);
                selectResult.startAnimation(aniSelectResult);
                aniSelectResult.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Animation aniPoint = AnimationUtils.loadAnimation(context, R.anim.move_up_small);
                        tvPoint.startAnimation(aniPoint);
                        imageCoin.startAnimation(aniPoint);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
        }
    }
}
