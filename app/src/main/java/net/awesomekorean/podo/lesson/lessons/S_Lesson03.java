package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class S_Lesson03 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_03";
    private String lessonTitle = "conjugation 1";
    private String lessonSubTitle = "아/어요";
    private int lessonImage = R.drawable.conjugation2;

    private int contents = R.string.SL_03_CONTENTS;

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

