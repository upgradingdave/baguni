package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.Lesson;
import net.awesomekorean.podo.lesson.LessonItem;

public class Lesson13 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_13";
    private int title = R.string.L_13_TITLE;
    private String subTitle = "밥을 먹기 30분 전에 약을 먹어요";
    private int lessonImage = R.drawable.hangul;

    final static String[] wordFront = {"감기에 걸리다", "먹다", "약", "밥", "전"};
    final static String[] wordPronunciation = {"-", "[먹따]", "-", "-", "-"};
    final static String[] wordSynonyms = {"-", "-", "-", "식사", "앞"};
    final static String[] wordAntonyms = {"감기가 낫다", "토하다", "독", "-", "후"};

    final static String[] sentenceFront = {
            "감기에 걸리다",
            "감기에 걸렸어요.",
            "먹다",
            "먹어요",
            "이 약을 먹어요.",
            "어떻게 먹어요?",
            "밥을 먹다",
            "밥을 먹기",
            "밥을 먹기 전에 먹어요.",
            "밥을 먹기 30분 전에 먹어요."
    };

    final static String[] sentenceClause = {
            "감기에 걸렸어요.",
            "이 약을 먹어요.",
            "어떻게 먹어요?",
            "밥을 먹기 30분 전에 먹어요."
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