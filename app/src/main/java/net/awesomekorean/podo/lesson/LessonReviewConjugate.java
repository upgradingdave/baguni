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
import net.awesomekorean.podo.lesson.lessonReview.LessonReview;
import net.awesomekorean.podo.lesson.lessonReview.R_Conjugation_Lesson00;

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
    int[] answerListBaseForm = new int[3];
    int[] answerListConjugation = new int[3];
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

        view = inflater.inflate(R.layout.activity_lesson_review_conjugation, container, false);

        tvEnglish = view.findViewById(R.id.tvEnglish);
        tvAnswer = view.findViewById(R.id.tvAnswer);
        flexBaseForm = view.findViewById(R.id.flexBaseForm);
        flexConjugation = view.findViewById(R.id.flexConjugation);
        btnConfirm = view.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(this);

        playSoundPool = new PlaySoundPool(getContext());

        lessonReview = activity.lessonReview;
        baseFormSize = activity.lessonReview.getBaseForm().length;
        conjugationSize = activity.lessonReview.getConjugation()[0].length;

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
                if(activity.progressCount < 20) {
                    makeQuiz();
                } else {
                    activity.replaceFragment(LessonReviewWord.newInstance());
                }
            }
        }, 2000);
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
                toggleButton.setText(lessonReview.getBaseForm()[answerListBaseForm[i]]);
                toggleButton.setTextOn(lessonReview.getBaseForm()[answerListBaseForm[i]]);
                toggleButton.setTextOff(lessonReview.getBaseForm()[answerListBaseForm[i]]);
                toggleButton.setTag(stringBaseForm);
                toggleButton.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.toggle_pink_transparent));
                flexBaseForm.addView(toggleButton);

            } else {
                toggleButton.setText(lessonReview.getConjugation()[selectedBaseFormIndex][answerListConjugation[i]]);
                toggleButton.setTextOn(lessonReview.getConjugation()[selectedBaseFormIndex][answerListConjugation[i]]);
                toggleButton.setTextOff(lessonReview.getConjugation()[selectedBaseFormIndex][answerListConjugation[i]]);
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
        baseFormIndex = getRandomNum(baseFormSize - 1);
        conjugationIndex = getRandomNum(conjugationSize - 1);
        tvEnglish.setText(lessonReview.getTranslate()[baseFormIndex][conjugationIndex]);
        makeBtns();
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
                    tvAnswer.setText(selectedBtnText);

                    if (selectedBtn.getTag().equals(stringBaseForm)) {
                        selectedBaseFormIndex = Arrays.asList(lessonReview.getBaseForm()).indexOf(selectedBtnText);
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
                        selectedConjugationIndex = Arrays.asList(lessonReview.getConjugation()[selectedBaseFormIndex]).indexOf(selectedBtnText);
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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof LessonReviewFrame) {
            activity = (LessonReviewFrame) context;
        }
    }
}
