package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class S_Lesson06 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_06";
    private String title = "Confusing expression";
    private String subTitle = "에 vs. 에서";
    private int lessonImage = R.drawable.confusingexpression;

    private int contents = R.string.SL_06_CONTENTS;

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

