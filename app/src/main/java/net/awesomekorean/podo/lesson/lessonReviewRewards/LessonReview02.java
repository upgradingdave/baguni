package net.awesomekorean.podo.lesson.lessonReviewRewards;

import net.awesomekorean.podo.lesson.lessons.Lesson;
import net.awesomekorean.podo.lesson.lessons.Lesson00;
import net.awesomekorean.podo.lesson.lessons.Lesson01;
import net.awesomekorean.podo.lesson.lessons.Lesson02;
import net.awesomekorean.podo.lesson.lessons.Lesson03;
import net.awesomekorean.podo.lesson.lessons.Lesson09;
import net.awesomekorean.podo.lesson.lessons.Lesson10;
import net.awesomekorean.podo.lesson.lessons.Lesson11;
import net.awesomekorean.podo.lesson.lessons.Lesson12;
import net.awesomekorean.podo.lesson.lessons.Lesson13;
import net.awesomekorean.podo.lesson.lessons.Lesson14;
import net.awesomekorean.podo.lesson.lessons.Lesson15;
import net.awesomekorean.podo.lesson.lessons.Lesson16;
import net.awesomekorean.podo.lesson.lessons.Lesson19;
import net.awesomekorean.podo.lesson.lessons.Lesson27;
import net.awesomekorean.podo.lesson.lessons.LessonInit;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import java.io.Serializable;

public class LessonReview02 extends LessonInit implements LessonItem, LessonReview, Serializable {

    private String lessonId = "LR_02";
    private String lessonTitle = "";
    private String lessonSubTitle = "";

    private Lesson[] lessons = {
            new Lesson27(), new Lesson09(), new Lesson10(), new Lesson11(), new Lesson12(), new Lesson13(),
            new Lesson14(), new Lesson15(), new Lesson16()
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
