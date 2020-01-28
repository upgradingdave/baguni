package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading19 implements Reading {

    String readingId = "R_19";

    final String title = "한국의 명절 - 설날";


    final String[] article = {
            "‘설날’은 ",
            "음력 새해",
            "를 말하며 ‘설’이라고도 합니다. \n설날에는 ",
            "친척",
            "들이 모두 모여서 맛있는 음식도 만들어 먹고 ",
            "전통놀이",
            "를 하기도 합니다. \n\n설날에 먹는 가장 중요한 음식은 ‘떡국’ 입니다. \n설날에 떡국을 먹으면 나이를 한 살 더 먹는다는 의미가 있습니다.\n\n그리고 설날에는 아이들이 가장 좋아하는 ‘세배’도 합니다. \n세배는 ",
            "어른",
            "들께 ‘새해 복 많이 받으세요’라고 말하면서 ",
            "절",
            "을 하는 것을 말합니다. \n절을 받은 어른들은 좋은 이야기를 해주고 아이들에게는 돈을 줍니다. \n이것을 ‘세뱃돈’이라고 합니다. \n그래서 아이들은 모든 어른들에게 세배를 하고 싶어합니다.\n\n설날에 하는 전통놀이도 많습니다. \n친척들과 팀을 나누어 ‘윷놀이’, ‘널뛰기’, ‘제기차기’ 등을 하며 즐거운 설날을 보냅니다.\n\n"
    };

    final String[] popUpFront = {"음력 새해", "친척", "전통놀이", "어른", "절"};
    final String[] popUpBack = {"lunar new year", "relative", "traditional game", "older people", "bow"};

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
