package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

import javax.annotation.meta.When;

public class Lesson16 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_16";
    private String lessonTitle = "possibility";
    private String lessonSubTitle = "~(으)ㄹ 수 있어요";
    private LessonItem specialLesson = new S_Lesson10();

    private String[] wordFront = {"예전에", "언어교환", "배우다", "좋다", "비슷하다", "단어"};

    private String[] wordBack = {"in the past", "language exchange", "to learn", "good, nice", "similar", "vocabulary"};

    private String[] wordPronunciation = {"[예저네]", "[어너교환]", "-", "[조타]", "[비스타다]", "[다너]"};

    private String[] sentenceFront = {
            "할 수 있어요?.",
            "한국어 할 수 있어요?",
            "네, 저는 한국 사람이에요.",
            "저는 일본 사람이에요.",
            "배우고 있어요.",
            "한국어 배우고 있어요.",
            "요즘 한국어 배우고 있어요.",
            "할 수 있어요?",
            "언어교환 할 수 있어요",
            "좋아요.",
            "저도 일본어 배우고 있어요.",
            "어때요?",
            "한국어 공부 어때요?",
            "많이 있어요.",
            "비슷한 단어 많이 있어요.",
            "일본어랑 비슷한 단어 많이 있어요.",
            "한국어에 일본어랑 비슷한 단어 많이 있어요.",
            "쉬울 수 있어요.",
            "그래서 다른 언어보다 쉬울 수 있어요."
    };

    private String[] sentenceBack = {
            "Can you do it?",
            "Can you speak Korean?",
            "Yes, I'm a Korean.",
            "I'm a Japanese.",
            "I'm learning.",
            "I'm learning Korean.",
            "I'm learning Korean recently.",
            "Can you do it?",
            "Can you exchange language?",
            "Nice",
            "I'm learning Japanese too.",
            "How's it?",
            "How's learning Korean?",
            "There's a lot.",
            "There's a lot of similar words.",
            "There's a lot of similar words with Japanese.",
            "There's a lot of similar words with Japanese in Korean.",
            "It could be easy.",
            "Therefore, it could be easier than other languages."
    };

    private String[] sentenceExplain = {
            "We've learned in the last lesson that this means 'ability'.",
            "-",
            "-",
            "(See the Lesson 8 - 'Simple Chinese character - 人')",
            "progressive form\n: '배우다' -> '배우' + '고 있다' = '배우고 있다'",
            "-",
            "-",
            "-",
            "언어 : language\n교환 : exchange",
            "(See the Lesson 9 - 'Confusing expression - 좋아요 vs. 좋아해요')",
            "-",
            "-",
            "-",
            "-",
            "We can use 'A-(으)ㄴ' form when the adjective modifies a noun.\n'비슷하다' -> '비슷하' + 'ㄴ' = '비슷한'",
            "-",
            "-",
            "We've learned that we can use 'V-(으)ㄹ 수 있다' form when it means an ability to do something in the previous lesson.\nBut one more meaning can be used, which is the 'possibility'.\nWhen it means possibility, you can use adjectives as well as verbs.\n\nconjugate 'A/V-(으)ㄹ 수 있다'\n'쉽다' -> '쉽' + '을 수 있다' = '쉽을 수 있다' -> '쉬울 수 있다' (irregular)",
            "When comparing, use '~보다'."
    };

    private String[] dialog = {
            "한국어 할 수 있어요?",
            "네, 저는 한국 사람이에요.",
            "저는 일본 사람이에요.\n요즘 한국어 배우고 있어요.\n언어교환 할 수 있어요?",
            "좋아요.\n저도 일본어 배우고 있어요.",
            "한국어 공부 어때요?",
            "한국어에 일본어랑 비슷한 단어\n많이 있어요. 그래서 다른 언어보다\n쉬울 수 있어요."
    };

    private int[] peopleImage = {2,1};

    private int[] reviewId = {1,3,6,8,10,12,16,18};


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
    public LessonItem getSLesson() {
        return specialLesson;
    }
}
