package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class Lesson15 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_15";
    private String lessonTitle = "ability";
    private String lessonSubTitle = "~을 수 있어요";
    private int lessonImage = R.drawable.ability;

    final static String[] wordFront = {"그런데", "아직", "자막", "이해하다", "괜찮다"};
    final static String[] wordPronunciation = {"-", "-", "-", "-", "[괜찬타]"};
    final static String[] wordSynonyms = {"하지만", "-", "-", "알다", "-"};
    final static String[] wordAntonyms = {"그리고", "이미, 벌써", "-", "-", "-"};

    final static String[] sentenceFront = {
            "재미있어요.",
            "이 드라마 재미있어요",
            "그런데",
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
    public String[] getWordSynonyms() {
        return wordSynonyms;
    }

    @Override
    public String[] getWordAntonyms() {
        return wordAntonyms;
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
