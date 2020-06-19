package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson28 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_28";
    private String lessonTitle = "while";
    private String lessonSubTitle = "~(으)면서";
    private int lessonImage = R.drawable.l_28_word_0;

    private String[] wordFront = {"다이어트", "시작하다", "매일", "운동하다", "혼자", "재미없다"};

    private String[] wordBack = {"diet", "start", "everyday", "exercise", "alone", "not funny"};

    private String[] wordPronunciation = {"-", "[시자카다]", "-", "-", "-", "[재미업따]"};

    private String[] sentenceFront = {
            "시작했어요.",
            "다이어트를 시작했어요.",
            "요즘 다이어트를 시작했어요.",
            "운동해요.",
            "집에서 운동해요.",
            "매일 집에서 운동해요.",
            "혼자 운동해요?",
            "재미없지 않아요?",
            "TV를 보다.",
            "TV를 보면서 운동해요."
    };

    private String[] sentenceBack = {
            "I started.",
            "I started to go on a diet.",
            "I recently started to go on a diet.",
            "I exercise.",
            "I exercise at home.",
            "I exercise at home every day.",
            "Do you exercise alone?",
            "It's not funny, isn't it?",
            "Watch TV.",
            "I exercise while watching TV."
    };

    private String[] sentenceExplain = {
            "시작해요' -> '시작했어요' (past tense)",
            "You can say '다이어트' for losing weight.",
            "-",
            "-",
            "-",
            "If you still don't know the difference between '에' and '에서', \nsee the Lesson 4 - confusing expression : '에' vs '에서'.",
            "-",
            "You may be confused because there are 2 negatives.\nWhen you want to check if the other person's thoughts are the same as me, you can ask '~지 않아요?'.\n'재미없다' -> '재미없' + '지 않아요?' = '재미없지 않아요?'\n\nWhen it is not asking a question,'~지 않아요' is just a negative.\n(Refer to the Lesson 5 - Negative)",
            "-",
            "When doing any two actions, use the '~(으)면서' form.\n\n'TV를 보다' + '운동하다' -> 'TV를 보면서 운동하다'\n'밥을 먹다' + 'TV를 보다' -> 밥을 먹으면서 TV를 보다'"
    };

    private String[] dialog = {
            "요즘 다이어트를 시작했어요.\n매일 집에서 운동해요.",
            "혼자 운동해요?\n재미없지 않아요?",
            "네, 그래서 TV를 보면서 운동해요."
    };

    private int[] peopleImage = {3,4};

    private int[] reviewId = {2,5,6,7,9};


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
