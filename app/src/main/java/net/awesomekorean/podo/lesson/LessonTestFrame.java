package net.awesomekorean.podo.lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessons.LessonReview;

public class LessonTestFrame extends AppCompatActivity {

    FragmentManager fm;
    FragmentTransaction ft;

    ImageView btnClose;
    public static int totalTestNo = 20;
    public static int progressCount;
    public static ProgressBar progressBar;
    public static TextView tvProgress;
    public static int numberOfCorrect;

    LessonReview lesson;



    LessonTestConjugate lessonTestConjugate = LessonTestConjugate.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_test_frame);

        btnClose = findViewById(R.id.btnClose);
        progressBar = findViewById(R.id.progressBar);
        tvProgress = findViewById(R.id.tvProgress);
        numberOfCorrect = 0;
        progressCount = 1;
        tvProgress.setText(progressCount + " / " + totalTestNo);

        lesson = (LessonReview) getIntent().getSerializableExtra(getResources().getString(R.string.LESSON));


        replaceFragment(lessonTestConjugate);
    }


    public void replaceFragment(Fragment fragment) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commitAllowingStateLoss();
    }


    public static void progressCount() {
        progressCount++;
        progressBar.setProgress(progressCount*100/totalTestNo);
        tvProgress.setText(progressCount + " / " + totalTestNo);
    }
}
