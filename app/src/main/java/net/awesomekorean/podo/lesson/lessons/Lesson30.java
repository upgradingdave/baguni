package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson30 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_30";
    private String lessonTitle = "present, past tense";
    private String lessonSubTitle = "~(으)ㄴ/는 것 같다";
    private int lessonImage = R.drawable.l_30_word_2;

    private String[] wordFront = {"소리", "들리다", "비가 내리다", "우산", "가져오다"};

    private String[] wordBack = {"sound", "hear", "rain", "umbrella", "bring"};

    private String[] wordPronunciation = {"-", "-", "-", "-", "-"};

    private String[] sentenceFront = {
            "안 들려요?",
            "무슨 소리 안 들려요?",
            "비가 내리는 소리",
            "비가 내리는 소리인 것 같아요.",
            "가져왔어요?",
            "우산 가져왔어요?",
            "많이 안 오다.",
            "많이 안 오는 것 같아요."
    };

    private String[] sentenceBack = {
            "Can your hear?",
            "Can your hear the sound?",
            "The sound of rain",
            "It sounds like rain.",
            "Did you bring it?",
            "Did you bring an umbrella?",
            "It doesn't rain much",
            "I think it doesn't rain much."
    };

    private String[] sentenceExplain = {
            "When there is something you want to check with others, you can use '안' or '~지 않아요'.\n\n무슨 소리 안 들려요?\n무슨 소리 들리지 않아요?",
            "-",
            "When you want to describe a noun with an adjective or verb, use the '~ (으) ㄴ / 는 / (으) ㄹ' form like we'd learned in Lesson 14 - '아/어 보다'.\n\nAgain, because there are many conjugations and exceptions in this form, it is better to remember them naturally when you learn words rather than trying to learn the rules.\n\n'비가 내리다' -> '비가 내리' + '는' = '비가 내리는'",
            "When guessing about the present or the past, use the form '~(으)ㄴ/는 것 같다'.\n\n'소리이다' -> '소리이' + 'ㄴ 것 같다' = '소리인 것 같다'",
            "가지다'(to take) + '오다'(to come) = '가져오다'\n\n'가져와요' -> '가져와' + 'ㅆ어요' = '가져왔어요'",
            "-",
            "'비가 내리다' : rain is falling \n'비가 오다' : rain is coming\n\nBoth are the same meaning and they are all used in real life, so it's good to remember them all.",
            "'오다' -> '오' + '는 것 같다' = '오는 것 같다'\n\n(Refer to Lesson 15 - more expression : '~것 같다')"
    };

    private String[] dialog = {
            "무슨 소리 안 들려요?",
            "비가 내리는 소리인 것 같아요.",
            "우산 가져왔어요?",
            "아니요. 괜찮아요.\n많이 안 오는 것 같아요."
    };

    private int[] peopleImage = {8,7};

    private int[] reviewId = {1,3,5,7};


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
