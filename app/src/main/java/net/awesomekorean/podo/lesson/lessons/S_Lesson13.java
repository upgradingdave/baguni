package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class S_Lesson13 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_13";
    private String lessonTitle = "confusing expression 4";
    private String lessonSubTitle = "'here' and 'there'";
    private int lessonImage = R.drawable.confusingexpression3;

    private int contents = R.string.SL_13_CONTENTS;

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

