package net.awesomekorean.podo.lesson.lessonReview;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessons.Lesson;
import net.awesomekorean.podo.lesson.lessons.Lesson17;
import net.awesomekorean.podo.lesson.lessons.Lesson18;
import net.awesomekorean.podo.lesson.lessons.Lesson20;
import net.awesomekorean.podo.lesson.lessons.Lesson21;
import net.awesomekorean.podo.lesson.lessons.Lesson23;
import net.awesomekorean.podo.lesson.lessons.Lesson24;
import net.awesomekorean.podo.lesson.lessons.Lesson35;
import net.awesomekorean.podo.lesson.lessons.LessonInit;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class R_Word_Lesson03 extends LessonInit implements LessonItem, Serializable {

    private String lessonId = "RW_03";
    private String lessonTitle = "word";
    private String lessonSubTitle = "review";
    private int lessonImage = R.drawable.l_16_word_5;

    private List<String> front = new ArrayList<>();

    private List<String> back = new ArrayList<>();

    private List<String> imageString = new ArrayList<>();

    private List<String> audioString = new ArrayList<>();

    private List<String> audioFolder = new ArrayList<>();


    private Lesson[] lessonsInReview = {
            new Lesson17(), new Lesson20(), new Lesson18(), new Lesson21(), new Lesson23(), new Lesson35(),
            new Lesson24()
    };


    public R_Word_Lesson03() {

        for(int i=0; i<lessonsInReview.length; i++) {

            String lessonId = lessonsInReview[i].getLessonId().toLowerCase();

            for(int j=0; j<lessonsInReview[i].getWordFront().length; j++) {

                this.front.add(lessonsInReview[i].getWordFront()[j]);

                this.back.add(lessonsInReview[i].getWordBack()[j]);

                this.imageString.add(lessonId + "_word_" + j);

                this.audioString.add(lessonId + "_word_" + j + ".mp3");

                this.audioFolder.add("lesson/" + lessonId);
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
