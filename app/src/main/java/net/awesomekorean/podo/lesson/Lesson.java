package net.awesomekorean.podo.lesson;

public interface Lesson {

    String[] getWordFront();

    String[] getWordBack();

    void setWordBack(String[] strings);

    String[] getWordPronunciation();

    String[] getWordSynonyms();

    String[] getWordAntonyms();

    String[] getWordApplication();

    String[] getSentenceFront();

    String[] getSentenceBack();

    void setSentenceBack(String[] strings);

    String[] getSentenceExplain();

    void setSentenceExplain(String[] strings);

    String[] getSentenceClause();

    int[] getSentenceClauseAorB();

    int[] getPeopleImage();
}