package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading17 implements Reading {

    String readingId = "R_17";

    final String title = "되요 vs. 돼요";


    final String[] article = {
            "‘안 돼요’\n‘안 되요’\n무엇이 맞을까요?\n\n한국 사람도 많이 헷갈리는 ",
            "문법",
            " 중에 ‘되’와 ‘돼’가 있습니다. \n하지만 ",
            "생각보다",
            " 쉽습니다.\n\n먼저, ‘돼’는 ‘되어’의 ",
            "줄임말",
            "입니다. \n따라서 ‘돼’ 대신 ‘되어’를 써도 ",
            "어색하",
            "지 않아야 합니다. \n",
            "예를 들어",
            ",\n‘안 돼요’ = ‘안 되어요’\n‘해도 돼요’ = ‘해도 되어요’\n이렇게 ‘되어’로 바꾸어 써도 어색하지 않은 문장은 ‘돼’가 맞습니다.\n\n이제 ‘되’를 볼까요?\n‘안 됨’ = ‘안 되엄’ (?)\n‘해도 됩니다’ = ‘해도 되업니다’ (?)\n위 문장들은 ‘되어’로 바꾸면 어색한 문장이 됩니다.\n따라서 ‘되어’가 어색한 문장에는 ‘되’를 사용해야 합니다.\n\n"
    };

    final String[] popUpFront = {"문법", "생각보다", "줄임말", "어색하다", "예를 들어"};
    final String[] popUpBack = {"grammar", "more than I expected", "abbreviation", "awkward", "for example"};

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
