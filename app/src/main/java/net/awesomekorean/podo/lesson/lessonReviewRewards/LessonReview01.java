package net.awesomekorean.podo.lesson.lessonReviewRewards;

import net.awesomekorean.podo.lesson.lessons.Lesson;
import net.awesomekorean.podo.lesson.lessons.Lesson00;
import net.awesomekorean.podo.lesson.lessons.Lesson01;
import net.awesomekorean.podo.lesson.lessons.Lesson02;
import net.awesomekorean.podo.lesson.lessons.Lesson03;
import net.awesomekorean.podo.lesson.lessons.Lesson04;
import net.awesomekorean.podo.lesson.lessons.Lesson05;
import net.awesomekorean.podo.lesson.lessons.Lesson06;
import net.awesomekorean.podo.lesson.lessons.Lesson07;
import net.awesomekorean.podo.lesson.lessons.Lesson08;
import net.awesomekorean.podo.lesson.lessons.Lesson19;
import net.awesomekorean.podo.lesson.lessons.LessonInit;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import java.io.Serializable;

public class LessonReview01 extends LessonInit implements LessonItem, LessonReview, Serializable {

    private String lessonId = "LR_01";
    private String lessonTitle = "";
    private String lessonSubTitle = "";

    private Lesson[] lessons = {
            new Lesson04(), new Lesson05(), new Lesson06(), new Lesson07(), new Lesson08()
    };

    private String[] baseForm = {
            "재미있다", "춥다", "오다", "바쁘다", "피곤하다", "일어나다", "싸다", "비싸다", "사다", "팔다"};

    private String[][] conjugation = {
            {"재미있었어요", "재미있을 거예요", "재미있어서"},
            {"추웠어요", "추울 거예요", "안 추워요 / 춥지 않아요", "추워서"},
            {"왔어요", "올 거예요", "오고 있어요", "안 와요 / 오지 않아요", "못 와요 / 오지 못해요", "와서"},
            {"바빴어요", "바쁠 거예요", "안 바빠요 / 바쁘지 않아요", "바빠서"},
            {"피곤했어요", "피곤할 거예요", "안 피곤해요 / 피곤하지 않아요", "피곤해서"},
            {"일어났어요", "일어날 거예요", "안 일어나요 / 일어나지 않아요", "못 일어나요 / 일어나지 못해요", "일어나서"},
            {"쌌어요", "쌀 거예요", "안 싸요 / 싸지 않아요", "싸서"},
            {"비쌌어요", "비쌀 거예요", "안 비싸요 / 비싸지 않아요", "비싸서"},
            {"샀어요", "살 거예요", "사고 있어요", "안 사요 / 사지 않아요", "못 사요 / 사지 못해요", "사서"},
            {"팔았어요", "팔 거예요", "팔고 있어요", "안 팔아요 / 팔지 않아요", "못 팔아요 / 팔지 못해요", "팔아서"}
    };

    private String[][] translate = {
            {"It was fun", "It will be fun", "Because it's fun"},
            {"It was cold", "It will be cold", "It's not cold", "Because it's cold"},
            {"He came", "He will come", "He is coming", "He is not coming", "He can't come", "Because he comes"},
            {"I was busy", "I will be busy", "I'm not busy", "Because I'm busy"},
            {"I was tired", "I will be tired", "I'm not tired", "Because I'm tired"},
            {"I woke up", "I will wake up", "I don't wake up", "I can't wake up", "Because I wake up"},
            {"It was cheap", "It will be cheap", "It's not cheap", "Because it's cheap"},
            {"It was expensive", "It will be expensive", "It's not expensive", "Because it's expensive"},
            {"I bought", "I will buy", "I'm buying", "I don't buy", "I can't buy", "Because I buy"},
            {"I sold", "I will sell", "I'm selling", "I don't sell", "I can't sell", "Because I sell"}
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
