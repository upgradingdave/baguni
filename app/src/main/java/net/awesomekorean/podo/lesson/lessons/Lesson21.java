package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson21 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_21";
    private String lessonTitle = "assisting";
    private String lessonSubTitle = "~아/어 줄게요";
    private LessonItem specialLesson = new S_Lesson07();

    private String[] wordFront = {"글", "주다", "틀리다", "아주", "정말요?"};

    private String[] wordBack = {"article", "give", "wrong", "very", "really?"};

    private String[] wordPronunciation = {"-", "-", "-", "-", "-"};

    private String[] sentenceFront = {
            "썼어요",
            "글을 썼어요.",
            "한국어로 글을 썼어요.",
            "제가 한국어로 글을 썼어요.",
            "봐주세요.",
            "한 번 봐주세요.",
            "주세요.",
            "제가 봐줄게요.",
            "틀렸어요.",
            "음... 조금 틀렸어요.",
            "하지만 아주 잘했어요.",
            "정말요? 고마워요."
    };

    private String[] sentenceBack = {
            "I wrote it.",
            "I wrote an article.",
            "I wrote an article in Korean.",
            "I wrote an article in Korean.",
            "please take a look.",
            "please take a look.",
            "give me.",
            "I'll take a look.",
            "It's wrong.",
            "um... a little wrong.",
            "but very good.",
            "really? thank you."
    };

    private String[] sentenceExplain = {
            "쓰다' -> '썼어요' (past tense)",
            "-",
            "N(으)로' is used when expressing means of transport, tools, and materials used to make something.",
            "-",
            "보다' -> '봐 주세요' (request)",
            "-",
            "-",
            "When you are trying to do something for somebody, use the 'V-아/어 줄게요' form.\n\nconjugate 'V-아/어 줄게요'\n'보다' -> '봐요'\n'봐요' -> '봐' + '줄게요' = '봐 줄게요'",
            "틀리다' -> '틀렸어요' (past tense)",
            "-",
            "-",
            "-"
    };

    private String[] dialog = {
            "제가 한국어로 글을 썼어요. \n 한 번 봐주세요.",
            "주세요. 제가 봐줄게요. \n 음... 조금 틀렸어요. \n 하지만 아주 잘했어요.",
            "정말요? 고마워요."
    };

    private int[] peopleImage = {1,2};

    private int[] reviewId = {3,5,7,8,10};


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
