package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class S_Lesson12 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_12";
    private String lessonTitle = "Confusing expression 3";
    private int lessonImage = R.drawable.confusingexpression3;

    private int contents = R.string.SL_12_CONTENTS;

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

