package net.awesomekorean.baguni.lesson;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.awesomekorean.baguni.R;
import net.awesomekorean.baguni.lesson.LessonHangul;
import net.awesomekorean.baguni.lesson.LessonHangulConsonant;

import java.io.Serializable;

public class LessonHangulMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_hangul_menu);
    }

    public void btnMenuConsonant(View v) {

        LessonHangulConsonant consonant = new LessonHangulConsonant();

        Intent intent = new Intent(getApplicationContext(), LessonHangul.class);
        intent.putExtra("conVowBat", "consonant");
        startActivity(intent);
    }

    public void btnMenuVowel(View v) {
        Intent intent = new Intent(getApplicationContext(), LessonHangul.class);
        intent.putExtra("conVowBat", "vowel");
        startActivity(intent);
    }

    public void btnMenuBatchim(View v) {
        Intent intent = new Intent(getApplicationContext(), LessonHangul.class);
        intent.putExtra("conVowBat", "batchim");
        startActivity(intent);
    }

    public void btnMenuAssembly(View v) {
        Intent intent = new Intent(getApplicationContext(), LessonHangulAssemblyMenu.class);
        startActivity(intent);
    }




}
