package net.awesomekorean.podo.lesson.lessons;

import java.io.Serializable;

public class Lesson38 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_38";
    private String lessonTitle = "don't have to";
    private String lessonSubTitle = "~지 않아도 돼요";

    private String[] wordFront = {"축하하다", "우리", "맛있다", "필요하다"};

    private String[] wordBack = {"celebrate", "we", "delicious", "need"};

    private String[] wordPronunciation = {"[추카하다]", "-", "[마싣따]", "[피료하다]"};

    private String[] sentenceFront = {
            "어제 이사했어요.",
            "어제 새 집으로 이사했어요.",
            "축하해요.",
            "맛있는 거 먹어요.",
            "오늘 우리 집에서 맛있는 거 먹어요.",
            "와인 필요해요?",
            "집에 있어요.",
            "사오지 않아도 돼요."
    };

    private String[] sentenceBack = {
            "I moved yesterday.",
            "I moved to a new house yesterday.",
            "Congratulations.",
            "Let's have something delicious.",
            "Today, let's have something delicious at my home.",
            "Do you need a wine?",
            "I have it at home.",
            "You don't have to buy it."
    };

    private String[] sentenceExplain = {
            "-",
            "-",
            "-",
            "맛있는 것' = '맛있는 거' (casual form)",
            "In fact '우리' means 'we', but Korean people use '우리' in front of the more attached words such as 'home', 'mom', 'dad', 'school', 'company', 'country', etc. and it means 'my~'.\n\nex)\n'우리 집', '우리 엄마', '우리 아빠', '우리 학교', '우리 회사', '우리 나라'",
            "-",
            "-",
            "When you don't really need to do something, use the '~지 않아도 돼요' form."
    };

    private String[] dialog = {
            "어제 새 집으로 이사했어요.",
            "아! 정말요? 축하해요.",
            "오늘 우리 집에서 맛있는 거 먹어요.",
            "좋아요! 와인 필요해요?",
            "아니요, 집에 있어요.\n사오지 않아도 돼요."
    };

    private int[] peopleImage = {11,12};

    private int[] reviewId = {1,4,5,7};


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
