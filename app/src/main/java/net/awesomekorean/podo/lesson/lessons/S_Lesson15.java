package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class S_Lesson15 extends S_LessonInit implements LessonItem, LessonSpecial, Serializable {

    private String lessonId = "SL_15";
    private String lessonTitle = "confusing expression";
    private String lessonSubTitle = "뭐 vs 무슨 vs 어떤";

    private int contents = R.string.SL_15_CONTENTS;

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

