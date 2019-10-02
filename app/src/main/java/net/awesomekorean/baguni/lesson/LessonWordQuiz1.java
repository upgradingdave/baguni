package net.awesomekorean.baguni.lesson;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import net.awesomekorean.baguni.R;

import java.util.ArrayList;
import java.util.List;

public class LessonWordQuiz1 extends Fragment implements Button.OnClickListener {

    View view;

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btnAudio;

    ImageView ox; // 정답, 오답 시 각각의 이미지 출력

    String[] wordBack = LessonWord.wordBack;
    String[] quizNow = new String[4]; // 현재 퀴즈 array


    int quizQuantity; // 문제 개수
    int quizNo = 0;
    int regularQuizNo = 0; // 정규퀴즈 문제 번호
    List<Integer> wrongQuizList; // 틀린 문제 번호를 이 list 에 추가

    public static LessonWordQuiz1 newInstance() {
        return new LessonWordQuiz1();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_word_quiz1, container, false);

        quizQuantity = wordBack.length;
        wrongQuizList = new ArrayList<>();

        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        ox = view.findViewById(R.id.ox);

        makeQuiz(quizNo);


        return view;
    }

    public void makeQuiz(int quizNo) {

        int j = 0;

        for(int i=0; i<4; i++) {

            if(quizNo + i >= wordBack.length) {
                quizNow[i] = wordBack[j];
                j++;
            } else {
                quizNow[i] = wordBack[quizNo + i];
            }
        }

        LessonWordQuiz3.randomArray(quizNow);

        btn1.setText(quizNow[0]);
        btn2.setText(quizNow[1]);
        btn3.setText(quizNow[2]);
        btn4.setText(quizNow[3]);
    }


    public void makeNextQuiz() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                LessonFrame.progressCount++;
                LessonFrame.progressCount();

                ox.setVisibility(View.GONE);

                if(regularQuizNo == quizQuantity-1) {
                    // 오답이 있을 경우, 해당 문제 다시 출력
                    if(wrongQuizList.size() > 0) {
                        makeWrongQuiz(wrongQuizList.get(0));
                    } else {
                        // Sentence Lesson
                        ((LessonFrame)getActivity()).replaceFragment(LessonWordQuiz2.newInstance());
                    }
                } else {

                    quizNo ++;
                    regularQuizNo ++;
                    makeQuiz(quizNo);
                }
            }
        }, 1000);
    }


    public void makeWrongQuiz(int wrongQuizNo) {

        wrongQuizList.remove(0);
        quizNo = wrongQuizNo;
        makeQuiz(quizNo);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case(R.id.btnAudio) :

                break;

            default :

                Button selectedBtn = (Button) view;

                if(wordBack[quizNo].equals(selectedBtn.getText().toString())) {

                    ox.setImageResource(R.drawable.samplecorrect);
                    ox.setVisibility(View.VISIBLE);

                    // 정답소리 출력할 것

                    makeNextQuiz();


                } else {

                    ox.setImageResource(R.drawable.samplewrong);
                    ox.setVisibility(View.VISIBLE);
                    // 오답소리 출력할 것

                    wrongQuizList.add(quizNo);

                    makeNextQuiz();

                }

        }

    }

}
