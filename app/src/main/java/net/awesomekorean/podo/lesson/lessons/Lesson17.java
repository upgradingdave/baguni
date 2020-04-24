package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class Lesson17 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_17";
    private String lessonTitle = "want to";
    private String lessonSubTitle = "~고 싶어요";
    private int lessonImage = R.drawable.hope;

    final static String[] wordFront = {"휴가", "여행", "음식", "연습", "그동안", "공부", "열심히"};
    final static String[] wordPronunciation = {"-", "-", "-", "-", "-", "-", "-"};

    final static String[] sentenceFront = {
            "가고 싶어요.",
            "한국 여행 가고 싶어요.",
            "다음 휴가 때 한국 여행 가고 싶어요.",
            "하고 싶어요?",
            "뭐 하고 싶어요?",
            "한국에서 뭐 하고 싶어요?",
            "먹고 싶어요.",
            "한국 음식 먹고 싶어요.",
            "한국 음식 많이 먹고 싶어요.",
            "하고 싶어요.",
            "한국어 연습도 하고 싶어요.",
            "그리고 한국어 연습도 하고 싶어요.",
            "잘 할 수 있어요.",
            "잘 할 수 있을 거예요.",
            "한국어 공부 열심히 했으니까 잘 할 수 있을 거예요.",
            "좋아요. 그동안 한국어 공부 열심히 해서 잘 할 수 있을 거예요."
    };

    final static String[] dialog = {
            "이번 휴가에 한국 여행 가고 싶어요.",
            "한국에서 뭐 하고 싶어요?",
            "한국 음식 많이 먹고 싶어요.\n그리고 한국어 연습도 하고 싶어요.",
            "좋아요.\n그동안 한국어 공부 열심히 해서 잘 할 수 있을 거예요."
    };

    final static int[] peopleImage = {5,6};

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
