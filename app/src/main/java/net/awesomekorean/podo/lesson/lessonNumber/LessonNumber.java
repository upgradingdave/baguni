package net.awesomekorean.podo.lesson.lessonNumber;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.awesomekorean.podo.ConfirmQuit;
import net.awesomekorean.podo.MediaPlayerManager;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.Number;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberAge;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberDate;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberMoney;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberNative;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberSino;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberTime;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LessonNumber extends AppCompatActivity implements View.OnClickListener {

    FirebaseStorage storage = FirebaseStorage.getInstance();

    ImageView btnClose;
    ProgressBar progressBar;
    TextView progressTextView;

    TextView numberFront;
    TextView numberBack;

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

    MediaPlayerManager mediaPlayerManager;

    Map<Integer, byte[]> audiosNumber = new HashMap<>();
    boolean isFirstAudio = true;

    boolean[] checkProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_number_frame);

        mediaPlayerManager = MediaPlayerManager.getInstance();

        btnClose = findViewById(R.id.btnClose);
        progressBar = findViewById(R.id.progressBar);
        progressTextView = findViewById(R.id.progressTextView);
        btnRandom = findViewById(R.id.btnRandom);
        btnInOrder = findViewById(R.id.btnInOrder);
        btnAudio = findViewById(R.id.btnAudio);
        btnNext = findViewById(R.id.btnNext);
        numberFront = findViewById(R.id.numberFront);
        numberBack = findViewById(R.id.numberBack);
        btnClose.setOnClickListener(this);
        btnRandom.setOnClickListener(this);
        btnInOrder.setOnClickListener(this);
        btnAudio.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        Intent intent = getIntent();

        numberTitle = intent.getExtras().getString(getString(R.string.EXTRA_ID));

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
        displayNumber();
    }

    private void setNumbers(String numberTitle) {

        numberLength = number.getFront().length;
        numberAudio = new String[numberLength];

        checkProgress = new boolean[numberLength];

        String folder = "lesson/number/" + numberTitle;

        for(int i=0; i<numberLength; i++) {
            final Integer audioIndex = i;
            numberAudio[i] = "number_" + numberTitle + "_" + i + ".mp3";
            StorageReference storageRef = storage.getReference().child(folder).child(numberAudio[i]);
            final long ONE_MEGABYTE = 1024 * 1024;
            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    System.out.println("오디오를 로드했습니다.");
                    audiosNumber.put(audioIndex, bytes);
                    if(audioIndex == 0) {
                        displayNumber();
                        isFirstAudio = false;
                    }
                }
            });

        }
    }


    private void displayNumber() {
        numberFront.setText(number.getFront()[index]);
        numberBack.setText(number.getBack()[index]);
        checkProgress[index] = true;
        int count = 0;

        for(int i=0; i<numberLength; i++) {
            if(checkProgress[i]) {
                count++;
            }
        }

        progressBar.setProgress(count * 100 / numberLength);
        progressTextView.setText(count + "/" + numberLength);
    }



    // 랜덤학습 시작
    public void numberStudyRandom() {
        int randomIndex = random.nextInt(number.getFront().length);
        index = randomIndex;
        displayNumber();
    }


    public void openConfirmQuit() {
        Intent intent = new Intent(getApplicationContext(), ConfirmQuit.class);
        intent.putExtra(getResources().getString(R.string.LESSON_ID), number.getLessonId());
        startActivityForResult(intent, 200);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            finish();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnClose :
                openConfirmQuit();
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
                displayNumber();
                break;

            case R.id.btnAudio :
                if(audiosNumber.get(index) != null && audiosNumber.get(index).length > 0) {
                    mediaPlayerManager.playMediaPlayer(false);
                }
                break;

            case R.id.btnNext :
                if(numberBack.getVisibility()==View.INVISIBLE) {

                    if(mediaPlayerManager != null && audiosNumber.get(index) != null && audiosNumber.get(index).length > 0) {
                        mediaPlayerManager.setMediaPlayerByte(false, audiosNumber.get(index));

                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.AUDIO_LOADING), Toast.LENGTH_LONG).show();
                    }

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
                        displayNumber();
                    }
                }
                break;

        }
    }


    @Override
    public void onBackPressed() {
        openConfirmQuit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mediaPlayerManager != null) {
            mediaPlayerManager.releaseMediaPlayer();
        }
    }
}
