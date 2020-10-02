package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson31 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_31";
    private String lessonTitle = "guess (future)";
    private String lessonSubTitle = "~(으)ㄹ 것 같다";
    private LessonItem specialLesson = new S_Lesson14();

    private String[] wordFront = {"역", "방금", "기차", "내리다", "도착하다", "늦다"};

    private String[] wordBack = {"station", "a minute ago", "train", "get off", "arrive", "late"};

    private String[] wordPronunciation = {"-", "-", "-", "-", "[도차카다]", "[늗따]"};

    private String[] sentenceFront = {
            "지금 어디예요?",
            "서울역이에요.",
            "내렸어요.",
            "기차에서 내렸어요.",
            "방금 기차에서 내렸어요.",
            "언제 도착해요?",
            "도착하다",
            "도착할 것 같아요.",
            "10분 후에 도착할 것 같아요.",
            "미안해요.",
            "저는 택시 탔어요.",
            "저는 방금 택시 탔어요.",
            "늦다",
            "늦을 것 같아요.",
            "조금 늦을 것 같아요."
    };

    private String[] sentenceBack = {
            "Where are you now?",
            "I'm at Seoul Station.",
            "I got off.",
            "I got off from the train.",
            "I just got off from the train.",
            "When do you arrive?",
            "Arrive",
            "I think I will arrive.",
            "I think I will arrive in 10 minutes.",
            "Sorry.",
            "I took a taxi.",
            "I just took a taxi.",
            "late",
            "I think I will be late.",
            "I think I will be a little late."
    };

    private String[] sentenceExplain = {
            "-",
            "-",
            "내리다' -> '내리' + '어요' = '내리어요' -> '내려요'\n'내려요' -> '내려' + 'ㅆ어요' = '내렸어요'",
            "-",
            "In real life, we also can use '아까' more casually.\n\n'아까 기차에서 내렸어요.'",
            "-",
            "-",
            "When we say our guess about the future, we use '~(으)ㄹ 것 같다'.\n\n'도착하다' -> '도착하' + 'ㄹ 것 같아요' = '도착할 것 같아요'\n\n(Refer to Lesson 15 - more expression : '~것 같다')",
            "-",
            "-",
            "타요' -> '타' + 'ㅆ어요' = '탔어요'",
            "= '저는 아까 택시 탔어요'",
            "-",
            "늦다' -> '늦' + '을 것 같아요' = '늦을 것 같아요'",
            "-"
    };

    private String[] dialog = {
            "지금 어디예요?",
            "서울역이에요.\n방금 기차에서 내렸어요.",
            "언제 도착해요?",
            "10분 후에 도착할 것 같아요.",
            "미안해요.\n저는 방금 택시 탔어요.\n조금 늦을 것 같아요."
    };

    private int[] peopleImage = {9,10};

    private int[] reviewId = {0,1,4,5,8,11,14};


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
