package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class S_Lesson09 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_09";
    private String lessonTitle = "Newly-coined word";
    private int lessonImage = R.drawable.hangul;

    private int contents = R.string.SL_09_CONTENTS;

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

