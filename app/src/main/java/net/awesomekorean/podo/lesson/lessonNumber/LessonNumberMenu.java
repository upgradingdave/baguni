package net.awesomekorean.podo.lesson.lessonNumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.UnitProgressInfo;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberAge;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberDate;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberMoney;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberPractice;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberTime;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class LessonNumberMenu extends AppCompatActivity implements View.OnClickListener {

    public static LessonItem numberPractice;

    LessonItem[] numberPractices = {
            new NumberDate(), new NumberAge(), new NumberMoney(), new NumberTime()
    };

    ImageView btnBack;

    Context context;

    UserInformation userInformation;

    LinearLayout layoutDate;
    LinearLayout layoutAge;
    LinearLayout layoutMoney;
    LinearLayout layoutTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_number_menu);

        context = getApplicationContext();

        userInformation = SharedPreferencesInfo.getUserInfo(context);

        btnBack = findViewById(R.id.btnBack);
        layoutDate = findViewById(R.id.layoutDate);
        layoutAge = findViewById(R.id.layoutAge);
        layoutMoney = findViewById(R.id.layoutMoney);
        layoutTime = findViewById(R.id.layoutTime);
        btnBack.setOnClickListener(this);
        layoutDate.setOnClickListener(this);
        layoutAge.setOnClickListener(this);
        layoutMoney.setOnClickListener(this);
        layoutTime.setOnClickListener(this);
    }


    private void openLessonNumber(String value) {
        Intent intent = new Intent(context, LessonNumber.class);
        intent.putExtra(getString(R.string.EXTRA_ID), value);
        intent.putExtra("isNumberPractice", true);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnBack :
                finish();
                break;

            case R.id.layoutDate :
                openLessonNumber(getString(R.string.DATE));
                break;

            case R.id.layoutAge :
                openLessonNumber(getString(R.string.AGE));
                break;

            case R.id.layoutMoney :
                openLessonNumber(getString(R.string.MONEY));
                break;

            case R.id.layoutTime :
                openLessonNumber(getString(R.string.TIME));
                break;
        }
    }
}
