package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class S_Lesson09 extends S_LessonInit implements LessonItem, LessonSpecial, Serializable {

    private String lessonId = "SL_09";
    private String lessonTitle = "coinage";
    private String lessonSubTitle = "coinage";

    private int contents = R.string.SL_09_CONTENTS;

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

