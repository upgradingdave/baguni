package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class S_Lesson00 implements LessonItem {

    private String lessonId = "SL_00";
    private String lessonTitle = "Start of learning Korean";
    private int lessonImage = R.drawable.hangul;
    private int lessonProgress = 0;
    private Boolean isSpecial = true;
    private Boolean isLock = false;
    private Boolean isCompleted = false;

    @Override
    public String getLessonId() {
        return lessonId;
    }

    @Override
    public String getLessonTitle() {
        return lessonTitle;
    }

    @Override
    public int getLessonImage() {
        return lessonImage;
    }

    @Override
    public int getLessonProgress() {
        return lessonProgress;
    }

    @Override
    public void setLessonProgress(int progress) {
        this.lessonProgress = progress;
    }

    @Override
    public boolean getIsSpecial() {
        return isSpecial;
    }

    @Override
    public boolean getIsLock() {
        return isLock;
    }

    @Override
    public void setIsLocked(boolean b) {
        this.isLock = b;
    }
}
