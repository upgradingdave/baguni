package net.awesomekorean.podo.lesson;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.awesomekorean.podo.R;

public class LessonFrame extends AppCompatActivity implements Button.OnClickListener {

    public static int progressCount;
    public static int totalPageNo = 0;

    public static ProgressBar progressBar;
    public static TextView progressTextView;

    ImageView btnClose;
    LinearLayout confirmQuit;
    Button btnNo;
    Button btnYes;

    Intent intent;

    private GestureDetectorCompat gestureDetectorCompat = null;

    public static String swipePage;

    FragmentManager fm;
    FragmentTransaction ft;
    LessonWord lessonWord = new LessonWord();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_frame);

        progressCount = 1;
        progressBar = findViewById(R.id.progressBar);
        progressTextView = findViewById(R.id.progressTextView);
        confirmQuit = findViewById(R.id.confirmQuit);
        btnNo = findViewById(R.id.btnNo);
        btnYes = findViewById(R.id.btnYes);
        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);
        btnNo.setOnClickListener(this);
        btnYes.setOnClickListener(this);

        replaceFragment(lessonWord);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Pass activity on touch event to the gesture detector
        gestureDetectorCompat.onTouchEvent(event);
        return true;
    }

    public void replaceFragment(Fragment fragment) {

        LessonSwipeListener gestureListener = new LessonSwipeListener();
        gestureListener.setActivity(this);
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.lessonFrame, fragment);
        ft.commit();
    }

    // 프로그레스바 및 카운트 표시, true:다음, false:이전
    public static void progressCount() {
        progressBar.setProgress(progressCount*100/totalPageNo);
        progressTextView.setText(progressCount + " / " + totalPageNo);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnClose :
                confirmQuit.setVisibility(View.VISIBLE);
                break;

            case R.id.btnNo :
                confirmQuit.setVisibility(View.GONE);
                break;

            case R.id.btnYes :
                finish();
                break;
        }
    }
}

