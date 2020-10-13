package net.awesomekorean.podo.lesson.lessonReview;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessons.Lesson;
import net.awesomekorean.podo.lesson.lessons.Lesson13;
import net.awesomekorean.podo.lesson.lessons.Lesson14;
import net.awesomekorean.podo.lesson.lessons.Lesson15;
import net.awesomekorean.podo.lesson.lessons.Lesson16;
import net.awesomekorean.podo.lesson.lessons.Lesson22;
import net.awesomekorean.podo.lesson.lessons.Lesson28;
import net.awesomekorean.podo.lesson.lessons.Lesson29;
import net.awesomekorean.podo.lesson.lessons.LessonInit;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class R_Word_Lesson02 extends LessonInit implements LessonItem, Serializable {

    private String lessonId = "RW_02";
    private String lessonTitle = "word";
    private String lessonSubTitle = "review";
    private int lessonImage = R.drawable.l_16_word_5;

    private List<String> front = new ArrayList<>();

    private List<String> back = new ArrayList<>();

    private List<String> imageString = new ArrayList<>();

    private List<String> audioString = new ArrayList<>();

    private List<String> audioFolder = new ArrayList<>();


    private Lesson[] lessonsInReview = {
            new Lesson15(), new Lesson16(), new Lesson13(), new Lesson14(), new Lesson22(), new Lesson28(),
            new Lesson29()
    };


    public R_Word_Lesson02() {

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
