package net.awesomekorean.podo.lesson.lessonReview;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessons.Lesson;
import net.awesomekorean.podo.lesson.lessons.Lesson00;
import net.awesomekorean.podo.lesson.lessons.Lesson01;
import net.awesomekorean.podo.lesson.lessons.Lesson02;
import net.awesomekorean.podo.lesson.lessons.Lesson03;
import net.awesomekorean.podo.lesson.lessons.Lesson04;
import net.awesomekorean.podo.lesson.lessons.Lesson05;
import net.awesomekorean.podo.lesson.lessons.Lesson06;
import net.awesomekorean.podo.lesson.lessons.Lesson19;
import net.awesomekorean.podo.lesson.lessons.LessonInit;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LessonReview00 extends LessonInit implements LessonItem, LessonReview, Serializable {

    private String lessonId = "LR_00";
    private String lessonTitle = "review";
    private String lessonSubTitle = "00";
    private int lessonImage = R.drawable.l_16_word_5;

    private Lesson[] lessons = {
            new Lesson00(), new Lesson19(), new Lesson01(), new Lesson02(), new Lesson03(),
            new Lesson04(), new Lesson05(), new Lesson06()
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

    @Override
    public int getLessonImage() {
        return lessonImage;
    }
}
