package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson20 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_20";
    private String lessonTitle = "wish";
    private String lessonSubTitle = "~았/었으면 좋겠어요";
    private LessonItem specialLesson = new S_Lesson13();

    private String[] wordFront = {"덥다", "시원하다", "곳", "들어가다", "저기", "크다", "예쁘다", "유명하다", "마시다"};

    private String[] wordBack = {"hot", "cool", "place", "enter", "there", "big", "pretty", "famous", "drink"};

    private String[] wordPronunciation = {"[덥따]", "-", "[곧]", "[드러가다]", "-", "-", "-", "-", "-"};

    private String[] sentenceFront = {
            "너무 더워요.",
            "여기 너무 더워요.",
            "들어갔으면 좋겠어요.",
            "시원한 곳에 들어갔으면 좋겠어요.",
            "큰 쇼핑몰",
            "큰 쇼핑몰에 들어가요.",
            "저기 큰 쇼핑몰에 들어가요.",
            "유명한 카페",
            "예쁘고 유명한 카페",
            "예쁘고 유명한 카페가 있어요.",
            "거기에 예쁘고 유명한 카페가 있어요.",
            "시원한 주스",
            "시원한 주스 마셔요.",
            "거기에서 시원한 주스 마셔요.",
            "좋아요. 거기에서 시원한 주스 마셔요."
    };

    private String[] sentenceBack = {
            "It's too hot.",
            "It's too hot here.",
            "I want to go inside.",
            "I want to go to a cool place.",
            "big shopping mall",
            "Let's go into the big shopping mall.",
            "Let's go into the big shopping mall there.",
            "famous cafe",
            "pretty and famous cafe",
            "There's a pretty and famous cafe.",
            "There's a pretty and famous cafe.",
            "cold juice",
            "Let's drink a cold juice.",
            "Let's drink a cold juice there.",
            "Great. Let's drink a cold juice there."
    };

    private String[] sentenceExplain = {
            "덥다' -> '덥어요' -> '더워요' (irregular)",
            "-",
            "conjugate 'V-았/었으면 좋겠어요'\n'들어가다' -> '들어갔다'\n'들어갔다' -> '들어갔' + '으면 좋겠어요' = '들어갔으면 좋겠어요'",
            "We can use 'A-(으)ㄴ' form when the adjective modifies a noun.\n'시원하다' -> '시원하' + 'ㄴ' = '시원한'",
            "-",
            "-",
            "-",
            "유명하다' -> '유명하' + 'ㄴ' = '유명한'",
            "~고' form is used.",
            "-",
            "여기 : here\n저기 : there\n거기 : there\n(See the Lesson 11 -  'confusing expression : here and there')",
            "시원하다' -> '시원하' + 'ㄴ' = '시원한'",
            "-",
            "-",
            "-"
    };

    private String[] dialog = {
            "여기 너무 더워요. \n 시원한 곳에 들어갔으면 좋겠어요.",
            "저기 큰 쇼핑몰에 들어가요. \n 거기에 예쁘고 유명한 카페가 있어요.",
            "좋아요. \n 거기에서 시원한 주스 마셔요."
    };

    private int[] peopleImage = {11,12};

    private int[] reviewId = {1,3,6,10,13};


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
    public LessonItem getSLesson() {
        return specialLesson;
    }
}
