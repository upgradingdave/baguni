package net.awesomekorean.podo.lesson.lessonReviewRewards;

import net.awesomekorean.podo.lesson.lessons.Lesson;
import net.awesomekorean.podo.lesson.lessons.Lesson00;
import net.awesomekorean.podo.lesson.lessons.Lesson01;
import net.awesomekorean.podo.lesson.lessons.Lesson02;
import net.awesomekorean.podo.lesson.lessons.Lesson03;
import net.awesomekorean.podo.lesson.lessons.Lesson09;
import net.awesomekorean.podo.lesson.lessons.Lesson10;
import net.awesomekorean.podo.lesson.lessons.Lesson11;
import net.awesomekorean.podo.lesson.lessons.Lesson12;
import net.awesomekorean.podo.lesson.lessons.Lesson13;
import net.awesomekorean.podo.lesson.lessons.Lesson14;
import net.awesomekorean.podo.lesson.lessons.Lesson15;
import net.awesomekorean.podo.lesson.lessons.Lesson16;
import net.awesomekorean.podo.lesson.lessons.Lesson19;
import net.awesomekorean.podo.lesson.lessons.Lesson27;
import net.awesomekorean.podo.lesson.lessons.LessonInit;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import java.io.Serializable;

public class LessonReview02 extends LessonInit implements LessonItem, LessonReview, Serializable {

    private String lessonId = "LR_02";
    private String lessonTitle = "";
    private String lessonSubTitle = "";

    private Lesson[] lessons = {
            new Lesson27(), new Lesson09(), new Lesson10(), new Lesson11(), new Lesson12(), new Lesson13(),
            new Lesson14(), new Lesson15(), new Lesson16()
    };

    private String[] baseForm = {
            "일하다", "좋아하다", "노래를 부르다", "춤을 추다", "어렵다", "말하다", "쓰다", "읽다", "쉽다"
            , "먹다", "끝나다", "추천하다", "이해하다", "괜찮다", "배우다", "좋다", "비슷하다"};

    private String[][] conjugation = {
            {"일하고", "일하지만", "일하기 전에", "일한 후에", "일할 수 있어요", "일할 수 없어요"},
            {"좋아하고", "좋아하지만", "좋아할 수 있어요", "좋아할 수 없어요"},
            {"노래를 부르고", "노래를 부르지만", "노래를 부르기 전에", "노래를 부른 후에", "노래를 부를 수 있어요", "노래를 부를 수 없어요"},
            {"춤을 추고", "춤을 추지만", "춤을 추기 전에", "춤을 춘 후에", "춤을 출 수 있어요", "춤을 출 수 없어요"},
            {"어렵고", "어렵지만", "어려울 수 있어요", "어려울 수 없어요"},
            {"말하고", "말하지만", "말하기 전에", "말한 후에", "말할 수 있어요", "말할 수 없어요"},
            {"쓰고", "쓰지만", "쓰기 전에", "쓴 후에", "쓸 수 있어요", "쓸 수 없어요"},
            {"읽고", "읽지만", "읽기 전에", "읽은 후에", "읽을 수 있어요", "읽을 수 없어요"},
            {"쉽고", "쉽지만", "쉬울 수 있어요", "쉬울 수 없어요"},
            {"먹고", "먹지만", "먹기 전에", "먹은 후에", "먹을 수 있어요", "먹을 수 없어요"},
            {"끝나기 전에", "끝난 후에", "끝날 수 있어요", "끝날 수 없어요"},
            {"추천하고", "추천하지만", "추천하기 전에", "추천한 후에", "추천할 수 있어요", "추천할 수 없어요"},
            {"이해하고", "이해하지만", "이해한 후에", "이해할 수 있어요", "이해할 수 없어요"},
            {"괜찮고", "괜찮지만", "괜찮을 수 있어요", "괜찮을 수 없어요"},
            {"배우고", "배우지만", "배우기 전에", "배운 후에", "배울 수 있어요", "배울 수 없어요"},
            {"좋고", "좋지만", "좋을 수 있어요", "좋을 수 없어요"},
            {"비슷하고", "비슷하지만", "비슷할 수 있어요", "비슷할 수 없어요"}
    };

    private String[][] translate = {
            {"I work and", "I work but", "Before I work", "After I work", "I can work", "I can't work"},
            {"I like and", "I like but", "I can like", "I can't like"},
            {"I sing a song and", "I sing a song but", "Before I sing a song", "After I sing a song", "I can sing a song", "I can't sing a song"},
            {"I dance and", "I dance but", "Before I dance", "After I dance", "I can dance", "I can't dance"},
            {"It's difficult and", "It's difficult but", "It can be difficult", "It can't be difficult"},
            {"I say and", "I say but", "Before I say", "After I say", "I can say", "I can't say"},
            {"I write and", "I write but", "Before I write", "After I write", "I can write", "I can't write"},
            {"I read and", "I read but", "Before I read", "After I read", "I can read", "I can't read"},
            {"It's easy and", "It's easy but", "It can be easy", "It can't be easy"},
            {"I eat and", "I eat but", "Before I eat", "After I eat", "I can eat", "I can't eat"},
            {"Before it's over", "After it's over", "It can be over", "It can't be over"},
            {"I recommend and", "I recommend but", "Before I recommend", "After I recommend", "I can recommend", "I can't recommend"},
            {"I understand and", "I understand but", "After I understand", "I can understand", "I can't understand"},
            {"It's okay and", "It's okay but", "It can be okay", "It can't be okay"},
            {"I learn and", "I learn but", "Before I learn", "After I learn", "I can learn", "I can't learn"},
            {"It's good and", "It's good but", "It can be good", "It can't be good"},
            {"It's similar and", "It's similar but", "It can be similar", "It can't be similar"}
    };


    public String[] getBaseForm() {
        return baseForm;
    }

    public String[][] getConjugation() {
        return conjugation;
    }

    public String[][] getTranslate() {
        return translate;
    }

    public Lesson[] getLessons() {
        return lessons;
    }

    @Override
    public String getLessonId() {
        return lessonId;
    }

    @Override
    public String getLessonTitle() {
        return lessonTitle;
    }

    @Override
    public String getLessonSubTitle() {
        return lessonSubTitle;
    }

}
