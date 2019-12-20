package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.Lesson;
import net.awesomekorean.podo.lesson.LessonItem;

public class Lesson1 implements Lesson, LessonItem {

    private String title = "인사";
    private String subTitle = "여러가지 인사";
    private int lessonImage = R.drawable.hangul;
    private Boolean isSpecial = false;
    private Boolean isLock = false;
    private Boolean isCompleted = false;

    final static String[] wordFront = {"안녕", "다음", "또", "보다", "가다"};
    String[] wordBack = {};

    final static String[] wordPronunciation = {"안녕", "다음", "또", "보다", "가다"};

    final static String[] wordSynonyms = {"안녕", "다음", "또", "보다", "가다"};

    final static String[] wordAntonyms = {"안녕", "다음", "또", "보다", "가다"};

    final static String[] wordApplication = {"안녕", "다음", "또", "보다", "가다"};

    final static String[] wordAudio = {"word_1_1", "word_1_2", "word_1_3", "word_1_4", "word_1_5"};
    final static String[] sentenceAudio = {"sentence_1_1", "sentence_1_2", "sentence_1_3", "sentence_1_4", "sentence_1_5", "sentence_1_6"};


    final static String[] sentenceFront = {
            "안녕하세요?",
            "반가워요.",
            "오랜만이에요.",
            "잘 지냈어요?",
            "다음에 또 봐요.",
            "안녕히 가세요."};

    String[] sentenceBack = {};

    String[] sentenceExplain = {};

    final static String[] sentenceClause = {
            "안녕하세요?",
            "반가워요.",
            "오랜만이에요.",
            "잘 지냈어요?",
            "다음에 또 봐요.",
            "안녕히 가세요."};

    final static int[] sentenceClauseAorB = {1,2,1,2,1,2};
    final static int[] peopleImage = {R.drawable.people1, R.drawable.people2, R.drawable.people1, R.drawable.people2,R.drawable.people1, R.drawable.people2};


    @Override
    public String[] getWordFront() {
        return wordFront;
    }

    @Override
    public String[] getWordBack() {
        return wordBack;
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
    public String[] getWordAudio() {
        return wordAudio;
    }

    @Override
    public String[] getSentenceFront() {
        return sentenceFront;
    }

    @Override
    public String[] getSentenceBack() {
        return sentenceBack;
    }

    @Override
    public String[] getSentenceExplain() {
        return sentenceExplain;
    }

    @Override
    public void setWordBack(String[] strings) {
        this.wordBack = strings;
    }

    @Override
    public void setSentenceBack(String[] strings) {
        this.sentenceBack = strings;
    }

    @Override
    public void setSentenceExplain(String[] strings) {
        this.sentenceExplain = strings;
    }

    @Override
    public String[] getSentenceClause() {
        return sentenceClause;
    }

    @Override
    public String[] getSentenceAudio() {
        return sentenceAudio;
    }

    @Override
    public int[] getSentenceClauseAorB() {
        return sentenceClauseAorB;
    }

    @Override
    public int[] getPeopleImage() {
        return peopleImage;
    }


    // 레슨어뎁터 아이템


    @Override
    public String getTitle() {
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
}
