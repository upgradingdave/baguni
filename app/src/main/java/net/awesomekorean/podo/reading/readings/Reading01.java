package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

import java.io.Serializable;

public class Reading01 extends ReadingInit implements Reading, Serializable {

    String readingId = "R_01";
    int readingLevel = 1;
    final String title = "‘당신’, ‘그/그녀’ 보다 좋은 표현";


    final String[] article = {
            "영어의 ‘you/he/she’를 한국어 사전에서 찾으면  ‘당신/그/그녀’가 나옵니다.\n하지만 한국어로 말을 할 때 ‘당신/그/그녀’는 ",
            "자연스럽",
            "지 않은 ",
            "표현",
            "입니다.\n\n",
            "예전에",
            " 한 학생이 ‘당신의 블로그를 봤어요.’라고 말했습니다.\n쉽게 이해할 수 있었지만 조금 ",
            "어색한",
            " 문장이었습니다.\n",
            "그럼",
            " ‘you/he/she’를 한국어에서는 어떻게 사용해야 할까요?\n\n‘이름’이나 ‘직업’을 말하면 됩니다.\n‘데니 씨의 블로그를 봤어요’나 ‘선생님의 블로그를 봤어요’로 ",
            "바꾸",
            "면 더 자연스러운 표현이 됩니다.\n\n그럼 이름도 모르고 직업도 모를 때는 어떻게 해야 할까요?\n",
            "가까운",
            " 것과 ",
            "먼",
            " 것을 말할 때 ‘이것/저것/그것’이라고 하는 것",
            "처럼",
            "‘이 사람/저 사람/ 그 사람’이라고 하면 됩니다.\n좀 더 ",
            "예의 있게",
            " 말하고 싶으면 ‘이분 / 저분 / 그분’ 이라고 할 수도 있습니다.\n\n"
    };

    final String[] popUpFront = {"자연스럽다", "표현", "예전에", "어색하다", "그럼", "바꾸다", "가깝다", "멀다", "처럼", "예의 있다"};
    final String[] popUpBack = {"natural", "expression", "in the past", "awkward", "then", "change", "near", "far", "like, as", "have good manners"};

    private int readingImage = R.drawable.heshe;

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
