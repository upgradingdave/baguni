package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class Lesson02 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_02";
    private String lessonTitle = "shopping";
    private String lessonSubTitle = "얼마예요?";
    private int lessonImage = R.drawable.shopping;

    final static String[] wordFront = {"이거", "저거", "얼마", "세일", "그래서"};
    final static String[] wordPronunciation = {"-", "-", "-", "[쎄일]", "-"};
    final static String[] wordSynonyms = {"이것", "저것", "-", "할인", "그러므로, 따라서"};
    final static String[] wordAntonyms = {"-", "-", "-", "-", "그러나"};

    final static String[] sentenceFront = {
            "얼마",
            "얼마예요?",
            "이거 얼마예요?",
            "10,000(만)원이에요.",
            "저거",
            "저거는요?",
            "세일",
            "세일이에요.",
            "50% 세일이에요.",
            "저거는 50% 세일이에요.",
            "5,000(오천)원이에요.",
            "그래서 5,000(오천)원이에요."
    };

    final static String[] dialog = {
            "이거 얼마예요?",
            "10,000(만)원이에요.",
            "저거는요?",
            "저거는 50% 세일이에요.\n 그래서 5,000(오천)원이에요."
    };

    final static int[] peopleImage = {4,3};


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
