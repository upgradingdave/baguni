package net.awesomekorean.baguni.lesson;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.awesomekorean.baguni.R;

import static android.support.constraint.ConstraintSet.BOTTOM;
import static android.support.constraint.ConstraintSet.END;
import static android.support.constraint.ConstraintSet.START;
import static android.support.constraint.ConstraintSet.TOP;
import static java.security.AccessController.getContext;

public class LessonHangulAssembly extends AppCompatActivity {

    TextView c;
    TextView v;

    ConstraintSet constraintSet = new ConstraintSet();
    ConstraintLayout constraintLayout;


    int widthC;
    int heightC;

    int widthV;
    int heightV;

    Button batchim;

    String[] consonant = {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ", "ㄲ", "ㄸ", "ㅃ", "ㅆ", "ㅉ"};
    String[] vowel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_hangul_assembly);

        c = findViewById(R.id.c);
        v = findViewById(R.id.v);

        batchim = findViewById(R.id.btnBatchim);

        constraintLayout = findViewById(R.id.constraintLayout);

        Intent intent = getIntent();

        switch (intent.getExtras().getString("type")) {

            case "cv_h":

                vowel = new String[] {"ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅣ", "ㅐ", "ㅒ", "ㅔ", "ㅖ"};

                widthC = dpToPx(150);
                heightC = dpToPx(250);

                widthV = dpToPx(100);
                heightV = dpToPx(250);

                setTextViewSize(widthC, heightC, widthV, heightV);
                setTextViewPosition(START, END);

                batchim.setVisibility(View.GONE);

                makeHangulBox(consonant, vowel);

                break;

            case "cv_v":

                widthC = dpToPx(250);
                heightC = dpToPx(150);

                widthV = dpToPx(250);
                heightV = dpToPx(100);

                setTextViewSize(widthC, heightC, widthV, heightV);
                setTextViewPosition(TOP, BOTTOM);

                batchim.setVisibility(View.GONE);

                break;

            case "cvc_h":

                break;

            case "cvc_v":

                break;


        }
    }

    private void makeHangulBox(String[] consonant, String[] vowel) {



    }

    private void setTextViewPosition(int vPosition, int cPosition) {

        constraintSet.clone(constraintLayout);
        constraintSet.connect(R.id.v, vPosition, R.id.c, cPosition);
        constraintSet.applyTo(constraintLayout);

    }

    private void setTextViewSize(int widthC, int heightC, int widthV, int heightV) {

        c.setLayoutParams(new ConstraintLayout.LayoutParams(widthC, heightC));
        v.setLayoutParams(new ConstraintLayout.LayoutParams(widthV, heightV));

    }

    public int dpToPx(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (dp * scale + 0.5f);

        return pixels;
    }
}




