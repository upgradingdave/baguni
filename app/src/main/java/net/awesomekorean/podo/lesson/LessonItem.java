package net.awesomekorean.podo.lesson;

public interface LessonItem {

    String getLessonId();

    int getTitle();

    String getSubTitle();

    int getLessonImage();

    boolean getIsSpecial();

    boolean getIsLock();

    boolean getIsCompleted();

    void setIsCompleted(boolean b);
    void setIsLocked(boolean b);

}
