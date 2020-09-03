package net.awesomekorean.podo.lesson.lessonReview;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessons.LessonInit;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

public class R_Ranking_Lesson00 extends LessonInit implements LessonItem {

    private String lessonId = "RR_00";
    private String lessonTitle = "ranking";
    private String lessonSubTitle = "coming soon";
    private int lessonImage = R.drawable.l_11_word_2;



    @Override
    public String getLessonSubTitle() {
        return lessonSubTitle;
    }

    @Override
    public String getLessonId() {
        return lessonId;
    }


    // 레슨어뎁터 아이템

    @Override
    public String getLessonTitle() {
        return lessonTitle;
    }

    @Override
    public int getLessonImage() {
        return lessonImage;
    }

}
