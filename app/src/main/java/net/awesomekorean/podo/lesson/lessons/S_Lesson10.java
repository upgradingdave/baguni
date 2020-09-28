package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class S_Lesson10 extends S_LessonInit implements LessonItem, LessonSpecial, Serializable {

    private String lessonId = "SL_10";
    private String lessonTitle = "simple Chinese";
    private String lessonSubTitle = "人";

    private int contents = R.string.SL_10_CONTENTS;

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

