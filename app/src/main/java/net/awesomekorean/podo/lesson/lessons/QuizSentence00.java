package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

public class QuizSentence00 extends LessonInit implements LessonItem {

    private String lessonId = "Q_S_00";

    private String lessonTitle = "Sentence";

    private String lessonSubTitle = "";

    private int lessonImage = R.drawable.l_10_word_1;

    private String[] front = {};

    private String[] back = {};


    public String[] getFront() { return front; }

    public String[] getBack() { return back; }


    @Override
    public String getLessonId() {
        return lessonId;
    }

    @Override
    public String getLessonTitle() {
        return lessonTitle;
    }

    @Override
    public String getLessonSubTitle() {
        return lessonSubTitle;
    }

    @Override
    public int getLessonImage() {
        return lessonImage;
    }
}
