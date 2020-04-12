package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class S_Lesson03 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_03";
    private String title = "Conjugation";
    private String subTitle = "The most important '아요/어요'";
    private int lessonImage = R.drawable.conjugation2;

    private int contents = R.string.SL_03_CONTENTS;

    @Override
    public String getLessonId() {
        return lessonId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSubTitle() {
        return subTitle;
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

