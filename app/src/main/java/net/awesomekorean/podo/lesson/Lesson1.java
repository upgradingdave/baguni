package net.awesomekorean.podo.lesson;

public class Lesson1 implements Lesson{

    final static String[] wordFront = {"안녕", "다음", "또", "보다", "가다"};
    String[] wordBack = {};

    final static String[] wordPronunciation = {"안녕", "다음", "또", "보다", "가다"};

    final static String[] wordSynonyms = {"안녕", "다음", "또", "보다", "가다"};

    final static String[] wordAntonyms = {"안녕", "다음", "또", "보다", "가다"};

    final static String[] wordApplication = {"안녕", "다음", "또", "보다", "가다"};

    final static String[] wordAudio = {"word_1_1", "word_1_2", "word_1_3", "word_1_4", "word_1_5"};



    final static String[] sentenceFront = {
            "안녕하세요?",
            "반가워요.",
            "오랜만이에요.",
            "잘 지냈어요?",
            "다음에 또 봐요.",
            "안녕히 가세요."};

    String[] sentenceBack = {};

    String[] sentenceExplain = {};

    final static String[] sentenceClause = {
            "안녕하세요?",
            "반가워요.",
            "오랜만이에요.",
            "잘 지냈어요?",
            "다음에 또 봐요.",
            "안녕히 가세요."};


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
    public String[] getWordAudio() {
        return wordAudio;
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
}
