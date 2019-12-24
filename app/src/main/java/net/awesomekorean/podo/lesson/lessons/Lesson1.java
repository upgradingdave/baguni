package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.Lesson;
import net.awesomekorean.podo.lesson.LessonItem;

public class Lesson1 implements Lesson, LessonItem {

    private String title = "인사";
    private String subTitle = "여러가지 인사";
    private int lessonImage = R.drawable.hangul;
    private Boolean isSpecial = false;
    private Boolean isLock = false;
    private Boolean isCompleted = false;

    final static String[] wordFront = {"안녕하세요", "다음", "또", "보다", "가다"};
    String[] wordBack = {};

    final static String[] wordPronunciation = {"-", "-", "-", "-", "-"};

    final static String[] wordSynonyms = {"-", "-", "다시", "-", "-"};

    final static String[] wordAntonyms = {"안녕히 계세요 / 안녕히 가세요", "전", "-", "안 보다", "오다"};

    final static String[] wordApplication = {"-", "다음 역은 서울역입니다", "한국에 또 가고 싶어요", "영화를 보다", "학교에 가다"};


    final static String[] sentenceFront = {
            "안녕하세요? / 안녕?",
            "반가워요",
            "오랜만이에요",
            "잘 지냈어요?",
            "다음에 또 봐요.",
            "안녕히 가세요 / 안녕히 계세요 / 안녕~"};

    String[] sentenceBack = {};

    String[] sentenceExplain = {
            "'몸과 마음에 아무 문제나 걱정없이 편안한 상태인가요?' 라는 의미이다. 아침, 점심, 저녁 모두 시간에 상관없이 이렇게 인사를 한다. 친한 친구(나이가 같은 사람)나 친한 동생(나이가 어린 사람)에게는 간단하게 '안녕?'이라고 인사한다.",
            "주로 처음 만난 사람과 서로 소개를 한 후에 바로 이렇게 인사한다. 좀 더 공손하게 '반갑습니다'라고 해도 된다. 오랜만에 만난 사람과도 이 인사를 할 수 있다.",
            "원래 알던 사이에서 오랜만에 만났을 때 하는 인사이다. '반가워요'와 같이 사용해도 좋다.",
            "그 동안 별 일이 없었는지 관습적으로 묻는 말이다",
            "진짜로 다시 볼 상황이 아니더라도 관습적으로 이렇게 인사 할 수 있다.",
            "‘가세요’는 ‘가다’에서 나온 말이고 ‘계세요’는 ‘있다’에서 나온 말이다. 즉, 헤어지는 인사를 한 후에 상대방이 ‘가는’ 상황이면 ‘안녕히 가세요’를, 상대방이 ‘있는’ 상황이면 ‘안녕히 계세요’를 사용한다. 친한 사이에는 간단하게 ‘안녕~’이라고 하면 된다.‘가세요’는 ‘가다’에서 나온 말이고 ‘계세요’는 ‘있다’에서 나온 말이다. 즉, 헤어지는 인사를 한 후에 상대방이‘가세요’는 ‘가다’에서 나온 말이고 ‘계세요’는 ‘있다’에서 나온 말이다. 즉, 헤어지는 인사를 한 후에 상대방이‘가세요’는 ‘가다’에서 나온 말이고 ‘계세요’는 ‘있다’에서 나온 말이다. 즉, 헤어지는 인사를 한 후에 상대방이"
    };

    final static String[] sentenceClause = {
            "안녕하세요?",
            "반가워요.",
            "오랜만이에요.",
            "잘 지냈어요?",
            "다음에 또 봐요.",
            "안녕히 가세요."};

    final static int[] sentenceClauseAorB = {1,2,1,2,1,2};
    final static int[] peopleImage = {R.drawable.people1, R.drawable.people2, R.drawable.people1, R.drawable.people2,R.drawable.people1, R.drawable.people2};


    @Override
    public String[] getWordFront() {
        return wordFront;
    }

    @Override
    public String[] getWordBack() {
        return wordBack;
    }

    @Override
    public String[] getWordPronunciation() {
        return wordPronunciation;
    }

    @Override
    public String[] getWordSynonyms() {
        return wordSynonyms;
    }

    @Override
    public String[] getWordAntonyms() {
        return wordAntonyms;
    }

    @Override
    public String[] getWordApplication() {
        return wordApplication;
    }

    @Override
    public String[] getSentenceFront() {
        return sentenceFront;
    }

    @Override
    public String[] getSentenceBack() {
        return sentenceBack;
    }

    @Override
    public String[] getSentenceExplain() {
        return sentenceExplain;
    }

    @Override
    public void setWordBack(String[] strings) {
        this.wordBack = strings;
    }

    @Override
    public void setSentenceBack(String[] strings) {
        this.sentenceBack = strings;
    }

    @Override
    public void setSentenceExplain(String[] strings) {
        this.sentenceExplain = strings;
    }

    @Override
    public String[] getSentenceClause() {
        return sentenceClause;
    }

    @Override
    public int[] getSentenceClauseAorB() {
        return sentenceClauseAorB;
    }

    @Override
    public int[] getPeopleImage() {
        return peopleImage;
    }


    // 레슨어뎁터 아이템

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSubTitle() {
        return subTitle;
    }

    @Override
    public int getLessonImage() {
        return lessonImage;
    }

    @Override
    public boolean getIsSpecial() {
        return isSpecial;
    }

    @Override
    public boolean getIsLock() {
        return isLock;
    }

    @Override
    public boolean getIsCompleted() {
        return isCompleted;
    }
}
