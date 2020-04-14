package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class S_Lesson04 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_04";
    private String lessonTitle = "about particles";
    private String lessonSubTitle = "why use it?";
    private int lessonImage = R.drawable.particles1;

    private int contents = R.string.SL_04_CONTENTS;

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

