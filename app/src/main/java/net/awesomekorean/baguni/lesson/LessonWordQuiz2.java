package net.awesomekorean.baguni.lesson;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import net.awesomekorean.baguni.DpToPx;
import net.awesomekorean.baguni.R;

import java.util.ArrayList;
import java.util.List;

public class LessonWordQuiz2 extends Fragment implements Button.OnClickListener {

    View view;

    LinearLayout layout1;
    LinearLayout layout2;

    ToggleButton selectedBtn;
    ToggleButton selectedBtnFirst;

    String[] wordInKorean = LessonWord.wordInKorean;
    String[] wordInEnglish = LessonWord.wordInEnglish;

    String[] quizArray; // 한글과 영어 array 를 합친 것

    int[] checkAnswer = new int[2];

    int wordNo = wordInKorean.length; // 단어 개수

    public static LessonWordQuiz2 newInstance() {
        return new LessonWordQuiz2();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_word_quiz2, container, false);

        quizArray = new String[wordNo*2];

        layout1 = view.findViewById(R.id.layout1);
        layout2 = view.findViewById(R.id.layout2);


        makeQuiz(); // 퀴즈 버튼 만들기

        return view;
    }


    public void makeQuiz() {

        for(int i=0; i<wordNo; i++) {

            quizArray[i] = wordInKorean[i];
            quizArray[i+wordNo] = wordInEnglish[i];
        }

        LessonWordQuiz3.randomArray(quizArray);


        for(int i=0; i<wordNo*2; i++) {

            ToggleButton button = new ToggleButton(getContext());

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(DpToPx.getDpToPx(getResources(), 150), DpToPx.getDpToPx(getResources(), 100));

            button.setLayoutParams(params);
            button.setOnClickListener(this);
            button.setTextOff(quizArray[i]);
            button.setTextOn(quizArray[i]);
            button.setTag("button"+i);
            button.setChecked(false);

            if(i < wordNo) {
                layout1.addView(button);
            } else {
                layout2.addView(button);
            }
        }

    }


    @Override
    public void onClick(View view) {

        selectedBtn = (ToggleButton) view;

        String selectedText = selectedBtn.getText().toString();

        System.out.println("SELECTEDTEXT : " + selectedText);

        for(int i=0; i<wordNo; i++) {

            if(wordInKorean[i].equals(selectedText)) {

                checkAnswer[0] = i+1;


            } else if(wordInEnglish[i].equals(selectedText)) {

                checkAnswer[1] = i+1;

            }
        }

        if(checkAnswer[0] !=0 && checkAnswer[1] !=0) {

            if(checkAnswer[0] == checkAnswer[1]) {

                // 딩동 소리 출력

                selectedBtnFirst.setVisibility(View.INVISIBLE);
                selectedBtn.setVisibility(View.INVISIBLE);

                isCorrectAll();

            } else {

                // 땡 소리 출력
            }

            checkAnswer[0] = 0;
            checkAnswer[1] = 0;

            selectedBtnFirst.setChecked(false);
            selectedBtn.setChecked(false);
            selectedBtnFirst = null;

        } else {

            if(selectedBtnFirst != null) {  // 같은 언어의 버튼을 눌렀을 때

                selectedBtnFirst.setChecked(false);
            }

            if(selectedBtn == selectedBtnFirst) { // 같은 버튼을 연속으로 눌렀을 때

                checkAnswer[0] = 0;
                checkAnswer[1] = 0;
            }
            selectedBtnFirst = selectedBtn;
        }

    }

    public void isCorrectAll() {

        int count = 0;

        for(int i=0; i<wordNo; i++) {

            if(layout1.findViewWithTag("button"+i).getVisibility()==View.INVISIBLE) {
                count++;
            }
            if(layout2.findViewWithTag("button"+(i+wordNo)).getVisibility()==View.INVISIBLE) {
                count++;
            }
        }

        if(count == wordNo*2) {
            LessonFrame.progressCount++;
            LessonFrame.setProgressNow(LessonFrame.progressCount*100/LessonWord.totalPageNo);
            ((LessonFrame)getActivity()).replaceFragment(LessonWordQuiz3.newInstance());
        }
    }
}
