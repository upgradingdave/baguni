package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson22 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_22";
    private String lessonTitle = "when";
    private String lessonSubTitle = "~을 때";
    private int lessonImage = R.drawable.l_22_word_1;

    private String[] wordFront = {"누구", "어리다", "사진을 찍다", "귀엽다", "그런데", "엄마", "아빠"};

    private String[] wordBack = {"who", "young", "take a picture", "cute", "by the way", "mom", "dad"};

    private String[] wordPronunciation = {"-", "-", "[사지늘 찍따]", "[귀엽따]", "-", "-", "-"};

    private String[] sentenceFront = {
            "누구예요?",
            "이 사진 누구예요?",
            "사진이에요.",
            "제 어릴 때 사진이에요.",
            "귀여워요.",
            "여기 어디예요?",
            "그런데 여기 어디예요?",
            "찍은 거예요.",
            "한국 여행 갔을 때 찍은 거예요.",
            "엄마, 아빠랑 한국 여행 갔을 때 찍은 거예요."
    };

    private String[] sentenceBack = {
            "who?",
            "Who is this picture?",
            "It's a picture.",
            "It's a picture of my childhood.",
            "cute.",
            "Where is here?",
            "By the way, where is here?",
            "It was taken.",
            "It was taken when I went to travel to Korea.",
            "It was taken when I went to travel to Korea with my mom and dad."
    };

    private String[] sentenceExplain = {
            "-",
            "-",
            "-",
            "It is used as a meaning of 'when' or 'during'.\n\nNoun + '때'\nAdjective / Verb + '(으)ㄹ 때\n\nex)\n'휴가' -> '휴가' + '때' = '휴가 때'\n'어리다' -> '어리' + 'ㄹ 때' = '어릴 때'\n'먹다' -> '먹' + '을 때' = '먹을 때'\n\nHowever, '에' is used instead of '때' in noun that means 'time' or 'date', such as '오전', '오후', '아침', '점심', '저녁' and '요일'.\n\nex) \n오전 때 (x) / 오전에 (o)\n아침 때 (x) / 아침에 (o)\n월요일 때 (x) / 월요일에 (o)",
            "귀엽다' -> '귀엽' + '어요' = '귀엽어요' -> '귀여워요' (irregular)",
            "-",
            "-",
            "-",
            "'갔다' -> '갔' + '을 때' = '갔을 때'",
            "-"
    };

    private String[] dialog = {
            "이 사진 누구예요?",
            "제 어릴 때 사진이에요.",
            "귀여워요.\n그런데 여기 어디예요?",
            "엄마, 아빠랑\n한국 여행 갔을 때\n찍은 거예요."
    };

    private int[] peopleImage = {3,4};

    private int[] reviewId = {1,3,4,5,8};


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
