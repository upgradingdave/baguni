package net.awesomekorean.podo;

import java.util.ArrayList;
import java.util.List;

public class UserInformation {

    private List<Boolean> attendance = new ArrayList<>(7); // [0]~[6], 일~토
    private int points;
    private List<String> lessonComplete = new ArrayList<>();
    private List<String> readingComplete = new ArrayList<>();
    private List<String> specialLessonUnlock = new ArrayList<>();
    private List<String> readingUnlock = new ArrayList<>();
    private Boolean isPremium;
    private Long datePurchase;
    private Long dateExpire;

    public UserInformation() {
        for(int i=0; i<7; i++) {
            this.attendance.add(false);
        }
        this.points = 0;
        this.isPremium = false;
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

    public void setIsPremium(Boolean b) {
        this.isPremium = b;
    }

    public Long getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(Long datePurchase) {
        this.datePurchase = datePurchase;
    }

    public Long getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(Long dateExpire) {
        this.dateExpire = dateExpire;
    }

    public List<String> getSpecialLessonUnlock() {
        return specialLessonUnlock;
    }

    public void addSpecialLessonUnlock(String lessonId) {
        this.specialLessonUnlock.add(lessonId);
    }

    public List<String> getReadingUnlock() {
        return readingUnlock;
    }

    public void addReadingUnlock(String readingId) {
        this.readingUnlock.add(readingId);
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
