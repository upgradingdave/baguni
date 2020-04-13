package net.awesomekorean.podo.lesson.lessons;

public class LessonInit {

    private int lessonProgress = 0;
    private Boolean isSpecial = false;
    private Boolean isLock = false;

    public int getLessonProgress() { return lessonProgress; }
    public void setLessonProgress(int progress) { this.lessonProgress = progress; }
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
