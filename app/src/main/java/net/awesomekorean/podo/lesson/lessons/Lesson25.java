package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson25 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_25";
    private String lessonTitle = "try";
    private String lessonSubTitle = "~아/어 보다";
    private int lessonImage = R.drawable.l_25_word_0;

    private String[] wordFront = {"알다", "모르다", "쓰다", "가르치다", "선생님", "만들다", "받다"};

    private String[] wordBack = {"know", "don't know", "to use", "to teach", "teacher", "to make", "to receive"};

    private String[] wordPronunciation = {"-", "-", "-", "-", "-", "-", "[받따]"};

    private String[] sentenceFront = {
            "알아요?",
            "포도앱 알아요?",
            "아니요. 몰라요.",
            "뭐예요?",
            "그게 뭐예요?",
            "선생님이 만든 앱이에요.",
            "한국어를 가르치는 선생님이 만든 앱이에요.",
            "써 보세요.",
            "한번 써 보세요.",
            "다운 받아 볼게요."
    };

    private String[] sentenceBack = {
            "Do you know?",
            "Do you know the 'podo' app?",
            "No. I don't know.",
            "What is it?",
            "What is it?",
            "It's an app developed by a teacher.",
            "It's an app developed by a teacher who teaches Korean.",
            "Try it.",
            "Try it once.",
            "I'll download it."
    };

    private String[] sentenceExplain = {
            "-",
            "Usually referred to as '앱' or '어플'",
            "Sometimes students are confused.\n\n'아니요' : no\n'아니에요' : not",
            "-",
            "'그게' = '그 것이'",
            "When you want to describe a noun with an adjective or verb, use the '~ (으) ㄴ / 는 / (으) ㄹ' form.\nHowever, it is recommended to learn in the sentence rather than memorizing it grammatically because the changing forms are so diverse and there are many exceptions.\n\n'만들다' -> '만들' + '은' = '만들은' -> '만든' (irregular)\n\nex)\n'읽다' -> '읽은'\n'먹다' -> '먹은'\n'배우다' -> '배운'",
            "However, it is good to learn the '~ (으) ㄴ / 는 / (으) ㄹ' form that changes according to the verb tense.\n\npast tense : (으)ㄴ\npresent tense : 는\nfuture tense : (으)ㄹ\n\nex)\n'가르치다' \n-> '가르친' (past tense) : 한국어를 가르친 선생님 (The teacher who taught Korean)\n-> '가르치는' (present tense) : 한국어를 가르치는 선생님 (The teacher  who is teaching Korean)\n-> '가르칠' (future tense) : 한국어를 가르칠 선생님 (The teacher who will teach Korean)",
            "'쓰다' -> '써요' -> '써 보다'",
            "-",
            "We usually say '다운로드'(download) to '다운' to be more simple.\nAnd the verb '받다' goes well with this word.\n\n'보다' -> '보' + 'ㄹ 게요' = '볼게요'"
    };

    private String[] dialog = {
            "포도앱 알아요?",
            "아니요. 몰라요.\n그게 뭐예요?",
            "한국어를 가르치는 선생님이 만든 앱이에요.\n한번 써 보세요.",
            "고마워요.\n다운 받아 볼게요"
    };

    private int[] peopleImage = {9,10};

    private String[] reviewFront = {
            "포도앱 알아요?",
            "그게 뭐예요?",
            "한국어를 가르치는 선생님이 만든 앱이에요.",
            "한번 써 보세요.",
            "다운 받아 볼게요"
    };

    private String[] reviewBack = {
            "Do you know the 'podo' app?",
            "What is it?",
            "It's an app developed by a teacher who teaches Korean.",
            "Try it once.",
            "I'll download it."
    };


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
    public String[] getReviewFront() {
        return reviewFront;
    }

    @Override
    public String[] getReviewBack() {
        return reviewBack;
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
