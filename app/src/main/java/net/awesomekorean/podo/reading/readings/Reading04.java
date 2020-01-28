package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading04 implements Reading {

    String readingId = "R_04";

    final String title = "혈액형의 의미";


    final String[] article = {
            "",
            "혈액형",
            "은 큰 ",
            "사고가 났",
            "을 때 중요한 ",
            "정보",
            "입니다. \n하지만 한국 사람들은 다른 ",
            "의미",
            "로도 혈액형에 ",
            "관심",
            "이 많습니다. \n같은 혈액형은 비슷한 ",
            "성격",
            "을 가지고 있다고 생각하기 때문입니다. \n물론 ",
            "재미로",
            " 그렇게 생각합니다.\n모든 사람의 성격을 4가지로 나누는 것은 ",
            "말도 안되",
            "기 때문입니다. \n하지만 비슷한 경우도 많습니다. \n한 번 같이 볼까요?\n\nA형 : ",
            "신중한",
            "성격이고 상대방의 말을 잘 들어줍니다. 하지만 ",
            "소심하",
            "고 ",
            "걱정",
            "이 많습니다.\n\nB형 : ",
            "자유로운",
            " 생각을 가지고 있으며 아이디어가 많습니다. 하지만 한 가지 일에 ",
            "집중",
            "을 잘 못합니다.\n\nO형 : 성격이 ",
            "밝고",
            " 사람을 잘 도와줘서 ",
            "인기가 많",
            "습니다. 하지만 너무 친절해서 ",
            "귀찮",
            "을 수도 있습니다.\n\nAB형 : ",
            "머리가 좋",
            "고 재미있는 말을 잘 합니다. 하지만 ",
            "싫증",
            "을 잘 내고 빨리 ",
            "포기합",
            "니다.\n\n"
    };

    final String[] popUpFront = {"혈액형", "사고가 나다", "정보", "의미", "관심", "성격", "재미로", "말도 안되다", "신중하다", "소심하다", "걱정", "자유롭다", "집중", "밝다", "인기가 많다", "귀찮다", "머리가 좋다", "싫증 내다", "포기하다"};
    final String[] popUpBack = {"blood type", "have an accident", "information", "meaning", "interest", "personality", "for fun", "ridiculous", "careful", "timid", "worry", "free", "concentration", "bright", "popular", "annoying", "smart", "lose interest", "give up"};

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
