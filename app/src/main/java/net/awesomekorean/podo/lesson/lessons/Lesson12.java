package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.Lesson;
import net.awesomekorean.podo.lesson.LessonItem;

public class Lesson12 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_12";
    private int title = R.string.L_12_TITLE;
    private String subTitle = "한국어는 어렵지만 재미있어요";
    private int lessonImage = R.drawable.hangul;

    final static String[] wordFront = {"어렵다", "말하다", "쓰다", "하지만", "읽다", "쉽다"};
    final static String[] wordPronunciation = {"[어렵따]", "-", "-", "-", "[익따]", "[쉽따]"};
    final static String[] wordSynonyms = {"-", "-", "-", "그런데", "-", "-"};
    final static String[] wordAntonyms = {"쉽다", "-", "-", "-", "-", "어렵다"};

    final static String[] sentenceFront = {
            "어때요?",
            "한국어 공부 어때요?",
            "어렵다",
            "어려워요.",
            "한국어는 어려워요.",
            "재미있다",
            "재미있어요",
            "한국어는 어려워요. 하지만 재미있어요.",
            "한국어는 어렵지만 재미있어요.",
            "어려워요?",
            "뭐가 어려워요?",
            "뭐가 제일 어려워요?",
            "어려워요.",
            "말하기가 어려워요.",
            "말하기랑 쓰기가 어려워요.",
            "쉬워요.",
            "읽기는 쉬워요.",
            "말하기랑 쓰기가 어려워요. 하지만 읽기는 쉬워요.",
            "말하기랑 쓰기가 어렵지만 읽기는 쉬워요."
    };

    final static String[] sentenceClause = {
            "한국어 공부 어때요?",
            "한국어는 어려워요. 하지만 재미있어요.",
            "뭐가 제일 어려워요?",
            "말하기랑 쓰기가 어려워요. 하지만 읽기는 쉬워요."
    };

    final static int[] peopleImage = {5,6};


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
    public String[] getWordSynonyms() {
        return wordSynonyms;
    }

    @Override
    public String[] getWordAntonyms() {
        return wordAntonyms;
    }

    @Override
    public String[] getSentenceFront() {
        return sentenceFront;
    }

    @Override
    public String[] getSentenceClause() {
        return sentenceClause;
    }

    @Override
    public int[] getPeopleImage() {
        return peopleImage;
    }


    // 레슨어뎁터 아이템

    @Override
    public int getTitle() {
        return title;
    }

    @Override
    public String getSubTitle() {
        return subTitle;
    }

    @Override
    public int getLessonImage() {
        return lessonImage;
    }
}
