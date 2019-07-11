package net.awesomekorean.baguni.lessonHangul;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.awesomekorean.baguni.R;

import static android.support.constraint.ConstraintSet.GONE;
import static android.support.constraint.ConstraintSet.VISIBLE;

public class LessonHangulAssembly extends AppCompatActivity {

    TextView textViewIntro;
    TextView assemblyTextView;

    int deviceWidth;

    int introVisible = 0;

    Button btnConsonant;
    Button btnBatchim;

    LinearLayout consonantBoxLayout1;
    LinearLayout consonantBoxLayout2;
    LinearLayout consonantBoxLayout3;
    LinearLayout vowelBoxLayout1;
    LinearLayout vowelBoxLayout2;
    LinearLayout vowelBoxLayout3;
    LinearLayout batchimBoxLayout1;
    LinearLayout batchimBoxLayout2;
    LinearLayout batchimBoxLayout3;

    Boolean consonantSelected = false;
    Boolean vowelSelected = false;
    Boolean batchimSelected = false;

    String selectedConsonant = null;
    String selectedVowel = null;
    String selectedBatchim = null;

    Boolean consonantBtnClicked = false;
    Boolean vowelBtnClicked = false;
    Boolean batBtnClicked = false;

    String[] consonant = {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ", "ㄲ", "ㄸ", "ㅃ", "ㅆ", "ㅉ"};
    String[] vowel = {};
    String[] batchim = {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ", "ㄲ", "ㅆ"};
    String assemblyIntro = "We have finished learning every Hangul!\n" +
                    "Now let's make every character by yourself.\n" +
                    "We have 4 ways to make characters.\n" +
                    "There are 2 rules when making characters.\n" +
                    "1. The first Hangul should be a consonant.\n" +
                    "2. A consonant and vowel should be used alternately.\n" +
                    "Note)\n" +
                    "You'll see some words that have a double Batchim later.\n" +
                    "But don't worry.\n" +
                    "It sounds one of them and we will not learn about this yet.\n" +
                    "You can learn it easily when you start to learn words later.\n";

    public View.OnClickListener hangulBoxBtnSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_hangul_assembly);

        consonantBoxLayout1 = findViewById(R.id.consonantBox1);
        consonantBoxLayout2 = findViewById(R.id.consonantBox2);
        consonantBoxLayout3 = findViewById(R.id.consonantBox3);
        vowelBoxLayout1 = findViewById(R.id.vowelBox1);
        vowelBoxLayout2 = findViewById(R.id.vowelBox2);
        vowelBoxLayout3 = findViewById(R.id.vowelBox3);
        batchimBoxLayout1 = findViewById(R.id.batchimBox1);
        batchimBoxLayout2 = findViewById(R.id.batchimBox2);
        batchimBoxLayout3 = findViewById(R.id.batchimBox3);

        assemblyTextView = findViewById(R.id.assemblyTextView);


        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {

                    case R.id.btnIntro :

                        textViewIntro = findViewById(R.id.textViewIntro);

                        textViewIntro.setText(assemblyIntro);

                        if(introVisible == 0) {
                            textViewIntro.setVisibility(View.VISIBLE);
                            introVisible = 1;
                        } else {
                            textViewIntro.setVisibility(View.GONE);
                            introVisible = 0;
                        }

                        break;

                    case R.id.btnRepeat :

                        break;

                    case R.id.cvH :

                        vowel = new String[] {"ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅣ", "ㅐ", "ㅒ", "ㅔ", "ㅖ"};

                        initialization();

                        btnBatchim.setVisibility(View.GONE);

                        makeHangulBox(consonant, vowel, batchim);

                        break;

                    case R.id.cvV :

                        vowel = new String[] {"ㅗ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅘ", "ㅙ", "ㅚ", "ㅝ", "ㅞ", "ㅟ", "ㅢ"};

                        initialization();

                        btnBatchim.setVisibility(View.GONE);

                        makeHangulBox(consonant, vowel, batchim);

                        break;

                    case R.id.cvcH :

                        vowel = new String[] {"ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅣ", "ㅐ", "ㅒ", "ㅔ", "ㅖ"};

                        initialization();

                        btnBatchim.setVisibility(View.VISIBLE);

                        makeHangulBox(consonant, vowel, batchim);

                        break;

                    case R.id.cvcV :

                        vowel = new String[] {"ㅗ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅘ", "ㅙ", "ㅚ", "ㅝ", "ㅞ", "ㅟ", "ㅢ"};

                        initialization();

                        btnBatchim.setVisibility(View.VISIBLE);

                        makeHangulBox(consonant, vowel, batchim);

                        break;

                    case R.id.btnConsonant :

                        conVowBtnClicked(true, false, false);

                        hangulBoxVisible(VISIBLE, GONE, GONE);

                        break;

                    case R.id.btnVowel :

                        conVowBtnClicked(false, true, false);

                        hangulBoxVisible(GONE, VISIBLE, GONE);

                        break;

                    case R.id.btnBatchim :

                        conVowBtnClicked(false, false, true);

                        hangulBoxVisible(GONE, GONE, VISIBLE);

                        break;
                }
            }
        };

        Button btnIntro = findViewById(R.id.btnIntro);
        Button btnRepeat = findViewById(R.id.btnRepeat);
        ImageButton cvH = findViewById(R.id.cvH);
        ImageButton cvV =findViewById(R.id.cvV);
        ImageButton cvcH = findViewById(R.id.cvcH);
        ImageButton cvcV = findViewById(R.id.cvcV);
        btnConsonant = findViewById(R.id.btnConsonant);
        Button btnVowel = findViewById(R.id.btnVowel);
        btnBatchim =  findViewById(R.id.btnBatchim);
        btnIntro.setOnClickListener(onClickListener);
        btnRepeat.setOnClickListener(onClickListener);
        cvH.setOnClickListener(onClickListener);
        cvV.setOnClickListener(onClickListener);
        cvcH.setOnClickListener(onClickListener);
        cvcV.setOnClickListener(onClickListener);
        btnConsonant.setOnClickListener(onClickListener);
        btnVowel.setOnClickListener(onClickListener);
        btnBatchim.setOnClickListener(onClickListener);



        hangulBoxBtnSelected = new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                Button selectedBtn = (Button) view;
                String selectedHangul = selectedBtn.getText().toString();

                if (consonantBtnClicked) {
                    displayHangul(selectedHangul, consonant);
                } else if (vowelBtnClicked) {
                    displayHangul(selectedHangul, vowel);
                } else if (batBtnClicked) {
                    displayHangul(selectedHangul, batchim);
                }

                if(consonantSelected == true && vowelSelected == true && batchimSelected == false) {
                    HangulUniCode hangul = new HangulUniCode(selectedConsonant, selectedVowel);
                    assemblyTextView.setText(hangul.getAssembledHangul());
                } else if(consonantSelected == true && vowelSelected == true && batchimSelected == true) {
                    HangulUniCode hangul = new HangulUniCode(selectedConsonant, selectedVowel, selectedBatchim);
                    assemblyTextView.setText(hangul.getAssembledHangul());
                } else {
                    assemblyTextView.setText(selectedHangul);
                }


            }
        };
        cvH.callOnClick();
    }

    public void initialization() {

        assemblyTextView.setText("");
        btnConsonant.callOnClick();
        consonantSelected = false;
        vowelSelected = false;
        batchimSelected = false;
    }

    public void conVowBtnClicked(boolean con, boolean vow, boolean bat) {
        consonantBtnClicked = con;
        vowelBtnClicked = vow;
        batBtnClicked = bat;
    }

    public void displayHangul(String selectedHangul, String[] conVow) {

        for(String string : conVow) {
            if (selectedHangul.equals(string)) {
                assemblyTextView.setText(selectedHangul);
            }
        }

        if (conVow == consonant) {
            selectedConsonant = selectedHangul;
            consonantSelected = true;
        } else if (conVow == vowel) {
            selectedVowel = selectedHangul;
            vowelSelected = true;
        } else if (conVow == batchim) {
            selectedBatchim = selectedHangul;
            batchimSelected = true;
        }
    }


    public void makeHangulBox(String[] consonant, String[] vowel, String[] batchim) {

        getDisplayWidth();

        removeAllView();

        makeHangulBoxIterate(consonant, consonantBoxLayout1, consonantBoxLayout2, consonantBoxLayout3);
        makeHangulBoxIterate(vowel, vowelBoxLayout1, vowelBoxLayout2, vowelBoxLayout3);
        makeHangulBoxIterate(batchim, batchimBoxLayout1, batchimBoxLayout2, batchimBoxLayout3);
    }

    private void removeAllView() {
        consonantBoxLayout1.removeAllViews();
        consonantBoxLayout2.removeAllViews();
        consonantBoxLayout3.removeAllViews();
        vowelBoxLayout1.removeAllViews();
        vowelBoxLayout2.removeAllViews();
        vowelBoxLayout3.removeAllViews();
        batchimBoxLayout1.removeAllViews();
        batchimBoxLayout2.removeAllViews();
        batchimBoxLayout3.removeAllViews();
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


    public void getDisplayWidth() {

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();

        deviceWidth = dm.widthPixels;
    }


    public int dpToPx(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (dp * scale + 0.5f);

        return pixels;
    }


    public void hangulBoxVisible(int consonantBoxLayout, int vowelBoxLayout, int batchimBoxLayout) {

        consonantBoxLayout1.setVisibility(consonantBoxLayout);
        consonantBoxLayout2.setVisibility(consonantBoxLayout);
        consonantBoxLayout3.setVisibility(consonantBoxLayout);

        vowelBoxLayout1.setVisibility(vowelBoxLayout);
        vowelBoxLayout2.setVisibility(vowelBoxLayout);
        vowelBoxLayout3.setVisibility(vowelBoxLayout);

        batchimBoxLayout1.setVisibility(batchimBoxLayout);
        batchimBoxLayout2.setVisibility(batchimBoxLayout);
        batchimBoxLayout3.setVisibility(batchimBoxLayout);
    }

}




