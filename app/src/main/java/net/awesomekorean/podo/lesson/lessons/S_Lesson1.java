package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.LessonItem;
import net.awesomekorean.podo.lesson.LessonSpecial;

public class S_Lesson1 implements LessonItem, LessonSpecial {

    private String lessonId = "SL_01";
    private int title = R.string.SL_01_TITLE;
    private String subTitle = "먹다 -> 먹어요, 먹으면, 먹어서, 먹고...";
    private int lessonImage = R.drawable.hanbok;
    private Boolean isSpecial = true;
    private Boolean isLock = true;
    private Boolean isCompleted = false;

    private int contents = R.string.SL_01_CONTENTS;

    @Override
    public String getLessonId() {
        return lessonId;
    }

    @Override
    public int getTitle() {
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
    public int getContents() {
        return contents;
    }

    @Override
    public void setIsCompleted(boolean b) {
        this.isCompleted = b;
    }
}

