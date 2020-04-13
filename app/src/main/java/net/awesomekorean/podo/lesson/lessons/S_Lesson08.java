package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class S_Lesson08 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_08";
    private String lessonTitle = "Conjugation";
    private int lessonImage = R.drawable.conjugation3;

    private int contents = R.string.SL_08_CONTENTS;

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

