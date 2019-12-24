package net.awesomekorean.podo.lesson;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.flexbox.FlexboxLayout;

import net.awesomekorean.podo.lesson.lessonHangul.DpToPx;
import net.awesomekorean.podo.R;

public class LessonWordQuiz2 extends Fragment implements Button.OnClickListener {

    View view;

    FlexboxLayout flex1;
    FlexboxLayout flex2;

    ToggleButton firstSelectedBtn;
    ToggleButton justSelectedBtn;

    String[] wordFront = LessonWord.wordFront;
    int[] wordBack = LessonWord.wordBack;
    String[] wordAudio = LessonWord.wordAudio;

    MediaPlayer mp;

    PlayAudioWithString playAudioWithString = new PlayAudioWithString();

    int[] checkAnswer = new int[2];

    int wordNo = wordFront.length; // 단어 개수

    int correctCount = 0;

    public static LessonWordQuiz2 newInstance() {
        return new LessonWordQuiz2();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_word_quiz2, container, false);

        flex1 = view.findViewById(R.id.flex1);
        flex2 = view.findViewById(R.id.flex2);


        makeQuiz(); // 퀴즈 버튼 만들기

        return view;
    }


    public void makeQuiz() {

        String[] mixedFront = new String[wordNo];
        String[] mixedBack = new String[wordNo];

        for(int i=0; i<wordNo; i++) {
            mixedFront[i] = wordFront[i];
            mixedBack[i] = getString(wordBack[i]);
        }

        RandomArray.randomArray(mixedFront);
        RandomArray.randomArray(mixedBack);

        int j = 1;

        for(int i=0; i<wordNo*2; i++) {

            ToggleButton button = new ToggleButton(getContext());

            int gap20 = DpToPx.getDpToPx(getResources(), 20);
            int gap10 = DpToPx.getDpToPx(getResources(), 10);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.rightMargin = gap10;
            params.bottomMargin = gap10;

            button.setLayoutParams(params);
            button.setOnClickListener(this);
            button.setTextColor(Color.BLACK);
            button.setPadding(gap10, gap20, gap10, gap20);
            button.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.toggle_white_purple));
            button.setTag("button"+i);
            button.setChecked(false);

            if(i < wordNo) {
                button.setText(mixedFront[i]);
                button.setTextOn(mixedFront[i]);
                button.setTextOff(mixedFront[i]);
                flex1.addView(button);
            } else {
                button.setText(mixedBack[i-j]);
                button.setTextOn(mixedBack[i-j]);
                button.setTextOff(mixedBack[i-j]);
                flex2.addView(button);
                j = j + 2;
            }
        }
    }


    @Override
    public void onClick(View view) {

        justSelectedBtn = (ToggleButton) view;
        justSelectedBtn.setTextColor(Color.WHITE);

        String selectedText = justSelectedBtn.getText().toString();


        for(int i=0; i<wordNo; i++) {

            if(wordFront[i].equals(selectedText)) {

                checkAnswer[0] = i+1;

            } else if(getString(wordBack[i]).equals(selectedText)) {

                checkAnswer[1] = i+1;
            }
        }

        // Front, Back 을 입력 했을 때
        if(checkAnswer[0] !=0 && checkAnswer[1] !=0) {

            if(checkAnswer[0] == checkAnswer[1]) {  // 정답

                mp = MediaPlayer.create(getContext(), R.raw.correct);
                mp.start();

                Drawable transparent = ContextCompat.getDrawable(getContext(), R.drawable.bg_transparent);

                firstSelectedBtn.setBackgroundDrawable(transparent);
                justSelectedBtn.setBackgroundDrawable(transparent);

                firstSelectedBtn.setPaintFlags(firstSelectedBtn.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                justSelectedBtn.setPaintFlags(justSelectedBtn.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                firstSelectedBtn.setTextColor(Color.BLACK);
                justSelectedBtn.setTextColor(Color.BLACK);

                playAudioWithString.playAudio(getContext(), wordAudio[checkAnswer[0]-1]);


                isCorrectAll();

            } else {  // 오답

                mp = MediaPlayer.create(getContext(), R.raw.wrong);
                mp.start();

                firstSelectedBtn.setTextColor(Color.BLACK);
                justSelectedBtn.setTextColor(Color.BLACK);

                firstSelectedBtn.setChecked(false);
                justSelectedBtn.setChecked(false);
            }

            checkAnswer[0] = 0;
            checkAnswer[1] = 0;

            firstSelectedBtn = null;


        } else {

            if(firstSelectedBtn != null) {  // 같은 언어의 버튼을 눌렀을 때

                firstSelectedBtn.setChecked(false);
                firstSelectedBtn.setTextColor(Color.BLACK);
            }

            if(justSelectedBtn == firstSelectedBtn) { // 같은 버튼을 연속으로 눌렀을 때

                checkAnswer[0] = 0;
                checkAnswer[1] = 0;

                firstSelectedBtn = null;

            } else {
                firstSelectedBtn = justSelectedBtn; // 첫 번째 클릭일 때
            }
        }
    }

    public void isCorrectAll() {

        correctCount++;

        if(correctCount == wordNo) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LessonFrame.progressCount++;
                    LessonFrame.progressCount();
                    ((LessonFrame)getActivity()).replaceFragment(LessonWordQuiz3.newInstance());
                }
            }, 1000);

        }
    }
}
