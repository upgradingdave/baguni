package net.awesomekorean.podo;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;

import java.util.List;
import java.util.TimerTask;

public class DailyMissionTimer extends CountDownTimer {

    long runningTime;

    Context context;

    public DailyMissionTimer(Context context, long millisInFuture, long countDownInterval, long runningTime) {
        super(millisInFuture, countDownInterval);
        this.context = context;
        this.runningTime = runningTime;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if(!IsAppInBackground.isAppInBackground(context)) {
            runningTime++;
            DailyMissionInfo dailyMissionInfo = SharedPreferencesInfo.getDailyMissionInfo(context);
            dailyMissionInfo.setRunningTime(runningTime);
            SharedPreferencesInfo.setDailyMissionInfo(context, dailyMissionInfo);

            long leftTime = dailyMissionInfo.getMissionTime() - runningTime;

            if (leftTime >= 0 && DailyMission.handlerMission != null) {
                String min = String.format("%02d", leftTime / 60);
                String sec = String.format("%02d", leftTime % 60);
                android.os.Message message = new android.os.Message();
                message.obj = min + ":" + sec;
                DailyMission.handlerMission.sendMessage(message);

            } else if (leftTime < 0 && DailyMission.handlerMission != null) {
                DailyMission.handlerMission.sendEmptyMessage(100);
                System.out.println("타이머 완료");
            }
        }
    }

    @Override
    public void onFinish() {
        System.out.println("쓰레드 종료");
    }
}
