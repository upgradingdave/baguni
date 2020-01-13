package net.awesomekorean.podo.lesson.lessonNumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.awesomekorean.podo.R;

public class LessonNumberPracticeMenu extends AppCompatActivity implements View.OnClickListener {

    ImageView btnBack;

    LinearLayout time;
    LinearLayout money;
    LinearLayout date;
    LinearLayout age;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_number_practice_menu);

        btnBack = findViewById(R.id.btnBack);
        time = findViewById(R.id.time);
        money = findViewById(R.id.money);
        date = findViewById(R.id.date);
        age = findViewById(R.id.age);
        btnBack.setOnClickListener(this);
        time.setOnClickListener(this);
        money.setOnClickListener(this);
        date.setOnClickListener(this);
        age.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.time :
                intent = new Intent(getApplicationContext(), LessonNumber.class);
                intent.putExtra("extra", "time");
                startActivity(intent);
                break;

            case R.id.money :
                intent = new Intent(getApplicationContext(), LessonNumber.class);
                intent.putExtra("extra", "money");
                startActivity(intent);
                break;

            case R.id.date :
                intent = new Intent(getApplicationContext(), LessonNumber.class);
                intent.putExtra("extra", "date");
                startActivity(intent);
                break;

            case R.id.age :
                intent = new Intent(getApplicationContext(), LessonNumber.class);
                intent.putExtra("extra", "age");
                startActivity(intent);
                break;

            case R.id.btnBack :
                finish();
                break;
        }
    }
}
