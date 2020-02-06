package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.Lesson;
import net.awesomekorean.podo.lesson.LessonItem;

public class Lesson06 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_06";
    private int title = R.string.L_06_TITLE;
    private String subTitle = "집에 가고 있어요";
    private int lessonImage = R.drawable.progressive;

    final static String[] wordFront = {"지금", "이따", "같이", "헬스장"};
    final static String[] wordPronunciation = {"-", "-", "[가치]", "[헬쓰장]"};
    final static String[] wordSynonyms = {"-", "이따가", "-", "-"};
    final static String[] wordAntonyms = {"-", "-", "-", "-"};

    final static String[] sentenceFront = {
            "어디",
            "어디예요?",
            "지금 어디예요?",
            "가다",
            "가고 있어요.",
            "집에 가고 있어요.",
            "가다",
            "가요",
            "헬스장에 가요.",
            "같이 헬스장에 가요.",
            "이따 같이 헬스장에 가요.",
            "좋아요."
    };

    final static String[] sentenceClause = {
            "지금 어디예요?",
            "집에 가고 있어요.",
            "이따 같이 헬스장에 가요.",
            "좋아요."
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
}
