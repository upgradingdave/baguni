package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class Lesson19 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_19";
    private String lessonTitle = "others";
    private String lessonSubTitle = "고맙습니다";
    private int lessonImage = R.drawable.thankyou;

    final static String[] wordFront = {"안녕히 주무세요", "고맙습니다", "죄송합니다", "잘 먹겠습니다", "잘 먹었습니다"};

    final static String[] wordBack = {"good night", "thank you", "I'm sorry", "thank you (before having a meal)", "thank you for the meal"};

    final static String[] wordPronunciation = {"-", "[고맙씀니다]", "[죄송함니다]", "[잘 먹겓씀니다]", "[잘 머걷씀니다]"};

    final static String[] sentenceFront = {
            "안녕히 주무세요.",
            "고맙습니다 / 감사합니다",
            "죄송합니다 / 미안합니다",
            "잘 먹겠습니다",
            "잘 먹었습니다"
    };

    final static String[] sentenceBack = {
            "good night",
            "thank you",
            "I'm sorry",
            "thank you (before having a meal)",
            "thank you for the meal."
    };

    final static String[] sentenceExplain = {
            "To close friends : '잘 자~'",
            "You can use whatever you want. It is 100% the same. (Refer to '고맙습니다 vs. 감사합니다' in the Reading)\nTo close friends : '고마워~'",
            "It's good to use '죄송합니다' for someone older than me or with a higher social status.\nTo close friends : '미안~'",
            "It's not in English. It's a greeting to the person who provided the meal for me.\nTo close friends : '잘 먹을게~'",
            "To close friends : '잘 먹었어~'"
    };


    final static String[] dialog = {
            "안녕히 주무세요.",
            "고맙습니다 / 감사합니다",
            "죄송합니다 / 미안합니다",
            "잘 먹겠습니다",
            "잘 먹었습니다"
    };

    final static int[] peopleImage = {9,10};


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
