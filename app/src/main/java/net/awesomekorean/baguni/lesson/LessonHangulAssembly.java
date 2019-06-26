package net.awesomekorean.baguni.lesson;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    int deviceWidth;

    LinearLayout consonantBoxLayout1;
    LinearLayout consonantBoxLayout2;
    LinearLayout consonantBoxLayout3;
    LinearLayout vowelBoxLayout1;
    LinearLayout vowelBoxLayout2;
    LinearLayout vowelBoxLayout3;


    Button batchim;

    String[] consonant = {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ", "ㄲ", "ㄸ", "ㅃ", "ㅆ", "ㅉ"};
    String[] vowel;

    private View.OnClickListener hangulBoxBtnSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_hangul_assembly);

        c = findViewById(R.id.c);
        v = findViewById(R.id.v);

        batchim = findViewById(R.id.btnBatchim);

        constraintLayout = findViewById(R.id.constraintLayout);

        hangulBoxBtnSelected = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button selectedBtn = (Button) view;
                String selectedHangul = selectedBtn.getText().toString();

                for(String string : consonant) {
                    if (selectedHangul.equals(string)) {
                        c.setText(selectedHangul);
                    }
                }

                for(String string : vowel) {
                    if(selectedHangul.equals(string)) {
                        v.setText(selectedHangul);
                    }
                }

            }
        };



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

    public void makeHangulBox(String[] consonant, String[] vowel) {

        consonantBoxLayout1 = findViewById(R.id.consonantBox1);
        consonantBoxLayout2 = findViewById(R.id.consonantBox2);
        consonantBoxLayout3 = findViewById(R.id.consonantBox3);
        vowelBoxLayout1 = findViewById(R.id.vowelBox1);
        vowelBoxLayout2 = findViewById(R.id.vowelBox2);
        vowelBoxLayout3 = findViewById(R.id.vowelBox3);

        getDisplayWidth();

        makeHangulBoxIterate(consonant, consonantBoxLayout1, consonantBoxLayout2, consonantBoxLayout3);
        makeHangulBoxIterate(vowel, vowelBoxLayout1, vowelBoxLayout2, vowelBoxLayout3);



    }

    private void makeHangulBoxIterate(String[] hangul, LinearLayout hangulBoxLayout1, LinearLayout hangulBoxLayout2, LinearLayout hangulBoxLayout3) {

        int numberOfBox = 0;

        for(String addHangul : hangul) {

            Button hangulBoxBtn = new Button(this);

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpToPx(50), dpToPx(50));

            hangulBoxBtn.setLayoutParams(params);

            hangulBoxBtn.setText(addHangul);
            hangulBoxBtn.setOnClickListener(hangulBoxBtnSelected);

            numberOfBox++;

            if (deviceWidth >= dpToPx(50 * numberOfBox)) {
                hangulBoxLayout1.addView(hangulBoxBtn);
            } else if (deviceWidth < dpToPx(50 * numberOfBox) && deviceWidth * 2 >= dpToPx(50 * numberOfBox)) {
                hangulBoxLayout2.addView(hangulBoxBtn);
            } else if (deviceWidth * 2 < dpToPx(50 * numberOfBox)) {
                hangulBoxLayout3.addView(hangulBoxBtn);
            }
        }


    }

    private void getDisplayWidth() {

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();

        deviceWidth = dm.widthPixels;
    }


    public void setTextViewPosition(int vPosition, int cPosition) {

        constraintSet.clone(constraintLayout);
        constraintSet.connect(R.id.v, vPosition, R.id.c, cPosition);
        constraintSet.applyTo(constraintLayout);

    }

    public void setTextViewSize(int widthC, int heightC, int widthV, int heightV) {

        c.setLayoutParams(new ConstraintLayout.LayoutParams(widthC, heightC));
        v.setLayoutParams(new ConstraintLayout.LayoutParams(widthV, heightV));

    }

    public int dpToPx(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (dp * scale + 0.5f);

        return pixels;
    }

    public void btnConsonantClicked(View view) {

        consonantBoxLayout1.setVisibility(View.VISIBLE);
        consonantBoxLayout2.setVisibility(View.VISIBLE);
        consonantBoxLayout3.setVisibility(View.VISIBLE);

        vowelBoxLayout1.setVisibility(View.GONE);
        vowelBoxLayout2.setVisibility(View.GONE);
        vowelBoxLayout3.setVisibility(View.GONE);

    }

    public void btnVowelClicked(View view) {

        consonantBoxLayout1.setVisibility(View.GONE);
        consonantBoxLayout2.setVisibility(View.GONE);
        consonantBoxLayout3.setVisibility(View.GONE);

        vowelBoxLayout1.setVisibility(View.VISIBLE);
        vowelBoxLayout2.setVisibility(View.VISIBLE);
        vowelBoxLayout3.setVisibility(View.VISIBLE);

    }
}




