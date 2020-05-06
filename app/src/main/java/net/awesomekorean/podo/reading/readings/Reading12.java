package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

import java.io.Serializable;

public class Reading12 extends ReadingInit implements Reading, Serializable {

    String readingId = "R_12";
    int readingLevel = 2;
    final String title = "집들이 선물";


    final String[] article = {
            "",
            "새로운",
            " 집에 ",
            "이사",
            "를 갔을 때 친구들을 집에 ",
            "초대",
            "하는 것을 ‘집들이’라고 합니다. \n한국 친구의 집들이에 초대를 받으면 어떤 선물을 하는 것이 좋을까요? \n\n물론 친구가 좋아하는 선물을 준비하는 것이 좋겠지만 무엇을 좋아하는지 잘 모를 때는 ‘",
            "휴지",
            "’나 ‘",
            "세제",
            "’를 선물로 줄 수 있습니다. \n‘휴지 선물’과 ‘세제 선물’ 조금 ",
            "이상한",
            "가요? \n\n아주 옛날에는 새로운 집에 이사를 가면 나쁜 ",
            "느낌",
            "을 없애고 좋은 느낌을 부르는 ",
            "제사",
            "를 했습니다. \n그래서 요즘에는 휴지와 세제처럼 더러운 것을 깨끗하게 해주는 물건을 선물하게 되었다고 합니다. \n\n",
            "또한",
            " ‘휴지를 ",
            "풀다",
            "’에서 ‘풀다’는 어려운 ",
            "문제",
            "나 ",
            "고민",
            "이 ",
            "해결된다",
            "는 뜻도 있기 때문에 새로운 집에서 모든 일이 잘 풀리기를 ",
            "바란다",
            "는 의미도 가지고 있습니다.\n",
            "게다가",
            " 휴지나 세제는 모든 집에 꼭 필요한 물건이고 아무리 많아도 다 사용할 수 있기 때문에 아주 ",
            "실용적",
            "이기도 합니다.\n\n어때요? 생각보다 괜찮은 선물이지요?\n\n"
    };

    final String[] popUpFront = {"새로운", "이사", "초대", "휴지", "세제", "이상하다", "느낌", "제사", "또한", "풀다", "문제", "고민", "해결되다", "바라다", "게다가", "실용적이다"};
    final String[] popUpBack = {"new", "move", "invitation", "tissue", "detergent", "strange", "feeling", "ancestral rites", "in addition", "be solved", "trouble", "worry", "be solved", "wish", "moreover", "practical"};

    private int readingImage = R.drawable.jipdli;

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
