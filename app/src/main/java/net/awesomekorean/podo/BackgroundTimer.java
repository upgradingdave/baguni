package net.awesomekorean.podo;

import android.content.Context;
import android.os.CountDownTimer;

public class BackgroundTimer extends CountDownTimer {

    int sleepingTime;
    int killingTime = 300; // 백그라운드 제한시간 5분

    Context context;

    public BackgroundTimer(Context context, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.context = context;
        this.sleepingTime = 0;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if(IsAppInBackground.isAppInBackground(context)) {
            sleepingTime++;

            if (sleepingTime >= killingTime) {
                //MainActivity.handleBackground.sendEmptyMessage(100);
                System.out.println("백그라운드 상태 5분 초과됨");
            }
        }
    }

    @Override
    public void onFinish() {
        System.out.println("쓰레드 종료");
    }
}
