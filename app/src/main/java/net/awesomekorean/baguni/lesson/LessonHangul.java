package net.awesomekorean.baguni.lesson;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import net.awesomekorean.baguni.R;

import java.io.File;

public class LessonHangul extends AppCompatActivity {

    TextView textViewHangul;
    TextView textViewHangulExplain;
    TextView textViewIntro;

    MediaPlayer mediaPlayer;

    String[] hangul;
    String[] hangulExplain;
    String hangulIntro;

    int currentHangul = 0;
    int introVisible = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_hangul);

        textViewHangul = findViewById(R.id.textViewHangul);
        textViewHangulExplain = findViewById(R.id.textViewHangulExplain);
        textViewIntro = findViewById(R.id.textViewIntro);

        Intent intent = getIntent();

        switch (intent.getExtras().getString("conVowBat")) {

            case "consonant" :

                LessonHangulConsonant consonant = new LessonHangulConsonant();
                hangul = consonant.hangul;
                hangulExplain = consonant.hangulExplain;
                hangulIntro = consonant.hangulIntro;
                setInitial();
                break;

            case "vowel" :

                LessonHangulVowel vowel = new LessonHangulVowel();
                hangul = vowel.hangul;
                hangulExplain = vowel.hangulExplain;
                hangulIntro = vowel.hangulIntro;
                setInitial();
                break;

        }

//        audioPlay();

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch (view.getId()) {
                    case R.id.btnLeft :
                        if(currentHangul == 0) {
                            currentHangul = hangul.length-1;
                        } else {
                            currentHangul--;
                        }
                        setHangul(hangul, hangulExplain);
                        break;

                    case R.id.btnRight :
                        if(currentHangul == hangul.length-1) {
                            currentHangul = 0;
                        } else {
                            currentHangul++;
                        }
                        setHangul(hangul, hangulExplain);
                        break;

                    case R.id.btnListening :
                        System.out.println("Listening button clicked");
                        break;

                    case R.id.btnWriting :
                        System.out.println("Writing button clicked");
                        break;

                    case R.id.btnHint :
                        System.out.println("Hint button clicked");
                        break;

                    case R.id.btnIntro :

                        textViewIntro.setText(hangulIntro);

                        if(introVisible == 0) {
                            textViewIntro.setVisibility(View.VISIBLE);
                            introVisible = 1;
                        } else {
                            textViewIntro.setVisibility(View.GONE);
                            introVisible = 0;
                        }

                        break;


                }
            }
        };

        Button btnLeft = (Button) findViewById(R.id.btnLeft);
        Button btnRight = (Button) findViewById(R.id.btnRight);
        Button btnListening = (Button) findViewById(R.id.btnListening);
        Button btnWriting = (Button) findViewById(R.id.btnWriting);
        Button btnHint = (Button) findViewById(R.id.btnHint);
        Button btnIntro = (Button) findViewById(R.id.btnIntro);
        btnLeft.setOnClickListener(onClickListener);
        btnRight.setOnClickListener(onClickListener);
        btnListening.setOnClickListener(onClickListener);
        btnWriting.setOnClickListener(onClickListener);
        btnHint.setOnClickListener(onClickListener);
        btnIntro.setOnClickListener(onClickListener);

    }

    private void setInitial() {

        textViewHangul.setText(hangul[0]);
        textViewHangulExplain.setText(hangulExplain[0]);
    }

//    public void audioPlay() {
//
//        String string = "R.raw.con0";
//
//        Uri currentPlay = Uri.parse(string);
//
//        mediaPlayer = MediaPlayer.create(getApplicationContext(), currentPlay);
//
//        mediaPlayer.start();
//
//
//    }


    public void setHangul(String[] conVow, String[] conVowExplain) {

        textViewHangul.setText(conVow[currentHangul]);
        textViewHangulExplain.setText(conVowExplain[currentHangul]);

    }



}
