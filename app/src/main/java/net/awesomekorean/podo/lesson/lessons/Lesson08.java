package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson08 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_08";
    private String lessonTitle = "can not";
    private String lessonSubTitle = "못~";
    private int lessonImage = R.drawable.negative2;

    final static String[] wordFront = {"요즘", "너무", "바쁘다", "많이", "피곤하다", "아침", "일찍", "일어나다"};

    final static String[] wordBack = {"recently", "too much", "busy", "many / much", "tired", "morning / breakfast", "early", "wake up"};

    final static String[] wordPronunciation = {"-", "-", "-", "[마니]", "-", "-", "-", "[이러나다]"};

    final static String[] sentenceFront = {
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

    final static String[] sentenceBack = {
            "busy",
            "I'm busy.",
            "I'm very busy.",
            "I'm very busy recently.",
            "tired",
            "I'm tired.",
            "I'm so tired.",
            "Therefore, I'm so tired.",
            "I'm so tired because I'm very busy recently.",
            "wake up",
            "wake up.",
            "I can't wake up.",
            "I can't wake up early.",
            "I can't wake up early in the morning.",
            "to eat",
            "I eat.",
            "I can't eat.",
            "So I can't have breakfast as well.",
            "I can't have breakfast as well because I can't wake up early in the morning."
    };

    final static String[] sentenceExplain = {
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
            "안' : do not\n'못' : can not\n\n'못 일어나요 ' -> [몬 이러나요]",
            "-",
            "시간 + '에'\n(See the special lesson '에 vs. 에서')",
            "-",
            "conjugate '아요/어요'\n: '먹다' -> '먹' + '어요' = '먹어요'",
            "-",
            "We can use '도' to mean 'also'.\n'아침/점심/저녁' means 'morning / noon / night' respectively.\nAlso, they have the meaning of 'breakfast / lunch / dinner.'",
            "conjugate '아서/어서'\n: '일어나요' -> '일어나' + '아서' = '일어나서'"
    };

    final static String[] dialog = {
            "요즘 너무 바빠요.",
            "그래서 많이 피곤해요.",
            "아침에 일찍 못 일어나요.",
            "그래서 아침도 못 먹어요."
    };

    final static int[] peopleImage = {4,4};


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


    // 레슨어뎁터 아이템

    @Override
    public String getLessonTitle() {
        return lessonTitle;
    }

    @Override
    public int getLessonImage() {
        return lessonImage;
    }
}
