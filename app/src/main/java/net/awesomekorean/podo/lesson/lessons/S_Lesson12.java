package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.LessonItem;
import net.awesomekorean.podo.lesson.LessonSpecial;

public class S_Lesson12 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_12";
    private int title = R.string.SL_12_TITLE;
    private String subTitle = "~아/어서 vs. ~으니까";
    private int lessonImage = R.drawable.confusingexpression3;

    private int contents = R.string.SL_12_CONTENTS;

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
    public int getContents() {
        return contents;
    }
}

