package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class Lesson13 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_13";
    private String lessonTitle = "before";
    private String lessonSubTitle = "~전에";
    private int lessonImage = R.drawable.timeexpression1;

    final static String[] wordFront = {"감기에 걸리다", "먹다", "약", "밥", "전"};

    final static String[] wordBack = {"have a cold", "to eat", "medicine", "rice / meal", "before"};

    final static String[] wordPronunciation = {"-", "[먹따]", "-", "-", "-"};

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

    final static String[] sentenceBack = {
            "have a cold",
            "I had a cold.",
            "to eat",
            "You eat.",
            "Take this medicine.",
            "How do I take it?",
            "have a meal",
            "having a meal",
            "Take this medicine before you have a meal.",
            "Take this medicine 30 minutes before you have a meal."
    };

    final static String[] sentenceExplain = {
            "감기' and '걸리다' is a commonly used word, so it is good to memorize it together.",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "You can also use 'N + 전에' instead of 'V + 기 전에'\nHowever, only nouns that can be used as 'N + 하다'\n(nouns that represent actions) are possible.\n\nex)\n'식사' + '하다' = '식사하다' (o)\n식사 하기 전에~\n식사 전에~\n'쇼핑' + '하다' = '쇼핑하다' (o)\n쇼핑 하기 전에~\n쇼핑 전에 ~",
            "-"
    };

    final static String[] dialog = {
            "감기에 걸렸어요.",
            "이 약을 먹어요.",
            "어떻게 먹어요?",
            "밥을 먹기 30분 전에 먹어요."
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
