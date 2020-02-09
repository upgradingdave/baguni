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

    TextView numberFront;
    TextView numberBack;

    ImageView btnBack;
    TextView btnRandom;
    TextView btnInOrder;
    ImageView btnAudio;
    Button btnNext;

    Number number;

    String numberTitle;
    String[] numberAudio;
    int numberLength;

    boolean randomBtnClicked = false;

    int index = 0; // 최신 플래시 카드부터 공부할 때의 인덱스

    Random random = new Random();

    PlayAudioWithString playAudioWithString = new PlayAudioWithString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_number_frame);

        btnBack = findViewById(R.id.btnBack);
        btnRandom = findViewById(R.id.btnRandom);
        btnInOrder = findViewById(R.id.btnInOrder);
        btnAudio = findViewById(R.id.btnAudio);
        btnNext = findViewById(R.id.btnNext);
        numberFront = findViewById(R.id.numberFront);
        numberBack = findViewById(R.id.numberBack);
        btnBack.setOnClickListener(this);
        btnRandom.setOnClickListener(this);
        btnInOrder.setOnClickListener(this);
        btnAudio.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        Intent intent = getIntent();

        numberTitle = intent.getExtras().getString("extra");

        switch (numberTitle) {

            case "sino" :
                number = new NumberSino();
                setNumbers(numberTitle);
                break;

            case "native" :
                number = new NumberNative();
                setNumbers(numberTitle);
                break;

            case "time" :
                number = new NumberTime();
                setNumbers(numberTitle);
                break;

            case "money" :
                number = new NumberMoney();
                setNumbers(numberTitle);
                break;

            case "date" :
                number = new NumberDate();
                setNumbers(numberTitle);
                break;

            case "age" :
                number = new NumberAge();
                setNumbers(numberTitle);
                break;
        }

        numberBack.setVisibility(View.INVISIBLE);
        numberStudyInOrder();
    }

    private void setNumbers(String numberTitle) {
        numberLength = number.getFront().length;
        numberAudio = new String[numberLength];
        for(int i=0; i<numberLength; i++) {
            numberAudio[i] = "number_" + numberTitle + "_" + i + ".mp3";
        }
    }

    // 랜덤학습 시작
    public void numberStudyRandom() {
        int randomIndex = random.nextInt(number.getFront().length);
        index = randomIndex;
        displayNumber();
    }

    // 최신부터 학습 모드
    public void numberStudyInOrder() {
        displayNumber();
    }

    private void displayNumber() {
        numberFront.setText(number.getFront()[index]);
        numberBack.setText(number.getBack()[index]);
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
                playAudioWithString.playAudioNumber(numberAudio[index], numberTitle);
                break;

            case R.id.btnNext :
                if(numberBack.getVisibility()==View.INVISIBLE) {
                    playAudioWithString.playAudioNumber(numberAudio[index], numberTitle);
                    btnAudio.setVisibility(View.VISIBLE);
                    numberBack.setVisibility(View.VISIBLE);
                    btnNext.setText(getString(R.string.NEXT));

                } else {
                    numberBack.setVisibility(View.INVISIBLE);
                    btnAudio.setVisibility(View.GONE);
                    btnNext.setText(getString(R.string.ANSWER));

                    if(randomBtnClicked) {
                        numberStudyRandom();

                    } else {
                        if(index < number.getFront().length-1) {
                            index++;
                        } else {
                            index = 0;
                        }
                        numberStudyInOrder();
                    }
                }
                break;

        }
    }
}
