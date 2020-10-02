package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson11 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_11";
    private String lessonTitle = "and";
    private String lessonSubTitle = "~고";

    private String[] wordFront = {"좋아하다", "네", "제일", "왜", "그리고", "잘", "노래를 부르다", "춤을 추다"};

    private String[] wordBack = {"like", "yes", "the most", "why", "and", "well", "sing a song", "dance"};

    private String[] wordPronunciation = {"[조아하다]", "-", "-", "-", "-", "-", "-", "-"};

    private String[] sentenceFront = {
            "좋아하다",
            "좋아해요?",
            "케이팝을 좋아해요?",
            "네",
            "네, 좋아해요",
            "네, 저는 BTS를 좋아해요.",
            "네, 저는 BTS를 제일 좋아해요.",
            "BTS를 왜 좋아해요?",
            "노래를 부르다",
            "노래를 불러요.",
            "노래를 잘 불러요.",
            "BTS는 노래를 잘 불러요.",
            "춤을 추다",
            "춤을 춰요.",
            "춤을 잘 춰요.",
            "춤도 잘 춰요.",
            "BTS는 노래를 잘 불러요. 그리고 춤도 잘 춰요.",
            "BTS는 노래를 잘 부르고 춤도 잘 춰요."
    };

    private String[] sentenceBack = {
            "like",
            "Do you like?",
            "Do you like K-pop?",
            "Yes",
            "Yes, I like it.",
            "Yes, I like BTS.",
            "Yes, I like BTS the most.",
            "Why do you like BTS?",
            "sing a song",
            "sing a song.",
            "good at singing.",
            "BTS is good at singing.",
            "dance",
            "dance",
            "good at dancing.",
            "good at dancing as well.",
            "BTS is good at singing. And they are good at dancing as well.",
            "BTS is good at singing and dancing as well."
    };

    private String[] sentenceExplain = {
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "부르다' -> '부르' + '어요' = '부르어요' -> '불러요' (exception)",
            "-",
            "-",
            "-",
            "-",
            "-",
            "We can use '도' to mean 'also'.",
            "Use '그리고' to list sentences.",
            "Use '~고' to combine two sentences together using '그리고'.\n\nex) '부르다' -> '부르' + '고' = '부르고'"
    };

    private String[] dialog = {
            "케이팝을 좋아해요?",
            "네, 저는 BTS를 제일 좋아해요.",
            "BTS를 왜 좋아해요?",
            "BTS는 노래를 잘 불러요.\n그리고 춤도 잘 춰요."
    };

    private int[] peopleImage = {9,10};

    private int[] reviewId = {2,6,7,17};


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
