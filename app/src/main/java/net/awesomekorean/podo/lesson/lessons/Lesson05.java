package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class Lesson05 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_05";
    private String lessonTitle = "Future tense";
    private int lessonImage = R.drawable.futuretense;

    final static String[] wordFront = {"내일", "식당", "주말", "재미있다", "영화"};
    final static String[] wordPronunciation = {"-", "[식땅]", "-", "[재미읻따]", "-"};
    final static String[] wordSynonyms = {"-", "-", "-", "-", "-"};
    final static String[] wordAntonyms = {"어제", "-", "평일", "재미없다", "-"};

    final static String[] sentenceFront = {
            "하다",
            "할 거예요?",
            "뭐 할 거예요?",
            "내일 뭐 할 거예요?",
            "가다",
            "갈 거예요.",
            "식당에 갈 거예요.",
            "한국 식당에 갈 거예요.",
            "친구랑 한국 식당에 갈 거예요.",
            "주말에는요?",
            "보다",
            "볼 거예요.",
            "영화를 볼 거예요.",
            "재미있는 영화를 볼 거예요."
    };

    final static String[] dialog = {
            "내일 뭐 할 거예요?",
            "친구랑 한국 식당에 갈 거예요.",
            "주말에는요?",
            "재미있는 영화를 볼 거예요."
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
    public String getLessonTitle() {
        return lessonTitle;
    }

    @Override
    public int getLessonImage() {
        return lessonImage;
    }
}
