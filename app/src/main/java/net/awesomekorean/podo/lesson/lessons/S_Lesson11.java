package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class S_Lesson11 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_11";
    private String lessonTitle = "Confusing expression 2";
    private int lessonImage = R.drawable.confusingexpression2;

    private int contents = R.string.SL_11_CONTENTS;

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

