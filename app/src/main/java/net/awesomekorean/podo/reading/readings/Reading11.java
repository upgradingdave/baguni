package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

import java.io.Serializable;

public class Reading11 extends ReadingInit implements Reading, Serializable {

    String readingId = "R_11";
    int readingLevel = 2;
    final String title = "한국의 식사 예절";


    final String[] article = {
            "한국 친구의 집에 식사 ",
            "초대",
            "를 받으면 한국의 식사 ",
            "예절",
            "을 배우는 것이 좋습니다. \n",
            "물론",
            " 여러분들은 외국인이기 때문에 너무 ",
            "걱정",
            "할 ",
            "필요는 없",
            "습니다. \n그냥 한국 문화를 ",
            "경험",
            "해 본다는 생각으로 재미있게 배우면 좋을 것 같습니다. \n\n- 가장 ",
            "어른",
            "이 ",
            "숟가락",
            "을 ",
            "든",
            " 후에 식사를 시작합니다. 음식을 빨리 먹고싶어서 먼저 숟가락을 들면 안 됩니다.\n\n- ",
            "반찬",
            "은 모든 사람이 같이 먹기 때문에 ",
            "젓가락",
            "으로 한 번 ",
            "집은",
            " 음식은 다시 ",
            "내려놓",
            "지 않습니다.\n\n- 음식은 ",
            "남기",
            "지 말고 다 먹는 것이 좋습니다. 음식이 많이 ",
            "남으면",
            " 음식을 만든 사람은 음식이 맛이 없어서 먹지 않는다고 생각할 수 있습니다. \n\n-",
            " 잔",
            "에 술이 있을 때는 술을 ",
            "추가",
            "하지 않아야 합니다. 잔에 술이 없을 때에만 술을 추가하는 것이 좋습니다.\n\n"
    };

    final String[] popUpFront = {"초대", "예절", "물론", "걱정", "필요 없다", "경험", "어른", "숟가락", "들다", "반찬", "젓가락", "집다", "내려놓다", "남기다", "남다", "잔", "추가"};
    final String[] popUpBack = {"invitation", "manners", "of course", "worry", "unnecessary", "experience", "older people", "spoon", "put up", "side dish", "chopsticks", "pick up", "put down", "leave", "be left", "glass", "addition"};

    private int readingImage = R.drawable.diningetiquette;

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
