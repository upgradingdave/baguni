package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class Lesson07 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_07";
    private String lessonTitle = "do not";
    private String lessonSubTitle = "안~";
    private int lessonImage = R.drawable.negative1;

    final static String[] wordFront = {"날씨", "어때요?", "여기", "겨울", "조금", "춥다", "눈", "오다", "별로"};
    final static String[] wordPronunciation = {"-", "-", "-", "-", "-", "[춥따]", "-", "-", "-"};

    final static String[] sentenceFront = {
            "어때요?",
            "날씨가 어때요?",
            "한국 날씨가 어때요?",
            "겨울",
            "겨울이에요.",
            "여기는 겨울이에요.",
            "춥다",
            "추워요.",
            "조금 추워요.",
            "오다",
            "와요?",
            "눈이 와요?.",
            "와요.",
            "안 와요.",
            "눈은 안 와요.",
            "눈은 별로 안 와요."
    };

    final static String[] dialog = {
            "한국 날씨가 어때요?",
            "여기는 겨울이에요.",
            "조금 추워요.",
            "눈이 와요?",
            "눈은 별로 안 와요."
    };

    final static int[] peopleImage = {1,2};

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
