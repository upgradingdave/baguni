package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class Lesson19 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_19";
    private String lessonTitle = "others";
    private String lessonSubTitle = "고맙습니다";
    private int lessonImage = R.drawable.thankyou;

    final static String[] wordFront = {"안녕히 주무세요", "고맙습니다", "죄송합니다", "잘 먹겠습니다", "잘 먹었습니다"};
    final static String[] wordPronunciation = {"-", "[고맙씀니다]", "-", "[잘 먹겓씀니다]", "[잘 머걷씀니다]"};

    final static String[] sentenceFront = {
            "안녕히 주무세요.",
            "고맙습니다 / 감사합니다",
            "죄송합니다 / 미안합니다",
            "잘 먹겠습니다",
            "잘 먹었습니다"
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
