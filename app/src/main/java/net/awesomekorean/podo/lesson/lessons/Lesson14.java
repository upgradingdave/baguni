package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.Lesson;
import net.awesomekorean.podo.lesson.LessonItem;

public class Lesson14 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_14";
    private int title = R.string.L_14_TITLE;
    private String subTitle = "저녁을 먹은 후에 드라마를 볼 거예요";
    private int lessonImage = R.drawable.hangul;

    final static String[] wordFront = {"오늘", "저녁", "일", "끝나다", "후", "가족", "드라마", "보다", "추천하다"};
    final static String[] wordPronunciation = {"-", "-", "-", "[끈나다]", "-", "-", "-", "-", "-"};
    final static String[] wordSynonyms = {"-", "밤", "-", "다하다", "-", "식구", "-", "-", "소개하다"};
    final static String[] wordAntonyms = {"-", "아침", "-", "시작하다", "전", "-", "-", "-", "-"};

    final static String[] sentenceFront = {
            "뭐 해요?",
            "저녁에 뭐 해요?",
            "오늘 저녁에 뭐 해요?",
            "끝나다",
            "끝난 후에",
            "일이 끝난 후에",
            "일이 끝난 후에 저녁을 먹어요.",
            "일이 끝난 후에 가족이랑 저녁을 먹어요.",
            "먹다",
            "먹은 후에",
            "저녁을 먹은 후에",
            "저녁을 먹은 후에 드라마를 봐요.",
            "저녁을 먹은 후에 드라마를 볼 거예요.",
            "보다",
            "봐요?",
            "드라마 봐요?",
            "요즘 드라마 봐요?",
            "추천하다",
            "추천해요.",
            "추천해 주세요.",
            "저도 추천해 주세요."
    };

    final static String[] dialog = {
            "일이 끝난 후에 가족이랑 저녁을 먹어요.",
            "저녁을 먹은 후에 드라마를 볼 거예요.",
            "요즘 드라마 봐요?",
            "저도 추천해 주세요."
    };

    final static int[] peopleImage = {5,6};


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
    public int getTitle() {
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
