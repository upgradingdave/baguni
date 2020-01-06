package net.awesomekorean.podo;

import java.util.ArrayList;
import java.util.List;

public class UserInformation {

    private List<Boolean> attendance = new ArrayList<>(7); // [0]~[6], 일~토
    private int points;
    private List<Integer> lessonComplete = new ArrayList<>();
    private List<Integer> readingComplete = new ArrayList<>();
    private List<Integer> specialLessonUnlock = new ArrayList<>();

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

    public List<Integer> getLessonComplete() {
        return lessonComplete;
    }

    public void setLessonComplete(List<Integer> lessonComplete) {
        this.lessonComplete = lessonComplete;
    }

    public void addLessonComplete(int lessonUnit) {
        this.lessonComplete.add(lessonUnit);
    }

    public List<Integer> getReadingComplete() {
        return readingComplete;
    }

    public void addReadingComplete(int readingUnit) {
        this.readingComplete.add(readingUnit);
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
