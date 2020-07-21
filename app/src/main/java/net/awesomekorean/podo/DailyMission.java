package net.awesomekorean.podo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextPaint;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DailyMission extends AppCompatActivity implements View.OnClickListener {

    LinearLayout dailyMissionPageInfo;
    LinearLayout dailyMissionComplete;
    TextView tvRewardPoints;
    ImageView iconComplete1;
    ImageView iconComplete2;
    ImageView iconComplete3;
    ImageView btnInformationDM;
    ImageView btnCloseDM;
    Button btnGetBonusPoint;
    Button btnCloseInformationDM;
    Button btnCloseMissionComplete;
    TextView tvLessonCount;
    TextView tvReviewCount;
    TextView tvTimer;
    PlaySoundPool playSoundPool;


    DailyMissionInfo dailyMissionInfo;

    public static HandlerMission handlerMission;


    public static class HandlerMission extends Handler {

        private Activity activity;
        private TextView tvTimer;

        public HandlerMission(Activity activity, TextView tvTimer) {
            super();
            this.activity = activity;
            this.tvTimer = tvTimer;
        }

        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 100) {
                tvTimer.setVisibility(View.GONE);
                activity.finish();
            } else {
                tvTimer.setText(msg.obj.toString());
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_mission);

        playSoundPool = new PlaySoundPool(getApplicationContext());

        dailyMissionPageInfo = findViewById(R.id.dailyMissionPageInfo);
        dailyMissionComplete = findViewById(R.id.dailyMissionComplete);
        tvRewardPoints = findViewById(R.id.tvRewardPoints);
        iconComplete1 = findViewById(R.id.iconComplete1);
        iconComplete2 = findViewById(R.id.iconComplete2);
        iconComplete3 = findViewById(R.id.iconComplete3);
        btnInformationDM = findViewById(R.id.btnInformationDM);
        btnCloseDM = findViewById(R.id.btnCloseDM);
        btnGetBonusPoint = findViewById(R.id.btnGetBonus);
        btnCloseInformationDM = findViewById(R.id.btnCloseInformationDM);
        btnCloseMissionComplete = findViewById(R.id.btnCloseMissionComplete);
        tvTimer = findViewById(R.id.tvTimer);
        tvLessonCount = findViewById(R.id.tvLessonCount);
        tvReviewCount = findViewById(R.id.tvReviewCount);

        btnInformationDM.setOnClickListener(this);
        btnCloseDM.setOnClickListener(this);
        btnGetBonusPoint.setOnClickListener(this);
        btnCloseInformationDM.setOnClickListener(this);
        btnCloseMissionComplete.setOnClickListener(this);

        Intent intent = getIntent();
        int rewardPoints = intent.getExtras().getInt("rewardPoints");

        if (rewardPoints > 0) {
            getRewards(rewardPoints);
        }

        dailyMissionInfo = SharedPreferencesInfo.getDailyMissionInfo(getApplicationContext());
        boolean[] missionComplete = dailyMissionInfo.getIsCompleted();

        setCheckComplete(iconComplete1, missionComplete[0]);
        setCheckComplete(iconComplete2, missionComplete[1]);
        setCheckComplete(iconComplete3, missionComplete[2]);


        if (missionComplete[0]) {
            tvLessonCount.setVisibility(View.GONE);

        } else {
            tvLessonCount.setText(String.valueOf(dailyMissionInfo.getLessonComplete().size()));
        }

        if(missionComplete[1]) {
            tvReviewCount.setVisibility(View.GONE);

        } else {
            tvReviewCount.setText(String.valueOf(dailyMissionInfo.getWordReviewComplete()));
        }

        if (missionComplete[2]) {
            tvTimer.setVisibility(View.GONE);

        } else {
            handlerMission = new HandlerMission(this, tvTimer);
            tvTimer.setVisibility(View.VISIBLE);
        }

        // 보너스 포인트를 받을 조건이 되는지 확인
        if(dailyMissionInfo.canUserGetBonusPoint()) {
            btnGetBonusPoint.setEnabled(true);
            btnGetBonusPoint.setBackgroundResource(R.drawable.bg_purple_30);
        } else {
            btnGetBonusPoint.setEnabled(false);
            btnGetBonusPoint.setBackgroundResource(R.drawable.bg_grey_30);
        }
    }


    public void getRewards(int rewards) {
        tvRewardPoints.setText(Integer.toString(rewards));
        dailyMissionComplete.setVisibility(View.VISIBLE);
        playSoundPool.playSoundLesson(2);
        Animation aniScale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_1000);
        tvRewardPoints.startAnimation(aniScale);

        // 포인트 합산하기
        UserInformation userInformation = SharedPreferencesInfo.getUserInfo(getApplicationContext());
        userInformation.addRewardPoints(getApplicationContext(), rewards);
    }


    public void setCheckComplete(ImageView imageView, boolean isComplete) {
        if (isComplete) {
            imageView.setImageResource(R.drawable.complete_gradation);
        } else {
            imageView.setImageResource(R.drawable.complete_gradation_no);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnInformationDM:
                dailyMissionPageInfo.setVisibility(View.VISIBLE);
                break;

            case R.id.btnCloseDM:
                finish();
                break;

            case R.id.btnGetBonus:
                // 전체미션 완료 보너스 5점 적립하기
                getRewards(5);
                btnGetBonusPoint.setEnabled(false);
                btnGetBonusPoint.setBackgroundResource(R.drawable.bg_grey_30);
                dailyMissionInfo.setGotBonusPoint();
                SharedPreferencesInfo.setDailyMissionInfo(getApplicationContext(), dailyMissionInfo);

                // analytics 로그 이벤트 얻기
                FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
                Bundle bundle = new Bundle();
                firebaseAnalytics.logEvent("dailyMission_Complete", bundle);
                break;

            case R.id.btnCloseInformationDM:
                dailyMissionPageInfo.setVisibility(View.GONE);
                break;

            case R.id.btnCloseMissionComplete:
                dailyMissionComplete.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerMission = null;
    }
}

