package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import javax.annotation.meta.When;

public class Lesson10 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_10";
    private String lessonTitle = "for time";
    private String lessonSubTitle = "~부터~까지";
    private int lessonImage = R.drawable.range2;

    final static String[] wordFront = {"시간", "일하다", "몇", "오전", "오후", "점심", "언제"};

    final static String[] wordBack = {"time", "to work", "how many", "am.", "pm.", "noon / lunch", "when"};

    final static String[] wordPronunciation = {"-", "-", "-", "-", "-", "-", "-"};

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

    final static String[] sentenceBack = {
            "to work",
            "Do you work?",
            "How many hours do you work?",
            "I work.",
            "I work until 6 pm.",
            "I work from 9 am to 6 pm.",
            "when",
            "When is it?",
            "When is the lunch break?",
            "It's from 12:30 to 1:30."
    };

    final static String[] sentenceExplain = {
            "-",
            "-",
            "Use '몇' when asking questions about numbers.",
            "-",
            "-",
            "When referring to a range of time, use '~부터 ~까지'.\nIt's also okay to use '~에서 ~ 까지' like a range of places",
            "-",
            "-",
            "-",
            "30 minutes can also be called '반'."
    };

    final static String[] dialog = {
            "몇 시간 일해요?",
            "오전 9시부터 오후 6시까지 일해요.",
            "점심 시간은 언제예요?",
            "12시 반부터 1시 반까지예요."
    };


    final static int[] peopleImage = {8,7};

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
