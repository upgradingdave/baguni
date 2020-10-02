package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson13 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_13";
    private String lessonTitle = "before";
    private String lessonSubTitle = "~전에";

    private String[] wordFront = {"감기에 걸리다", "먹다", "약", "밥", "전"};

    private String[] wordBack = {"catch a cold / get a cold", "eat", "medicine", "rice / meal", "before"};

    private String[] wordPronunciation = {"-", "[먹따]", "-", "-", "-"};

    private String[] sentenceFront = {
            "감기에 걸리다",
            "감기에 걸렸어요.",
            "먹다",
            "먹어요",
            "이 약을 먹어요.",
            "어떻게 먹어요?",
            "밥을 먹다",
            "밥을 먹기",
            "밥을 먹기 전에 먹어요.",
            "밥을 먹기 30분 전에 먹어요."
    };

    private String[] sentenceBack = {
            "get a cold",
            "I've got a cold.",
            "eat",
            "eat.",
            "Take this medicine.",
            "How do I take it?",
            "have a meal",
            "having a meal",
            "Take this medicine before meals.",
            "Take this medicine 30 minutes before meals."
    };

    private String[] sentenceExplain = {
            "감기' and '걸리다' is a commonly used word, so it is good to memorize it together.",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "You can also use 'N + 전에' instead of 'V + 기 전에'\nHowever, only nouns that can be used as 'N + 하다'\n(nouns that represent actions) are possible.\n\nex)\n'식사' + '하다' = '식사하다' (o)\n식사 하기 전에~\n식사 전에~\n'쇼핑' + '하다' = '쇼핑하다' (o)\n쇼핑 하기 전에~\n쇼핑 전에 ~",
            "-"
    };

    private String[] dialog = {
            "감기에 걸렸어요.",
            "이 약을 먹어요.",
            "어떻게 먹어요?",
            "밥을 먹기 30분 전에 먹어요."
    };

    private int[] peopleImage = {1,2};

    private int[] reviewId = {1,4,5,9};


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
}
