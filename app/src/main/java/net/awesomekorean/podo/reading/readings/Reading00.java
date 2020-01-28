package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading00 implements Reading {

    String readingId = "R_00";

    final String title = "‘네’ 를 말하는 여러가지 방법";


    final String[] article = {
            "한국어에는 ",
            "성조",
            "가 없습니다.\n하지만 ‘네’는 ",
            "소리",
            "에 따라 ",
            "의미",
            "가 달라집니다.\n\n",
            "첫째",
            ", ‘네/아니오’에서 ‘yes’의 의미가 있습니다.\n이 때 소리는 ",
            "짧고",
            " 내려갑니다.\n\n둘째, ‘네?’ 처럼 올라가는 소리를 내면, ",
            "상대방",
            "의 말이 잘 들리지 않았거나 이해할 수 없었을 때 ‘다시 말해 주세요’ 라는 의미가 될 수 있습니다.\n\n셋째, ‘네~’ 처럼 ",
            "길게",
            " 소리를 내면 ‘이해 합니다’, ‘그렇군요’와 같이 상대방의 말에 ",
            "동의",
            "하는 의미로 사용할 수도 있습니다.\n\n"
    };

    final String[] popUpFront = {"성조", "소리", "의미", "첫째", "짧다", "상대방", "길다", "동의"};
    final String[] popUpBack = {"intonation", "sound", "meaning", "first", "short", "the other side person", "long", "agree"};

    private int readingImage = R.drawable.hangul;
    private boolean isCompleted = false;
    private boolean isLock = false;

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
    public boolean getIsLock() {
        return isLock;
    }

    @Override
    public boolean getIsCompleted() {
        return isCompleted;
    }

    @Override
    public void setIsCompleted(boolean b) {
        this.isCompleted = b;
    }

    @Override
    public void setIsLock(boolean b) {
        this.isLock = b;
    }
}
