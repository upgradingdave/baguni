package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

import java.io.Serializable;

public class Reading13 extends ReadingInit implements Reading, Serializable {

    String readingId = "R_13";
    int readingLevel = 2;
    final String title = "때밀이 문화";


    final String[] article = {
            "한국 사람들은 집에서 ",
            "욕조",
            "에 뜨거운 물을 채우거나 ‘",
            "대중 목욕탕",
            "’에 가서 목욕을 합니다. \n\n한국의 목욕 문화에서 가장 ",
            "독특한",
            " 것은 ‘때밀이’ 입니다. \n‘때’는 ",
            "피부",
            "에 ",
            "붙어있는",
            " 더러운 것들을 말합니다. \n",
            "평소에",
            "는 눈에 잘 보이지 않지만 뜨거운 물이 있는 욕조에 몇 분 동안 들어갔다가 나온 후에 때밀이 타월로 몸을 ",
            "문지르",
            "면 보이게 됩니다. \n이렇게 목욕을 하면 피부가 ",
            "부드러워집",
            "니다. \n\n하지만 피부를 너무 ",
            "세게",
            " 문지르거나 ",
            "자주",
            " 때를 미는 것은 ",
            "오히려",
            " 피부를 ",
            "다치",
            "게 할 수 있기 때문에 조심해야 합니다.\n\n"
    };

    final String[] popUpFront = {"욕조", "대중 목욕탕", "독특하다", "피부", "붙어있다", "평소에", "문지르다", "부드러워지다", "세게", "자주", "오히려", "다치다"};
    final String[] popUpBack = {"bathtub", "public bath", "unusual", "skin", "stick", "usually", "scrub", "soften", "strongly", "often", "instead", "hurt"};

    private int readingImage = R.drawable.ddaemili;

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
