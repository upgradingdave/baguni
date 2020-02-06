package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.LessonItem;

public class S_Lesson02 extends S_LessonInit implements LessonItem {

    private String lessonId = "SL_02";
    private int title = R.string.SL_02_TITLE;
    private String subTitle = "숫자";
    private int lessonImage = R.drawable.hangul;

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

}
