package net.awesomekorean.baguni;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.awesomekorean.baguni.lesson.LessonHangul;
import net.awesomekorean.baguni.lesson.LessonHangulConsonant;

import java.io.Serializable;

public class LessonHangulMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_hangul_menu);
    }

    public void btnConsonant(View v) {

        LessonHangulConsonant consonant = new LessonHangulConsonant();

        Intent intent = new Intent(getApplicationContext(), LessonHangul.class);
        intent.putExtra("conVowBat", "consonant");
        startActivity(intent);
    }

    public void btnVowel(View v) {
        Intent intent = new Intent(getApplicationContext(), LessonHangul.class);
        intent.putExtra("conVowBat", "vowel");
        startActivity(intent);
    }

}
