package net.awesomekorean.podo.lesson.lessonNumber.numbers;

import net.awesomekorean.podo.lesson.lessonNumber.Number;

public class NumberTime implements Number {

    String[] front = {
            "1시","2시","3시","4시","5시","6시","7시","8시","9시","10시","11시","12시",
            "1시 30분", "2시 13분", "3시 49분", "5시 32분", "6시 51분", "8시 8분", "9시 16분", "10시 49분", "12시 12분"
    };

    String[] back = {
            "한시", "두시", "세시", "네시", "다섯시", "여섯시", "일곱시", "여덟시[여덜시]", "아홉시", "열시", "열한시",
            "열두시", "한시 삼십분(반)", "두시 십삼분", "세시 사십구분", "다섯시 삼십이분", "여섯시 오십일분", "여덟시 팔분",
            "아홉시 십육분[심뉵분]", "열시 사십구분", "열두시 십이분[시비분]"
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
