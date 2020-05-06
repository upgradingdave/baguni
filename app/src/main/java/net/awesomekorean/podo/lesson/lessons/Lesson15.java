package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson15 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_15";
    private String lessonTitle = "ability";
    private String lessonSubTitle = "~(으)ㄹ 수 있어요";
    private int lessonImage = R.drawable.ability;

    final static String[] wordFront = {"아직", "자막", "이해하다", "괜찮다"};

    final static String[] wordBack = {"yet", "subtitles", "understand", "all right, OK"};

    final static String[] wordPronunciation = {"-", "-", "-", "[괜찬타]"};

    final static String[] sentenceFront = {
            "재미있어요.",
            "이 드라마 재미있어요",
            "그런데",
            "없어요.",
            "자막이 없어요.",
            "아직 자막이 없어요.",
            "어때요?",
            "이해하다",
            "이해할 수 있어요?",
            "괜찮아요.",
            "조금 이해할 수 있어요.",
            "볼 거예요.",
            "두 번 볼 거예요"
    };

    final static String[] sentenceBack = {
            "It's interesting.",
            "This drama is interesting.",
            "However",
            "There isn't.",
            "There's no subtitles.",
            "There's no subtitles yet.",
            "How is it?",
            "understand",
            "Can you understand?",
            "That's OK.",
            "I can understand a little bit.",
            "I'll watch it.",
            "I'll watch it twice."
    };

    final static String[] sentenceExplain = {
            "-",
            "-",
            "same meaning with '하지만'",
            "-",
            "-",
            "-",
            "-",
            "-",
            "Use it when you have the ability to do something.\nOn the contrary, when you don't have an ability to do something, you can say 'V-(으)ㄹ 수 없다'.\nYou can also use '못' learned from 'negative2'.\n\nconjugate 'V-(으)ㄹ 수 있다/없다'\n: '이해하다' -> '이해하' + 'ㄹ 수 있다' = '이해할 수 있다'\n: '이해하다' ->' 이해하' + 'ㄹ 수 없다' = '이해할 수 없다'\n\nnegative '못'\n: '이해하다' -> '이해 못 하다'",
            "It can be used when it's not very good but not that bad.",
            "-",
            "Used for future tense.",
            "The unit noun representing the number of times is '번'."
    };

    final static String[] dialog = {
            "이 드라마 재미있어요.\n그런데 아직 자막이 없어요.\n어때요?\n이해할 수 있어요?",
            "괜찮아요.\n조금 이해할 수 있어요.\n두 번 볼 거예요."
    };

    final static int[] peopleImage = {4,3};


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
