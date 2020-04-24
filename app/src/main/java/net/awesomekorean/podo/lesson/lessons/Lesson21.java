package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class Lesson21 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_21";
    private String lessonTitle = "assisting";
    private String lessonSubTitle = "~아/어 줄게요";
    private int lessonImage = R.drawable.thankyou;

    final static String[] wordFront = {"글", "주다", "틀리다", "아주", "정말요?"};
    final static String[] wordPronunciation = {"-", "-", "-", "-", "-"};

    final static String[] sentenceFront = {
            "썼어요",
            "글을 썼어요.",
            "한국어로 글을 썼어요.",
            "제가 한국어로 글을 썼어요.",
            "봐주세요.",
            "한 번 봐주세요.",
            "주세요.",
            "제가 봐줄게요.",
            "틀렸어요.",
            "음... 조금 틀렸어요.",
            "하지만 아주 잘했어요.",
            "정말요? 고마워요."
    };

    final static String[] dialog = {
            "제가 한국어로 글을 썼어요. \n 한 번 봐주세요.",
            "주세요. 제가 봐줄게요. \n 음... 조금 틀렸어요. \n 하지만 아주 잘했어요.",
            "정말요? 고마워요."
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
