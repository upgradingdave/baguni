package net.awesomekorean.podo.lesson.intermediateLessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessons.LessonItem;
import net.awesomekorean.podo.lesson.lessons.S_LessonInit;

import java.io.Serializable;

public class I_Lesson00 extends S_LessonInit implements I_Lesson, LessonItem, Serializable {

    private String lessonId = "IL_00";
    private String lessonTitle = "order";
    private String lessonSubTitle = "식당 예약";

    private String[] dialog = {
            "안녕하세요?\n포도 식당입니다.",
            "안녕하세요?\n오늘 저녁에 예약할 수 있어요?",
            "네, 몇 시에요?",
            "저녁 6시요.",
            "몇 분이에요?",
            "4명이요.",
            "네, 이름이 뭐예요?",
            "데니예요.",
            "예약했습니다.",
            "고맙습니다."
    };

    private String[] dialogEng = {
            "Hello?\nThis is ‘podo’ restaurant.",
            "Hello?\nCan I make a reservation\nfor this evening?",
            "Yes, what time?",
            "6 o'clock in the evening.",
            "How many people?",
            "4 people.",
            "ok, what's your name?",
            "I’m Danny.",
            "I made a reservation.",
            "Thank you."
    };

    private int[] peopleImage = {R.drawable.people1, R.drawable.people2};

    @Override
    public String getLessonSubTitle() {
        return lessonSubTitle;
    }

    @Override
    public String getLessonId() {
        return lessonId;
    }

    @Override
    public String[] getDialog() {
        return dialog;
    }

    @Override
    public String[] getDialogEng() {
        return dialogEng;
    }

    @Override
    public int[] getPeopleImage() {
        return peopleImage;
    }



    // 레슨어뎁터 아이템

    @Override
    public String getLessonTitle() {
        return lessonTitle;
    }

}
