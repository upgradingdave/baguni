package net.awesomekorean.podo.lesson.lessons;

public class LessonInit {

    private Boolean isSpecial = false;
    private Boolean isLock = false;
    private Boolean isCompleted = false;

    public boolean getIsSpecial() {
        return isSpecial;
    }
    public boolean getIsLock() {
        return isLock;
    }
    public boolean getIsCompleted() {
        return isCompleted;
    }
    public void setIsCompleted(boolean b) {
        this.isCompleted = b;
    }
}
