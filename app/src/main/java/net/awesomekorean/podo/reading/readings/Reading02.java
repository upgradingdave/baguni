package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading02 extends ReadingInit implements Reading {

    String readingId = "R_02";

    final String title = "한국어의 존댓말";


    final String[] article = {
            "한국 사람들에게 ",
            "존댓말",
            "은 아주 ",
            "중요합",
            "니다.\n처음 보는 사람이나 나이가 많은 사람에게는 꼭 존댓말을 사용해야 합니다.\n왜냐하면 한국에는 존댓말을 사용해서 ",
            "상대방",
            "을 ‘",
            "존중",
            "’하는 ",
            "문화",
            "가 있기 때문입니다.\n\n보통 ‘(으)시’를 사용해서 존댓말을 만들지만 ",
            "전혀",
            " 다른 단어를 사용해야 할 때도 있습니다. 그래서 많은 한국어 학습자들이 존댓말 때문에 한국어가 어렵다고 말합니다.\n하지만 처음에는 ‘아요/어요’ 만 사용해도 ",
            "충분합",
            "니다.\n",
            "대부분",
            "의 한국 사람들은 한국어를 공부하는 외국인들이 ",
            "이미",
            " 한국 문화를 존중하고 있다고 생각하기 때문입니다.\n\n"
    };

    final String[] popUpFront = {"존댓말", "중요하다", "상대방", "존중", "문화", "전혀", "충분하다", "대부분", "이미"};
    final String[] popUpBack = {"honorific", "important", "the other side person", "respect", "culture", "totally", "enough", "mostly", "already"};

    private int readingImage = R.drawable.honorific;

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
