package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import javax.annotation.meta.When;

public class Lesson07 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_07";
    private String lessonTitle = "do not";
    private String lessonSubTitle = "안~";
    private int lessonImage = R.drawable.negative1;

    final static String[] wordFront = {"날씨", "어때요?", "여기", "겨울", "조금", "춥다", "눈", "오다", "별로"};

    final static String[] wordBack = {"weather", "How is it?", "here", "winter", "a bit", "cold", "snow", "to come", "not that much"};

    final static String[] wordPronunciation = {"-", "-", "-", "-", "-", "[춥따]", "-", "-", "-"};

    final static String[] sentenceFront = {
            "어때요?",
            "날씨가 어때요?",
            "한국 날씨가 어때요?",
            "겨울",
            "겨울이에요.",
            "여기는 겨울이에요.",
            "춥다",
            "추워요.",
            "조금 추워요.",
            "오다",
            "와요?",
            "눈이 와요?.",
            "와요.",
            "안 와요.",
            "눈은 안 와요.",
            "눈은 별로 안 와요."
    };

    final static String[] sentenceBack = {
            "How is it?",
            "How's the weather?",
            "How's the weather in Korea?",
            "winter",
            "It's winter.",
            "It's winter here.",
            "cold",
            "It's cold.",
            "It's a bit cold.",
            "to come",
            "Is it comming?",
            "Is it snowing?",
            "It's comming.",
            "It's not comming.",
            "It's not snowing.",
            "It's not snowing a lot."
    };

    final static String[] sentenceExplain = {
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "conjugate '아요/어요'\n: '춥다' -> '춥' + '어요' = '춥어요' -> '추워요' (exception)\n\nMost exceptions are for ease of pronunciation.\n'추워요' is more comfortable and natural to pronounce than '춥어요'.",
            "-",
            "-",
            "conjugate '아요/어요'\n: '오다' -> '오' + '아요' = '오아요' -> '와요'",
            "-",
            "-",
            "You can make negative forms by using '안' before a verb or adjective.\nWhen a verb composed of 'noun + 하다' is made into a negative sentence, it should be written as 'noun + 안 + 하다'.\n\nex) '얘기하다' -> '얘기 안 하다'\n\nNegative sentences using '안' are direct expressions.\nIf you want to express it indirectly, use '~지 않다'.\n\nex) '얘기하다' -> '얘기하지 않다'",
            "-",
            "-"
    };

    final static String[] dialog = {
            "한국 날씨가 어때요?",
            "여기는 겨울이에요.",
            "조금 추워요.",
            "눈이 와요?",
            "눈은 별로 안 와요."
    };

    final static int[] peopleImage = {1,2};


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
