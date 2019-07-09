package net.awesomekorean.baguni.lesson;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.awesomekorean.baguni.R;

public class LessonFrame extends AppCompatActivity {

    FragmentManager fm;
    FragmentTransaction ft;
    LessonWord lessonWord = new LessonWord();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_frame);

        setFrag(lessonWord);
    }

    public void setFrag(Fragment page) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.lessonFrame, page);
        ft.commit();
    }
}
