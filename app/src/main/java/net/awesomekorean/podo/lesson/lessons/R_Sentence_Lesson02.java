package net.awesomekorean.podo.lesson.lessons;

import net.awesomekorean.podo.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class R_Sentence_Lesson02 extends LessonInit implements LessonReview, LessonItem, Serializable {

    private String lessonId = "RS_02";
    private String lessonTitle = "sentence";
    private String lessonSubTitle = "review";
    private int lessonImage = R.drawable.l_12_word_2;

    private List<String> front = new ArrayList<>();

    private List<String> back = new ArrayList<>();

    private List<String> audioString = new ArrayList<>();

    private List<String> audioFolder = new ArrayList<>();


    private Lesson[] lessonsInReview = {
            new Lesson13(), new Lesson14(), new Lesson15(), new Lesson16(), new Lesson17(), new Lesson20()
    };


    public R_Sentence_Lesson02() {

        for(int i=0; i<lessonsInReview.length; i++) {

            String lessonId = lessonsInReview[i].getLessonId().toLowerCase();

            for(int j=0; j<lessonsInReview[i].getReviewFront().length; j++) {

                this.front.add(lessonsInReview[i].getReviewFront()[j]);

                this.back.add(lessonsInReview[i].getReviewBack()[j]);

                this.audioString.add(lessonId + "_review_" + j + ".mp3");

                this.audioFolder.add("lesson/review/");
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
