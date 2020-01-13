package net.awesomekorean.podo.lesson.lessonNumber.numbers;

import net.awesomekorean.podo.lesson.lessonNumber.Number;

public class NumberSino implements Number {

    String[] front = {
            "0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","30",
            "40","50","60","70","80","90",
            "100","1,000","10,000","100,000","1,000,000","10,000,000","100,000,000"};

    String[] back = {
            "영", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구", "십", "십일[시빌]", "십이[시비]", "십삼",
            "십사", "십오[시보]", "십육[심뉵]", "십칠", "십팔", "십구", "이십", "삼십", "사십", "오십", "육십", "칠십",
            "팔십", "구십", "백", "천", "만", "십만[심만]", "백만[뱅만]", "천만", "억"
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
