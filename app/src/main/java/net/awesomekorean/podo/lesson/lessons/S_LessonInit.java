package net.awesomekorean.podo.lesson.lessons;

public class S_LessonInit {

    private boolean isCompleted = false;
    private Boolean isSpecial = true;
    private Boolean isLock = true;

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean b) {
        isCompleted = b;
    }

    public boolean getIsSpecial() {
        return isSpecial;
    }
    public boolean getIsLock() {
        return isLock;
    }
    public void setIsLocked(boolean b) {
        this.isLock = b;
    }

}
