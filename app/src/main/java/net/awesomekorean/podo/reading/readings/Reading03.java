package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading03 extends ReadingInit implements Reading {

    String readingId = "R_03";

    final String title = "이상한 ‘한국 나이’";


    final String[] article = {
            "한국에서 나이를 ",
            "세",
            "는 ",
            "방법",
            "은 조금 ",
            "이상합",
            "니다.\n\n다른 나라에서는 아기가 ",
            "태어나",
            "면 ‘0살’이고 생일이 지나면 ‘+1’살이 되지만\n한국에서는 아기가 태어나면 ‘1살’이고 1월 1일이 되면 모든 사람이 ‘+1’살이 됩니다.\n",
            "만약",
            " 12월 31일에 아기가 태어나면 그 아기는 다음 날 2살이 됩니다.\n정말 이상하지요?\n그래서 외국 사람이 한국 사람한테 나이를 물어보면 ‘한국 나이’로 말해야 할지 ‘외국 나이’로 말해야 할지 ",
            "헷갈려",
            " 합니다.\n\n그리고 한국 사람들은 한국 나이를 ",
            "별로",
            " 좋아하지 않습니다.\n나이가 많아지는 것은 슬픈 일이니까요.\n",
            "얼마 전",
            "에 한국 나이가 없어질 수도 있다는 뉴스를 봤는데 빨리 그렇게 되었으면 좋겠습니다.\n\n"
    };

    final String[] popUpFront = {"세다", "방법", "이상하다", "태어나다", "만약", "헷갈리다", "별로", "얼마 전"};
    final String[] popUpBack = {"count", "way", "strange", "be born", "if", "be confused", "not that much", "lately"};

    private int readingImage = R.drawable.hangul;

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
