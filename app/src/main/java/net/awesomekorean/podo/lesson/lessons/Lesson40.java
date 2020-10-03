package net.awesomekorean.podo.lesson.lessons;

import java.io.Serializable;

public class Lesson40 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_40";
    private String lessonTitle = "to do";
    private String lessonSubTitle = "~(으)려고";

    private String[] wordFront = {"월 / 달", "똑똑하다", "기억하다", "수업"};

    private String[] wordBack = {"month", "smart", "remember", "lesson"};

    private String[] wordPronunciation = {"-", "[똑또카다]", "[기어카다]", "-"};

    private String[] sentenceFront = {
            "언제부터 배웠어요?",
            "한국어를 언제부터 배웠어요?",
            "3개월 배웠어요.",
            "3개월 정도 배웠어요.",
            "3개월 밖에 안 배웠어요?",
            "그런데 이렇게 잘해요?",
            "똑똑한 것 같아요.",
            "정말 똑똑한 것 같아요.",
            "수업 시간에 배운 것",
            "수업 시간에 배운 것을 기억하려고 ",
            "수업 시간에 배운 것을 기억하려고 매일 공부해요."
    };

    private String[] sentenceBack = {
            "From when did you learn?",
            "From when did you learn Korean?",
            "I learned for 3 months.",
            "I learned about 3 months.",
            "Have you only learned 3 months?",
            "But how do you so well like this?",
            "I think you are smart.",
            "I think you are really smart.",
            "What I've learned in the lesson",
            "To remember what I've learned in the lesson",
            "I study every day to remember what I've learned in the lesson."
    };

    private String[] sentenceExplain = {
            "-",
            "-",
            "월 : comes from Chinese character\n달 : native Korean\n\nThere are 2 ways counting months.\n3개월 [삼개월]\n3달 [세달]\n\nDon't be confused that 3월[삼월] is a March.",
            "If you write '정도' after the word indicating quantity, it means 'approximately'.",
            "When you want to say very small or little, you can use '~밖에', and there is always a negative form after that.\n\nex)\n'~밖에 안~'\n'~밖에 못~'\n'~밖에 없다'",
            "You can use '이렇게' to express a big surprise.",
            "-",
            "-",
            "배우다' -> '배우' + 'ㄴ 것' = '배운 것'",
            "When expressing the intention or plan of the person speaking, we use '~(으)려고'.\n\n'기억하다' -> '기억하' + '려고' = '기억하려고'",
            "-"
    };

    private String[] dialog = {
            "한국어를 언제부터 배웠어요?",
            "3개월 정도 배웠어요.",
            "와! 3개월 밖에 안 배웠어요?\n그런데 이렇게 잘해요?\n정말 똑똑한 것 같아요.",
            "아니에요.\n수업 시간에 배운 것을\n기억하려고 매일 공부해요."
    };

    private int[] peopleImage = {3,4};

    private int[] reviewId = {1,3,4,7,10};


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
