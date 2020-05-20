package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson24 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_24";
    private String lessonTitle = "even though";
    private String lessonSubTitle = "~아/어도";
    private int lessonImage = R.drawable.l_24_word_1;

    final static String[] wordFront = {"얼굴", "아프다", "자다", "계속", "병원", "평일"};

    final static String[] wordBack = {"face", "painful / sick", "sleep", "continuously", "hospital", "weekday"};

    final static String[] wordPronunciation = {"-", "-", "-", "-", "-", "-"};

    final static String[] sentenceFront = {
            "어디 아파요?",
            "얼굴이 안 좋아요.",
            "계속 피곤해요.",
            "많이 자도 계속 피곤해요.",
            "요즘 많이 자도 계속 피곤해요.",
            "병원에 갔어요?",
            "병원에 갔다 왔어요?",
            "시간이 없어요.",
            "일이 많아서 시간이 없어요.",
            "평일에는 일이 많아서 시간이 없어요.",
            "주말에 갈 거예요."
    };

    final static String[] sentenceBack = {
            "Are you sick?",
            "You don't look well.",
            "I feel tired.",
            "I feel tired even though I sleep a lot.",
            "I feel tired even though I sleep a lot these days.",
            "Did you see a doctor?",
            "Did you see a doctor?",
            "There is no time.",
            "Because I have a lot of work I don't have time.",
            "Because I have a lot of work on weekdays I don't have time.",
            "I'll go on the weekend."
    };

    final static String[] sentenceExplain = {
            "-",
            "When somebody seems to be sick somewhere or seems to happen something bad, you can say this.",
            "-",
            "'자요' -> '자도'\n\nex)\n'바빠요' -> '바빠도'\n'많아요' -> '많아도'\n'봐요' -> '봐도'",
            "-",
            "In English, we say 'to see a doctor', but in Korean, 'to go to hospital' is more natural.",
            "When you want to say 'visit' somewhere, use this expression.\n\n'갔다' + '왔다' = '갔다 왔다'\n\nConversely, when someone visits my place and goes, it is said to '왔다 갔다'.\n\nex)\n우리 집에 누가 왔다 갔어요?",
            "-",
            "'많다' -> '많' + '아서' = '많아서'",
            "When you want to give the meaning of 'limited', you can use '은 / 는'.",
            "'가다' -> '가' + 'ㄹ 거예요' = '갈 거예요'"
    };

    final static String[] dialog = {
            "얼굴이 안 좋아요. 어디 아파요?",
            "요즘 많이 자도 계속 피곤해요",
            "병원에 갔다 왔어요?",
            "아니요.\n평일에는 일이 많아서 시간이 없어요.\n주말에 갈 거예요."
    };

    final static int[] peopleImage = {8,7};


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
