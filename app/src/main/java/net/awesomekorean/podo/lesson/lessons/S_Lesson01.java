package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.LessonItem;
import net.awesomekorean.podo.lesson.LessonSpecial;

public class S_Lesson01 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_01";
    private int title = R.string.SL_01_TITLE;
    private String subTitle = "What is it?";
    private int lessonImage = R.drawable.conjugation;

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
    public int getContents() {
        return contents;
    }
}

