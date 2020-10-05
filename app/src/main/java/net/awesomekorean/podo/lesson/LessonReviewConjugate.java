package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.gms.common.util.ArrayUtils;

import net.awesomekorean.podo.PlaySoundPool;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessonReviewRewards.LessonReview;

import java.util.Arrays;

public class LessonReviewConjugate extends Fragment implements View.OnClickListener {

    View view;

    public static LessonReviewConjugate newInstance() {
        return new LessonReviewConjugate();
    }

    TextView tvEnglish;
    TextView tvAnswer;
    FlexboxLayout flexBaseForm;
    FlexboxLayout flexConjugation;
    Button btnConfirm;

    int baseFormSize;
    int conjugationSize;
    int baseFormIndex;
    int conjugationIndex;
    int selectedBaseFormIndex;
    int selectedConjugationIndex;
    int[] answerList = new int[3];
    ToggleButton toggleButton;
    boolean isBaseForm;
    PlaySoundPool playSoundPool;
    boolean isCorrect;
    ToggleButton selectedBaseToggle;
    ToggleButton selectedConjugationToggle;

    String stringBaseForm = "baseForm";
    String stringConjugation = "conjugation";

    LessonReviewFrame activity;
    LessonReview lessonReview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_review_conjugation, container, false);

        tvEnglish = view.findViewById(R.id.tvEnglish);
        tvAnswer = view.findViewById(R.id.tvAnswer);
        flexBaseForm = view.findViewById(R.id.flexBaseForm);
        flexConjugation = view.findViewById(R.id.flexConjugation);
        btnConfirm = view.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(this);

        playSoundPool = new PlaySoundPool(getContext());

        setBtnConfirm(false);

        lessonReview = activity.lessonReview;
        baseFormSize = activity.lessonReview.getBaseForm().length;

        isBaseForm = true;
        makeQuiz();

        return view;
    }


    private void answered(int sound, int outline, final boolean isCorrect) {
        playSoundPool.playSoundLesson(sound);
        tvAnswer.setBackground(ContextCompat.getDrawable(getContext(), outline));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isBaseForm = true;
                tvAnswer.setText("");
                tvAnswer.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_white_10));
                activity.progressCount(isCorrect);
                if(activity.progressCount < 15) {
                    makeQuiz();
                } else {
                    activity.replaceFragment(LessonReviewSentence.newInstance());
                }
            }
        }, 2000);
    }


    private int getRandomNum(int size) {
        int randomNum = (int) (Math.random() * size);
        return randomNum;
    }


    // 정답을 제외한 보기 2개를 중복되지 않게 넣기
    private void getAnswerList(int size) {
        // answerList 초기화
        for(int i=0; i<3; i++) {
            answerList[i] = size;
        }

        int count = 0;

        if(isBaseForm) {
            answerList[0] = baseFormIndex;
            count = 1;

        // 선택한 기본형이 정답일 때
        } else if(selectedBaseFormIndex == baseFormIndex) {
            answerList[0] = conjugationIndex;
            count = 1;
        }

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


    private void setBtns() {
        if(isBaseForm) {
            getAnswerList(baseFormSize);
        } else {
            if(conjugationSize < 3) {
                answerList[0] = conjugationIndex;
            } else {
                getAnswerList(lessonReview.getConjugation()[selectedBaseFormIndex].length);
            }
        }

        if(!isBaseForm && conjugationSize < 3) {
            makeBtns(1);
        } else {
            makeBtns(3);
        }
    }


    private void makeBtns(int leng) {
        for(int i=0; i<leng; i++) {
            toggleButton = new ToggleButton(getContext());
            DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
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
                toggleButton.setText(lessonReview.getBaseForm()[answerList[i]]);
                toggleButton.setTextOn(lessonReview.getBaseForm()[answerList[i]]);
                toggleButton.setTextOff(lessonReview.getBaseForm()[answerList[i]]);
                toggleButton.setTag(stringBaseForm);
                toggleButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.toggle_pink_transparent));
                flexBaseForm.addView(toggleButton);

            } else {
                toggleButton.setText(lessonReview.getConjugation()[selectedBaseFormIndex][answerList[i]]);
                toggleButton.setTextOn(lessonReview.getConjugation()[selectedBaseFormIndex][answerList[i]]);
                toggleButton.setTextOff(lessonReview.getConjugation()[selectedBaseFormIndex][answerList[i]]);
                toggleButton.setTag(stringConjugation);
                toggleButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.toggle_purple_transparent));
                flexConjugation.addView(toggleButton);
            }
        }
    }


    private void makeQuiz() {
        btnConfirm.setEnabled(false);
        flexBaseForm.removeAllViews();
        flexConjugation.removeAllViews();

        // 같은 문제 출제 방지
        int randomNum = getRandomNum(baseFormSize - 1);
        while(randomNum == baseFormIndex) {
            randomNum = getRandomNum(baseFormSize - 1);
        }

        baseFormIndex = randomNum;
        conjugationIndex = getRandomNum(lessonReview.getConjugation()[baseFormIndex].length - 1);
        tvEnglish.setText(lessonReview.getTranslate()[baseFormIndex][conjugationIndex]);
        setBtns();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnConfirm :
                btnConfirm.setEnabled(false);
                if(baseFormIndex == selectedBaseFormIndex && conjugationIndex == selectedConjugationIndex) {
                    answered(0, R.drawable.bg_lavendar_10_stroke_lavendar, true);

                } else {
                    answered(1, R.drawable.bg_pink_10_stroke_red, false);
                }
                break;


            default:
                ToggleButton selectedBtn = (ToggleButton) v;
                if(selectedBtn.isChecked()) {
                    selectedBtn.setTextColor(Color.WHITE);
                    String selectedBtnText = selectedBtn.getText().toString();

                    // 기본형 버튼 클릭
                    if (selectedBtn.getTag().equals(stringBaseForm)) {
                        setBtnConfirm(false);
                        selectedBaseFormIndex = Arrays.asList(lessonReview.getBaseForm()).indexOf(selectedBtnText);
                        if (selectedBaseToggle != null && !selectedBtn.equals(selectedBaseToggle)) {
                            selectedBaseToggle.setChecked(false);
                            selectedBaseToggle.setTextColor(Color.GRAY);
                        }
                        selectedBaseToggle = selectedBtn;
                        isBaseForm = false;
                        flexConjugation.removeAllViews();
                        conjugationSize = lessonReview.getConjugation()[selectedBaseFormIndex].length;
                        setBtns();

                    // 활용 버튼 클릭
                    } else {
                        setBtnConfirm(true);
                        tvAnswer.setText(selectedBtnText);
                        btnConfirm.setEnabled(true);
                        selectedConjugationIndex = Arrays.asList(lessonReview.getConjugation()[selectedBaseFormIndex]).indexOf(selectedBtnText);
                        if (selectedConjugationToggle != null && !selectedBtn.equals(selectedConjugationToggle)) {
                            selectedConjugationToggle.setChecked(false);
                            selectedConjugationToggle.setTextColor(Color.GRAY);
                        }
                        selectedConjugationToggle = selectedBtn;
                    }

                // 같은 버튼 한번 더 클릭
                } else {
                    setBtnConfirm(false);
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


    public void setBtnConfirm(boolean b) {
        if(b) {
            btnConfirm.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_purple_25));
        } else {
            btnConfirm.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_purple_25_transparent));
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof LessonReviewFrame) {
            activity = (LessonReviewFrame) context;
        }
    }
}
