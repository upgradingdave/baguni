package net.awesomekorean.podo.lesson.lessons;

public class LessonInit {

    private boolean isActive = false;
    private LessonItem specialLesson = null;
    private boolean isCurrent = false;

    private Boolean isLocked = false;
    private boolean isCompleted = false;

    public boolean getIsLocked() {
        return isLocked;
    }
    public void setIsLocked(boolean b) {
        this.isLocked = b;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }
    public void setIsCompleted(boolean b) {
        this.isCompleted = b;
    }

    public boolean getIsActive() {
        return isActive;
    }
    public boolean setIsActive(boolean isActive) {
        return this.isActive = isActive;
    }

    public LessonItem getSLesson() {
        return specialLesson;
    }

    public boolean getIsCurrent() {
        return isCurrent;
    }
    public void setIsCurrent(boolean current) {
        isCurrent = current;
    }
}
