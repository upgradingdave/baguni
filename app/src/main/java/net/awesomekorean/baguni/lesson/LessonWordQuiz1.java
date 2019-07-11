package net.awesomekorean.baguni.lesson;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.awesomekorean.baguni.R;
import net.awesomekorean.baguni.lessonHangul.HangulUniCode;

import java.util.Random;

public class LessonWordQuiz1 extends Fragment implements Button.OnClickListener {

    View view;

    String[] wordForQuiz;

    public View.OnClickListener selectorButtonClick;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        // wordInKorean의 length-1 만큼 랜덤 숫자 추출. 이 때 숫자 중복 되면 안됨.
        // 해당 index의 단어의 글자수를 파악
        // 파악된 글자수만큼 wordQuizAnswer에 textView생성. 각 textView에 숫자로 id 부여. text에는 _ 표시
        // 해당 단어의 글자를 분해해서 랜덤으로 버튼 만들기
        // 버튼 선택하면 해당 텍스트가 순서대로 wordQuizAnswer의 textView에 입력
        // reset 버튼 추가
        // 글자 완성되면 자동으로 정답/오답 표기 후 다음 문제로 넘어감. 오답 시 정답 알려줌.
        // 정답 시 progress bar 진행. 오답 시 wordQuiz 마지막에 다시 나옴. 맞출 때까지 나옴.
        // 모든 문제가 정답으로 표시되면 lessonSentence로 넘어감.

        view = inflater.inflate(R.layout.lesson_word_quiz1, container, false);

        wordForQuiz = LessonWord.wordInKorean;

        TextView wordQuiz1Answer = view.findViewById(R.id.wordQuiz1Answer);
        LinearLayout wordQuiz1Selector = view.findViewById(R.id.wordQuiz1Selector);


        selectorButtonClick = new View.OnClickListener() {

            @Override
            public void onClick(View view) {



            }
        };


        // 각 레슨 단어 묶음을 wordForQuiz에 랜덤으로 반환하기
        for(int i=0; i<wordForQuiz.length; i++ ) {

            String temp = wordForQuiz[i];
            wordForQuiz[i] = wordForQuiz[randomIndex(wordForQuiz)];
            wordForQuiz[randomIndex(wordForQuiz)] = temp;

        }
        System.out.println("wordForQuiz : " + wordForQuiz);
        System.out.println("wfirstcharacter : " + wordForQuiz[0]);


        String eachCharacter = wordForQuiz[0];

        for(int i=0; i<eachCharacter.length(); i++) {

            wordQuiz1Answer.append("_ ");
            
            String splitCharacter = eachCharacter.substring(i, i+1);

            Button selectorButton = new Button(getContext());

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpToPx(50), dpToPx(50));

            selectorButton.setLayoutParams(params);
            selectorButton.setText(splitCharacter);
            selectorButton.setOnClickListener(selectorButtonClick);

            wordQuiz1Selector.addView(selectorButton);
        }

        return view;
    }


    public int randomIndex(String[] strings) {

        Random random = new Random();

        int rnum = random.nextInt(strings.length);

        return rnum;
    }

    public int dpToPx(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        int pixels = (int) (dp * scale + 0.5f);

        return pixels;
    }


    @Override
    public void onClick(View view) {

    }
}
