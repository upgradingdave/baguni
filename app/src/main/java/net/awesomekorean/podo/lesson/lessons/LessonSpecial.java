package net.awesomekorean.podo.lesson.lessons;

public interface LessonSpecial {

    String getLessonTitle();
    String getLessonSubTitle();
    int getContents();
    int getLessonProgress();
    void setLessonProgress(int progress);
}
