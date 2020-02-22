package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.LessonItem;
import net.awesomekorean.podo.lesson.LessonSpecial;

public class S_Lesson05 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_05";
    private int title = R.string.SL_05_TITLE;
    private String subTitle = "Basic pronunciation rule";
    private int lessonImage = R.drawable.speakingnaturally1;

    private int contents = R.string.SL_05_CONTENTS;

    @Override
    public String getLessonId() {
        return lessonId;
    }

    @Override
    public int getTitle() {
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

