package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class S_Lesson04 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_04";
    private String title = "Particles";
    private String subTitle = "Why we use it?";
    private int lessonImage = R.drawable.particles1;

    private int contents = R.string.SL_04_CONTENTS;

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

