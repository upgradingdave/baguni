package net.awesomekorean.baguni.lesson.lessonHangul;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import net.awesomekorean.baguni.R;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class LessonHangul extends AppCompatActivity implements Button.OnClickListener{

    Hangul thisHangul;

    TextView textViewHangul;
    TextView textViewHangulExplain;
    TextView textViewIntro;
    ImageView imageViewHangul;

    MediaPlayer mediaPlayer;

    String[] hangul;
    String[] hangulExplain;
    String hangulIntro;
    String conVowBat;

    int currentHangul = 0;
    int introVisible = 0;
    int writingBtnClicked = 0;
    int hintBtnClicked = 0;

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

//        audioPlay();


        Button btnLeft = findViewById(R.id.btnLeft);
        Button btnRight = findViewById(R.id.btnRight);
        Button btnListening = findViewById(R.id.btnListening);
        Button btnWriting = findViewById(R.id.btnWriting);
        Button btnHint = findViewById(R.id.btnHint);
        Button btnIntro = findViewById(R.id.btnIntro);
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnListening.setOnClickListener(this);
        btnWriting.setOnClickListener(this);
        btnHint.setOnClickListener(this);
        btnIntro.setOnClickListener(this);

    }

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
                if(imageViewHangul != null) {
                    visible(VISIBLE, GONE);
                }
                break;

            case R.id.btnRight :
                if(currentHangul == hangul.length-1) {
                    currentHangul = 0;
                } else {
                    currentHangul++;
                }
                setHangul(hangul, hangulExplain);
                if(imageViewHangul != null) {
                    visible(VISIBLE, GONE);
                }
                break;

            case R.id.btnListening :
                System.out.println("Listening button clicked");
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

                textViewIntro.setText(hangulIntro);

                if(introVisible == 0) {
                    textViewIntro.setVisibility(VISIBLE);
                    introVisible = 1;
                } else {
                    textViewIntro.setVisibility(GONE);
                    introVisible = 0;
                }

                break;
        }
    }

    public void getThisHangul(String comVowBat) {

        conVowBat = comVowBat;
        hangul = thisHangul.getHangul();
        hangulExplain = thisHangul.getHangulExplain();
        hangulIntro = thisHangul.getHangulIntro();
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

        mediaPlayer = new MediaPlayer();

        String string = "R.raw.con0";

        int currentPlay = getResources().getIdentifier(string, "raw", getPackageName());


        mediaPlayer = MediaPlayer.create(getApplicationContext(), currentPlay);

        mediaPlayer.start();
    }


    public void setHangul(String[] conVow, String[] conVowExplain) {

        textViewHangul.setText(conVow[currentHangul]);
        textViewHangulExplain.setText(conVowExplain[currentHangul]);

    }
}
