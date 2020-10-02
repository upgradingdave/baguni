package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson09 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_09";
    private String lessonTitle = "from to(place)";
    private String lessonSubTitle = "~에서~까지";
    private LessonItem specialLesson = new S_Lesson12();

    private String[] wordFront = {"회사", "어떻게", "지하철", "걸리다"};

    private String[] wordBack = {"company", "how", "subway", "take time"};

    private String[] wordPronunciation = {"-", "[어떠케]", "-", "-"};

    private String[] sentenceFront = {
            "가다",
            "가요?",
            "어떻게 가요?",
            "회사까지 어떻게 가요?",
            "집에서 회사까지 어떻게 가요?",
            "타다",
            "타요.",
            "지하철을 타요.",
            "걸리다",
            "걸려요?",
            "얼마나 걸려요?",
            "30분 걸려요."
    };

    private String[] sentenceBack = {
            "go",
            "go?",
            "How do you go?",
            "How do you go to work.",
            "How do you go to work from home.",
            "take / ride",
            "take. / ride.",
            "I take a subway.",
            "It takes (time)",
            "Does it take?",
            "How long does it take?",
            "It takes 30 mins."
    };

    private String[] sentenceExplain = {
            "-",
            "-",
            "-",
            "-",
            "When referring to a range of places, use '~에서 ~까지'.",
            "-",
            "-",
            "-",
            "-",
            "-",
            "When asking about the amount of something, use '얼마나'.",
            "-"
    };


    private String[] dialog = {
            "집에서 회사까지 어떻게 가요?",
            "지하철을 타요.",
            "얼마나 걸려요?",
            "30분 걸려요."
    };

    private int[] peopleImage = {5,6};

    private int[] reviewId = {4,7,10,11};


    @Override
    public String getLessonSubTitle() {
        return lessonSubTitle;
    }

    @Override
    public String getLessonId() {
        return lessonId;
    }

    @Override
    public String[] getWordFront() {
        return wordFront;
    }

    @Override
    public String[] getWordPronunciation() {
        return wordPronunciation;
    }

    @Override
    public String[] getSentenceFront() {
        return sentenceFront;
    }

    @Override
    public String[] getDialog() {
        return dialog;
    }

    @Override
    public int[] getPeopleImage() {
        return peopleImage;
    }

    @Override
    public String[] getWordBack() {
        return wordBack;
    }

    @Override
    public String[] getSentenceBack() {
        return sentenceBack;
    }

    @Override
    public String[] getSentenceExplain() {
        return sentenceExplain;
    }

    @Override
    public int[] getReviewId() {
        return reviewId;
    }


    // 레슨어뎁터 아이템

    @Override
    public String getLessonTitle() {
        return lessonTitle;
    }

    @Override
    public LessonItem getSLesson() {
        return specialLesson;
    }
}
