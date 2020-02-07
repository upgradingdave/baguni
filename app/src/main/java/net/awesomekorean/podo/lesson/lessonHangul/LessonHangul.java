package net.awesomekorean.podo.lesson.lessonHangul;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.PlayAudioWithString;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class LessonHangul extends AppCompatActivity implements Button.OnClickListener {

    Hangul thisHangul;

    LinearLayout layoutHangul;
    TextView textViewHangul;
    TextView textViewHangulExplain;
    ConstraintLayout layoutIntro; // 인트로 버튼 눌렀을 때 뷰
    TextView textViewIntro; // 인트로 텍스트
    ImageView btnClose; // 인트로뷰 닫기 버튼

    ImageView imageViewHangul;

    String[] hangul;
    String[] hangulExplain;
    String hangulIntro;
    String conVowBat;

    int currentHangul = 0;
    int writingBtnClicked = 0;
    int hintBtnClicked = 0;

    ImageView btnAudio;
    LinearLayout btnWriting;
    LinearLayout btnHint;
    Button btnIntro;
    ImageView btnBack;

    PlayAudioWithString playAudioWithString =  new PlayAudioWithString();

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_hangul);

        context = getApplicationContext();

        layoutHangul = findViewById(R.id.layoutHangul);
        textViewHangul = findViewById(R.id.textViewHangul);
        textViewHangulExplain = findViewById(R.id.textViewHangulExplain);
        layoutIntro = findViewById(R.id.layoutIntro);
        textViewIntro = findViewById(R.id.textViewIntro);
        btnClose = findViewById(R.id.btnClose);
        btnAudio = findViewById(R.id.btnAudio);
        btnWriting = findViewById(R.id.btnWriting);
        btnHint = findViewById(R.id.btnHint);
        btnIntro = findViewById(R.id.btnIntro);
        btnBack = findViewById(R.id.btnBack);
        btnAudio.setOnClickListener(this);
        btnWriting.setOnClickListener(this);
        btnHint.setOnClickListener(this);
        btnIntro.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        Intent intent = getIntent();

        switch (intent.getExtras().getString("conVowBat")) {

            case "consonant" :

                thisHangul = new LessonHangulConsonant();
                getThisHangul("con");
                break;

            case "vowel" :

                thisHangul = new LessonHangulVowel();
                getThisHangul("vow");
                break;

            case "batchim" :

                thisHangul = new LessonHangulBatchim();
                getThisHangul("bat");
                break;
        }

        layoutHangul.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeRight() {
                if(currentHangul == 0) {
                    currentHangul = hangul.length-1;
                } else {
                    currentHangul--;
                }
                setHangul(hangul, hangulExplain);
                if(imageViewHangul != null) {
                    visible(VISIBLE, GONE);
                }
            }

            public void onSwipeLeft() {
                if(currentHangul == hangul.length-1) {
                    currentHangul = 0;
                } else {
                    currentHangul++;
                }
                setHangul(hangul, hangulExplain);
                if(imageViewHangul != null) {
                    visible(VISIBLE, GONE);
                }
            }
        });

        audioPlay();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnAudio :
                audioPlay();
                break;

            case R.id.btnWriting :
                if(writingBtnClicked == 0) {

                    String resName = "@drawable/w" + conVowBat + currentHangul;
                    String packName = getApplicationContext().getPackageName();
                    int resID = getResources().getIdentifier(resName, "drawable", packName);

                    imageViewHangul = findViewById(R.id.imageViewHangul);
                    imageViewHangul.setImageResource(resID);

                    visible(GONE, VISIBLE);
                    writingBtnClicked = 1;
                    hintBtnClicked = 0;

                } else {
                    visible(VISIBLE, GONE);
                    writingBtnClicked = 0;
                }

                break;

            case R.id.btnHint :
                if(hintBtnClicked == 0) {

                    String resName = "@drawable/h" + conVowBat + currentHangul;
                    String packName = getApplicationContext().getPackageName();
                    int resID = getResources().getIdentifier(resName, "drawable", packName);

                    imageViewHangul = findViewById(R.id.imageViewHangul);
                    imageViewHangul.setImageResource(resID);

                    visible(GONE, VISIBLE);
                    hintBtnClicked = 1;
                    writingBtnClicked = 0;

                } else {
                    visible(VISIBLE, GONE);
                    hintBtnClicked = 0;
                }

                break;

            case R.id.btnIntro :
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

    public void getThisHangul(String comVowBat) {

        conVowBat = comVowBat;
        hangul = thisHangul.getHangul();
        hangulExplain = thisHangul.getHangulExplain();
        hangulIntro = thisHangul.getHangulIntro();
        textViewIntro.setText(hangulIntro);
        textViewIntro.setMovementMethod(new ScrollingMovementMethod());
        setInitial();

    }

    private void visible(int textView, int imageView) {
        imageViewHangul.setVisibility(imageView);
        textViewHangul.setVisibility(textView);

    }

    private void setInitial() {

        textViewHangul.setText(hangul[0]);
        textViewHangulExplain.setText(hangulExplain[0]);
    }



    public void audioPlay() {

        String audioFile = thisHangul.getHangulAudio(currentHangul) + ".mp3";
        playAudioWithString.playAudioHangul(audioFile, conVowBat);
    }


    public void setHangul(String[] conVow, String[] conVowExplain) {

        textViewHangul.setText(conVow[currentHangul]);
        textViewHangulExplain.setText(conVowExplain[currentHangul]);
        audioPlay();
    }
}
