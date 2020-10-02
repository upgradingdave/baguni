package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson29 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_29";
    private String lessonTitle = "during";
    private String lessonSubTitle = "동안";
    private LessonItem specialLesson = new S_Lesson15();

    private String[] wordFront = {"영어", "무슨", "주로", "여행"};

    private String[] wordBack = {"English", "what kind of", "mostly", "travel / trip"};

    private String[] wordPronunciation = {"-", "-", "-", "-"};

    private String[] sentenceFront = {
            "시작했어요.",
            "언어교환을 시작했어요.",
            "한국 친구랑 언어교환을 시작했어요.",
            "몇 시간",
            "몇 시간 동안",
            "몇 시간 동안 해요?",
            "영어로 얘기해요.",
            "한국어랑 영어로 얘기해요.",
            "1시간 동안 한국어랑 영어로 얘기해요.",
            "언어교환 하는 동안",
            "무슨 얘기 해요?",
            "이야기 많이 해요.",
            "음식, 여행, 운동 이야기 많이 해요.",
            "주로 음식, 여행, 운동 이야기 많이 해요."
    };

    private String[] sentenceBack = {
            "I started.",
            "I started a language exchange.",
            "I started a language exchange with a Korean friend.",
            "how many hours",
            "how many hours",
            "How many hours do you do it?",
            "We speak English.",
            "We speak Korean and English.",
            "We speak Korean and English for 1 hour.",
            "During a language exchange",
            "What do you talk?",
            "We talk a lot.",
            "We talk a lot about food, travel and exercise.",
            "Mostly we talk a lot about food, travel and exercise."
    };

    private String[] sentenceExplain = {
            "시작해요' -> '시작했어요' (past tense)",
            "-",
            "-",
            "Use '몇' when asking questions about numbers",
            "You can say '동안' to express the length of time for an action.\nNoun + '동안'",
            "-",
            "얘기하다' = '이야기하다'",
            "-",
            "1시간 + '동안'",
            "Verb + '는 동안'\n'언어교환 하다' -> '언어교환 하' + '는 동안' = '언어교환 하는 동안'",
            "You can use '어떤' instead of '무슨'.\n\n(Refer to the Lesson 10 - confusing expression : '어떤' vs '무슨')",
            "-",
            "-",
            "주로' : mostly, mainly\n'보통' : normally, usually\n\nRemember this because it's a common word in conversation."
    };

    private String[] dialog = {
            "한국 친구랑 언어교환을 시작했어요.",
            "몇 시간 동안 해요?",
            "1시간 동안 한국어랑 영어로 얘기해요. ",
            "언어교환 하는 동안 무슨 얘기 해요?",
            "주로 음식, 여행, 운동 이야기 많이 해요."
    };

    private int[] peopleImage = {5,6};

    private int[] reviewId = {2,5,8,10,13};


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
