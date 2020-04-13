package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class Lesson00 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_00";
    private String lessonTitle = "Greetings";
    private int lessonImage = R.drawable.greetings;

    final static String[] wordFront = {"안녕하세요", "다음", "또", "보다", "가다"};
    final static String[] wordPronunciation = {"-", "-", "-", "-", "-"};
    final static String[] wordSynonyms = {"-", "-", "다시", "-", "-"};
    final static String[] wordAntonyms = {"안녕히 계세요 / 안녕히 가세요", "전", "-", "안 보다", "오다"};

    final static String[] sentenceFront = {
            "안녕하세요? / 안녕?",
            "반가워요",
            "오랜만이에요",
            "잘 지냈어요?",
            "다음에 또 봐요.",
            "안녕히 가세요/안녕히 계세요"};

    final static String[] dialog = {
            "안녕하세요?",
            "반가워요.",
            "오랜만이에요.",
            "잘 지냈어요?",
            "다음에 또 봐요.",
            "안녕히 가세요."};

    final static int[] peopleImage = {1,2};


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
    public String[] getDialog() {
        return dialog;
    }

    @Override
    public int[] getPeopleImage() {
        return peopleImage;
    }


    // 레슨어뎁터 아이템

    @Override
    public String getLessonTitle() {
        return lessonTitle;
    }

    @Override
    public int getLessonImage() {
        return lessonImage;
    }

}
