package net.awesomekorean.podo.lesson.lessons;

public interface Lesson {

    String[] getWordFront();

    String[] getWordBack();

    String[] getWordPronunciation();

    String[] getSentenceFront();

    String[] getSentenceBack();

    String[] getSentenceExplain();

    String[] getDialog();

    int[] getPeopleImage();
}