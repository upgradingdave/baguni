package net.awesomekorean.podo.lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.google.firebase.storage.FirebaseStorage;

import net.awesomekorean.podo.PlaySoundPool;
import net.awesomekorean.podo.R;

import java.util.Arrays;

public class TestGrammar extends AppCompatActivity {

    FirebaseStorage storage = FirebaseStorage.getInstance();
    TextView tvEnglish;
    TextView tvAnswer;
    FlexboxLayout flexBaseForm;
    FlexboxLayout flexConjugation;
    ImageView btnClose;
    Button btnConfirm;

    TestGrammarItem testItem;
    int baseFormSize;
    int conjugationSize;
    int baseFormIndex;
    int conjugationIndex;
    int selectedBaseFormIndex;
    int selectedConjugationIndex;
    int[] answerList = new int[3]; // 선택지 3개
    ToggleButton btnSelector;
    View.OnClickListener selectorButtonClick; // 정답 입력 버튼 클릭 시 작동하는 함수
    boolean isBaseForm;
    PlaySoundPool playSoundPool;
    boolean isCorrect;
    int testCount = 1; // 문제 번호
    int correctCount = 0; // 정답개수


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_grammar);

        tvEnglish = findViewById(R.id.tvEnglish);
        tvAnswer = findViewById(R.id.tvAnswer);
        flexBaseForm = findViewById(R.id.flexBaseForm);
        flexConjugation = findViewById(R.id.flexConjugation);
        btnClose = findViewById(R.id.btnClose);
        btnConfirm = findViewById(R.id.btnConfirm);

        playSoundPool = new PlaySoundPool(this);

        testItem = new TestGrammarItem();

        baseFormSize = testItem.getBaseForm().length;
        conjugationSize = testItem.getConjugate()[0].length;

        selectorButtonClick = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Button selectedBtn = (Button) view;
                String selectedBtnText = selectedBtn.getText().toString();
                tvAnswer.setText(selectedBtnText);


                if(isBaseForm) {
                    selectedBaseFormIndex = Arrays.asList(testItem.getBaseForm()).indexOf(selectedBtnText);
                    //flexboxLayout.removeAllViews();
                    isBaseForm = false;
                    makeBtns(conjugationSize);

                } else {
                    selectedConjugationIndex = Arrays.asList(testItem.getConjugate()[selectedBaseFormIndex]).indexOf(selectedBtnText);

                    if(baseFormIndex == selectedBaseFormIndex && conjugationIndex == selectedConjugationIndex) {
                        isCorrect = true;
                        answered(0, R.drawable.bg_white_10_stroke_purple);

                    } else {
                        isCorrect = false;
                        answered(1, R.drawable.bg_white_10_stroke_red);
                    }

                    //flexboxLayout.removeAllViews();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if(isCorrect) {
                                correctCount++;
                            }
                            testCount++;
                            //countText.setText(testCount + " / 10");
                            isBaseForm = true;
                            tvAnswer.setText("");
                            makeTest();
                            //answerLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_white_10));
                        }
                    }, 2000);
                }
            }
        };

        isBaseForm = true;
        makeTest();
    }


    private void answered(int sound, int outline) {
        playSoundPool.playSoundLesson(sound);
        //answerLayout.setBackground(ContextCompat.getDrawable(this, outline));
    }


    private int getRandomNum(int size) {
        int randomNum = (int) (Math.random() * size);
        return randomNum;
    }


    private void makeBtns(int size) {
        // 정답을 제외한 보기 3개를 중복되지 않게 넣기
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

        for(int i=0; i<answerList.length; i++) {
            btnSelector = new ToggleButton(this);
            DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
            int width = dm.widthPixels;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            params.leftMargin = width / 100;
            params.rightMargin = width / 100;
            params.bottomMargin = width * 2 / 100;

            btnSelector.setLayoutParams(params);
            if(isBaseForm) {
                btnSelector.setText(testItem.getBaseForm()[i]);
                btnSelector.setTextOn(testItem.getBaseForm()[i]);
                btnSelector.setTextOff(testItem.getBaseForm()[i]);
                btnSelector.setTextColor(Color.GRAY);
                btnSelector.setBackground(ContextCompat.getDrawable(this, R.drawable.toggle_pink_transparent));

            } else {
                btnSelector.setText(testItem.getConjugate()[selectedBaseFormIndex][i]);
            }
            //btnSelector.setOnClickListener(selectorButtonClick);
            flexBaseForm.addView(btnSelector);
        }
    }


    private void makeTest() {
        baseFormIndex = getRandomNum(baseFormSize - 1);
        conjugationIndex = getRandomNum(conjugationSize - 1);

        tvEnglish.setText(testItem.getTranslate()[baseFormIndex][conjugationIndex]);

        answerList[0] = baseFormIndex;
        makeBtns(baseFormSize);
    }
}
