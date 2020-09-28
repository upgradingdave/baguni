package net.awesomekorean.podo.lesson.lessonReviewRewards;

import net.awesomekorean.podo.lesson.lessons.Lesson;
import net.awesomekorean.podo.lesson.lessons.Lesson00;
import net.awesomekorean.podo.lesson.lessons.Lesson01;
import net.awesomekorean.podo.lesson.lessons.Lesson02;
import net.awesomekorean.podo.lesson.lessons.Lesson03;
import net.awesomekorean.podo.lesson.lessons.Lesson17;
import net.awesomekorean.podo.lesson.lessons.Lesson18;
import net.awesomekorean.podo.lesson.lessons.Lesson19;
import net.awesomekorean.podo.lesson.lessons.Lesson20;
import net.awesomekorean.podo.lesson.lessons.Lesson21;
import net.awesomekorean.podo.lesson.lessons.Lesson22;
import net.awesomekorean.podo.lesson.lessons.Lesson23;
import net.awesomekorean.podo.lesson.lessons.Lesson24;
import net.awesomekorean.podo.lesson.lessons.Lesson28;
import net.awesomekorean.podo.lesson.lessons.Lesson29;
import net.awesomekorean.podo.lesson.lessons.Lesson35;
import net.awesomekorean.podo.lesson.lessons.LessonInit;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import java.io.Serializable;

public class LessonReview03 extends LessonInit implements LessonItem, LessonReview, Serializable {

    private String lessonId = "LR_03";
    private String lessonTitle = "";
    private String lessonSubTitle = "";

    private Lesson[] lessons = {
            new Lesson22(), new Lesson28(), new Lesson29(), new Lesson17(), new Lesson20(), new Lesson18(),
            new Lesson21(), new Lesson23(), new Lesson35()
    };

    private String[] baseForm = {"가다", "먹다", "오다", "사다", "팔다"};

    private String[][] conjugation = {
            {"가요", "갔어요", "갈 거예요", "가고 있어요", "안 가요"},
            {"먹어요", "먹었어요", "먹을 거예요", "먹고 있어요", "안 먹어요"},
            {"와요", "왔어요", "올 거예요", "오고 있어요", "안 와요"},
            {"사요", "샀어요", "살 거예요", "사고 있어요", "안 사요"},
            {"팔아요", "팔았어요", "팔 거예요", "팔고 있어요", "안 팔아요"}
    };

    private String[][] translate = {
            {"go", "went", "will go", "going", "don't go"},
            {"eat", "ate", "will eat", "eating", "don't eat"},
            {"come", "came", "will come", "coming", "don't come"},
            {"buy", "bought", "will buy", "buying", "don't buy"},
            {"sell", "sold", "will sell", "selling", "don't sell"}
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
