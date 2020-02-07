package net.awesomekorean.podo.lesson.lessonNumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.PlayAudioWithString;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberAge;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberDate;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberMoney;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberNative;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberSino;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberTime;

import java.util.Random;

public class LessonNumber extends AppCompatActivity implements View.OnClickListener {

    TextView studyFront;
    TextView studyBack;
    String studyAudio;

    ImageView btnBack;
    TextView btnRandom;
    TextView btnInOrder;
    ImageView btnAudio;
    Button btnNext;

    Number number;

    boolean randomBtnClicked = false;

    int index = 0; // 최신 플래시 카드부터 공부할 때의 인덱스

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_number_frame);

        btnBack = findViewById(R.id.btnBack);
        btnRandom = findViewById(R.id.btnRandom);
        btnInOrder = findViewById(R.id.btnInOrder);
        btnAudio = findViewById(R.id.btnAudio);
        btnNext = findViewById(R.id.btnNext);
        studyFront = findViewById(R.id.studyFront);
        studyBack = findViewById(R.id.studyBack);
        btnBack.setOnClickListener(this);
        btnRandom.setOnClickListener(this);
        btnInOrder.setOnClickListener(this);
        btnAudio.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        Intent intent = getIntent();

        switch (intent.getExtras().getString("extra")) {

            case "sino" :
                number = new NumberSino();
                break;

            case "native" :
                number = new NumberNative();
                break;

            case "time" :
                number = new NumberTime();
                break;

            case "money" :
                number = new NumberMoney();
                break;

            case "date" :
                number = new NumberDate();
                break;

            case "age" :
                number = new NumberAge();
                break;

        }

        studyBack.setVisibility(View.INVISIBLE);
        numberStudyInOrder();
    }

    // 랜덤학습 시작
    public void numberStudyRandom() {
        int randonIndex = random.nextInt(number.getFront().length);
        studyFront.setText(number.getFront()[randonIndex]);
        studyBack.setText(number.getBack()[randonIndex]);
    }


    // 최신부터 학습 모드
    public void numberStudyInOrder() {
        studyFront.setText(number.getFront()[index]);
        studyBack.setText(number.getBack()[index]);

        if(index < number.getFront().length-1) {
            index++;
        } else {
            index = 0;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnBack :
                finish();
                break;

            case R.id.btnRandom :
                btnRandom.setTextColor(ContextCompat.getColor(this, R.color.WHITE));
                btnInOrder.setTextColor(ContextCompat.getColor(this, R.color.GREY_TEXT));
                btnRandom.setBackgroundResource(R.drawable.bg_purple_20_right);
                btnInOrder.setBackgroundResource(R.drawable.bg_white_20_left_stroke_purple);
                randomBtnClicked = true;
                numberStudyRandom();
                break;

            case R.id.btnInOrder :
                btnInOrder.setTextColor(ContextCompat.getColor(this, R.color.WHITE));
                btnRandom.setTextColor(ContextCompat.getColor(this, R.color.GREY_TEXT));
                btnRandom.setBackgroundResource(R.drawable.bg_white_20_right_stroke_purple);
                btnInOrder.setBackgroundResource(R.drawable.bg_purple_20_left);
                index = 0;
                randomBtnClicked = false;
                numberStudyInOrder();
                break;

            case R.id.btnAudio :
                if(studyAudio != null) {
                    PlayAudioWithString playAudioWithString = new PlayAudioWithString();
                    playAudioWithString.playAudio(getApplicationContext(), studyAudio);
                }
                break;

            case R.id.btnNext :
                if(studyBack.getVisibility()==View.INVISIBLE) {
                    studyBack.setVisibility(View.VISIBLE);
                    btnNext.setText(getString(R.string.NEXT));
                } else {
                    studyBack.setVisibility(View.INVISIBLE);
                    btnNext.setText(getString(R.string.ANSWER));

                    if(randomBtnClicked) {
                        numberStudyRandom();
                    } else {
                        numberStudyInOrder();
                    }
                }
                break;

        }
    }
}
