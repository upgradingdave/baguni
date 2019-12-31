package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.LessonItem;

public class S_Lesson1 implements LessonItem {

    private int title = R.string.SL1_TITLE;
    private String subTitle = "한글(Hangul)";
    private int lessonImage = R.drawable.hangul;
    private Boolean isSpecial = true;
    private Boolean isLock = false;
    private Boolean isCompleted = false;


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
}
