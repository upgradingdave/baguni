package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.LessonItem;
import net.awesomekorean.podo.lesson.LessonSpecial;

public class S_Lesson06 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_06";
    private int title = R.string.SL_06_TITLE;
    private String subTitle = "에 vs. 에서";
    private int lessonImage = R.drawable.hangul;

    private int contents = R.string.SL_06_CONTENTS;

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

