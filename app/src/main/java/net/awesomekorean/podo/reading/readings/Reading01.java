package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading01 implements Reading {

    String readingId = "R_01";

    final String title = "‘당신’, ‘그/그녀’ 대신 사용할 수 있는 표현";


    final String[] article = {
            "영어의 ‘you/he/she’를 한국어 사전에서 찾으면  ‘당신/그/그녀’라고 나옵니다.\n하지만 한국어로 말을 할 때 ‘당신/그/그녀’는 ",
            "자연스럽",
            "지 않은 ",
            "표현",
            "이 될 수 있습니다.\n\n",
            "예전에",
            " 한 학생이 저에게 ‘당신의 블로그를 봤어요.’라는 메시지를 보냈습니다.\n쉽게 이해할 수는 있었지만 조금 ",
            "어색한",
            " 문장이었습니다.\n",
            "그럼",
            " ‘you/he/she’를 한국어에서는 어떻게 사용해야 할까요?\n\n방법은 ‘이름’이나 ‘직업’을 사용하는 것입니다.\n위 문장을 ‘데니 씨의 블로그를 봤어요’나 ‘선생님의 블로그를 봤어요’로 바꾸면 더 자연스러운 표현이 될 수 있습니다.\n\n만약에 이름도 모르고 직업도 모르는 사람에 대해 이야기를 할 때는 어떻게 해야 할까요?\n",
            "가까운",
            " 것과 ",
            "먼",
            " 것을 말할 때 ‘이것/저것/그것’이라고 말하는 것처럼\n남자나 여자 모두 ‘이 사람/저 사람/ 그 사람’이라고 하면 됩니다.\n좀 더 ",
            "예의 있게",
            " 말하고 싶으면 ‘이분 / 저분 / 그분’ 이라고 할 수도 있습니다.\n\n"
    };

    final String[] popUpFront = {"자연스럽다", "표현", "예전에", "어색하다", "그럼", "가깝다", "멀다", "예의 있다"};
    final String[] popUpBack = {"natural", "expression", "in the past", "awkward", "then", "near", "far", "have good manners"};

    private int readingImage = R.drawable.hangul;
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
