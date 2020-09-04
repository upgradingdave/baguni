package net.awesomekorean.podo.lesson;

import net.awesomekorean.podo.lesson.lessons.Lesson;
import net.awesomekorean.podo.lesson.lessons.Lesson00;
import net.awesomekorean.podo.lesson.lessons.Lesson01;
import net.awesomekorean.podo.lesson.lessons.Lesson02;
import net.awesomekorean.podo.lesson.lessons.Lesson03;
import net.awesomekorean.podo.lesson.lessons.Lesson04;
import net.awesomekorean.podo.lesson.lessons.Lesson05;
import net.awesomekorean.podo.lesson.lessons.Lesson06;
import net.awesomekorean.podo.lesson.lessons.Lesson07;
import net.awesomekorean.podo.lesson.lessons.Lesson08;
import net.awesomekorean.podo.lesson.lessons.Lesson09;
import net.awesomekorean.podo.lesson.lessons.Lesson10;
import net.awesomekorean.podo.lesson.lessons.Lesson11;
import net.awesomekorean.podo.lesson.lessons.Lesson12;
import net.awesomekorean.podo.lesson.lessons.Lesson13;
import net.awesomekorean.podo.lesson.lessons.Lesson14;
import net.awesomekorean.podo.lesson.lessons.Lesson15;
import net.awesomekorean.podo.lesson.lessons.Lesson16;
import net.awesomekorean.podo.lesson.lessons.Lesson17;
import net.awesomekorean.podo.lesson.lessons.Lesson18;
import net.awesomekorean.podo.lesson.lessons.Lesson19;
import net.awesomekorean.podo.lesson.lessons.Lesson20;
import net.awesomekorean.podo.lesson.lessons.Lesson21;
import net.awesomekorean.podo.lesson.lessons.Lesson22;
import net.awesomekorean.podo.lesson.lessons.Lesson23;
import net.awesomekorean.podo.lesson.lessons.Lesson24;
import net.awesomekorean.podo.lesson.lessons.Lesson25;
import net.awesomekorean.podo.lesson.lessons.Lesson26;
import net.awesomekorean.podo.lesson.lessons.Lesson27;
import net.awesomekorean.podo.lesson.lessons.Lesson28;
import net.awesomekorean.podo.lesson.lessons.Lesson29;
import net.awesomekorean.podo.lesson.lessons.Lesson30;
import net.awesomekorean.podo.lesson.lessons.Lesson31;
import net.awesomekorean.podo.lesson.lessons.Lesson32;
import net.awesomekorean.podo.lesson.lessons.Lesson33;
import net.awesomekorean.podo.lesson.lessons.Lesson34;
import net.awesomekorean.podo.lesson.lessons.Lesson35;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LessonProgress {

    public LessonProgress(List<String> lessonComplete) {
        this.lessonComplete = lessonComplete;
        setTotalNo();
        setWordsSentences();
        setMyWordsSentences();
    }

    private Lesson[] lessons = {new Lesson00(), new Lesson01(), new Lesson02(), new Lesson03(), new Lesson04(),
            new Lesson05(), new Lesson06(), new Lesson07(), new Lesson08(), new Lesson09(), new Lesson10(),
            new Lesson11(), new Lesson12(), new Lesson13(), new Lesson14(), new Lesson15(), new Lesson16(),
            new Lesson17(), new Lesson18(), new Lesson19(), new Lesson20(), new Lesson21(), new Lesson22(),
            new Lesson23(), new Lesson24(), new Lesson25(), new Lesson26(), new Lesson27(), new Lesson28(),
            new Lesson29(), new Lesson30(), new Lesson31(), new Lesson32(), new Lesson33(), new Lesson34(),
            new Lesson35()};

    private int totalWordNo;
    private int totalSentenceNo;

    private String[][] wordFront = new String[lessons.length][];
    private String[][] wordBack = new String[lessons.length][];
    private String[][] sentenceFront = new String[lessons.length][];
    private String[][] setnenceBack = new String[lessons.length][];

    private List<String> lessonComplete;
    private Map<String, String> myWords = new HashMap<>();
    private Map<String, String> mySentences = new HashMap<>();


    // 전체 단어/문장 개수 구하기
    private void setTotalNo() {
        for (Lesson lesson : lessons) {
            totalWordNo += lesson.getWordFront().length;
            totalSentenceNo += lesson.getReviewId().length;
        }
    }


    // 전체 단어/문장 세팅하기
    private void setWordsSentences() {
        for (int i=0; i<lessons.length; i++) {

            int lengthWord = lessons[i].getWordFront().length;
            for(int j=0; j<lengthWord; j++) {
                wordFront[i] = new String[lengthWord];
                wordBack[i] = new String[lengthWord];
                wordFront[i][j] = lessons[i].getWordFront()[j];
                wordBack[i][j] = lessons[i].getWordBack()[j];
            }

            int lengthSentence = lessons[i].getReviewId().length;
            for(int j=0; j<lengthSentence; j++) {
                int reviewNo = lessons[i].getReviewId()[j];
                sentenceFront[i] = new String[lengthSentence];
                setnenceBack[i] = new String[lengthSentence];
                sentenceFront[i][j] = lessons[i].getSentenceFront()[reviewNo];
                setnenceBack[i][j] = lessons[i].getSentenceBack()[reviewNo];
            }
        }
    }

    private void setMyWordsSentences() {
        for(String lesson : lessonComplete) {
            String[] lessonSplit = lesson.split("_");

            if(lessonSplit[0].equals("L")) {
                int lessonNo = Integer.parseInt(lessonSplit[1]);

                for(int i=0; i<lessons[lessonNo].getWordFront().length; i++) {
                    myWords.put(lessons[lessonNo].getWordFront()[i], lessons[lessonNo].getWordBack()[i]);
                }

                for(int i=0; i<lessons[lessonNo].getReviewId().length; i++) {
                    int reviewNo = lessons[lessonNo].getReviewId()[i];
                    mySentences.put(lessons[lessonNo].getSentenceFront()[reviewNo], lessons[lessonNo].getSentenceBack()[reviewNo]);
                }
            }
        }
    }


    public int getTotalWordNo() {
        return totalWordNo;
    }

    public int getTotalSentenceNo() {
        return totalSentenceNo;
    }

    public Map<String, String> getMyWords() {
        return myWords;
    }

    public Map<String, String> getMySentences() {
        return mySentences;
    }
}
