package net.awesomekorean.podo;

import java.util.TimerTask;

public class DailyMissionTimer extends TimerTask {

    Long checkInTime;

    boolean timerFinished;


    public DailyMissionTimer(Long checkInTime) {
        this.checkInTime = checkInTime;
        this.timerFinished = false;
    }

    @Override
    public void run() {
        if(!timerFinished) {
            Long leftTime = 10 - (UnixTimeStamp.getTimeNow() - checkInTime); // 10초후 미션완료

            if (leftTime >= 0 && DailyMission.handlerMission != null) {
                String min = String.format("%02d", leftTime / 60);
                String sec = String.format("%02d", leftTime % 60);
                android.os.Message message = new android.os.Message();
                message.obj = min + ":" + sec;
                DailyMission.handlerMission.sendMessage(message);

            } else if (leftTime < 0 && DailyMission.handlerMission != null) {
                DailyMission.handlerMission.sendEmptyMessage(100);
                System.out.println("타이머 완료");
                timerFinished = true;
            }
        }
    }
}
