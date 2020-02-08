package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading14 extends ReadingInit implements Reading {

    String readingId = "R_14";
    int readingLevel = 2;
    final String title = "찜질방";


    final String[] article = {
            "한국에는 ‘대중 목욕탕’과 다른 ‘찜질방’이라는 것이 있습니다. \n‘대중 목욕탕’처럼 목욕을 할 수도 있고 여러가지 사우나와 식사, 영화, 게임 등 ",
            "다양한",
            " 것들을 모두 ",
            "즐길",
            " 수 있습니다.\n대부분의 찜질방이 ",
            "24시간 영업",
            "을 하고 손님들이 잠을 잘 수 있는 ",
            "공간",
            "도 있습니다.\n\n찜질방에 가면 꼭 먹어야 하는 음식이 있는데 그것은 ‘",
            "식혜",
            "’와 ‘",
            "삶은 계란",
            "’ 입니다.\n",
            "평소에",
            "도 맛있는 음식들이지만 찜질방에서 먹으면 더 맛있는 것 같습니다. \n\n한국 여행을 가면 사우나도 하고 다양한 즐길 것과 먹을 것도 있는 찜질방에 한 번 가보는 것이 어떨까요?\n\n"
    };

    final String[] popUpFront = {"다양한", "즐기다", "24시간 영업", "공간", "식혜", "삶은 계란", "평소에"};
    final String[] popUpBack = {"various", "enjoy", "open for 24 hours", "space", "sweet rice drink", "boiled egg", "usually"};

    private int readingImage = R.drawable.jjimjilbang;

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

    @Override
    public int getReadingLevel() {
        return this.readingLevel;
    }
}
