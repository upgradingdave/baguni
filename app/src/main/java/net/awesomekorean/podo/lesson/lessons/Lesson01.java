package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class Lesson01 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_01";
    private String lessonTitle = "Self-introduction";
    private int lessonImage = R.drawable.selfintroduction;

    final static String[] wordFront = {"이름", "뭐", "어느", "나라", "사람", "한국"};
    final static String[] wordPronunciation = {"-", "-", "-", "-", "-", "-"};
    final static String[] wordSynonyms = {"-", "무엇", "-", "국가", "~인", "-"};
    final static String[] wordAntonyms = {"-", "-", "-", "-", "물건", "-"};

    final static String[] sentenceFront = {
            "뭐",
            "뭐예요?",
            "이름이 뭐예요?",
            "데니예요. / 메간이에요.",
            "나라",
            "어느 나라",
            "어느 나라 사람이에요?",
            "사람",
            "사람이에요",
            "한국 사람이에요."};

    final static String[] dialog = {
            "이름이 뭐예요?",
            "데니예요.",
            "어느 나라 사람이에요?",
            "한국 사람이에요."};

    final static int[] peopleImage = {2,1};


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
