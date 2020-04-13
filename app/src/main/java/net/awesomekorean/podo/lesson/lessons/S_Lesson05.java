package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class S_Lesson05 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_05";
    private String lessonTitle = "Speaking naturally";
    private int lessonImage = R.drawable.speakingnaturally1;

    private int contents = R.string.SL_05_CONTENTS;

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

