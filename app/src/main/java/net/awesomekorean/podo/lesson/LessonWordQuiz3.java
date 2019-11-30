package net.awesomekorean.podo.lesson;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.flexbox.FlexboxLayout;

import net.awesomekorean.podo.DpToPx;
import net.awesomekorean.podo.R;
import java.util.Random;

public class LessonWordQuiz3 extends Fragment implements Button.OnClickListener{

    View view;
    static String[] words; // 단어 front array

    String word; // 퀴즈용 단어 묶음에 있는 각 단어

    String[] syllables; // wordInIndex의 단어를 각 음절로 나눔

    LinearLayout answerLayout;

    TextView tvAnswer; // 입력한 정답이 표시되는 텍스트뷰

    FlexboxLayout flexboxLayout; // 정답을 입력하는 버튼이 들어가는 layout

    Button btnSelector; // 정답을 입력하기 위해 만들어진 한글 버튼

    ImageView btnAudio;
    ImageView btnReset;

    int quizCount = 0; // 단어퀴즈 순서

    MediaPlayer mediaPlayer;

    View.OnClickListener selectorButtonClick; // 정답 입력 버튼 클릭 시 작동하는 함수


    // fragment 간 전환을 위해 만듦
    public static LessonWordQuiz3 newInstance() {
        return new LessonWordQuiz3();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_word_quiz3, container, false);

        answerLayout = view.findViewById(R.id.answerLayout);
        tvAnswer = view.findViewById(R.id.tvAnswer);
        flexboxLayout = view.findViewById(R.id.flexboxLayout);
        btnAudio = view.findViewById(R.id.btnAudio);
        btnReset = view.findViewById(R.id.btnReset);
        btnAudio.setOnClickListener(this);
        btnReset.setOnClickListener(this);


        selectorButtonClick = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Button selectedBtn = (Button) view;

                String selectedBtnText = selectedBtn.getText().toString();
                tvAnswer.append(selectedBtnText);

                btnReset.setVisibility(View.VISIBLE);

                if(tvAnswer.getText().length() == word.length()) {

                    if(tvAnswer.getText().toString().equals(word)) { // 정답

                        answerLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_white_10_stroke_purple));
                        mediaPlayer = MediaPlayer.create(getContext(), R.raw.correct);
                        mediaPlayer.start();


                    } else {  // 오답
                        answerLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_white_10_stroke_red));
                        mediaPlayer = MediaPlayer.create(getContext(), R.raw.wrong);
                        mediaPlayer.start();
                    }

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if(tvAnswer.getText().toString().equals(word)) {
                                quizCount++;

                                LessonFrame.progressCount++;
                                LessonFrame.progressCount();

                                if (quizCount != words.length) {  // 문제가 남아있을 때
                                    flexboxLayout.removeAllViews();
                                    makeQuiz();

                                } else {  // 문제 다 풀었을 때
                                    quizCount = 0;
                                    ((LessonFrame) getActivity()).replaceFragment(LessonSentence.newInstance());
                                }
                            }
                            tvAnswer.setText("");
                            btnReset.setVisibility(View.GONE);
                            answerLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_white_10));
                        }
                    }, 2000);
                }
            }
        };

        words = LessonWord.wordFront;

        makeQuiz();

        return view;
    }


    // word의 단어를 음절로 나누고 램덤으로 섞어서 버튼으로 만들어 줌
    public void makeQuiz() {

        word = words[quizCount];

        syllables = new String[word.length()];

        for(int i=0; i<word.length(); i++) {
            syllables[i] = word.substring(i, i+1);
        }

        RandomArray.randomArray(syllables);

        for(int i=0; i<syllables.length; i++) {

            btnSelector = new Button(getContext());

            int gap10 = DpToPx.getDpToPx(getResources(), 10);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.rightMargin = gap10;
            params.bottomMargin = gap10;

            btnSelector.setLayoutParams(params);
            btnSelector.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_white_10));
            btnSelector.setText(syllables[i]);
            btnSelector.setOnClickListener(selectorButtonClick);
            flexboxLayout.addView(btnSelector);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnReset :
                tvAnswer.setText("");
                btnReset.setVisibility(View.GONE);
                break;

            case R.id.btnAudio :
                break;
        }
    }


}