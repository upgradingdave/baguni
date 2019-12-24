package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import net.awesomekorean.podo.R;

import java.util.ArrayList;
import java.util.List;

public class LessonWordQuiz1 extends Fragment implements Button.OnClickListener {

    View view;

    TextView answer;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    ImageView btnAudio;


    String[] wordFront = LessonWord.wordFront;
    String[] wordBack = LessonWord.wordBack;
    String[] wordAudio = LessonWord.wordAudio;
    String[] answerArray = new String[4]; // 현재 퀴즈 array


    int quizQuantity; // 문제 개수
    int quizNoNow = 0;
    Boolean solveWrongQuizAgain = false;
    List<Integer> wrongQuizList; // 틀린 문제 번호를 이 list 에 추가

    MediaPlayer mp;
    PlayAudioWithString playAudioWithString = new PlayAudioWithString();


    public static LessonWordQuiz1 newInstance() {
        return new LessonWordQuiz1();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_word_quiz1, container, false);

        quizQuantity = wordBack.length;
        wrongQuizList = new ArrayList<>();

        answer = view.findViewById(R.id.answer);
        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);
        btnAudio = view.findViewById(R.id.btnAudio);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btnAudio.setOnClickListener(this);

        makeQuiz(quizNoNow);


        return view;
    }

    public void makeQuiz(int quizNoNow) {


        answer.setText(wordFront[quizNoNow]);
        int j = 0;

        for(int i=0; i<4; i++) {

            // 단어 array 이에서 현재 단어포함해서 순서대로 4개의 단어를 보기로 제시함.
            if(quizNoNow + i >= wordBack.length) {
                answerArray[i] = wordBack[j];
                j++;
            } else {
                answerArray[i] = wordBack[quizNoNow + i];
            }
        }

        RandomArray.randomArray(answerArray);

        btn1.setText(answerArray[0]);
        btn2.setText(answerArray[1]);
        btn3.setText(answerArray[2]);
        btn4.setText(answerArray[3]);

        playAudioWithString.playAudio(getContext(), wordAudio[quizNoNow]);
    }



    public void makeNextQuiz(final Button selectedBtn) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                selectedBtn.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_white_10));
                answer.setVisibility(View.GONE);
                btnAudio.setVisibility(View.VISIBLE);

                LessonFrame.progressCount++;
                LessonFrame.progressCount();

                if(solveWrongQuizAgain) {
                    // 오답이 있을 경우, 해당 문제 다시 출력
                    if(wrongQuizList.size() > 0) {
                        makeWrongQuiz(wrongQuizList.get(0));
                    } else {
                        // Sentence Lesson
                        ((LessonFrame)getActivity()).replaceFragment(LessonWordQuiz2.newInstance());
                    }
                } else {

                    quizNoNow ++;
                    if(quizNoNow == quizQuantity-1) {
                        solveWrongQuizAgain = true;
                    }
                    makeQuiz(quizNoNow);
                }
            }
        }, 2000);
    }


    public void makeWrongQuiz(int wrongQuizNo) {

        wrongQuizList.remove(0);
        quizNoNow = wrongQuizNo;
        makeQuiz(quizNoNow);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case(R.id.btnAudio) :
                playAudioWithString.playAudio(getContext(), wordAudio[quizNoNow]);
                break;

            default :

                Button selectedBtn = (Button) view;

                if(wordBack[quizNoNow].equals(selectedBtn.getText().toString())) {

                    // 정답소리 출력하고 선택박스에 파란색 테두리
                    answered(selectedBtn, R.raw.correct, R.drawable.bg_white_10_stroke_purple);


                } else {

                    // 오답소리 출력하고 선택박스에 빨간색 테두리
                    wrongQuizList.add(quizNoNow);
                    answered(selectedBtn, R.raw.wrong, R.drawable.bg_white_10_stroke_red);
                }
        }
    }

    private void answered(Button selectedBtn, int sound, int outline) {

        mp = MediaPlayer.create(getContext(), sound);
        mp.start();
        selectedBtn.setBackground(ContextCompat.getDrawable(getContext(), outline));
        answer.setVisibility(View.VISIBLE);
        btnAudio.setVisibility(View.GONE);

        makeNextQuiz(selectedBtn);
    }
}
