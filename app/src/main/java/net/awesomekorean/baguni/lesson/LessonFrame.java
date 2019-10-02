package net.awesomekorean.baguni.lesson;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.awesomekorean.baguni.DetectSwipeGestureListener;
import net.awesomekorean.baguni.MainActivity;
import net.awesomekorean.baguni.R;

public class LessonFrame extends AppCompatActivity implements Button.OnClickListener {

    public static int progressCount;
    public static int totalPageNo = 0;

    public static ProgressBar progressBar;
    public static TextView progressTextView;
    ImageButton btnExit1;
    ImageButton btnExit2;
    Button btnYes;
    Button btnNo;
    LinearLayout saveLayout;
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
        btnExit1 = findViewById(R.id.btnExit1);
        btnExit2 = findViewById(R.id.btnExit2);
        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);
        saveLayout = findViewById(R.id.saveLayout);
        btnExit1.setOnClickListener(this);
        btnExit2.setOnClickListener(this);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);

        replaceFragment(lessonWord);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Pass activity on touch event to the gesture detector
        gestureDetectorCompat.onTouchEvent(event);
        return true;
    }

    public void replaceFragment(Fragment fragment) {

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

    // 프로그레스바 및 카운트 표시, true:다음, false:이전
    public static void progressCount() {
        progressBar.setProgress(progressCount*100/totalPageNo);
        progressTextView.setText(progressCount + " / " + totalPageNo);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnExit1 :
                saveLayout.setVisibility(View.VISIBLE);
                break;

            case R.id.btnExit2 :
                saveLayout.setVisibility(View.GONE);
                break;

            case R.id.btnYes :
                // DB 에 현재 위치 저장하기
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.btnNo :
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}

