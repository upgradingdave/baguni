package net.awesomekorean.podo;

import java.util.ArrayList;
import java.util.List;

public class DailyMissionInfo {

    private List<String> lessonComplete;

    private boolean wordReviewComplete;

    private long runningTime;

    private boolean[] isCompleted;

    private boolean gotBonusPoint;

    private int lessonCompleteRequired = 5;  // 미션 1 : 일반레슨 5개 완료

    private int missionTime = 1200; // 미션 3 : 20분 이상 앱 사용


    public DailyMissionInfo() {

        lessonComplete = new ArrayList<>();

        wordReviewComplete = false;

        this.runningTime = 0;

        this.isCompleted = new boolean[3];

        this.gotBonusPoint = false;
    }

    public void addLessonComplete(String lessonId) {
        if(!lessonComplete.contains(lessonId)) {
            lessonComplete.add(lessonId);
        }
    }

    public void setWordReviewComplete() {
        this.wordReviewComplete = true;
    }

    public int isThereMissionCompleted() {
        int rewardPoints = 0;

        if(lessonComplete.size() == lessonCompleteRequired && !isCompleted[0]) {
            setComplete(0);
            rewardPoints = rewardPoints + 3;
        }

        if(wordReviewComplete && !isCompleted[1]) {
            setComplete(1);
            rewardPoints = rewardPoints + 3;
        }

        if(runningTime >= missionTime && !isCompleted[2]) {
            setComplete(2);
            rewardPoints = rewardPoints + 3;
        }

        return rewardPoints;
    }


    public void setComplete(int index) {
        isCompleted[index] = true;
    }

    public boolean canUserGetBonusPoint() {
        if(!gotBonusPoint) {
            if(isCompleted[0] && isCompleted[1] && isCompleted[2]) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void setGotBonusPoint() {
        gotBonusPoint = true;
    }

    public long getMissionTime() { return missionTime;}

    public long getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(long runningTime) {
        this.runningTime = runningTime;
    }

    // 개발용 (지우기)
    public List<String> getLessonComplete() {
        return this.lessonComplete;
    }
    public boolean getWordReviewComplete() {
        return this.wordReviewComplete;
    }
    public boolean[] getIsCompleted() {
        return this.isCompleted;
    }
}
