package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class NumberNative implements LessonItem {

    private String lessonId = "N_native";

    private String title = "Native number (1~99)";

    private String subTitle = "";

    private int lessonImage = R.drawable.nativekoreannumber;

    private int lessonProgress = 0;

    private Boolean isSpecial = true;

    private Boolean isLock = false;

    private Boolean isCompleted = false;


    @Override
    public String getLessonId() {
        return lessonId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSubTitle() {
        return subTitle;
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
    public boolean getIsCompleted() {
        return isCompleted;
    }

    @Override
    public void setIsCompleted(boolean b) {

        this.isCompleted = b;
    }

    @Override
    public void setIsLocked(boolean b) {

        this.isLock = b;
    }

}
