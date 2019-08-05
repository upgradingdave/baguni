package net.awesomekorean.baguni.reading;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

public class Reading0 implements Reading {

    final String title = "화성, 지구에 가장 가까워지다";

    final String article = "미국항공우주국(NASA)에 따르면 7월 31일에 화성이 15년 만에 지구에 가장 가깝게 접근한다고 한다.\n" +
            "이 때 화성의 밝기는 평소의 3배나 되며 맨눈으로도 화성을 관찰할 수 있을 정도이다." +
            "화성이 지구에 가장 가까웠던 2003년만큼 관찰하기 좋을 것이라고 한다." +
            "화성을 가까이에서 관찰할 수 있는 다음 기회는 2035년 9월 15일이 될 것이라고 한다." +
            "한편, 현재 화성은 약 4분의 1의 지역에서 아주 심한 먼지 폭풍이 발생하고 있다." +
            "이 때문에 NASA는 화성 탐사 로봇 'Opportunity'를 절전모드로 전환했다고 한다." +
            "또다른 탐사 로봇 'Curiosity'는 먼지 폭풍이 적은 지역에 있어서 계속 탐사 활동을 진행 중이다.";

    final int[] start = {27, 38, 49};
    final int[] end = {29, 40, 51};

    final String[] popUpText = {"뜻a", "뜻b", "뜻c"};

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
    public String[] getPopUpText() {
        return popUpText;
    }
}
