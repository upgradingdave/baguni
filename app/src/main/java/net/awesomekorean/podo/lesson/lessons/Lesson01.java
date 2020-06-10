package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

import javax.annotation.meta.When;

public class Lesson01 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_01";
    private String lessonTitle = "self-introduction";
    private String lessonSubTitle = "저는 데니예요";
    private int lessonImage = R.drawable.selfintroduction;

    private String[] wordFront = {"이름", "뭐", "어느", "나라", "사람", "한국"};

    private String[] wordBack = {"name", "what", "which", "country", "people", "Korea"};

    private String[] wordPronunciation = {"-", "-", "-", "-", "-", "-"};

    private String[] sentenceFront = {
            "뭐",
            "뭐예요?",
            "이름이 뭐예요?",
            "데니예요. / 메간이에요.",
            "나라",
            "어느 나라",
            "어느 나라 사람이에요?",
            "사람",
            "사람이에요",
            "한국 사람이에요."};

    private String[] sentenceBack = {
            "what",
            "what is it?",
            "What is your name?",
            "Danny / Megan",
            "country",
            "which country",
            "Where are you from?",
            "person / people",
            "It’s a person.",
            "I’m Korean."
    };

    private String[] sentenceExplain = {
            "-",
            "This expression is very useful when you have questions.\nYou can point your finger at something you don't know and say ‘이거 뭐예요?’ (what's this?)\nWhen you point something out a little farther away, you can say '저거 뭐예요?' (what's that?)",
            "You may be wondering what '이' is after '이름'.\nThis is the main challenge for Korean language learners, called 'subject particle' but don't struggle to know it now.\nJust remember that you can also say '이름 뭐예요?' but '이름이 뭐예요?' is a better expression.\nIt is important to get used to it in this level.\n(See the special lesson ‘particles : Why we use particles?)",
            "If there isn't a batchim (last consonant) in the previous letter, we use ‘예요’.\n\nIf there is a batchim in the previous letter, we use ‘이에요’.",
            "-",
            "-",
            "사람이에요 [사라미에요]\n(See Lesson 7 - 'Speaking Naturally : basic rules')",
            "-",
            "-",
            "Find out what your country is in Korean and make it into a sentence."
    };

    private String[] dialog = {
            "이름이 뭐예요?",
            "데니예요.",
            "어느 나라 사람이에요?",
            "한국 사람이에요."
    };

    private int[] peopleImage = {2,1};

    private int[] reviewId = {2,6,9};


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
