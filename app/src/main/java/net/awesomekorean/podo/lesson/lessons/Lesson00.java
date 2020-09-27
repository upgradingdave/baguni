package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson00 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_00";
    private String lessonTitle = "greetings";
    private String lessonSubTitle = "안녕하세요?";

    private String[] wordFront = {"안녕하세요", "다음", "또", "보다", "가다"};

    private String[] wordBack = {"Hello", "next", "again", "to see / to watch", "to go"};

    private String[] wordPronunciation = {"-", "-", "-", "-", "-"};

    private String[] sentenceFront = {
            "안녕하세요? / 안녕?",
            "반가워요",
            "오랜만이에요",
            "잘 지냈어요?",
            "다음에 또 봐요.",
            "안녕히 가세요/안녕히 계세요"};

    private String[] sentenceBack = {
            "Hello",
            "Nice to meet you",
            "Long time no see",
            "How have you been?",
            "See you again",
            "Good bye"
    };

    private String[] sentenceExplain = {
            "No matter in the morning, afternoon, evening, we say hello like this.\nYou can just say '안녕' to your close friend (friend means same age) or a close sister/brother (It means a close friend that is younger than you)",
            "We say this to someone who met for the first time or after a long time.",
            "-",
            "Conventionally asking if nothing has happened so far.",
            "-",
            "가세요' is from 'to go' and '계세요' is from 'to stay'.\nTherefore, if the other person will 'go' from here after saying goodbye, we say '안녕히 가세요' and if the other person will 'stay' here after saying goodbye, we say '안녕히 계세요'.\nIf they are close friends or younger friends, you can just say goodbye like this '안녕'."
    };

    private String[] dialog = {
            "안녕하세요?",
            "반가워요.",
            "오랜만이에요.",
            "잘 지냈어요?",
            "다음에 또 봐요.",
            "안녕히 가세요."
    };

    private int[] peopleImage = {1,2};

    private int[] reviewId = {};



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
