package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading05 extends ReadingInit implements Reading {

    String readingId = "R_05";

    final String title = "한국의 김장문화";


    final String[] article = {
            "‘김장’은 추운 겨울이 오기 전에 ",
            "내년",
            " 동안 먹을 많은 김치를 친척이나 이웃들과 ",
            "같이",
            " 만드는 한국의 문화입니다. \n이 ",
            "시기",
            "를 ‘김장철’이라고 합니다. \n",
            "보통",
            " 11월에서 12월 사이에 김장하는 날을 하루 ",
            "정하",
            "면 사람들이 모두 모여서 같이 김장을 합니다. \n그래서 김장철에는 우리 집에서 김장하는 날을 사람들에게 ",
            "알려줘",
            "야 합니다. \n김장이 끝나면 만든 김치를 사람들에게 조금씩 ",
            "나누어 줍",
            "니다. \n\n김장을 하는 동안 재미있는 이야기도 하면서 친척들과 이웃들과 더 친해질 수 있습니다.\n이렇게 만든 많은 김치는 김치 냉장고에 ",
            "보관하",
            "면 시간에 따라 맛이 달라집니다. \n그래서 1년 동안 ",
            "다양한",
            " 맛의 김치를 먹을 수 있습니다.\n\n"
    };

    final String[] popUpFront = {"내년", "같이", "시기", "보통", "정하다", "알려주다", "나누어 주다", "보관하다", "다양하다"};
    final String[] popUpBack = {"next year", "together", "period", "usually", "set", "inform", "divide", "store", "various"};

    private int readingImage = R.drawable.kimchi;

    @Override
    public String getReadingId() {
        return readingId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String[] getArticle() {
        return article;
    }

    @Override
    public String[] getPopUpFront() {
        return popUpFront;
    }

    @Override
    public String[] getPopUpBack() {
        return popUpBack;
    }

    @Override
    public int getReadingImage() {
        return this.readingImage;
    }
}
