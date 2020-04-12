package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class S_Lesson09 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_09";
    private String title = "Newly-coined word";
    private String subTitle = "The most popular words in 2019";
    private int lessonImage = R.drawable.hangul;

    private int contents = R.string.SL_09_CONTENTS;

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

