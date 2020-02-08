package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading10 extends ReadingInit implements Reading {

    String readingId = "R_10";
    int readingLevel = 2;
    final String title = "태극기의 의미";


    final String[] article = {
            "한국 ",
            "국기",
            "의 이름은 ‘태극기’입니다.\n태극기는 다른 나라들의 국기와 ",
            "비교하",
            "면 조금 ",
            "복잡한",
            " ",
            "모양",
            "을 가지고 있습니다. \n어떤 의미를 가지고 있을까요?\n\n",
            "가운데",
            "에 빨간색과 파란색은 ",
            "각각",
            " (+) 와 (-)를 의미합니다. \n",
            "세상",
            "의 모든 것이 (+)와 (-)의 ",
            "조화",
            "로 ",
            "이루어진다",
            "는 뜻을 가지고 있습니다. \n그리고 그 ",
            "주위",
            "에 4가지의 검은색 선들은 각각 하늘, 땅, 물, 불을 의미합니다. \n",
            "마지막으로",
            " 하얀색 ",
            "바탕",
            "은 옛날부터 하얀색 옷을 즐겨 입었던 한국인을 의미하는데 이 하얀색은 ",
            "밝음",
            ", ",
            "순수",
            ", ",
            "평화",
            "를 나타냅니다. \n\n",
            "즉",
            ", 태극기는 ",
            "자연",
            "과 조화를 이루며 평화를 사랑하는 한국인이라는 뜻을 가지고 있습니다. \n\n"
    };

    final String[] popUpFront = {"국기", "비교하다", "복잡하다", "모양", "가운데", "각각", "세상", "조화", "이루어지다", "주위", "마지막으로", "바탕", "밝음", "순수", "평화", "즉", "자연"};
    final String[] popUpBack = {"national flag", "compare", "complicated", "shape", "center", "each", "the world", "harmony", "consist of", "surroundings", "finally", "background", "brightness", "purity", "peace", "in other words", "nature"};

    private int readingImage = R.drawable.koreanflag;

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
