package net.awesomekorean.podo.lesson.lessons;

import com.google.rpc.Help;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson18 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_18";
    private String lessonTitle = "request";
    private String lessonSubTitle = "~아/어 주세요";
    private int lessonImage = R.drawable.request;

    final static String[] wordFront = {"주", "호텔", "예약", "돕다", "처음", "아마", "기다리다"};

    final static String[] wordBack = {"week", "hotel", "reservation", "to help", "first time", "maybe", "to wait"};

    final static String[] wordPronunciation = {"-", "-", "-", "-", "-", "-", "-"};

    final static String[] sentenceFront = {
            "저 부산 여행 가요.",
            "다음 주에 저 부산 여행 가요.",
            "도와주세요.",
            "좀 도와주세요.",
            "호텔 예약 좀 도와주세요.",
            "처음 가요?",
            "부산에 처음 가요?",
            "네.",
            "재미있을 거예요.",
            "아마 재미있을 거예요.",
            "지금 예약할게요.",
            "기다려주세요.",
            "조금 기다려주세요."
    };

    final static String[] sentenceBack = {
            "I am going to travel to Busan.",
            "I'll go to travel to Busan next week.",
            "Help me.",
            "Please help me.",
            "Please help me book a hotel.",
            "Is it the first time?",
            "Is it your first time to go to Busan?",
            "Yes.",
            "It'll be interesting.",
            "Maybe it'll be interesting.",
            "I'll book a hotel now.",
            "Please wait.",
            "Please wait a minute.",
    };

    final static String[] sentenceExplain = {
            "Use '저' instead of '나' when you speak honorific.",
            "이번 주 : this week\n저번 주: last week\n다음 주: next week",
            "Use the 'V-아/어 주세요' form to ask something.\n\nconjugate 'V-아/어 주세요'\n'돕다' -> '돕아요' -> '도와요' (irregular)\n'도와요' -> '도와' + '주세요' = '도와주세요'",
            "Using '좀' means 'a little' when asking for help in more polite expressions.",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "In English, there are many forms like 'reservation', 'booking' and 'appointment' but they are all '예약' in Korean.\n\nex)\n호텔예약 : booking\n식당예약 : reservation\n병원예약 : appointment",
            "conjugate 'V-아/어 주세요'\n'기다리다' -> '기다리' + '어요' = '기다리어요' -> '기다려요'\n'기다려요' -> '기다려' + '주세요' = '기다려 주세요'",
            "-"
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
