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

    String[] consonant = {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ", "ㄲ", "ㄸ", "ㅃ", "ㅆ", "ㅉ"};
    String[] consonantExplain = {"기역", "니은", "디귿", "리을", "미음", "비읍", "시옷", "이응, No sound", "지읒", "치읓", "키읔", "티읕", "피읖", "히읗", "쌍기역", "쌍디귿", "쌍비읍", "쌍시옷", "쌍지읒"};

    TextView hangul;
    TextView hangulExplain;

    MediaPlayer mediaPlayer;

    int currentHangul = 0;

    FragmentManager fm;
    FragmentTransaction ft;
    TextView intro;
    LessonHangulIntro lessonHangulIntro = new LessonHangulIntro();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_hangul);

        Intent intent = getIntent();

        Object conVowBat = intent.getExtras().getString("conVowBat");
        System.out.println(conVowBat);
        if(conVowBat == "consonant") {
            System.out.println("CONSONANT!!!!!");
        } else if(conVowBat == "vowel") {
            System.out.println("VOWEL!!!!!!!");
        }

//        audioPlay();

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                hangul = (TextView) findViewById(R.id.hangul);
                hangulExplain = (TextView) findViewById(R.id.hangulExplain);
                intro = (TextView) findViewById(R.id.intro);

                System.out.println("introoo : " + intro);
                System.out.println("hangul : " + hangul);

                switch (view.getId()) {
                    case R.id.btnLeft :
                        if(currentHangul == 0) {
                            currentHangul = consonant.length-1;
                        } else {
                            currentHangul--;
                        }
                        setHangul(consonant, consonantExplain);
                        break;

                    case R.id.btnRight :
                        if(currentHangul == consonant.length-1) {
                            currentHangul = 0;
                        } else {
                            currentHangul++;
                        }
                        setHangul(consonant, consonantExplain);
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

                        System.out.println("Introoo22222 : " + intro);
                        fm = getSupportFragmentManager();
                        ft = fm.beginTransaction();
                        ft.replace(R.id.introFragment, lessonHangulIntro);

                        ft.commit();
                        if(intro != null) {
                            intro.setText("Korean only has 14 consonants and 5 double consonants.\n"+
                                    "We can make the sound of each consonant by using articulators like the lips, tongue, mouth, throat and nose.\n"+
                                    "<b>That's why we should think about the correct position of each articulator.</b>\n"+
                                    "This will make you pronounce Korean like a native Korean.\n" +
                                    "Some of consonants could be similar to sounds in your language.\n" +
                                    "But some of the others could be difficult to pronounce exactly right because you have never made that sound in your life.\n" +
                                    "Your muscles in your mouth may not be strong enough to make that new sound yet.\n" +
                                    "<b>Fortunately, we can train our muscles with practice.</b> Let's warm up your mouth before we start.");
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

        hangul.setText(conVow[currentHangul]);
        hangulExplain.setText(conVowExplain[currentHangul]);

    }



}
