package net.awesomekorean.podo.lesson.lessonNumber.numbers;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessons.LessonItem;
import net.awesomekorean.podo.lesson.lessons.S_LessonInit;

import java.io.Serializable;

public class NumberPractice extends S_LessonInit implements LessonItem, Serializable {

    private String lessonId = "N_practice";

    private String lessonTitle = "Practice";

    private String lessonSubTitle = "time, date, age...";


    @Override
    public String getLessonSubTitle() {
        return lessonSubTitle;
    }

    @Override
    public String getLessonId() {
        return lessonId;
    }

    @Override
    public String getLessonTitle() {
        return lessonTitle;
    }

}
