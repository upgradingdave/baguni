package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.Lesson;
import net.awesomekorean.podo.lesson.LessonItem;

public class Lesson0 implements Lesson, LessonItem {

    private String lessonId = "L_00";
    private int title = R.string.L_00_TITLE;
    private String subTitle = "안녕하세요?";
    private int lessonImage = R.drawable.hangul;
    private Boolean isSpecial = false;
    private Boolean isLock = false;
    private Boolean isCompleted = false;

    final static String[] wordFront = {"안녕하세요", "다음", "또", "보다", "가다"};
    final static String[] wordPronunciation = {"-", "-", "-", "-", "-"};
    final static String[] wordSynonyms = {"-", "-", "다시", "-", "-"};
    final static String[] wordAntonyms = {"안녕히 계세요 / 안녕히 가세요", "전", "-", "안 보다", "오다"};
    final static String[] wordApplication = {"-", "다음 역은 서울역입니다", "한국에 또 가고 싶어요", "영화를 보다", "학교에 가다"};

    final static String[] sentenceFront = {
            "안녕하세요? / 안녕?",
            "반가워요",
            "오랜만이에요",
            "잘 지냈어요?",
            "다음에 또 봐요.",
            "안녕히 가세요 / 안녕히 계세요 / 안녕~"};

    final static String[] sentenceClause = {
            "안녕하세요?",
            "반가워요.",
            "오랜만이에요.",
            "잘 지냈어요?",
            "다음에 또 봐요.",
            "안녕히 가세요."};

    final static int[] peopleImage = {1,2};


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
    public String[] getWordApplication() {
        return wordApplication;
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

    @Override
    public boolean getIsSpecial() {
        return isSpecial;
    }

    @Override
    public boolean getIsLock() {
        return isLock;
    }

    @Override
    public boolean getIsCompleted() {
        return isCompleted;
    }

    @Override
    public void setIsCompleted(boolean b) {
        this.isCompleted = b;
    }
}
