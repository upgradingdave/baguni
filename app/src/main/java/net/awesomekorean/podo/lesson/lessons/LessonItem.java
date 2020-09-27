package net.awesomekorean.podo.lesson.lessons;

public interface LessonItem {

    String getLessonId();

    String getLessonTitle();

    String getLessonSubTitle();

    boolean getIsActive();
    boolean setIsActive(boolean b);

    boolean getIsCurrent();
    void setIsCurrent(boolean b);

    LessonItem getSLesson();

    boolean getIsLocked();
    void setIsLocked(boolean b);

    boolean getIsCompleted();
    void setIsCompleted(boolean b);
}
