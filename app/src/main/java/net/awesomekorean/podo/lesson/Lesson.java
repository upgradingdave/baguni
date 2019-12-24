package net.awesomekorean.podo.lesson;

public interface Lesson {

    String[] getWordFront();

    String[] getWordPronunciation();

    String[] getWordSynonyms();

    String[] getWordAntonyms();

    String[] getWordApplication();

    String[] getSentenceFront();

    String[] getSentenceClause();

    int[] getSentenceClauseAorB();

    int[] getPeopleImage();
}