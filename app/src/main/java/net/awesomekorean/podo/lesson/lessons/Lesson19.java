package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson19 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_19";
    private String lessonTitle = "others";
    private String lessonSubTitle = "고맙습니다";
    private LessonItem specialLesson = new S_Lesson01();

    private String[] wordFront = {"안녕히 주무세요", "고맙습니다", "죄송합니다", "잘 먹겠습니다", "잘 먹었습니다"};

    private String[] wordBack = {"good night", "thank you", "I'm sorry", "thank you (before having a meal)", "thank you for the meal"};

    private String[] wordPronunciation = {"-", "[고맙씀니다]", "[죄송함니다]", "[잘 먹겓씀니다]", "[잘 머걷씀니다]"};

    private String[] sentenceFront = {
            "안녕히 주무세요.",
            "고맙습니다 / 감사합니다",
            "죄송합니다 / 미안합니다",
            "잘 먹겠습니다",
            "잘 먹었습니다"
    };

    private String[] sentenceBack = {
            "good night",
            "thank you",
            "I'm sorry",
            "Thank you for the meal",
            "I had a good meal."
    };

    private String[] sentenceExplain = {
            "To close friends : '잘 자~'",
            "You can use whatever you want. It is 100% the same. (Refer to '고맙습니다 vs. 감사합니다' in the Reading)\nTo close friends : '고마워~'",
            "It's good to use '죄송합니다' for someone older than me or with a higher social status.\nTo close friends : '미안~'",
            "It's not in English. It's a greeting to the person who provided the meal for me.\nTo close friends : '잘 먹을게~'",
            "To close friends : '잘 먹었어~'"
    };


    private String[] dialog = {
            "안녕히 주무세요.",
            "고맙습니다 / 감사합니다",
            "죄송합니다 / 미안합니다",
            "잘 먹겠습니다",
            "잘 먹었습니다"
    };

    private int[] peopleImage = {9,9};

    private int[] reviewId = {0,3,4};


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
