package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class R_Sentence_Lesson03 extends LessonInit implements LessonReview, LessonItem, Serializable {

    private String lessonId = "RS_03";
    private String lessonTitle = "sentence";
    private String lessonSubTitle = "review";
    private int lessonImage = R.drawable.l_12_word_2;

    private List<String> front = new ArrayList<>();

    private List<String> back = new ArrayList<>();

    private List<String> audioString = new ArrayList<>();

    private List<String> audioFolder = new ArrayList<>();


    private Lesson[] lessonsInReview = {
            new Lesson17(), new Lesson20(), new Lesson18(), new Lesson21(), new Lesson23(), new Lesson35(),
            new Lesson24()
    };


    public R_Sentence_Lesson03() {

        for(int i=0; i<lessonsInReview.length; i++) {

            String lessonId = lessonsInReview[i].getLessonId().toLowerCase();

            for(int j=0; j<lessonsInReview[i].getReviewId().length; j++) {

                int index = lessonsInReview[i].getReviewId()[j];

                this.front.add(lessonsInReview[i].getSentenceFront()[index]);

                this.back.add(lessonsInReview[i].getSentenceBack()[index]);

                this.audioString.add(lessonId + "_sentence_" + index + ".mp3");

                this.audioFolder.add("lesson/" + lessonId + "/");
            }
        }
    }


    @Override
    public String getLessonSubTitle() {
        return lessonSubTitle;
    }

    @Override
    public String getLessonId() {
        return lessonId;
    }

    @Override
    public List<String> getFront() {
        return front;
    }

    @Override
    public List<String> getBack() {
        return back;
    }

    @Override
    public List<String> getImageString() {
        return null;
    }

    @Override
    public List<String> getAudioString() {
        return audioString;
    }

    @Override
    public List<String> getAudioFolder() {
        return audioFolder;
    }


    // 레슨어뎁터 아이템

    @Override
    public String getLessonTitle() {
        return lessonTitle;
    }

    @Override
    public int getLessonImage() {
        return lessonImage;
    }

}
