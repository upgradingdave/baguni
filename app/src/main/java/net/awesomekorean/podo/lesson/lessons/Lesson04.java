package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.Lesson;
import net.awesomekorean.podo.lesson.LessonItem;

public class Lesson04 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_04";
    private int title = R.string.L_04_TITLE;
    private String subTitle = "어제 뭐 했어요?";
    private int lessonImage = R.drawable.pasttense;

    final static String[] wordFront = {"어제", "집", "아니요", "쇼핑"};
    final static String[] wordPronunciation = {"-", "-", "-", "-"};
    final static String[] wordSynonyms = {"-", "-", "-", "사다"};
    final static String[] wordAntonyms = {"내일", "-", "네", "팔다"};

    final static String[] sentenceFront = {
            "있다",
            "있어요",
            "있었어요?",
            "집에 있었어요?",
            "어제 집에 있었어요?",
            "아니요.",
            "하다",
            "해요",
            "했어요?",
            "뭐 했어요?",
            "어제 뭐 했어요?",
            "쇼핑 했어요."
    };

    final static String[] sentenceClause = {
            "어제 집에 있었어요?",
            "아니요.",
            "어제 뭐 했어요?",
            "쇼핑 했어요."
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
