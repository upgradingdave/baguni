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

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class LessonNumberMenu extends AppCompatActivity implements View.OnClickListener {

    public static NumberPractice numberPractice;

    NumberPractice[] numberPractices = {
            new NumberDate(), new NumberAge(), new NumberMoney(), new NumberTime()
    };

    ImageView btnBack;
    Button btnIntro;

    ImageView btnClose;
    ConstraintLayout layoutIntro;
    TextView textViewIntro;

    ListView listView;

    Context context;

    UserInformation userInformation;

    LessonNumberMenuAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_number_menu);

        context = getApplicationContext();

        userInformation = SharedPreferencesInfo.getUserInfo(context);

        btnBack = findViewById(R.id.btnBack);
        btnIntro = findViewById(R.id.btnIntro);
        btnClose = findViewById(R.id.btnClose);
        layoutIntro = findViewById(R.id.layoutIntro);
        listView = findViewById(R.id.listView);
        btnBack.setOnClickListener(this);
        btnIntro.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        setCompletedLessons();

        adapter = new LessonNumberMenuAdapter(context, numberPractices);

        listView.setAdapter(adapter);

        listView.setDivider(null);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                numberPractice = (NumberPractice) parent.getItemAtPosition(position);

                switch (numberPractice.getPracticeTitle()) {

                    case "time" :
                        openLessonNumber(getString(R.string.TIME));
                        break;

                    case "money" :
                        openLessonNumber(getString(R.string.MONEY));
                        break;

                    case "date" :
                        openLessonNumber(getString(R.string.DATE));
                        break;

                    case "age" :
                        openLessonNumber(getString(R.string.AGE));
                        break;
                }
            }
        });
    }


    private void openLessonNumber(String value) {

        Intent intent = new Intent(context, LessonNumber.class);

        intent.putExtra(getString(R.string.EXTRA_ID), value);

        intent.putExtra("isNumberPractice", true);

        startActivity(intent);
    }


    private void setCompletedLessons() {

        UnitProgressInfo unitProgressInfo = new UnitProgressInfo(context, false);

        int sumOfNumberPractice = 0;  // 레슨연습 전체 진도율

        String lessonId;

        for(int i=0; i<numberPractices.length; i++) {

            lessonId = numberPractices[i].getLessonId();

            int progress = unitProgressInfo.getProgress(lessonId);

            if(progress != -1) {

                numberPractices[i].setPracticeProgress(progress);

                sumOfNumberPractice += progress;
            }
        }


        // 레슨연습 전체 진도율 업데이트
        int totalProgress = sumOfNumberPractice / numberPractices.length;

        userInformation = SharedPreferencesInfo.getUserInfo(context);

        userInformation.updateCompleteList(context, "N_practice", totalProgress, false);
    }


    @Override
    public void onResume() {

        super.onResume();
        if(adapter != null) {
            setCompletedLessons();
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnIntro :
                textViewIntro = findViewById(R.id.textViewIntro);
                textViewIntro.setMovementMethod(new ScrollingMovementMethod());
                layoutIntro.setVisibility(VISIBLE);
                break;

            case R.id.btnClose :
                layoutIntro.setVisibility(GONE);
                break;

            case R.id.btnBack :
                finish();
                break;
        }
    }
}
