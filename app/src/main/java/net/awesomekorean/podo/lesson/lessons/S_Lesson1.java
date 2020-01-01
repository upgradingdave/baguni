package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.LessonItem;
import net.awesomekorean.podo.lesson.LessonSpecial;

public class S_Lesson1 implements LessonItem, LessonSpecial {

    private int title = R.string.SL1_TITLE;
    private String subTitle = "한글(Hangul)";
    private int lessonImage = R.drawable.hangul;
    private Boolean isSpecial = true;
    private Boolean isLock = false;
    private Boolean isCompleted = false;

    private String lessonSpecialTitle = "Lesson special title";
    private String lessonSpecialArticle = "Lesson special article";


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
    public String getLessonSpecialTitle() {
        return null;
    }

    @Override
    public String getLessonSpecialArticle() {
        return null;
    }
}
