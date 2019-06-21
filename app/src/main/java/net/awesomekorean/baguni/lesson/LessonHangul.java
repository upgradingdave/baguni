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

    MediaPlayer mediaPlayer;

    String[] hangul;
    String[] hangulExplain;
    String hangulIntro;

    int currentHangul = 0;

    FragmentManager fm;
    FragmentTransaction ft;
    TextView textViewIntro;
    LessonHangulIntro lessonHangulIntro = new LessonHangulIntro();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_hangul);

        textViewHangul = findViewById(R.id.textviewHangul);
        textViewHangulExplain = findViewById(R.id.textviewHangulExplain);

        Intent intent = getIntent();

        switch (intent.getExtras().getString("conVowBat")) {

            case "consonant" :

                LessonHangulConsonant consonant = new LessonHangulConsonant();
                hangul = consonant.hangul;
                hangulExplain = consonant.hangulExplain;
                hangulIntro = consonant.hangulIntro;
                textViewHangul.setText(hangul[0]);
                textViewHangulExplain.setText(hangulExplain[0]);
                break;

            case "vowel" :

                LessonHangulVowel vowel = new LessonHangulVowel();
                hangul = vowel.hangul;
                hangulExplain = vowel.hangulExplain;
                hangulIntro = vowel.hangulIntro;
                textViewHangul.setText(hangul[0]);
                textViewHangulExplain.setText(hangulExplain[0]);
                break;

        }

//        audioPlay();

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                textViewIntro = (TextView) findViewById(R.id.intro);

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

                        fm = getSupportFragmentManager();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.introFragment, lessonHangulIntro);

                        ft.commit();
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
