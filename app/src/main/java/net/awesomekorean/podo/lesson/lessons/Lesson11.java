package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.Lesson;
import net.awesomekorean.podo.lesson.LessonItem;

public class Lesson11 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_11";
    private int title = R.string.L_11_TITLE;
    private String subTitle = "BTS는 노래를 잘 부르고 춤도 잘 춰요.";
    private int lessonImage = R.drawable.listing;

    final static String[] wordFront = {"좋아하다", "네", "제일", "왜", "그리고", "잘", "노래를 부르다", "춤을 추다"};
    final static String[] wordPronunciation = {"[조아하다]", "-", "-", "-", "-", "-", "-", "-"};
    final static String[] wordSynonyms = {"-", "예", "가장", "-", "또", "-", "-", "-"};
    final static String[] wordAntonyms = {"싫어하다", "아니요", "-", "-", "-", "못", "-", "-"};

    final static String[] sentenceFront = {
            "좋아하다",
            "좋아해요?",
            "K-pop을 좋아해요?",
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

    final static String[] dialog = {
            "K-pop을 좋아해요?",
            "네, 저는 BTS를 제일 좋아해요.",
            "BTS를 왜 좋아해요?",
            "BTS는 노래를 잘 불러요. 그리고 춤도 잘 춰요."
    };

    final static int[] peopleImage = {9,10};


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
