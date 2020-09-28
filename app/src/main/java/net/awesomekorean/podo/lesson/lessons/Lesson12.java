package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

import java.io.Serializable;

public class Lesson12 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_12";
    private String lessonTitle = "but";
    private String lessonSubTitle = "~지만";
    private LessonItem specialLesson = new S_Lesson05();

    private String[] wordFront = {"어렵다", "말하다", "쓰다", "읽다", "쉽다"};

    private String[] wordBack = {"difficult", "to speak", "to write", "to read", "easy"};

    private String[] wordPronunciation = {"[어렵따]", "-", "-", "[익따]", "[쉽따]"};

    private String[] sentenceFront = {
            "어때요?",
            "한국어 공부 어때요?",
            "어렵다",
            "어려워요.",
            "한국어는 어려워요.",
            "재미있다",
            "재미있어요",
            "한국어는 어려워요. 하지만 재미있어요.",
            "한국어는 어렵지만 재미있어요.",
            "어려워요?",
            "뭐가 어려워요?",
            "뭐가 제일 어려워요?",
            "어려워요.",
            "말하기가 어려워요.",
            "말하기랑 쓰기가 어려워요.",
            "쉬워요.",
            "읽기는 쉬워요.",
            "말하기랑 쓰기가 어려워요. 하지만 읽기는 쉬워요.",
            "말하기랑 쓰기가 어렵지만 읽기는 쉬워요."
    };

    private String[] sentenceBack = {
            "How is it?",
            "How's learning Korean?",
            "difficult",
            "It's difficult.",
            "Korean is difficult.",
            "interesting",
            "It's interesting.",
            "Korean is difficult but interesting.",
            "Korean is difficult but interesting.",
            "Is it difficult?",
            "What is difficult?",
            "What is the most difficult?",
            "It's difficult.",
            "Speaking is difficult.",
            "Speaking and writing are difficult.",
            "It's easy.",
            "Reading is easy.",
            "Speaking and writing are difficult but reading is easy.",
            "Speaking and writing are difficult but reading is easy."
    };

    private String[] sentenceExplain = {
            "-",
            "-",
            "-",
            "어렵다' -> '어렵' + '어요' = '어렵어요' -> '어려워요' (exception)",
            "-",
            "-",
            "-",
            "-",
            "Use '~지만' to combine two sentences together using '하지만'.\n\nex) '어렵다' -> '어렵' + '지만' = '어렵지만'",
            "-",
            "-",
            "-",
            "-",
            "When you change a verb to a noun, you can use '~기' like '~ing' in English.\n\nex)\n'말하다' -> '말하기' (speak -> speaking)\n'쓰다' -> '쓰기' (write -> writing)\n'읽다'-> '읽기' (read -> reading)",
            "-",
            "-",
            "-",
            "-",
            "-"
    };

    private String[] dialog = {
            "한국어 공부 어때요?",
            "한국어는 어려워요. 하지만 재미있어요.",
            "뭐가 제일 어려워요?",
            "말하기랑 쓰기가 어려워요.\n하지만 읽기는 쉬워요."
    };

    private int[] peopleImage = {12,11};

    private int[] reviewId = {1,8,11,18};


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
