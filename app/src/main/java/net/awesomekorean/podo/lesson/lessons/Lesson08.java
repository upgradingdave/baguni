package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson08 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_08";
    private String lessonTitle = "can not";
    private String lessonSubTitle = "못~";

    private String[] wordFront = {"요즘", "너무", "바쁘다", "많이", "피곤하다", "아침", "일찍", "일어나다"};

    private String[] wordBack = {"recently / lately", "too / too much", "busy", "many / much", "tired", "morning / breakfast", "early", "wake up"};

    private String[] wordPronunciation = {"-", "-", "-", "[마니]", "-", "-", "-", "[이러나다]"};

    private String[] sentenceFront = {
            "바쁘다",
            "바빠요.",
            "너무 바빠요.",
            "요즘 너무 바빠요.",
            "피곤하다",
            "피곤해요.",
            "많이 피곤해요.",
            "그래서 많이 피곤해요.",
            "요즘 너무 바빠서 많이 피곤해요.",
            "일어나다",
            "일어나요.",
            "못 일어나요.",
            "일찍 못 일어나요.",
            "아침에 일찍 못 일어나요.",
            "먹다",
            "먹어요.",
            "못 먹어요.",
            "그래서 아침도 못 먹어요.",
            "아침에 일찍 못 일어나서 아침도 못 먹어요."
    };

    private String[] sentenceBack = {
            "busy",
            "busy.",
            "I'm very busy.",
            "I'm very busy recently.",
            "tired",
            "tired.",
            "I'm so tired.",
            "Therefore, I'm so tired.",
            "I'm so tired because I'm very busy recently.",
            "wake up",
            "wake up.",
            "I can't wake up.",
            "I can't wake up early.",
            "I can't wake up early in the morning.",
            "eat",
            "eat.",
            "I can't eat.",
            "So I can't have breakfast as well.",
            "I can't have breakfast as well because I can't wake up early in the morning."
    };

    private String[] sentenceExplain = {
            "-",
            "conjugate '아요/어요'\n: '바쁘다' -> '바쁘' + '아요' = '바쁘아요' -> '바빠요'",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "Use '아서/어서' to combine two sentences together using '그래서'.\nconjugate '아요/어요'\n: '바쁘다' -> '바쁘' + '아요' = '바쁘아요' -> '바빠요'\nconjugate '아서/어서'\n: '바빠요' -> '바빠' + '아서' = '바빠아서' -> '바빠서'",
            "-",
            "conjugate '아요/어요'\n: '일어나다' -> '일어나' + '아요' = '일어나아요' -> '일어나요'",
            "안'(= ~지 않아요) : do not\n'못' (= ~지 못해요) : can not\n\n'못 일어나요' -> [몬 이러나요]\n\n'일어나다' -> '일어나' + '지 못해요' = '일어나지 못해요'",
            "-",
            "시간 + '에'\n(See the Lesson 4 - 'confusing expression : '에 vs. 에서')",
            "-",
            "conjugate '아요/어요'\n: '먹다' -> '먹' + '어요' = '먹어요'",
            "-",
            "We can use '도' to mean 'also'.\n'아침/점심/저녁' means 'morning / noon / night' respectively.\nAlso, they have the meaning of 'breakfast / lunch / dinner.'",
            "conjugate '아서/어서'\n: '일어나요' -> '일어나' + '아서' = '일어나서'"
    };

    private String[] dialog = {
            "요즘 너무 바빠요.",
            "그래서 많이 피곤해요.",
            "아침에 일찍 못 일어나요.",
            "그래서 아침도 못 먹어요."
    };

    private int[] peopleImage = {4,4};

    private int[] reviewId = {3,7,13,17};


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
