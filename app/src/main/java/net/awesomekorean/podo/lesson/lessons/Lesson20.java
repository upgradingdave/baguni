package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class Lesson20 extends LessonInit implements Lesson, LessonItem {

    private String lessonId = "L_20";
    private String lessonTitle = "wish";
    private String lessonSubTitle = "~았/었으면 좋겠어요";
    private int lessonImage = R.drawable.thankyou;

    final static String[] wordFront = {"덥다", "시원하다", "곳", "들어가다", "저기", "크다", "예쁘다", "유명하다", "마시다"};

    final static String[] wordBack = {"hot", "cool", "place", "enter", "there", "big", "pretty", "famous", "to drink"};

    final static String[] wordPronunciation = {"[덥따]", "-", "[곧]", "[드러가다]", "-", "-", "-", "-", "-"};

    final static String[] sentenceFront = {
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

    final static String[] sentenceBack = {
            "It's too hot.",
            "It's too hot here.",
            "I want to enter.",
            "I want to enter a cool place.",
            "big shopping mall",
            "Let's go into the big shopping mall.",
            "Let's go into the big shopping mall there.",
            "famous cafe",
            "pretty and famous cafe",
            "There's a pretty and famous cafe.",
            "There's a pretty and famous cafe there.",
            "cool juice",
            "Let's drink a cool juice.",
            "Let's drink a cool juice there.",
            "Great. Let's drink a cool juice there."
    };

    final static String[] sentenceExplain = {
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
            "여기 : here\n저기 : there\n거기 : there\n(See the special lesson 'confusing expression 4 : here and there')",
            "시원하다' -> '시원하' + 'ㄴ' = '시원한'",
            "-",
            "-",
            "-"
    };

    final static String[] dialog = {
            "여기 너무 더워요. \n 시원한 곳에 들어갔으면 좋겠어요.",
            "저기 큰 쇼핑몰에 들어가요. \n 거기에 예쁘고 유명한 카페가 있어요.",
            "좋아요. \n 거기에서 시원한 주스 마셔요."
    };

    final static int[] peopleImage = {11,12};


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
