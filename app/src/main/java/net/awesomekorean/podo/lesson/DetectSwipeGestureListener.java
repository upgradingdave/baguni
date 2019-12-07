package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.LessonClause;
import net.awesomekorean.podo.lesson.LessonFrame;
import net.awesomekorean.podo.lesson.LessonSentence;
import net.awesomekorean.podo.lesson.LessonWord;
import net.awesomekorean.podo.lesson.LessonWordQuiz1;
import net.awesomekorean.podo.lesson.LessonWordQuiz2;
import net.awesomekorean.podo.lesson.LessonWordQuiz3;

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

            Context context = getActivity();
            // 현재페이지가 LessonWord 일 때 동작
            if(LessonFrame.swipePage.equals(context.getString(R.string.LESSONWORD))) {

                // 왼쪽으로 스와이프 할 때
                if(deltaX > 0) {

                    LessonWord.lessonCount++;
                    LessonFrame.progressCount++;

                    // 마지막 단어이면 LessonWordQuiz1 로 넘어감
                    if(LessonWord.lessonCount == LessonWord.lessonWordLength) {
                        LessonWord.lessonCount = 0; // LessonSentence를 위해 lessonCount 초기화
                        ((LessonFrame) context).replaceFragment(LessonWordQuiz1.newInstance());

                    // 마지막 단어 아니면 다음 단어 표시
                    } else {
                        LessonWord.displayWord();
                    }

                // 오른쪽으로 스와이프 할 떄
                } else {
                    // 맨 처음 단어가 아니면 이전 단어를 표시
                    if(LessonWord.lessonCount != 0) {
                        LessonWord.lessonCount--;
                        LessonFrame.progressCount--;
                    }
                    LessonWord.displayWord();
                }

                // 스와이프가 발생할 때마다 프로그레스바 상태 계산
                LessonFrame.progressCount();
            }

            if(LessonFrame.swipePage.equals(context.getString(R.string.LESSONSENTENCE))) {

                if(deltaX > 0) {

                    LessonWord.lessonCount++;
                    LessonFrame.progressCount++;

                    if(LessonWord.lessonCount == LessonWord.lessonSentenceLength) {
                        LessonWord.lessonCount = 0;
                        ((LessonFrame)context).replaceFragment(LessonClause.newInstance());
                    } else {
                        LessonSentence.displaySentence();
                    }
                } else {

                    if(LessonWord.lessonCount != 0) {
                        LessonWord.lessonCount--;
                        LessonFrame.progressCount--;
                    }
                    LessonSentence.displaySentence();
                }

                LessonFrame.progressCount();
            }
        }
        return true;
    }
}
