package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading18 extends ReadingInit implements Reading {

    String readingId = "R_18";

    final String title = "생일에 미역국을 먹는 이유";


    final String[] article = {
            "한국 사람들은 생일이 되면 꼭 ‘",
            "미역국",
            "’을 먹습니다. \n생일 케이크처럼 ",
            "보통",
            " 가족이나 친한 친구가 미역국을 ",
            "챙겨 줍",
            "니다. \n",
            "주변",
            "에 가족이나 친한 친구가 없어서 미역국을 먹지 못하면 슬픈 생일이라고 생각할 정도로 한국인에게 생일 미역국은 아주 중요한 음식입니다. \n\n그럼 생일에 왜 미역국을 챙겨주는 걸까요? \n미역은 아이를 ",
            "낳은",
            " 후에 몸을 ",
            "회복시켜",
            "주는데 아주 좋은 음식입니다. \n그래서 한국에서 아이를 낳으면 ",
            "며칠 동안",
            " 계속 미역국을 먹습니다. \n\n이렇게 미역국은 나를 낳아준 엄마가 먹었던 것이지만 시간이 지나면서 생일을 축하하는 의미로 미역국을 챙겨주는 문화가 생겼습니다.\n\n"
    };

    final String[] popUpFront = {"미역국", "보통", "챙겨 주다", "주변", "낳다", "회복시키다", "며칠 동안"};
    final String[] popUpBack = {"seaweed soup", "normally", "prepared", "around", "give birth", "recovery", "for a few days"};

    private int readingImage = R.drawable.seaweedsoup;

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
