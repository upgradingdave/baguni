package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class S_Lesson11 extends S_LessonInit implements LessonItem, LessonSpecial, Serializable {

    private String lessonId = "SL_11";
    private String lessonTitle = "confusing expression";
    private String lessonSubTitle = "'좋아요' vs '좋아해요'";
    private int lessonImage = R.drawable.confusingexpression2;

    private int contents = R.string.SL_11_CONTENTS;

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

