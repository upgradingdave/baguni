package net.awesomekorean.baguni.lesson;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.awesomekorean.baguni.DetectSwipeGestureListener;
import net.awesomekorean.baguni.R;

public class LessonFrame extends AppCompatActivity {

    private GestureDetectorCompat gestureDetectorCompat = null;

    public static String swipePage;

    FragmentManager fm;
    FragmentTransaction ft;
    LessonWord lessonWord = new LessonWord();


    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_frame);

        replaceFragment(lessonWord);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Pass activity on touch event to the gesture detector
        gestureDetectorCompat.onTouchEvent(event);
        return true;
    }

    public void replaceFragment(Fragment fragment) {

        System.out.println("FRAGMENT : " + fragment);

        //Create a common gesture listener object
        DetectSwipeGestureListener gestureListener = new DetectSwipeGestureListener();

        //Set activity in the listener
        gestureListener.setActivity(this);

        //Create the gesture detector with the gesture listener
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.lessonFrame, fragment);
        ft.commit();
    }

}

