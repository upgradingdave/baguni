package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.Lesson;
import net.awesomekorean.podo.lesson.LessonItem;

public class Lesson10 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_10";
    private int title = R.string.L_10_TITLE;
    private String subTitle = "몇 시간 일해요?";
    private int lessonImage = R.drawable.range2;

    final static String[] wordFront = {"시간", "일하다", "몇", "오전", "오후", "점심", "언제"};
    final static String[] wordPronunciation = {"-", "-", "-", "-", "-", "-", "-"};
    final static String[] wordSynonyms = {"-", "-", "-", "-", "-", "-", "-"};
    final static String[] wordAntonyms = {"-", "쉬다", "-", "오후", "오전", "-", "-"};

    final static String[] sentenceFront = {
            "일하다",
            "일해요?",
            "몇 시간 일해요?",
            "일해요.",
            "오후 6시까지 일해요.",
            "오전 9시부터 오후 6시까지 일해요.",
            "언제",
            "언제예요?",
            "점심 시간은 언제예요?",
            "12시 반부터 1시 반까지예요."
    };

    final static String[] dialog = {
            "몇 시간 일해요?",
            "오전 9시부터 오후 6시까지 일해요.",
            "점심 시간은 언제예요?",
            "12시 반부터 1시 반까지예요."
    };

    final static int[] peopleImage = {8,7};


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
