package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class S_Lesson07 extends S_LessonInit implements LessonItem, LessonSpecial, Serializable {

    private String lessonId = "SL_07";
    private String lessonTitle = "speaking naturally 2";
    private String lessonSubTitle = "식당? 집?";

    private int contents = R.string.SL_07_CONTENTS;

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

