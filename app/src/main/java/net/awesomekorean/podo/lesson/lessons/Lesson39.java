package net.awesomekorean.podo.lesson.lessons;

import java.io.Serializable;

public class Lesson39 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_39";
    private String lessonTitle = "go to";
    private String lessonSubTitle = "~(으)러 가다";

    private String[] wordFront = {"책", "서점", "학생", "만나다", "근처"};

    private String[] wordBack = {"book", "bookstore", "student", "meet", "nearby"};

    private String[] wordPronunciation = {"-", "-", "[학쌩]", "-", "-"};

    private String[] sentenceFront = {
            "어디에 가요?",
            "서점에 가요.",
            "한국어 책을 사러 서점에 가요.",
            "저도 같이 가요.",
            "저는 서점 근처에 가고 있어요.",
            "저는 학생을 만나러 서점 근처에 가고 있어요."
    };

    private String[] sentenceBack = {
            "Where are you going?",
            "I'm going to the bookstore.",
            "I'm going to the bookstore to buy Korean books.",
            "Let's go together.",
            "I'm going near the bookstore.",
            "I am going near the bookstore to meet my student."
    };

    private String[] sentenceExplain = {
            "-",
            "-",
            "In a situation where you are moving to some place to do something, use the '~(으)러 가다/오다' form.\n'사다' -> '사' + '러 가다' = '사러 가다'",
            "We can use '도' to mean 'also'.",
            "-",
            "만나다' -> '만나' + '러 가다' = '만나러 가다'\n'만나러 가다' -> '만나러 가' + '고 있다' = '만나러 가고 있다'"
    };

    private String[] dialog = {
            "어디에 가요?",
            "한국어 책을 사러 서점에 가요.",
            "저도 같이 가요.\n저는 학생을 만나러 서점 근처에 가고 있어요."
    };

    private int[] peopleImage = {1,2};

    private int[] reviewId = {0,2,3,5};


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
}
