package net.awesomekorean.podo.lesson.lessons;

public class S_LessonInit_Unlock {

    private Boolean isSpecial = true;
    private Boolean isLock = false;
    private boolean isCompleted = false;

    public boolean getIsSpecial() {
        return isSpecial;
    }
    public boolean getIsLock() {
        return isLock;
    }
    public void setIsLocked(boolean b) {
        this.isLock = b;
    }
    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    public void setIsCompleted(boolean b) {
        this.isCompleted = b;
    }

}
