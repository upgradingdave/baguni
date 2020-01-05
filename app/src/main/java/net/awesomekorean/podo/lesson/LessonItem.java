package net.awesomekorean.podo.lesson;

public interface LessonItem {

    int getTitle();

    String getSubTitle();

    int getLessonImage();

    boolean getIsSpecial();

    boolean getIsLock();

    boolean getIsCompleted();

    void setIsCompleted(boolean b);

}
