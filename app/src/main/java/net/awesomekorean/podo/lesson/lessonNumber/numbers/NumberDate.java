package net.awesomekorean.podo.lesson.lessonNumber.numbers;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessons.LessonInit;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

public class NumberDate extends LessonInit implements Number, LessonItem {

    private String lessonId = "N_date";

    private String lessonTitle = "date";

    private String lessonSubTitle = "";

    private int lessonImage = R.drawable.numberdate;

    private String[] front = {
            "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월",
            "1월 1일", "1월 23일", "2월 14일", "2월 20일", "3월 3일", "3월 19일", "4월 17일", "4월 30일",
            "5월 8일", "5월 31일", "6월 6일", "6월 27일", "7월 2일", "7월 11일", "8월 1일", "8월 15일",
            "9월 7일", "9월 28일", "10월 8일", "10월 10일", "11월 11일", "11월 22일", "12월 4일", "12월 25일"
    };

    private String[] back = {
            "일월[이뤌]", "이월", "삼월", "사월", "오월", "유월[육월x]", "칠월", "팔월", "구월", "시월[십월x]",
            "십일월[시비뤌]", "십이월[시비월]",
            "일월 일일[이릴]", "일월 이십삼일", "이월 십사일", "이월 이십일", "삼월 삼일", "삼월 십구일", "사월 십칠일",
            "사월 삼십일", "오월 팔일", "오월 삼십일일", "유월 육일", "유월 이십칠일", "칠월 이일", "칠월 십일일",
            "팔월 일일", "팔월 십오일", "구월 칠일", "구월 이십팔일", "시월 팔일", "시월 십일", "십일월 십일일",
            "십일월 이십이일", "십이월 사일", "십이월 이십오일"
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

    public int getLessonImage() {
        return lessonImage;
    }

    @Override
    public String[] getFront() {
        return front;
    }

    @Override
    public String[] getBack() {
        return back;
    }
}
