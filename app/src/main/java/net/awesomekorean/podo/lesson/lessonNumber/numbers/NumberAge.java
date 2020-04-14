package net.awesomekorean.podo.lesson.lessonNumber.numbers;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessons.LessonInit;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

public class NumberAge extends LessonInit implements Number, LessonItem {

    private String lessonId = "N_age";

    private String lessonTitle = "age";

    private String lessonSubTitle = "";

    private int lessonImage = R.drawable.numberage;

    private String[] front = {
            "1살", "2살", "3살", "4살", "5살", "6살", "7살", "8살", "9살", "10살", "11살", "12살", "13살", "14살",
            "15살", "16살", "17살", "18살", "19살", "20살", "21살", "22살", "23살", "24살", "25살", "26살", "27살",
            "28살", "29살", "30살", "40살", "50살", "60살", "70살", "80살", "90살", "100살"
    };

    private String[] back = {
            "한살", "두살", "세살", "네살", "다섯살", "여섯살", "일곱살", "여덟살[여덜살]", "아홉살", "열살", "열한살",
            "열두살", "열세살", "열네살", "열다섯살", "열여섯살", "열일곱살", "열여덟살", "열아홉살", "스무살[스물살x]",
            "스물 한살", "스물 두살", "스물 세살", "스물 네살", "스물 다섯살", "스물 여섯살", "스물 일곱살", "스물 여덟살",
            "스물 아홉살", "서른살", "마흔살", "쉰살", "예순살", "일흔살", "여든살", "아흔살", "백살"
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
