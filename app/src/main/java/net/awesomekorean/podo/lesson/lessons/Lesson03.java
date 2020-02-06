package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.Lesson;
import net.awesomekorean.podo.lesson.LessonItem;

public class Lesson03 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_03";
    private int title = R.string.L_03_TITLE;
    private String subTitle = "한국 친구 있어요?";
    private int lessonImage = R.drawable.posession;

    final static String[] wordFront = {"있다", "친구", "얘기하다", "어디", "한국어"};
    final static String[] wordPronunciation = {"[읻따]", "-", "-", "-", "[한구거]"};
    final static String[] wordSynonyms = {"-", "-", "이야기하다", "-", "-"};
    final static String[] wordAntonyms = {"없다", "-", "-", "-", "-"};

    final static String[] sentenceFront = {
            "있다",
            "있어요?",
            "친구 있어요?",
            "한국 친구 있어요?",
            "네, 있어요",
            "네, 한 명 있어요.",
            "얘기하다",
            "얘기해요?",
            "어디에서 얘기해요?",
            "카카오톡",
            "카카오톡에서",
            "카카오톡에서 얘기해요"
    };

    final static String[] sentenceClause = {
            "한국 친구 있어요?",
            "네, 한 명 있어요.",
            "어디에서 얘기해요?",
            "카카오톡에서 얘기해요."
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
