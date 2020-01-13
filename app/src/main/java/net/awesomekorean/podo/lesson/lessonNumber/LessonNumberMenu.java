package net.awesomekorean.podo.lesson.lessonNumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.awesomekorean.podo.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class LessonNumberMenu extends AppCompatActivity implements View.OnClickListener {

    ImageView btnBack;

    Button btnIntro;
    ImageView btnClose;
    ConstraintLayout layoutIntro;
    TextView textViewIntro;

    LinearLayout sinoNumber;
    LinearLayout nativeNumber;
    LinearLayout practice;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_number_menu);

        btnBack = findViewById(R.id.btnBack);
        btnIntro = findViewById(R.id.btnIntro);
        btnClose = findViewById(R.id.btnClose);
        layoutIntro = findViewById(R.id.layoutIntro);
        sinoNumber = findViewById(R.id.sinoNumber);
        nativeNumber = findViewById(R.id.nativeNumber);
        practice = findViewById(R.id.practice);
        btnBack.setOnClickListener(this);
        btnIntro.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        sinoNumber.setOnClickListener(this);
        nativeNumber.setOnClickListener(this);
        practice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.sinoNumber :
                intent = new Intent(getApplicationContext(), LessonNumber.class);
                intent.putExtra("extra", "sino");
                startActivity(intent);
                break;

            case R.id.nativeNumber :
                intent = new Intent(getApplicationContext(), LessonNumber.class);
                intent.putExtra("extra", "native");
                startActivity(intent);
                break;

            case R.id.practice :
                intent = new Intent(getApplicationContext(), LessonNumberPracticeMenu.class);
                intent.putExtra("extra", "practice");
                startActivity(intent);
                break;

            case R.id.btnBack :
                finish();
                break;

            case R.id.btnIntro :
                textViewIntro = findViewById(R.id.textViewIntro);
                textViewIntro.setMovementMethod(new ScrollingMovementMethod());
                layoutIntro.setVisibility(VISIBLE);
                break;

            case R.id.btnClose :
                layoutIntro.setVisibility(GONE);
                break;
        }
    }
}
