package net.awesomekorean.baguni;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

import net.awesomekorean.baguni.lesson.LessonEnd;
import net.awesomekorean.baguni.lesson.LessonFrame;
import net.awesomekorean.baguni.lesson.LessonSentence;
import net.awesomekorean.baguni.lesson.LessonWord;

public class DetectSwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

    //Minimal x and y axis swipe distance
    private static int MIN_SWIPE_DISTANCE_X = 100;

    //Maximal x and y axis swipe distance
    private static int MAX_SWIPE_DISTANCE_X = 1000;

    //Source activity that apply swipe gesture
    private LessonFrame activity = null;

    public LessonFrame getActivity() {
        return activity;
    }

    public void setActivity(LessonFrame activity) {
        this.activity = activity;
    }

    //This method is invoked when a swipe gesture happened

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        float deltaX = e1.getX() - e2.getX();

        float deltaXAbs = Math.abs(deltaX);

        //Only when swipe distance between minimal and maximal distance value. It's effective swipe
        if((deltaXAbs >= MIN_SWIPE_DISTANCE_X) && (deltaXAbs <= MAX_SWIPE_DISTANCE_X)) {

            if(LessonFrame.swipePage == Strings.LESSONWORD) {

                if(deltaX > 0) {

                    LessonWord.lessonCount++;

                    if(LessonWord.lessonCount == LessonWord.lessonWordLength) {
                        LessonWord.lessonCount = 0; // LessonSentence를 위해 lessonCount 초기화
                        ((LessonFrame)getActivity()).replaceFragment(LessonSentence.newInstance());
                    } else {
                        LessonWord.displayWord();
                    }
                } else {

                    if(LessonWord.lessonCount != 0) {
                        LessonWord.lessonCount--;
                    }
                    LessonWord.displayWord();
                }
            }

            if(LessonFrame.swipePage == Strings.LESSONSENTENCE) {

                if(deltaX > 0) {

                    LessonWord.lessonCount++;

                    if(LessonWord.lessonCount == LessonWord.lessonSentenceLength) {
                        LessonWord.lessonCount = 0;
                        ((LessonFrame)getActivity()).replaceFragment(LessonEnd.newInstance());
                    } else {
                        LessonSentence.displaySentence();
                    }
                } else {

                    if(LessonWord.lessonCount != 0) {
                        LessonWord.lessonCount--;
                    }
                    LessonSentence.displaySentence();
                }
            }



        }

        return true;
    }
}
