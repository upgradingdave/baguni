package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class S_Lesson05 extends S_LessonInit implements LessonItem, LessonSpecial, Serializable {

    private String lessonId = "SL_05";
    private String lessonTitle = "basic rules";
    private String lessonSubTitle = "speak naturally";

    private int contents = R.string.SL_05_CONTENTS;

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
    public int getContents() {
        return contents;
    }
}

