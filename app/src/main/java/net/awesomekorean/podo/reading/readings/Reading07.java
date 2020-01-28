package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading07 implements Reading {

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
            " 표현이라고 하는데 이는 잘못된 생각입니다. \n\n아주 ",
            "옛날",
            "에 한글이 아직 없었을 때 한국 사람들은 중국의 한자를 사용했습니다. \n하지만 한자는 배우기가 어려워서 ",
            "똑똑하",
            "고 돈이 많은 사람들만 사용할 수 있었습니다. \n그리고 그 사람들은 자기들이 ",
            "특별하다",
            "는 것을 보여주고 싶어서 한자로 된 단어를 더 많이 사용했습니다.\n그 때의 생각이 잘못 ",
            "전해져",
            "서 한자로 된 단어를 더 좋은 표현으로 알고 있는 사람도 있게 되었습니다.\n\n‘감사합니다’가 안 좋은 말이라는 뜻은 아닙니다. \n두 말은 100% 같은 의미이고 모두 공손한 표현이므로 ",
            "그냥",
            " 둘 중에 쓰고 싶은 말을 사용하면 됩니다.\n\n"
    };

    final String[] popUpFront = {"여러분", "순수하다", "완전히", "공손하다", "옛날", "똑똑하다", "특별하다", "전해지다", "그냥"};
    final String[] popUpBack = {"everyone", "pure", "absolutely", "polite", "the old times", "smart", "special", "be told", "just"};

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
