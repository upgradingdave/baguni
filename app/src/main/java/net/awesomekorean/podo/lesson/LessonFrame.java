package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.ConfirmQuit;
import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.MediaPlayerManager;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SettingStatusBar;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UnlockActivity;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.lesson.lessons.Lesson;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import org.w3c.dom.ls.LSException;

public class LessonFrame extends AppCompatActivity implements View.OnClickListener {

    static Lesson lesson;

    public static int progressCount;
    public static int totalPageNo = 0;

    public static ProgressBar progressBar;
    public static TextView progressTextView;

    ImageView btnClose;

    public static TextView navigationWord;
    public static TextView navigationQuiz;
    public static TextView navigationSentence;
    public static TextView navigationDialog;

    private GestureDetectorCompat gestureDetectorCompat = null;
    public static String swipePage;

    FragmentManager fm;
    FragmentTransaction ft;
    LessonWord lessonWord = LessonWord.newInstance();

    LinearLayout loadingLayout;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_frame);

        context = getApplicationContext();

        navigationWord = findViewById(R.id.navigationWord);
        navigationQuiz = findViewById(R.id.navigationQuiz);
        navigationSentence = findViewById(R.id.navigationSentence);
        navigationDialog = findViewById(R.id.navigationDialog);
        navigationWord.setOnClickListener(this);
        navigationQuiz.setOnClickListener(this);
        navigationSentence.setOnClickListener(this);
        navigationDialog.setOnClickListener(this);

        progressCount = 1;
        progressBar = findViewById(R.id.progressBar);
        progressTextView = findViewById(R.id.progressTextView);
        btnClose = findViewById(R.id.btnClose);
        loadingLayout = findViewById(R.id.loadingLayout);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfirmQuit();
            }
        });

        lesson = (Lesson) getIntent().getSerializableExtra(getResources().getString(R.string.LESSON));

        totalPageNo = lesson.getWordFront().length * 3 + 1 + lesson.getSentenceFront().length + 2;

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


    public void onLoadingLayout(boolean b) {
        if(b) {
            loadingLayout.setVisibility(View.VISIBLE);
        } else {
            loadingLayout.setVisibility(View.GONE);
        }
    }


    public void openConfirmQuit() {

        Intent intent = new Intent(context, ConfirmQuit.class);

        intent.putExtra(getResources().getString(R.string.PROGRESS), progressBar.getProgress());

        intent.putExtra(getResources().getString(R.string.LESSON_ID), lesson.getLessonId());

        startActivityForResult(intent, 200);
    }


    // 네비게이션 배경색 변경
    public static void setNavigationColor(Context context, TextView textView, int bg) {

        navigationWord.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_grey_light_10));
        navigationQuiz.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_grey_light_10));
        navigationSentence.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_grey_light_10));
        navigationDialog.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_grey_light_10));

        textView.setBackground(ContextCompat.getDrawable(context, bg));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            MediaPlayerManager mediaPlayerManager = MediaPlayerManager.getInstance();
            mediaPlayerManager.stopMediaPlayer();
            finish();
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.navigationWord :
                setNavigationColor(context, navigationWord, R.drawable.bg_yellow_10);
                replaceFragment(LessonWord.newInstance());
                progressCount = 1;
                progressCount();
                break;

            case R.id.navigationQuiz :
                setNavigationColor(context, navigationQuiz, R.drawable.bg_green_10);
                replaceFragment(LessonWordQuiz1.newInstance());
                progressCount = 1 + lesson.getWordFront().length;
                progressCount();
                break;

            case R.id.navigationSentence :
                setNavigationColor(context, navigationSentence, R.drawable.bg_blue_10);
                replaceFragment(LessonSentence.newInstance());
                progressCount = 1 + lesson.getWordFront().length * 3 + 1;
                progressCount();
                break;

            case R.id.navigationDialog :
                setNavigationColor(context, navigationDialog, R.drawable.bg_purple_10);
                replaceFragment(LessonDialog.newInstance());
                progressCount = 1 + lesson.getWordFront().length * 3 + 1 + lesson.getSentenceFront().length;
                progressCount();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        openConfirmQuit();
    }
}

