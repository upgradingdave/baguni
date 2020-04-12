package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class NumberPractice extends S_LessonInit implements LessonItem {

    private String lessonId = "N_practice";

    private String title = "Practice";

    private String subTitle = "";

    private int lessonImage = R.drawable.numberpractice;


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
