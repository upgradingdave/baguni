package net.awesomekorean.podo.lesson.lessons;

public interface LessonItem {

    String getLessonId();

    String getLessonTitle();

    String getLessonSubTitle();

    int getLessonImage();

    boolean getIsSpecial();

    boolean getIsLock();

    void setIsLocked(boolean b);

    boolean getIsCompleted();

    void setIsCompleted(boolean b);
}
