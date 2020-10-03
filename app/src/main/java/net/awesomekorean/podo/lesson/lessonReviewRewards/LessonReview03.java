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

    private String[] baseForm = {
            "덥다", "시원하다", "들어가다", "크다", "마시다", "주다", "틀리다", "사진을 찍다", "돈을 벌다"
            , "시작하다", "운동하다", "재미없다", "타다", "돕다", "기다리다"};

    private String[][] conjugation = {
            {"더울 때", "더웠으면 좋겠어요", "더우면"},
            {"시원할 때", "시원했으면 좋겠어요", "시원하면"},
            {"들어갈 때", "들어가면서", "들어가고 싶어요", "들어갔으면 좋겠어요", "들어가 주세요", "들어가 줄게요", "들어가면", "들어가려면"},
            {"클 때", "컸으면 좋겠어요", "크면"},
            {"마실 때", "마시면서", "마시고 싶어요", "마셨으면 좋겠어요", "마셔 주세요", "마셔 줄게요", "마시면", "마시려면"},
            {"줄 때", "주면서", "주고 싶어요", "줬으면 좋겠어요", "주면", "주려면"},
            {"틀릴 때", "틀렸으면 좋겠어요", "틀리면"},
            {"사진을 찍을 때", "사진을 찍으면서", "사진을 찍고 싶어요", "사진을 찍었으면 좋겠어요", "사진을 찍어 주세요", "사진을 찍어 줄게요", "사진을 찍으면", "사진을 찍으려면"},
            {"돈을 벌 때", "돈을 벌고 싶어요", "돈을 벌었으면 좋겠어요", "돈을 벌면", "돈을 벌려면"},
            {"시작할 때", "시작하면서", "시작하고 싶어요", "시작했으면 좋겠어요", "시작해 주세요", "시작하면", "시작하려면"},
            {"운동할 때", "운동하면서", "운동하고 싶어요", "운동했으면 좋겠어요", "운동해 주세요", "운동하면", "운동하려면"},
            {"재미없을 때", "재미없었으면 좋겠어요", "재미없으면"},
            {"탈 때", "타면서", "타고 싶어요", "탔으면 좋겠어요", "타 주세요", "타면", "타려면"},
            {"도울 때", "도우면서", "돕고 싶어요", "도왔으면 좋겠어요", "도와 주세요", "도와 줄게요", "도우려면"},
            {"기다릴 때", "기다리면서", "기다리고 싶어요", "기다렸으면 좋겠어요", "기다려 주세요", "기다려 줄게요", "기다리면", "기다리려면"}
    };

    private String[][] translate = {
            {"When it's hot", "I wish it was hot", "If it's hot"},
            {"When it's cool", "I wish it was cool", "If it's cool"},
            {"When I come in", "While I am coming in", "I want to come in", "I wish I could come in", "Please come in", "I'll come in for you", "If I come in", "If I want to come in"},
            {"When it's big", "I wish it could be big", "If it's big"},
            {"When I drink", "While I'm drinking", "I want to drink", "I wish I could drink", "Please drink", "I'll drink it for you", "If I drink", "If I want to drink"},
            {"When I give", "While I'm giving", "I want to give", "I wish I could give", "If I give", "If I want to give"},
            {"When it's wrong", "I wish it was wrong", "If it is wrong"},
            {"When I take a picture", "While I'm taking a picture", "I want to take a picture", "I wish I could take a picture", "Please take a picture", "I'll take a picture for you", "If I take a picture", "If I want to take a picture"},
            {"When I make money", "I want to make money", "I wish I could make money", "If I make money", "If I want to make money"},
            {"When I start", "While I'm starting", "I want to start", "I wish I could start", "Please start", "If I start", "If I want to start"},
            {"When I exercise", "While I'm exercising", "I want to exercise", "I wish I could exercise", "Please exercise", "If I exercise", "If I want to exercise"},
            {"When it's not fun", "I hope it wasn't fun", "If it's not fun"},
            {"When I ride", "While I'm riding", "I want to ride", "I wish I could ride", "Please ride", "If I ride", "If I want to ride"},
            {"When I help", "While I'm helping", "I want to help", "I wish I could help", "Please help", "I'll help you", "If I want to help"},
            {"When I wait", "While I'm waiting", "I want to wait", "I wish I could wait", "Please wait", "I'll wait for you", "If you wait", "If I want to wait"}
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
