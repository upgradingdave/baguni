package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading07 extends ReadingInit implements Reading {

    String readingId = "R_07";

    final String title = "감사합니다 vs. 고맙습니다";


    final String[] article = {
            "",
            "여러분",
            "은 ‘감사합니다’와 ‘고맙습니다’ 중 어떤 말을 더 많이 사용하나요? \n두 말은 어떻게 다를까요? \n\n‘감사합니다’는 ‘감사’(感謝)라는 한자에서 온 말이고 ‘고맙습니다’는 ",
            "순수한",
            " 한국어입니다.\n의미는 ",
            "완전히",
            " 같습니다. \n어떤 사람들은 ‘감사합니다’가 더 ",
            "공손한",
            " 표현이라고 하는데 이것은 잘못된 생각입니다. \n\n두 말은 100% 같은 의미이고 모두 공손한 표현이므로 쓰고 싶은 말을 사용하면 됩니다.\n\n"
    };

    final String[] popUpFront = {"여러분", "순수하다", "완전히", "공손하다"};
    final String[] popUpBack = {"everyone", "pure", "totally", "polite"};

    private int readingImage = R.drawable.thankyou;

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
