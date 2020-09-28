package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson32 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_32";
    private String lessonTitle = "do";
    private String lessonSubTitle = "~(으)세요";
    private LessonItem specialLesson = new S_Lesson16();

    private String[] wordFront = {"화장실", "가게", "안", "밖", "왼쪽", "오른쪽"};

    private String[] wordBack = {"restroom", "shop", "inside", "outside", "left side", "right side"};

    private String[] wordPronunciation = {"-", "-", "-", "[박]", "-", "-"};

    private String[] sentenceFront = {
            "어디예요?",
            "화장실이 어디예요?",
            "밖으로 나가요.",
            "가게 밖으로 나가요.",
            "가게 밖으로 나가서",
            "가세요.",
            "오른쪽으로 가세요.",
            "가게 밖으로 나가서 오른쪽으로 가세요.",
            "고맙습니다."
    };

    private String[] sentenceBack = {
            "where is it?",
            "Where is the restroom?",
            "Go outside.",
            "Go outside the store.",
            "Go outside the store and",
            "go.",
            "go to the right side.",
            "Go out of the store and go right.",
            "Thank you."
    };

    private String[] sentenceExplain = {
            "-",
            "It is a necessary sentence when you go on a trip to other country. Should remember.",
            "When you say to the direction of movement, use '(으)로' form.\n\n(Refer to Lesson 16 - more expression : '~(으)로')",
            "-",
            "(Refer to Lesson 6 - reason : '아/어서')",
            "It's an expression you can use when you politely tell someone to do something.\n\n'가다' -> '가' + '세요' = '가세요'",
            "'오른쪽' + '으로'",
            "-",
            "-"
    };

    private String[] dialog = {
            "화장실이 어디예요?",
            "가게 밖으로 나가서 \n오른쪽으로 가세요.",
            "고맙습니다."
    };

    private int[] peopleImage = {11,12};

    private int[] reviewId = {1,7};


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
