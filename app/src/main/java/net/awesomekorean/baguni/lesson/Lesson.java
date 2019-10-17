package net.awesomekorean.baguni.lesson;

public interface Lesson {

    String[] getWordFront();

    String[] getWordBack();

    void setWordBack(String[] strings);

    String[] getSentenceFront();

    String[] getSentenceBack();

    void setSentenceBack(String[] strings);

    String[] getSentenceExplain();

    void setSentenceExplain(String[] strings);

    String[] getSentenceClause();

}