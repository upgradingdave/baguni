package net.awesomekorean.podo.lesson.lessons;

import android.os.Messenger;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson03 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_03";
    private String lessonTitle = "possession/existence";
    private String lessonSubTitle = "~있어요?";
    private int lessonImage = R.drawable.posession;

    private String[] wordFront = {"있다", "친구", "얘기하다", "어디", "한국어"};

    private String[] wordBack = {"there is / to have", "friend", "to talk", "where", "Korean language"};

    private String[] wordPronunciation = {"[읻따]", "-", "-", "-", "[한구거]"};

    private String[] sentenceFront = {
            "있다",
            "있어요?",
            "친구 있어요?",
            "한국 친구 있어요?",
            "네, 있어요",
            "네, 한 명 있어요.",
            "얘기하다",
            "얘기해요?",
            "어디에서 얘기해요?",
            "카카오톡",
            "카카오톡에서",
            "카카오톡에서 얘기해요"
    };

    private String[] sentenceBack = {
            "There is",
            "is there?",
            "Do you have a friend?",
            "Do you have a Korean friend?",
            "Yes, I have a korean friend.",
            "Yes, I have one.",
            "to talk",
            "Are you talking?",
            "Where are you talking?",
            "Kakao talk",
            "in Kakao talk",
            "We talk in Kakao talk."
    };
    private String[] sentenceExplain = {
            "You can say this when you 'have' something or someone 'exists'.\nIn Korean, we don't consider singular and plural that much\nand the meaning of 'There isn't' is '없다'.",
            "When you conjugate verbs or adjectives to the form of '아요/어요', if you say it with a lower intonation, it's a statement.\nBut if you raise the intonation at the end, it becomes a question.\n\nex)\n'있어요?' : is there?\n'있어요.' : there is.",
            "-",
            "-",
            "yes : '네' / no : '아니요'",
            "There are lots of unit nouns in Korean.\nDon't try to learn all at once.\nJust remember to use '명' for people and '개' for things.",
            "-",
            "-",
            "The words that describe the place should be followed by the place particle '에서'.",
            "kakao talk: Messenger name used by most Koreans.",
            "Because KakaoTalk is used as a place, '에서' is added.",
            "-"
    };


    private String[] dialog = {
            "한국 친구 있어요?",
            "네, 한 명 있어요.",
            "어디에서 얘기해요?",
            "카카오톡에서 얘기해요."
    };

    private int[] peopleImage = {5,6};

    private int[] reviewId = {3,5,8,11};


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
    public int getLessonImage() {
        return lessonImage;
    }
}
