package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class S_Lesson16 extends S_LessonInit implements LessonItem, LessonSpecial, Serializable {

    private String lessonId = "SL_16";
    private String lessonTitle = "more expression";
    private String lessonSubTitle = "~(으)로";
    private int lessonImage = R.drawable.more_expression;

    private int contents = R.string.SL_16_CONTENTS;

    @Override
    public String getLessonSubTitle() {
        return lessonSubTitle;
    }

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
    public int getContents() {
        return contents;
    }
}

