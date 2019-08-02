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

import net.awesomekorean.baguni.R;

import java.util.ArrayList;
import java.util.List;

public class LessonWordQuiz2 extends Fragment implements Button.OnClickListener {

    View view;

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn10;

    Button selectedBtn;

    String[] wordInKorean = LessonWord.wordInKorean;
    String[] wordInEnglish = LessonWord.wordInEnglish;

    String[] quizNow = new String[10]; // 현재 퀴즈 array

    int wordNo = wordInKorean.length;
    int tempKorean;
    int tempEnglish;


    public static LessonWordQuiz2 newInstance() {
        return new LessonWordQuiz2();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_word_quiz2, container, false);

        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);
        btn5 = view.findViewById(R.id.btn5);
        btn6 = view.findViewById(R.id.btn6);
        btn7 = view.findViewById(R.id.btn7);
        btn8 = view.findViewById(R.id.btn8);
        btn9 = view.findViewById(R.id.btn9);
        btn10 = view.findViewById(R.id.btn10);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);


        //makeQuiz();

        return view;
    }


    public void makeQuiz() {

        for(int i=0; i<10; i++) {

            quizNow[i] = wordInKorean[i];
            quizNow[i+5] = wordInEnglish[i];
        }

        LessonWordQuiz3.randomArray(quizNow);

        btn1.setText(quizNow[0]);
        btn2.setText(quizNow[1]);
        btn3.setText(quizNow[2]);
        btn4.setText(quizNow[3]);
        btn5.setText(quizNow[4]);
        btn6.setText(quizNow[5]);
        btn7.setText(quizNow[6]);
        btn8.setText(quizNow[7]);
        btn9.setText(quizNow[8]);
        btn10.setText(quizNow[9]);

    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            default :

                selectedBtn = (Button) view;

                selectedBtn.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        selectedBtn.setPressed(true);
                        return true;
                    }
                });

                String selectedText = selectedBtn.getText().toString();

                for(int i=0; i<wordNo; i++) {

                    if(wordInKorean[i].equals(selectedText)) {

                        tempKorean = i;

                    } else if(wordInEnglish[i].equals(selectedText)) {

                        tempEnglish = i;
                    }
                }

                if(tempKorean == tempEnglish ) {


                }


        }

    }

}
