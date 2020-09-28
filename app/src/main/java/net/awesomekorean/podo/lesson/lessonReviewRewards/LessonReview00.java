package net.awesomekorean.podo.lesson.lessonReviewRewards;

import net.awesomekorean.podo.lesson.lessons.Lesson;
import net.awesomekorean.podo.lesson.lessons.Lesson00;
import net.awesomekorean.podo.lesson.lessons.Lesson01;
import net.awesomekorean.podo.lesson.lessons.Lesson02;
import net.awesomekorean.podo.lesson.lessons.Lesson03;
import net.awesomekorean.podo.lesson.lessons.Lesson19;
import net.awesomekorean.podo.lesson.lessons.LessonInit;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import java.io.Serializable;

public class LessonReview00 extends LessonInit implements LessonItem, LessonReview, Serializable {

    private String lessonId = "LR_00";
    private String lessonTitle = "";
    private String lessonSubTitle = "";

    private Lesson[] lessons = {
            new Lesson00(), new Lesson19(), new Lesson01(), new Lesson02(), new Lesson03()
    };

    private String[] baseForm = {"보다", "가다", "있다", "얘기하다"};

    private String[][] conjugation = {
            {"봐요"},
            {"가요"},
            {"있어요"},
            {"얘기해요"},
    };

    private String[][] translate = {
            {"see / watch"},
            {"go"},
            {"there is / have"},
            {"talk"},
    };


    public String[] getBaseForm() {
        return baseForm;
    }

    public String[][] getConjugation() {
        return conjugation;
    }

    public String[][] getTranslate() {
        return translate;
    }

    public Lesson[] getLessons() {
        return lessons;
    }

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
