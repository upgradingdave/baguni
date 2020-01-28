package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading06 implements Reading {

    String readingId = "R_06";

    final String title = "한국의 전통주 막걸리";


    final String[] article = {
            "막걸리는 ",
            "쌀",
            "로 만든 한국의 ",
            "전통 술",
            "입니다. \n맛이 ",
            "달고",
            "",
            "부드러워",
            "서 한국 사람뿐만 아니라 외국 사람들에게도 인기가 많습니다.\n\n막걸리는 쌀을 ",
            "발효시켜",
            "서 만든 술이기 때문에 다른 술과 다르게 ",
            "유산균",
            "이 많이 들어있습니다. \n그래서 ",
            "장",
            " 건강에 도움을 줍니다. \n장의 건강은 ",
            "피부",
            "와 ",
            "면역력",
            "에도 좋은 ",
            "영향",
            "을 줍니다. \n하지만 막걸리 한 잔의 칼로리는 약 100kcal 로 높은 편이므로 너무 많이 마시면 다이어트에 좋지 않을 수도 있습니다. \n\n요즘은 바나나맛, 복숭아맛 등 다양한 맛의 막걸리도 나오고 있습니다.\n맛있다고 많이 마시면 쉽게 ",
            "취할",
            "수 있으니 ",
            "적당히",
            " 즐기면 좋을 것 같습니다.\n\n"
    };

    final String[] popUpFront = {"쌀", "전통 술", "달다", "부드럽다", "발효시키다", "유산균", "장", "피부", "면역력", "영향", "취하다", "적당히"};
    final String[] popUpBack = {"rice", "traditional wine", "sweet", "soft", "ferment", "lactic acid bacteria", "intestine", "skin", "immunity", "effect", "get drunk", "suitably"};

    private int readingImage = R.drawable.makguli;
    private boolean isCompleted = false;

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
    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    @Override
    public void setIsCompleted(boolean b) {
        this.isCompleted = b;
    }
}
