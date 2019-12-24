package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.Lesson;
import net.awesomekorean.podo.lesson.LessonItem;

public class Lesson2 implements Lesson, LessonItem {

    private int title = R.string.L2_TITLE;
    private String subTitle = "얼마예요?";
    private int lessonImage = R.drawable.hangul;
    private Boolean isSpecial = false;
    private Boolean isLock = false;
    private Boolean isCompleted = false;

    final static String[] wordFront = {"이거", "저거", "얼마", "세일", "그래서"};
    final static String[] wordPronunciation = {"-", "-", "-", "-", "[쎄일]", "-"};
    final static String[] wordSynonyms = {"이것", "저것", "-", "할인", "그러므로, 따라서"};
    final static String[] wordAntonyms = {"-", "-", "-", "-", "그러나"};
    final static String[] wordApplication = {"이거 뭐예요?", "저거 뭐예요?", "이거 얼마예요?", "지금 세일해요?", "떡복이 좋아요. 그래서 많이 먹어요."};

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

    final static String[] sentenceClause = {
            "이거 얼마예요?",
            "10,000(만)원이에요.",
            "저거는요?",
            "저거는 50% 세일이에요.\n 그래서 5,000(오천)원이에요."
    };

    final static int[] peopleImage = {5,6};



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
}
