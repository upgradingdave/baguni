package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.ConfirmQuit;
import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UnlockActivity;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.lesson.lessons.Lesson;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

public class LessonFrame extends AppCompatActivity {

    public static int progressCount;
    public static int totalPageNo = 0;

    public static ProgressBar progressBar;
    public static TextView progressTextView;

    ImageView btnClose;

    private GestureDetectorCompat gestureDetectorCompat = null;
    public static String swipePage;

    FragmentManager fm;
    FragmentTransaction ft;
    LessonWord lessonWord = new LessonWord();

    LinearLayout loadingLayout;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_frame);

        context = getApplicationContext();

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

        intent.putExtra("progress", progressBar.getProgress());

        startActivityForResult(intent, 200);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        openConfirmQuit();
    }
}

