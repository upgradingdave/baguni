package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.Lesson;
import net.awesomekorean.podo.lesson.LessonItem;

public class Lesson1 implements Lesson, LessonItem {

    private int title = R.string.L1_TITLE;
    private String subTitle = "제 이름은 데니예요";
    private int lessonImage = R.drawable.hangul;
    private Boolean isSpecial = false;
    private Boolean isLock = false;
    private Boolean isCompleted = false;

    final static String[] wordFront = {"이름", "뭐", "어느", "나라", "사람", "한국"};
    final static String[] wordPronunciation = {"-", "-", "-", "-", "-", "-"};
    final static String[] wordSynonyms = {"-", "무엇", "-", "국가", "~인", "-"};
    final static String[] wordAntonyms = {"-", "-", "-", "-", "물건", "-"};
    final static String[] wordApplication = {"이름이 뭐예요?", "이거 뭐예요?", "어느 것이 좋아요?", "우리 나라 좋아요", "한국 사람 = 한국인", "한국 사람이에요"};

    final static String[] sentenceFront = {
            "뭐",
            "뭐예요?",
            "이름이 뭐예요?",
            "데니예요. / 데닝이에요.",
            "나라",
            "어느 나라",
            "어느 나라 사람이에요?",
            "사람",
            "사람이에요",
            "한국 사람이에요."};

    final static String[] sentenceClause = {
            "이름이 뭐예요?",
            "데니예요.",
            "어느 나라 사람이에요?",
            "한국 사람이에요."};

    final static int[] peopleImage = {3,4};



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
