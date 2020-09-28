package net.awesomekorean.podo.lesson.lessonReviewRewards;

import net.awesomekorean.podo.lesson.lessons.LessonInit;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import java.io.Serializable;

public class Rewards00 extends LessonInit implements LessonItem, Serializable {

    private String lessonId = "RW_00";
    private String lessonTitle = "";
    private String lessonSubTitle = "";


    @Override
    public String getLessonId() {
        return lessonId;
    }

    @Override
    public String getLessonTitle() {
        return lessonTitle;
    }

    @Override
    public String getLessonSubTitle() {
        return lessonSubTitle;
    }

}
