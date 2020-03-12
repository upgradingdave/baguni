package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.LessonItem;
import net.awesomekorean.podo.lesson.LessonSpecial;

public class S_Lesson11 extends S_LessonInit implements LessonItem, LessonSpecial {

    private String lessonId = "SL_11";
    private int title = R.string.SL_11_TITLE;
    private String subTitle = "좋아요 vs. 좋아해요";
    private int lessonImage = R.drawable.confusingexpression2;

    private int contents = R.string.SL_11_CONTENTS;

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

