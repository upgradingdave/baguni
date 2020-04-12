package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class S_Lesson02 extends S_LessonInit implements LessonItem {

    private String lessonId = "SL_02";
    private String title = "Numbers";
    private String subTitle = "Don't be confused anymore";
    private int lessonImage = R.drawable.numbers;

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

}
