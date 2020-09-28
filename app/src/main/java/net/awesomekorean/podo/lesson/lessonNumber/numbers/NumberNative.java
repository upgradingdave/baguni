package net.awesomekorean.podo.lesson.lessonNumber.numbers;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessons.LessonItem;
import net.awesomekorean.podo.lesson.lessons.S_Lesson01;
import net.awesomekorean.podo.lesson.lessons.S_LessonInit_Unlock;

public class NumberNative extends S_LessonInit_Unlock implements Number, LessonItem {

    private String lessonId = "N_native";

    private String lessonTitle = "native number";

    private String lessonSubTitle = "하나, 둘, 셋...";

    private LessonItem specialLesson = new NumberPractice();

    private String[] front = {
            "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20",
            "30","40","50","60","70","80","90"
    };

    private String[] back = {
            "하나", "둘", "셋", "넷", "다섯", "여섯", "일곱", "여덟[여덜]", "아홉", "열", "열하나", "열둘", "열셋",
            "열넷", "열다섯", "열여섯", "열일곱", "열여덟[열여덜]", "열아홉", "스물", "서른", "마흔", "쉰", "예순",
            "일흔", "여든", "아흔"
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
    public String getLessonTitle() {
        return lessonTitle;
    }

    @Override
    public String[] getFront() {
        return front;
    }

    @Override
    public String[] getBack() {
        return back;
    }

    @Override
    public LessonItem getSLesson() {
        return specialLesson;
    }
}
