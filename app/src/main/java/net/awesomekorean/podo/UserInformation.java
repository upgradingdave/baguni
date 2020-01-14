package net.awesomekorean.podo;

import java.util.ArrayList;
import java.util.List;

public class UserInformation {

    private List<Boolean> attendance = new ArrayList<>(7); // [0]~[6], 일~토
    private int points;
    private List<String> lessonComplete = new ArrayList<>();
    private List<String> readingComplete = new ArrayList<>();
    private List<Integer> specialLessonUnlock = new ArrayList<>();
    private Boolean isPremium;
    private long datePurchase;
    // 구독 유효기간... 그 머더라 숫자로 시간 통일시키는 거 그거로 저장하기 그리고 현재시간이랑 빼기해서 유효성 검사

    public UserInformation() {
        for(int i=0; i<7; i++) {
            this.attendance.add(false);
        }
        this.points = 0;
    }

    public List<Boolean> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<Boolean> attendance) {
        this.attendance = attendance;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<String> getLessonComplete() {
        return lessonComplete;
    }

    public void addLessonComplete(String lessonId) {
        this.lessonComplete.add(lessonId);
    }

    public List<String> getReadingComplete() {
        return readingComplete;
    }

    public void addReadingComplete(String readingId) {
        this.readingComplete.add(readingId);
    }

    public Boolean getIsPremium() {return isPremium;}

    public void setIsPremium(boolean b) {
        this.isPremium = b;
    }

    public long getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(long datePurchase) {
        this.datePurchase = datePurchase;
    }

    public List<Integer> getSpecialLessonUnlock() {
        return specialLessonUnlock;
    }

    public void setSpecialLessonUnlock(List<Integer> specialLessonUnlock) {
        this.specialLessonUnlock = specialLessonUnlock;
    }

    public void resetDays(int today) {
        for(int i=0; i<7; i++) {
            this.attendance.set(i, false);
        }
        this.attendance.set(today, true);
    }

    public void setDay(int today) {
        this.attendance.set(today, true);
    }
}
