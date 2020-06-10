package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class S_Lesson14 extends S_LessonInit implements LessonItem, LessonSpecial, Serializable {

    private String lessonId = "SL_14";
    private String lessonTitle = "more expression";
    private String lessonSubTitle = "~ 것 같다";
    private int lessonImage = R.drawable.confusingexpression3;

    private int contents = R.string.SL_14_CONTENTS;

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

