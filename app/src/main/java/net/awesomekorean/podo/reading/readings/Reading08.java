package net.awesomekorean.podo.reading.readings;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.Reading;

public class Reading08 implements Reading {

    String readingId = "R_08";

    final String title = "한국돈 ‘만원’에 있는 사람은?";


    final String[] article = {
            "아마 한국어를 공부하는 학생들은 잘 알고 있을 것 같습니다. \n한국 돈 ‘만 원’에 있는 사람은 한글을 만든 ‘세종대왕’ 입니다. \n\n아주 ",
            "옛날",
            "에 한국 사람들은 중국의 한자를 사용했습니다. \n하지만 한자는 배우기가 어려워서 ",
            "보통",
            "의 ",
            "가난한",
            " 사람들은 배울 수가 없었습니다. \n나라에서 사람들에게 ",
            "알리",
            "고 싶은 말이 있으면 글로 써서 사람들이 많이 다니는 곳에 ",
            "붙였",
            "는데 돈이 많고 똑똑한 사람들만 이해할 수 있었습니다. \n그것을 보고 세종대왕이 누구나 배우기 쉬운 ‘한글’을 만들게 되었습니다. \n\n세종대왕은 한글 ",
            "뿐만 아니라",
            "",
            "과학",
            ", ",
            "예술",
            ", ",
            "문화",
            " 등에서도 많은 일을 했습니다. \n그래서 많은 한국 사람들은 세종대왕을 한국 ",
            "역사",
            "에서 가장 좋은 ",
            "왕",
            "으로 생각하고 있습니다.\n\n"
    };

    final String[] popUpFront = {"옛날", "보통", "가난하다", "알리다", "붙이다", "A 뿐만 아니라", "과학", "예술", "문화", "역사", "왕"};
    final String[] popUpBack = {"the old times", "normal", "poor", "inform", "paste", "not only A", "science", "art", "culture", "history", "king"};

    private int readingImage = R.drawable.sejong;
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
