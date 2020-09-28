package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class S_Lesson04 extends S_LessonInit implements LessonItem, LessonSpecial, Serializable {

    private String lessonId = "SL_04";
    private String lessonTitle = "particles";
    private String lessonSubTitle = "why use particles?";

    private int contents = R.string.SL_04_CONTENTS;

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

