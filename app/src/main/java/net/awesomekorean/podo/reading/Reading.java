package net.awesomekorean.podo.reading;

public interface Reading {

    String getTitle();

    String getArticle();

    int[] getStart();

    int[] getEnd();

    String[] getPopUpFront();

    String[] getPopUpBack();
}
