package net.awesomekorean.podo.lesson.intermediateLessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessons.LessonItem;
import net.awesomekorean.podo.lesson.lessons.S_LessonInit;

import java.io.Serializable;

public class I_Lesson00 extends S_LessonInit implements I_Lesson, LessonItem, Serializable {

    private String lessonId = "IL_00";
    private String lessonTitle = "호텔예약";
    private String lessonSubTitle = "";

    private String[] dialog = {
            "이름이 뭐예요?",
            "데니예요.",
            "어느 나라 사람이에요?",
            "한국 사람이에요."
    };

    private String[] dialogEng = {
            "what is your name?",
            "I'm Danny.",
            "Where are you from?",
            "I'm Korean."
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
