package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.Lesson;
import net.awesomekorean.podo.lesson.LessonItem;

public class Lesson09 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_09";
    private int title = R.string.L_09_TITLE;
    private String subTitle = "회사까지 어떻게 가요?";
    private int lessonImage = R.drawable.range1;

    final static String[] wordFront = {"회사", "어떻게", "지하철", "얼마나", "걸리다"};
    final static String[] wordPronunciation = {"-", "[어떠케]", "-", "-", "-"};
    final static String[] wordSynonyms = {"-", "-", "-", "-", "-"};
    final static String[] wordAntonyms = {"-", "-", "-", "-", "-"};

    final static String[] sentenceFront = {
            "가다",
            "가요?",
            "어떻게 가요?",
            "회사까지 어떻게 가요?",
            "집에서 회사까지 어떻게 가요?",
            "타다",
            "타요.",
            "지하철을 타요.",
            "걸리다",
            "걸려요?",
            "얼마나 걸려요?",
            "30분 걸려요."
    };

    final static String[] dialog = {
            "집에서 회사까지 어떻게 가요?",
            "지하철을 타요.",
            "얼마나 걸려요?",
            "30분 걸려요."
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
