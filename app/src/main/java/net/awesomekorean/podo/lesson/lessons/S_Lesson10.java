package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class S_Lesson10 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_10";
    private String lessonTitle = "simple Chinese 1";
    private String lessonSubTitle = "人";
    private int lessonImage = R.drawable.hanja1;

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
    public int getLessonImage() {
        return lessonImage;
    }

    @Override
    public int getContents() {
        return contents;
    }
}

