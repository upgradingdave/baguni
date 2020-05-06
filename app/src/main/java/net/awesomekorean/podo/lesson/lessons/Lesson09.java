package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson09 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_09";
    private String lessonTitle = "for place";
    private String lessonSubTitle = "~에서~까지";
    private int lessonImage = R.drawable.range1;

    final static String[] wordFront = {"회사", "어떻게", "지하철", "걸리다"};

    final static String[] wordBack = {"company", "how", "subway", "take time"};

    final static String[] wordPronunciation = {"-", "[어떠케]", "-", "-"};

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

    final static String[] sentenceBack = {
            "to go",
            "Do you go?",
            "How do you go?",
            "How do you get to the company.",
            "How do you get to the company from home.",
            "to take / to ride",
            "I take.",
            "I take a subway.",
            "It takes (time)",
            "Does it take?",
            "How long does it take?",
            "It takes 30 mins."
    };

    final static String[] sentenceExplain = {
            "-",
            "-",
            "-",
            "-",
            "When referring to a range of places, use '~에서 ~까지'.",
            "-",
            "-",
            "-",
            "-",
            "-",
            "When asking about the amount of something, use '얼마나'.",
            "-"
    };


    final static String[] dialog = {
            "집에서 회사까지 어떻게 가요?",
            "지하철을 타요.",
            "얼마나 걸려요?",
            "30분 걸려요."
    };

    final static int[] peopleImage = {5,6};


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

    @Override
    public String[] getWordBack() {
        return wordBack;
    }

    @Override
    public String[] getSentenceBack() {
        return sentenceBack;
    }

    @Override
    public String[] getSentenceExplain() {
        return sentenceExplain;
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
