package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class S_Lesson01 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_01";
    private String lessonTitle = "conjugation 0";
    private String lessonSubTitle = "what is it?";
    private int lessonImage = R.drawable.conjugation;

    private int contents = R.string.SL_01_CONTENTS;

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

