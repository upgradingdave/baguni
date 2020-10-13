package net.awesomekorean.podo.lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.gms.common.util.ArrayUtils;

import net.awesomekorean.podo.AdsManager;
import net.awesomekorean.podo.PlaySoundPool;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessonReview.R_Conjugation_Lesson00;

import java.util.Arrays;

public class LR_Conjugation extends AppCompatActivity implements View.OnClickListener {

    AdsManager adsManager;

    TextView countText;
    TextView tvEnglish;
    TextView tvAnswer;
    FlexboxLayout flexBaseForm;
    FlexboxLayout flexConjugation;
    ImageView btnClose;
    Button btnConfirm;

    R_Conjugation_Lesson00 testItem;
    int baseFormSize;
    int conjugationSize;
    int baseFormIndex;
    int conjugationIndex;
    int selectedBaseFormIndex;
    int selectedConjugationIndex;
    int[] answerListBaseForm = new int[3];
    int[] answerListConjugation = new int[3];
    ToggleButton toggleButton;
    boolean isBaseForm;
    PlaySoundPool playSoundPool;
    boolean isCorrect;
    int quizCount = 0; // 문제 번호
    //int numberOfCorrect = 0; // 정답개수
    //int totalTestNo = 20;

    ToggleButton selectedBaseToggle;
    ToggleButton selectedConjugationToggle;

    String stringBaseForm = "baseForm";
    String stringConjugation = "conjugation";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_review_conjugation);

        countText = findViewById(R.id.countText);
        tvEnglish = findViewById(R.id.tvEnglish);
        tvAnswer = findViewById(R.id.tvAnswer);
        flexBaseForm = findViewById(R.id.flexBaseForm);
        flexConjugation = findViewById(R.id.flexConjugation);
        btnClose = findViewById(R.id.btnClose);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnClose.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);

        adsManager = AdsManager.getInstance();

        if (adsManager.interstitialAd == null) {
            adsManager.loadFullAds(this);
        }

        playSoundPool = new PlaySoundPool(this);

        testItem = new R_Conjugation_Lesson00();

        baseFormSize = testItem.getBaseForm().length;
        conjugationSize = testItem.getConjugate()[0].length;

        isBaseForm = true;
        makeTest();
    }


    private void answered(int sound, int outline) {
        playSoundPool.playSoundLesson(sound);
        tvAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), outline));
    }


    private int getRandomNum(int size) {
        int randomNum = (int) (Math.random() * size);
        return randomNum;
    }


    // 정답을 제외한 보기 2개를 중복되지 않게 넣기
    private void getAnswerList(int[] answerList, int size, int index) {
        answerList[0] = index;

        int count = 1;

        while (count < answerList.length) {
            int number = getRandomNum(size);

            // 새로 뽑은 랜덤 숫자가 answerList 에 없으면 추가
            if(!ArrayUtils.contains(answerList, number)) {
                answerList[count] = number;
                count++;
            }
        }
        RandomArray.randomArrayInt(answerList);
    }


    private void makeBtns() {
        if(isBaseForm) {
            getAnswerList(answerListBaseForm, baseFormSize, baseFormIndex);
        } else {
            getAnswerList(answerListConjugation, conjugationSize, conjugationIndex);
        }

        for(int i=0; i<answerListBaseForm.length; i++) {
            toggleButton = new ToggleButton(getApplicationContext());
            DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
            int width = dm.widthPixels;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            params.leftMargin = width / 100;
            params.rightMargin = width / 100;
            params.bottomMargin = width * 2 / 100;

            toggleButton.setLayoutParams(params);
            toggleButton.setTextColor(Color.GRAY);
            toggleButton.setOnClickListener(this);

            if(isBaseForm) {
                toggleButton.setText(testItem.getBaseForm()[answerListBaseForm[i]]);
                toggleButton.setTextOn(testItem.getBaseForm()[answerListBaseForm[i]]);
                toggleButton.setTextOff(testItem.getBaseForm()[answerListBaseForm[i]]);
                toggleButton.setTag(stringBaseForm);
                toggleButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.toggle_pink_transparent));
                flexBaseForm.addView(toggleButton);

            } else {
                toggleButton.setText(testItem.getConjugate()[selectedBaseFormIndex][answerListConjugation[i]]);
                toggleButton.setTextOn(testItem.getConjugate()[selectedBaseFormIndex][answerListConjugation[i]]);
                toggleButton.setTextOff(testItem.getConjugate()[selectedBaseFormIndex][answerListConjugation[i]]);
                toggleButton.setTag(stringConjugation);
                toggleButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.toggle_purple_transparent));
                flexConjugation.addView(toggleButton);
            }
        }
    }


    private void makeTest() {
        btnConfirm.setEnabled(false);
        flexBaseForm.removeAllViews();
        flexConjugation.removeAllViews();
        baseFormIndex = getRandomNum(baseFormSize - 1);
        conjugationIndex = getRandomNum(conjugationSize - 1);
        tvEnglish.setText(testItem.getTranslate()[baseFormIndex][conjugationIndex]);
        makeBtns();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnClose :

                if(quizCount > 5) {
                    if(adsManager.interstitialAd.isLoaded()) {
                        adsManager.playFullAds(this);
                    }
                }

                finish();

                break;

            case R.id.btnConfirm :
                btnConfirm.setEnabled(false);
                if(baseFormIndex == selectedBaseFormIndex && conjugationIndex == selectedConjugationIndex) {
                    isCorrect = true;
                    answered(0, R.drawable.bg_lavendar_10_stroke_lavendar);

                } else {
                    isCorrect = false;
                    answered(1, R.drawable.bg_pink_10_stroke_red);
                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
/*
                        if(isCorrect) {
                            numberOfCorrect++;
                        }
                        progressBar.setProgress(quizCount*100/totalTestNo);
 */
                        quizCount++;
                        countText.setText(quizCount + " conjugations");
                        isBaseForm = true;
                        tvAnswer.setText("");
                        tvAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_white_10));
                        makeTest();
                    }
                }, 2000);
                break;


            default:
                ToggleButton selectedBtn = (ToggleButton) v;
                if(selectedBtn.isChecked()) {
                    selectedBtn.setTextColor(Color.WHITE);
                    String selectedBtnText = selectedBtn.getText().toString();
                    tvAnswer.setText(selectedBtnText);

                    if (selectedBtn.getTag().equals(stringBaseForm)) {
                        selectedBaseFormIndex = Arrays.asList(testItem.getBaseForm()).indexOf(selectedBtnText);
                        if (selectedBaseToggle != null && !selectedBtn.equals(selectedBaseToggle)) {
                            selectedBaseToggle.setChecked(false);
                            selectedBaseToggle.setTextColor(Color.GRAY);
                        }
                        selectedBaseToggle = selectedBtn;
                        isBaseForm = false;
                        flexConjugation.removeAllViews();
                        makeBtns();

                    } else {
                        btnConfirm.setEnabled(true);
                        selectedConjugationIndex = Arrays.asList(testItem.getConjugate()[selectedBaseFormIndex]).indexOf(selectedBtnText);
                        if (selectedConjugationToggle != null && !selectedBtn.equals(selectedConjugationToggle)) {
                            selectedConjugationToggle.setChecked(false);
                            selectedConjugationToggle.setTextColor(Color.GRAY);
                        }
                        selectedConjugationToggle = selectedBtn;
                    }

                } else {
                    btnConfirm.setEnabled(false);
                    selectedBtn.setChecked(false);
                    selectedBtn.setTextColor(Color.GRAY);
                    if(selectedBaseToggle != null) {
                        selectedBaseToggle.setChecked(false);
                        selectedBaseToggle.setTextColor(Color.GRAY);
                    }
                    flexConjugation.removeAllViews();
                    tvAnswer.setText("");
                }
                break;
        }
    }
}
