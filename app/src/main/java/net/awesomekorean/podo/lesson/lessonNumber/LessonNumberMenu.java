package net.awesomekorean.podo.lesson.lessonNumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessonHangul.LessonHangul;
import net.awesomekorean.podo.lesson.lessonHangul.LessonHangulAssembly;

public class LessonNumberMenu extends AppCompatActivity implements View.OnClickListener {

    ImageView btnBack;

    LinearLayout sinoNumber;
    LinearLayout nativeNumber;
    LinearLayout practice;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_number_menu);

        btnBack = findViewById(R.id.btnBack);
        sinoNumber = findViewById(R.id.consonant);
        nativeNumber = findViewById(R.id.vowel);
        practice = findViewById(R.id.batchim);
        btnBack.setOnClickListener(this);
        sinoNumber.setOnClickListener(this);
        nativeNumber.setOnClickListener(this);
        practice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.consonant :
                intent = new Intent(getApplicationContext(), LessonHangul.class);
                intent.putExtra("number", "sino");
                startActivity(intent);
                break;

            case R.id.vowel :
                intent = new Intent(getApplicationContext(), LessonHangul.class);
                intent.putExtra("number", "native");
                startActivity(intent);
                break;

            case R.id.batchim :
                intent = new Intent(getApplicationContext(), LessonHangul.class);
                intent.putExtra("number", "practice");
                startActivity(intent);
                break;

            case R.id.btnBack :
                finish();
                break;
        }
    }
}
