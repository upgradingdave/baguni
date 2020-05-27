package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;

public class Lesson17 extends LessonInit implements Lesson, LessonItem, Serializable {

    private String lessonId = "L_17";
    private String lessonTitle = "want to";
    private String lessonSubTitle = "~고 싶어요";
    private int lessonImage = R.drawable.hope;

    private String[] wordFront = {"휴가", "여행", "음식", "연습", "그동안", "공부", "열심히"};

    private String[] wordBack = {"vacation", "travel", "food", "practice", "so far", "study", "hardly"};

    private String[] wordPronunciation = {"-", "-", "-", "-", "-", "-", "-"};

    private String[] sentenceFront = {
            "가고 싶어요.",
            "한국 여행 가고 싶어요.",
            "다음 휴가 때 한국 여행 가고 싶어요.",
            "하고 싶어요?",
            "뭐 하고 싶어요?",
            "한국에서 뭐 하고 싶어요?",
            "먹고 싶어요.",
            "한국 음식 먹고 싶어요.",
            "한국 음식 많이 먹고 싶어요.",
            "하고 싶어요.",
            "한국어 연습도 하고 싶어요.",
            "그리고 한국어 연습도 하고 싶어요.",
            "잘 할 수 있어요.",
            "잘 할 수 있을 거예요.",
            "한국어 공부 열심히 했으니까 잘 할 수 있을 거예요.",
            "좋아요. 그동안 한국어 공부 열심히 해서 잘 할 수 있을 거예요."
    };

    private String[] sentenceBack = {
            "I want to go.",
            "I want to travel to Korea.",
            "I want to travel to Korea on next vacation.",
            "Do you want to do?",
            "What do you want to do?",
            "What do you want to do in Korea?",
            "I want to eat.",
            "I want to eat Korean food.",
            "I want to eat Korean food a lot.",
            "I want to do.",
            "I want to practice Korean too.",
            "And I want to practice Korean too.",
            "You can do it well.",
            "You will be able to do it well.",
            "You will be able to do it well because you studied Korean very hard.",
            "Cool! You will be able to do it well because you learned Korean very well."
    };

    private String[] sentenceExplain = {
            "Use the 'V-고 싶다' form to tell the wishes or hopes of the speaker.\n\nconjugate 'V-고 싶다'\n'가다' -> '가' + '고 싶다' = '가고 싶다'",
            "-",
            "이번 : this time\n저번 : last time\n다음 : next time",
            "conjugate 'V-고 싶다'\n'하다' -> '하' + '고 싶다' = '하고 싶다'",
            "-",
            "-",
            "conjugate 'V-고 싶다'\n'먹다' -> '먹' + '고 싶다' = '먹고 싶다'",
            "한국 음식' can be shortened to '한식'.",
            "-",
            "-",
            "-",
            "-",
            "A 'V-(으)ㄹ 수 있다' form was used to represent the 'ability' that we learned in the previous lesson.",
            "Used future tense to express the speaker's expectations.",
            "Use the 'A/V-(으)니까' form to tell the 'reason'.\nYou can also use the 'A/V-아/어서' form that you learned in the 'negative2' lesson.\n\n'공부 열심히 했으니까' = '공부 열심히 해서'\n(See the special lesson 'Confusing expression3 - ~아/어서 vs. ~으니까')",
            "-"
    };

    private String[] dialog = {
            "이번 휴가에 한국 여행 가고 싶어요.",
            "한국에서 뭐 하고 싶어요?",
            "한국 음식 많이 먹고 싶어요.\n그리고 한국어 연습도 하고 싶어요.",
            "좋아요.\n그동안 한국어 공부 열심히 해서 잘 할 수 있을 거예요."
    };

    private int[] peopleImage = {5,6};

    private String[] reviewFront = {
            "이번 휴가에 한국 여행 가고 싶어요.",
            "한국에서 뭐 하고 싶어요?",
            "한국 음식 많이 먹고 싶어요.",
            "한국어 연습도 하고 싶어요.",
            "잘 할 수 있을 거예요."
    };

    private String[] reviewBack = {
            "I want to travel to Korea on next vacation.",
            "What do you want to do in Korea?",
            "I want to eat Korean food a lot.",
            "And I want to practice Korean too.",
            "You will be able to do it well."
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
