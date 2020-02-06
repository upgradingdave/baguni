package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.Lesson;
import net.awesomekorean.podo.lesson.LessonItem;

public class Lesson08 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_08";
    private int title = R.string.L_08_TITLE;
    private String subTitle = "일찍 못 일어나요";
    private int lessonImage = R.drawable.negative2;

    final static String[] wordFront = {"요즘", "너무", "바쁘다", "많이", "피곤하다", "아침", "일찍", "일어나다"};
    final static String[] wordPronunciation = {"-", "-", "-", "[마니]", "-", "-", "-", "[이러나다]"};
    final static String[] wordSynonyms = {"최근", "아주", "-", "매우", "-", "-", "빨리", "-"};
    final static String[] wordAntonyms = {"-", "-", "-", "조금", "", "저녁", "늦게", "자다"};

    final static String[] sentenceFront = {
            "바쁘다",
            "바빠요.",
            "너무 바빠요.",
            "요즘 너무 바빠요.",
            "피곤하다",
            "피곤해요.",
            "많이 피곤해요.",
            "그래서 많이 피곤해요.",
            "요즘 너무 바빠서 많이 피곤해요.",
            "일어나다",
            "일어나요.",
            "못 일어나요.",
            "일찍 못 일어나요.",
            "아침에 일찍 못 일어나요.",
            "먹다",
            "먹어요.",
            "못 먹어요.",
            "그래서 아침도 못 먹어요.",
            "아침에 일찍 못 일어나서 아침도 못 먹어요."
    };

    final static String[] sentenceClause = {
            "요즘 너무 바빠요.",
            "그래서 많이 피곤해요.",
            "아침에 일찍 못 일어나요.",
            "그래서 아침도 못 먹어요."
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
