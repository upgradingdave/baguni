package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import net.awesomekorean.podo.R;

import org.w3c.dom.ls.LSException;

public class SwipeListenerSentence extends GestureDetector.SimpleOnGestureListener {

    //Minimal x and y axis swipe distance
    private static int MIN_SWIPE_DISTANCE_X = 100;

    //Maximal x and y axis swipe distance
    private static int MAX_SWIPE_DISTANCE_X = 1000;

    //Source activity that apply swipe gesture
    private LessonFrame activity = null;

    Animation animation1;
    Animation animation2;


    public LessonFrame getActivity() {
        return activity;
    }

    public void setActivity(LessonFrame activity) {
        this.activity = activity;
    }

    //This method is invoked when a swipe gesture happened
    LessonSentence lessonSentence;

    public SwipeListenerSentence() {
        super();
        this.lessonSentence = LessonSentence.newInstance();
    }

    String stringLeft = "left";
    String stringRight = "right";
    String stringSentence = "sentence";

    View viewLeft;
    View viewRight;
    int lessonLength;
    LinearLayout lessonLayout;


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if(e1 != null && e2 != null) {

            float deltaX = e1.getX() - e2.getX();

            float deltaXAbs = Math.abs(deltaX);

            //Only when swipe distance between minimal and maximal distance value. It's effective swipe
            if((deltaXAbs >= MIN_SWIPE_DISTANCE_X) && (deltaXAbs <= MAX_SWIPE_DISTANCE_X)) {

                Context context = getActivity();

                // 왼쪽으로 스와이프 할 때
                if(deltaX > 0) {

                    lessonSentence.lessonCount++;
                    LessonFrame.progressCount++;

                    // 마지막 문장이면 LessonDialog 로 넘어감
                    if(lessonSentence.lessonCount == LessonFrame.lesson.getSentenceFront().length) {
                        lessonSentence.lessonCount = 0;
                        ((LessonFrame)context).replaceFragment(LessonDialog.newInstance());

                        // 마지막 문장 아니면 다음 문장 표시
                    } else {
                        swipeAnimationStart(stringSentence, stringLeft);
                    }

                    // 오른쪽으로 스와이프 할 떄
                } else {
                    // 맨 처음 문장이 아니면 이전 문장을 표시
                    if(lessonSentence.lessonCount != 0) {
                        lessonSentence.lessonCount--;
                        LessonFrame.progressCount--;

                        swipeAnimationStart(stringSentence, stringRight);
                    }
                }
                // 스와이프가 발생할 때마다 프로그레스바 상태 계산
                LessonFrame.progressCount();
            }
        }

        return true;
    }

    // 스와이프 애니메이션 실행
    public void swipeAnimationStart(final String whichLesson, final String swipeDirection) {
        if(swipeDirection == stringLeft) {
            animation1 = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.move_left1);
            animation2 = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.move_left2);

        }else {
            animation1 = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.move_right1);
            animation2 = AnimationUtils.loadAnimation(activity.getApplicationContext(), R.anim.move_right2);
        }

        lessonLayout = lessonSentence.lessonLayout;
        lessonLayout.startAnimation(animation1);
        viewLeft = lessonSentence.viewLeft;
        viewRight = lessonSentence.viewRight;
        lessonLength = LessonFrame.lesson.getSentenceFront().length;

        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                lessonSentence.displaySentence();

                if(swipeDirection == stringLeft) {
                    viewLeft.setVisibility(View.VISIBLE);

                } else {
                    viewRight.setVisibility(View.VISIBLE);
                }

                lessonLayout.startAnimation(animation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
}
