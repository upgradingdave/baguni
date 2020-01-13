package net.awesomekorean.podo.lesson.lessonNumber.numbers;

import net.awesomekorean.podo.lesson.lessonNumber.Number;

import java.util.ArrayList;
import java.util.List;

public class NumberNative implements Number {

    String[] front = {
            "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20",
            "30","40","50","60","70","80","90"
    };

    String[] back = {
            "하나", "둘", "셋", "넷", "다섯", "여섯", "일곱", "여덟[여덜]", "아홉", "열", "열하나", "열둘", "열셋",
            "열넷", "열다섯", "열여섯", "열일곱", "열여덟[열여덜]", "열아홉", "스물", "서른", "마흔", "쉰", "예순",
            "일흔", "여든", "아흔"
    };


    @Override
    public String[] getFront() {
        return front;
    }

    @Override
    public String[] getBack() {
        return back;
    }
}
