package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class S_Lesson08 extends S_LessonInit implements LessonItem, LessonSpecial, Serializable {

    private String lessonId = "SL_08";
    private String lessonTitle = "conjugation 2";
    private String lessonSubTitle = "아/어서";
    private int lessonImage = R.drawable.conjugation3;

    private int contents = R.string.SL_08_CONTENTS;

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

