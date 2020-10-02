package net.awesomekorean.podo.lesson.lessonReviewRewards;

import net.awesomekorean.podo.lesson.lessons.Lesson;
import net.awesomekorean.podo.lesson.lessons.Lesson00;
import net.awesomekorean.podo.lesson.lessons.Lesson01;
import net.awesomekorean.podo.lesson.lessons.Lesson02;
import net.awesomekorean.podo.lesson.lessons.Lesson03;
import net.awesomekorean.podo.lesson.lessons.Lesson19;
import net.awesomekorean.podo.lesson.lessons.Lesson24;
import net.awesomekorean.podo.lesson.lessons.Lesson25;
import net.awesomekorean.podo.lesson.lessons.Lesson26;
import net.awesomekorean.podo.lesson.lessons.Lesson30;
import net.awesomekorean.podo.lesson.lessons.Lesson31;
import net.awesomekorean.podo.lesson.lessons.Lesson32;
import net.awesomekorean.podo.lesson.lessons.Lesson33;
import net.awesomekorean.podo.lesson.lessons.Lesson34;
import net.awesomekorean.podo.lesson.lessons.LessonInit;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import java.io.Serializable;

public class LessonReview04 extends LessonInit implements LessonItem, LessonReview, Serializable {

    private String lessonId = "LR_04";
    private String lessonTitle = "";
    private String lessonSubTitle = "";

    private Lesson[] lessons = {
            new Lesson24(), new Lesson25(), new Lesson26(), new Lesson30(), new Lesson31(), new Lesson32(),
            new Lesson33(), new Lesson34()
    };

    private String[] baseForm = {
            "아프다", "자다", "알다", "모르다", "쓰다"
            , "가르치다", "만들다", "받다", "비가 내리다", "가져오다"
            , "내리다", "도착하다", "늦다", "듣다", "보내다"
            , "괜찮다"};

    private String[][] conjugation = {
            {"아파도", "-", "아파 본 적 있어요", "아픈 것 같아요", "아플 것 같아요", "-", "-", "-"},
            {"자도", "-", "-", "자는 것 같아요", "잘 것 같아요", "자세요", "자야 돼요", "자도 돼요"},
            {"알아도", "-", "-", "아는 것 같아요", "알 것 같아요", "-", "알아야 돼요", "-"},
            {"몰라도", "-", "-", "모르는 것 같아요", "모를 것 같아요", "-", "-", "몰라도 돼요"},
            {"써도", "써 보다", "써 본 적 있어요", "쓰는 것 같아요", "쓸 것 같아요", "쓰세요", "써야 돼요", "써도 돼요"},
            {"가르쳐도", "가르쳐 보다", "가르쳐 본 적 있어요", "가르치는 것 같아요", "가르칠 것 같아요", "가르치세요", "가르쳐야 돼요", "가르쳐도 돼요"},
            {"만들어도", "만들어 보다", "만들어 본 적 있어요", "만드는 것 같아요", "만들 것 같아요", "만드세요", "만들어야 돼요", "만들어도 돼요"},
            {"받아도", "받아 보다", "받아 본 적 있어요", "받는 것 같아요", "받을 것 같아요", "받으세요", "받아야 돼요", "받아도 돼요"},
            {"비가 내려도", "-", "-", "비가 내리는 것 같아요", "비가 내릴 것 같아요", "-", "비가 내려야 돼요", "-"},
            {"가져와도", "가져와 보다", "가져와 본 적 있어요", "-", "가져올 것 같아요", "가져오세요", "가져와야 돼요", "가져와도 돼요"},
            {"-", "-", "-", "-", "내릴 것 같아요", "내리세요", "내려야 돼요", "내려도 돼요"},
            {"도착해도", "-", "-", "-", "도착할 것 같아요", "-", "-", "-"},
            {"늦어도", "-", "-", "늦는 것 같아요", "늦을 것 같아요", "-", "-", "늦어도 돼요"},
            {"들어도", "들어 보다", "들어 본 적 있어요", "듣는 것 같아요", "들을 것 같아요", "들으세요", "들어야 돼요", "들어도 돼요"},
            {"보내도", "보내 보다", "보내 본 적 있어요", "보내는 것 같아요", "보낼 것 같아요", "보내세요", "보내야 돼요", "보내도 돼요"},
            {"-", "-", "-", "괜찮은 것 같아요", "괜찮을 것 같아요", "-", "괜찮아야 돼요", "-"}
    };

    private String[][] translate = {
            {"Even if it hurts", "-", "Have you ever been sick", "I think I'm sick", "I think it will get sick", "-", "-", "-"},
            {"Even if I sleep", "-", "-", "I think he is sleeping", "I think I'll sleep", "Take a sleep", "I have to sleep", "You can sleep"},
            {"Even if I know", "-", "-", "I think he knows", "I think I may know", "-", "I have to know", "-"},
            {"Even if I don't know", "-", "-", "I think I don't know", "I think I may don't know", "-", "-", "You don't need to know"},
            {"Even if I use", "Try to use", "Have you ever used it", "I think he is using", "I think I will use it", "Use it", "I have to use it", "You can use it"},
            {"Even if I teach", "Try to teach", "Have you ever taught", "I think he is teaching", "I think I will teach", "Teach him", "I have to teach", "You can teach"},
            {"Even if I make", "Try to make", "Have you ever made", "I think he is making", "I think I will make it", "Make it", "I have to make it", "You can make it"},
            {"Even if I receive", "Try to receive", "Have you ever received it", "I think he is receiving", "I think I will receive", "Receive it", "I have to receive it", "You can receive it"},
            {"Even if it rains", "-", "-", "I think it's raining", "I think it's going to rain", "-", "It has to rain", "-"},
            {"Even if I bring it", "Try to bring", "Have you ever brought it", "-", "I think I will bring", "Bring it", "I have to bring it", "You can bring it"},
            {"-", "-", "-", "-", "I think I will get off", "Get off", "I have to get off", "You can get off"},
            {"Even if I arrive", "-", "-", "-", "I think I will arrive", "-", "-", "-"},
            {"Even if I'm late", "-", "-", "I think he is late", "I think I will be late", "-", "-", "You can be late"},
            {"Even if I listen", "Try to listen", "Have you ever heard?", "I think I hear/listen", "I think I will hear/listen", "Listen it", "I have to listen", "You can listen"},
            {"Even if I send", "Try to send", "Have you ever sent", "I think he is sending", "I think I will send", "Send it", "I have to send", "You can send it"},
            {"-", "-", "-", "I think it's okay", "I think it will be okay", "-", "It should be okay", "-"}
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
