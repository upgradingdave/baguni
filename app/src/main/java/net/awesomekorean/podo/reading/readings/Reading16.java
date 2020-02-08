package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading16 extends ReadingInit implements Reading {

    String readingId = "R_16";
    int readingLevel = 2;
    final String title = "한복";


    final String[] article = {
            "한복은 ",
            "직선",
            "과 ",
            "곡선",
            "이 ",
            "조화를 이룬",
            " 한국의 전통 옷입니다. \n한복은 몸에 ",
            "딱 붙",
            "지 않아 ",
            "움직이",
            "기 ",
            "편하",
            "고 다양한 색을 사용하여 ",
            "아름답",
            "습니다.\n\n요즘에는 ",
            "명절",
            "이나 ",
            "결혼식",
            " 때 한복을 많이 입습니다. \n전통한복을 ",
            "현대",
            "에 ",
            "어울리",
            "는 디자인으로 만들어 ",
            "일상생활",
            "에서도 편하게 입을 수 있는 ‘개량한복’도 있습니다.\n\n‘경복궁’이나 ‘민속촌’에서는 한복을 입은 손님에게 ",
            "무료 입장",
            "이나 ",
            "입장료 할인",
            " ",
            "혜택",
            "을 주기도 합니다.\n나에게 잘 어울리는 한복을 입고 옛날 한국인이 되어 보는 것도 재미있는 ",
            "경험",
            "이 될 것 같습니다.\n\n"
    };

    final String[] popUpFront = {"직선", "곡선", "조화를 이루다", "딱 붙다", "움직이다", "편하다", "아름답다", "명절", "결혼식", "현대", "어울리다", "일상생활", "무료 입장", "입장료 할인", "혜택", "경험"};
    final String[] popUpBack = {"straight line", "curve", "be harmonized", "skintight", "move", "comfortable", "beautiful", "traditional holiday", "wedding ceremony", "modern times", "suit", "daily life", "free entrance", "entrance fee discount", "benefit", "experience"};

    private int readingImage = R.drawable.hanbok;

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
