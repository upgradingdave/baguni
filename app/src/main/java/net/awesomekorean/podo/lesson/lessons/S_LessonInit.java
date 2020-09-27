package net.awesomekorean.podo.lesson.lessons;

public class S_LessonInit {
    private boolean isActive = false;
    private boolean isCurrent = false;
    private LessonItem specialLesson = null;
    private boolean isCompleted = false;
    private Boolean isLocked = true;

    public boolean getIsCompleted() {
        return isCompleted;
    }
    public void setIsCompleted(boolean b) {
        isCompleted = b;
    }

    public boolean getIsLocked() {
        return isLocked;
    }
    public void setIsLocked(boolean b) {
        this.isLocked = b;
    }

    public boolean getIsActive() {
        return isActive;
    }
    public boolean setIsActive(boolean isActive) {
        return this.isActive = isActive;
    }

    public boolean getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(boolean b) {
        isCurrent = b;
    }

    public LessonItem getSLesson() {
        return specialLesson;
    }
}
