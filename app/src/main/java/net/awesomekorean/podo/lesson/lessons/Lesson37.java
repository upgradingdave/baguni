package net.awesomekorean.podo.lesson.lessons;

import java.io.Serializable;

public class Lesson37 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_37";
    private String lessonTitle = "shouldn't";
    private String lessonSubTitle = "~(으)면 안 돼요";

    private String[] wordFront = {"이사하다", "찾다", "작다", "학교", "멀다"};

    private String[] wordBack = {"move(house)", "find", "small", "school", "far"};

    private String[] wordPronunciation = {"-", "[찯따]", "[작따]", "-", "-"};

    private String[] sentenceFront = {
            "이사할 집",
            "이사할 집을 찾고 있어요.",
            "어떤 집",
            "어떤 집을 좋아해요?",
            "너무 작지 않았으면 좋겠어요.",
            "학교가 멀다.",
            "학교가 멀면 안 돼요."
    };

    private String[] sentenceBack = {
            "A house to move",
            "I'm looking for a house to move.",
            "Which house",
            "Which house do you like?",
            "I hope it isn't too small.",
            "School is far.",
            "The school shouldn't be far."
    };

    private String[] sentenceExplain = {
            "Use '(으)ㄹ' of future tense.\n'이사하다' -> '이사하' +'ㄹ' = '이사할' ",
            "찾다' -> '찾' + '고 있어요' = '찾고 있어요' (progressive)",
            "-",
            "-",
            "작지 않다' -> '작지 않' + '았으면 좋겠어요' = '작지 않았으면 좋겠어요'",
            "-",
            "When talking about the meaning of 'prohibited' or 'restricted', use the'~(으)면 안 돼요' form."
    };

    private String[] dialog = {
            "이사할 집을 찾고 있어요.",
            "어떤 집을 좋아해요?",
            "너무 작지 않았으면 좋겠어요.\n그리고 학교가 멀면 안 돼요."
    };

    private int[] peopleImage = {9,10};

    private int[] reviewId = {1,3,4,6};


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
