package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson02 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_02";
    private String lessonTitle = "shopping";
    private String lessonSubTitle = "얼마예요?";
    private int lessonImage = R.drawable.shopping;

    final static String[] wordFront = {"이거", "저거", "얼마", "세일", "그래서"};

    final static String[] wordBack = {"this one", "that one", "how much", "discount", "so / therefore"};

    final static String[] wordPronunciation = {"-", "-", "-", "[쎄일]", "-"};

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

    final static String[] sentenceBack = {
            "how much",
            "How much is it?",
            "How much is this?",
            "It’s 10,000 won.",
            "that one",
            "What about that one?",
            "sale",
            "It’s on sale.",
            "It’s 50% off.",
            "That’s 50% off.",
            "It’s 5,000 won.",
            "So, it’s 5,000 won."
    };

    final static String[] sentenceExplain = {
            "-",
            "-",
            "‘이거’ is used a lot in the conversational language.\nAnd you should say ‘이것‘ in the formal situation.",
            "-",
            "‘저거’ is used a lot in the conversational language.\nAnd you should say ‘저것‘ in the formal situation.",
            "You can say like this for short of '저거는 얼마예요?'",
            "It means selling something in English.\nHowever, it is often used as a 'discount' in Korean.",
            "-",
            "-",
            "-",
            "-",
            "-"
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
