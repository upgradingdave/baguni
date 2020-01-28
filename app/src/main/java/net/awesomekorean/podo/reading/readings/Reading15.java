package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading15 extends ReadingInit implements Reading {

    String readingId = "R_15";

    final String title = "한옥의 온돌과 마루";


    final String[] article = {
            "한옥은 ",
            "나무",
            ", ",
            "흙",
            ", ",
            "돌",
            " 과 같은 ",
            "자연",
            "의 것들로 만든 한국의 ",
            "전통",
            " 집입니다. \n\n한옥의 ",
            "방바닥",
            "을 ‘온돌’이라고 하는데 주방에서 ",
            "불을 피우",
            "면 그 ",
            "열",
            "이 방바닥을 따뜻하게 해줍니다. \n온돌은 지금 한국의 아파트나 집에서도 볼 수 있습니다. \n온돌이 있는 따뜻한 방에 있으면 추운 겨울도 문제 없습니다.\n\n온돌과 ",
            "반대로",
            " ‘마루’라는 것도 있습니다. \n마루는 방 밖에 있는 나무로 만든 바닥입니다. \n",
            "땅",
            "과 ",
            "떨어져",
            " 있어 여름에 아주 시원합니다. \n\n한국에는 봄, 여름, 가을, 겨울의 ",
            "사계절",
            "이 있기 때문에 한옥은 따뜻함과 시원함을 모두 줄 수 있도록 만들었습니다. \n\n서울과 전주 등 유명한 한옥",
            "마을",
            "이 있는 곳이 있습니다. \n호텔도 좋지만 한옥에서 하루를 지내보는 것도 좋을 것 같습니다.\n\n"
    };

    final String[] popUpFront = {"나무", "흙", "돌", "자연", "전통", "방바닥", "불을 피우다", "열", "반대로", "땅", "떨어지다", "사계절", "마을"};
    final String[] popUpBack = {"wood", "soil", "stone", "nature", "tradition", "floor of the room", "make a fire", "heat", "the other way", "ground", "fall", "four seasons", "village"};

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
