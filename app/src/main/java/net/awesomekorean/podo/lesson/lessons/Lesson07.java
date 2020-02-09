package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.Lesson;
import net.awesomekorean.podo.lesson.LessonItem;

public class Lesson07 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_07";
    private int title = R.string.L_07_TITLE;
    private String subTitle = "눈이 안 와요";
    private int lessonImage = R.drawable.negative1;

    final static String[] wordFront = {"날씨", "어때요?", "여기", "겨울", "조금", "춥다", "눈", "오다", "별로"};
    final static String[] wordPronunciation = {"-", "-", "-", "-", "-", "[춥따]", "-", "-", "-"};
    final static String[] wordSynonyms = {"-", "-", "-", "-", "좀", "-", "-", "-", "그다지"};
    final static String[] wordAntonyms = {"-", "-", "거기", "여름", "", "덥다", "비", "가다", ""};

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
    public String[] getDialog() {
        return dialog;
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
