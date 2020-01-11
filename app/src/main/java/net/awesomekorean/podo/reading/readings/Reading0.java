package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading0 implements Reading {

    String readingId = "R_00";

    final String title = "\'네\' 를 말하는 여러가지 방법";


    final String article = "중국어나 태국어는 성조가 있습니다.\n" +
            "한국어는 성조가 없습니다.\n" +
            "하지만 한국어에도 상황에 따라 소리와 의미가 다른 단어가 있습니다.\n" +
            "예를 들어 ‘네’의 의미는…\n" +
            "첫째, ‘네/아니오’에서 ‘yes’의 의미가 있습니다. \n" +
            "이 때 소리는 짧고 끝이 내려갑니다.\n" +
            "둘째, ‘네?’ 처럼 끝이 올라가는 소리를 내면, 상대방의 말이 잘 들리지 않았거나 이해할 수 없었을 때 ‘다시 말해 주세요’ 라는 의미가 될 수 있습니다.\n" +
            "셋째, ‘네~’ 처럼 길게 소리를 내면 ‘이해 합니다’, ‘그렇군요’와 같이 상대방의 말에 동의하는 의미로 사용할 수 있습니다.\n" +
            "일상 생활에서 아주 많이 사용하는 ‘네’ 를 여러가지 상황에서 사용해 보세요.\n";

    final int[] start = {10, 45, 52, 56, 60, 89, 129, 170, 242, 281, 302, 337};
    final int[] end = {12, 47, 54, 58, 62, 91, 131, 173, 244, 283, 307, 339};

    final String[] popUpFront = {"성조", "상황", "소리", "의미", "다른", "첫째", "짧다", "상대방", "길다", "동의", "일상 생활", "사용"};
    final String[] popUpBack = {"intonation", "situation", "sound", "meaning", "different", "first", "short", "the other side person", "long", "agree", "daily life", "to use"};

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
    public String getArticle() {
        return article;
    }

    @Override
    public int[] getStart() {
        return start;
    }

    @Override
    public int[] getEnd() {
        return end;
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
