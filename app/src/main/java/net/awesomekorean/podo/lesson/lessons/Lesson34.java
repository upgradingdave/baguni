package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson34 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_34";
    private String lessonTitle = "allow";
    private String lessonSubTitle = "~아/어도 돼요";
    private int lessonImage = R.drawable.l_34_word_3;

    private String[] wordFront = {"지갑", "놓다", "잠시", "괜찮다", "천천히"};

    private String[] wordBack = {"wallet", "put", "for a while", "Okay", "slowly"};

    private String[] wordPronunciation = {"-", "[노타]", "-", "[괜찬타]", "-"};

    private String[] sentenceFront = {
            "놓고 왔어요.",
            "지갑을 놓고 왔어요.",
            "집에 지갑을 놓고 왔어요.",
            "기다려 주세요.",
            "잠시만 기다려 주세요.",
            "네, 괜찮아요.",
            "와도 돼요.",
            "갔다 와도 돼요.",
            "천천히 갔다 와도 돼요."
    };

    private String[] sentenceBack = {
            "I left it.",
            "I left my wallet.",
            "I left my wallet at home.",
            "Please wait.",
            "Please wait a second.",
            "yes I'm okay.",
            "You can come.",
            "You can go.",
            "You can take your time."
    };

    private String[] sentenceExplain = {
            "It is used often when you forgot to bring something.\n\n'놓다 ' + '그리고' + '오다' = '놓고 오다'\n\nㅎ + ㄱ = [ㅋ] \n놓고 [노코]",
            "-",
            "-",
            "'기다려요' -> '기다려' + '주세요' = '기다려 주세요'",
            "'잠시' (short time) + '만' (only) = '잠시만'\n\nWhen you call someone in a hurry, you can say '잠시만요!'.",
            "When someone tries to give you something or helps, it can also mean 'no thank you'.",
            "'오다' -> '와요' -> '와' + '도 돼요' = '와도 돼요'\n\nWhen expressing permission, use the '아/어도 돼요' form.",
            "If you go somewhere and will come back, you can say '갔다 오다'.\n\n'갔다 오다' -> '갔다 와요' -> '갔다 와' + '도 돼요' = '갔다 와도 돼요'",
            "-"
    };

    private String[] dialog = {
            "아! 집에 지갑을 놓고 왔어요.\n잠시만 기다려 주세요.",
            "네, 괜찮아요.\n천천히 갔다 와도 돼요."
    };

    private int[] peopleImage = {3,4};

    private int[] reviewId = {2,4,5,8};


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
