package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson35 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_35";
    private String lessonTitle = "if want to";
    private String lessonSubTitle = "~(으)려면";
    private int lessonImage = R.drawable.l_35_word_1;

    private String[] wordFront = {"타다", "선물", "화장품", "생일"};

    private String[] wordBack = {"to ride", "gift", "cosmetics", "birthday"};

    private String[] wordPronunciation = {"-", "-", "-", "-"};

    private String[] sentenceFront = {
            "명동에 가다.",
            "명동에 가려면",
            "명동에 가려면 어떻게 해요?",
            "지하철 타다.",
            "지하철 타고 가다.",
            "지하철 타고 갈 수 있어요.",
            "명동에 왜 가요?",
            "내일 친구 생일이에요.",
            "화장품 선물 사고 싶어요."
    };

    private String[] sentenceBack = {
            "Go to Myeong-dong",
            "To go to Myeong-dong",
            "How do I get to Myeong-dong?",
            "Take the subway",
            "Go by subway",
            "You can take the subway.",
            "Why are you going to Myeong-dong?",
            "It's my friend's birthday tomorrow.",
            "I want to buy a cosmetic gift."
    };

    private String[] sentenceExplain = {
            "'명동' is a famous place for shopping in Seoul. In particular, there are many cosmetic shops.",
            "When you say the conditions for doing something you can use '(으)려면'.\n\n'가다' -> '가' + '려면' = '가려면'",
            "-",
            "-",
            "'타다' 그리고 '가다' = '타고 가다'",
            "'가다' -> '가' + 'ㄹ 수 있다' = '갈 수 있다'",
            "-",
            "-",
            "-"
    };

    private String[] dialog = {
            "명동에 가려면 어떻게 해요?",
            "지하철 타고 갈 수 있어요.\n명동에 왜 가요?",
            "내일 친구 생일이에요.\n화장품 선물 사고 싶어요."
    };

    private int[] peopleImage = {5,6};

    private int[] reviewId = {2,5,6,7,8};


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

    @Override
    public int[] getReviewId() {
        return reviewId;
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
