package net.awesomekorean.podo.reading;

public interface Reading {

    String getReadingId();

    String getTitle();

    String[] getArticle();

    String[] getPopUpFront();

    String[] getPopUpBack();

    boolean getIsLock();


    // 리딩어뎁터 아이템

    int getReadingImage();

    boolean getIsCompleted();

    void setIsCompleted(boolean b);

    void setIsLocked(boolean b);

    int getReadingLevel();

    int getReadingProgress();

    void setReadingProgress(int progress);
}
