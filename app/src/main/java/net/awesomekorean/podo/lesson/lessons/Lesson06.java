package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson06 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_06";
    private String lessonTitle = "progressive";
    private String lessonSubTitle = "~고 있어요";
    private LessonItem specialLesson = new S_Lesson06();

    private String[] wordFront = {"지금", "이따", "같이", "헬스장"};

    private String[] wordBack = {"now", "later (short time)", "together", "gym (weight training)"};

    private String[] wordPronunciation = {"-", "-", "[가치]", "[헬쓰장]"};

    private String[] sentenceFront = {
            "어디",
            "어디예요?",
            "지금 어디예요?",
            "가다",
            "가고 있어요.",
            "집에 가고 있어요.",
            "가다",
            "가요",
            "헬스장에 가요.",
            "같이 헬스장에 가요.",
            "이따 같이 헬스장에 가요.",
            "좋아요."
    };

    private String[] sentenceBack = {
            "where",
            "Where are you?",
            "Where are you now?",
            "to go",
            "I'm going.",
            "I'm going home.",
            "to go",
            "I go.",
            "I go to the gym.",
            "Let's go to the gym together.",
            "Let's go the gym together later.",
            "Good."
    };

    private String[] sentenceExplain = {
            "-",
            "-",
            "-",
            "-",
            "conjugate '고 있다'\n: '가다' -> '가' + '고 있다' = '가고 있다'\nconjugate '아요/어요'\n: '가고 있다' -> '가고 있' + '어요' = '가고 있어요'",
            "If you use verbs related to movement ('가다', '오다', '들어가다' etc ...)\nwe add '에' after the noun that is the destination.\nWe can also omit \"\"에\"\" when speaking\n(See the Lesson 4 - 'confusing expression : '에 vs. 에서')",
            "-",
            "conjugate '아요/어요'\n: '가다' -> '가' + '아요' = '가아요' -> '가요'",
            "-",
            "You can also use '랑/이랑' that we learned in the previous lesson.\n'저' (a polite expression of 'me') + '랑' = '저랑 헬스장에 가요'\nOr you can also use '랑/이랑' with '같이' means together.\n'저랑 같이 헬스장에 가요'",
            "When you want to express a short time within a few hours, use '이따' or '이따가'.\nIt's for conversational speaking.",
            "-"
    };

    private String[] dialog = {
            "지금 어디예요?",
            "집에 가고 있어요.",
            "이따 같이 헬스장에 가요.",
            "좋아요."
    };

    private int[] peopleImage = {12,11};

    private int[] reviewId = {2,5,10,11};


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
