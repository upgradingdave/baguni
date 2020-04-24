package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class Lesson18 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_18";
    private String lessonTitle = "request";
    private String lessonSubTitle = "~아/어 주세요";
    private int lessonImage = R.drawable.request;

    final static String[] wordFront = {"주", "호텔", "예약", "도와주다", "처음", "아마", "기다리다"};
    final static String[] wordPronunciation = {"-", "-", "-", "-", "-", "-", "-"};

    final static String[] sentenceFront = {
            "저 부산 여행 가요.",
            "다음 주에 저 부산 여행 가요.",
            "도와주세요.",
            "좀 도와주세요.",
            "호텔 예약 좀 도와주세요.",
            "정말요?",
            "처음 가요?",
            "부산에 처음 가요?",
            "네.",
            "재미있을 거예요.",
            "아마 재미있을 거예요.",
            "지금 예약할게요.",
            "기다려주세요.",
            "조금 기다려주세요."
    };

    final static String[] dialog = {
            "다음 주에 저 부산 여행 가요.\n호텔 예약 좀 도와주세요.",
            "부산에 처음 가요?",
            "네.",
            "아마 재미있을 거예요.\n지금 예약할게요.\n조금 기다려주세요."
    };

    final static int[] peopleImage = {8,7};


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
