package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class Lesson16 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_16";
    private String lessonTitle = "possibility";
    private String lessonSubTitle = "~을 수 있어요";
    private int lessonImage = R.drawable.posibility;

    final static String[] wordFront = {"예전에", "살다", "언어교환", "배우다", "좋다", "고마워요", "비슷하다", "단어"};
    final static String[] wordPronunciation = {"[예저네]", "-", "[어너교환]", "-", "[조타]", "-", "[비스타다]", "[다너]"};
    final static String[] wordSynonyms = {"옛날에", "지내다", "-", "공부하다", "-", "고맙습니다, 감사합니다", "-", "어휘"};
    final static String[] wordAntonyms = {"-", "-", "-", "-", "나쁘다", "-", "-", "-"};

    final static String[] sentenceFront = {
            "할 수 있어요?.",
            "한국어 할 수 있어요?",
            "네, 저는 한국 사람이에요.",
            "저는 일본 사람이에요.",
            "배우고 있어요.",
            "한국어 배우고 있어요.",
            "요즘 한국어 배우고 있어요.",
            "할 수 있어요?",
            "언어교환 할 수 있어요",
            "좋아요.",
            "저도 일본어 배우고 있어요.",
            "어때요?",
            "한국어 공부 어때요?",
            "많이 있어요.",
            "비슷한 단어 많이 있어요.",
            "일본어랑 비슷한 단어 많이 있어요.",
            "한국어에 일본어랑 비슷한 단어 많이 있어요.",
            "쉬울 수 있어요.",
            "그래서 다른 언어보다 쉬울 수 있어요."
    };

    final static String[] dialog = {
            "한국어 할 수 있어요?",
            "네, 저는 한국 사람이에요.",
            "저는 일본 사람이에요.\n요즘 한국어 배우고 있어요.\n언어교환 할 수 있어요?",
            "좋아요.\n저도 일본어 배우고 있어요.",
            "한국어 공부 어때요?",
            "한국어에 일본어랑 비슷한 단어\n많이 있어요. 그래서 다른 언어보다\n쉬울 수 있어요."
    };

    final static int[] peopleImage = {2,1};

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
