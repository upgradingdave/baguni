package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class Lesson18 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_18";
    private String title = "Request";
    private String subTitle = "호텔 예약 좀 도와주세요";
    private int lessonImage = R.drawable.request;

    final static String[] wordFront = {"주", "호텔", "예약", "도와주다", "처음", "아마", "기다리다"};
    final static String[] wordPronunciation = {"-", "-", "-", "-", "-", "-", "-"};
    final static String[] wordSynonyms = {"-", "숙소", "예매", "돕다", "-", "아마도", "-"};
    final static String[] wordAntonyms = {"-", "-", "-", "-", "마지막", "-", "-"};

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
    public String getTitle() {
        return title;
    }

    @Override
    public String getSubTitle() {
        return subTitle;
    }

    @Override
    public int getLessonImage() {
        return lessonImage;
    }
}
