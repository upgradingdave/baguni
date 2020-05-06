package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

import java.io.Serializable;

public class Reading09 extends ReadingInit implements Reading, Serializable {

    String readingId = "R_09";
    int readingLevel = 2;
    final String title = "왜 ‘한’ 시 ‘일’ 분일까?";


    final String[] article = {
            "한국어에는 ‘한자어 숫자’(sino-Korean number)와 ‘고유어 숫자’(native-Korean number)가 있습니다.\n그래서 한국어를 공부하는 학생들이 숫자를 많이 어려워 합니다. \n특히 시간을 이야기 할 때 ‘시간’은 한자어를 ‘분’은 고유어를 사용해야 하기 때문에 정말 ",
            "헷갈려",
            " 합니다. \n그럼 왜 이렇게 사용하는 걸까요? \n\n",
            "이유",
            "는 아무도 모릅니다. \n하지만 어떤 사람들은 이렇게 생각합니다. \n아주 ",
            "옛날",
            "에 한국에 시계가 없었을 때에는 ",
            "해",
            "와 ",
            "달",
            "이 어디에 있는지를 보고 시간을 알 수 있었습니다. \n",
            "대략",
            " 시간은 알 수 있었지만 ",
            "정확한",
            " ‘분’이나 ‘",
            "초",
            "’는 알 수 없었습니다. \n그래서 사람들에게 ",
            "익숙한",
            " ‘시간’에는 고유어를 사용하지만 ",
            "나중에",
            " 중국에서 시계가 들어온 후 알게 된 ‘분’이나 ‘초’는 한자어를 사용하게 되었다는 ",
            "추측",
            "이 있습니다.\n\n대부분의 한국 사람들은 ",
            "습관적으로",
            " ‘시간’과 ‘분’을 ",
            "자연스럽",
            "게 사용합니다. \n사실 ‘시간’과 ‘분’을 다르게 쓰고 있다는 사실도 모르는 사람들도 많습니다. \n여러분도 많이 듣고 많이 말하면 생각하지 않아도 ",
            "정확히",
            " 사용할 수 있을거라고 생각합니다.\n\n"
    };

    final String[] popUpFront = {"헷갈리다", "이유", "옛날", "해", "달", "대략", "정확하다", "초", "익숙한", "나중에", "추측", "습관적으로", "자연스럽다", "정확하다"};
    final String[] popUpBack = {"be confused", "reason", "the old times", "sun", "moon", "approximately", "exact", "second", "familiar", "later", "guess", "habitually", "natural", "correct"};

    private int readingImage = R.drawable.onehouronemin;

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
