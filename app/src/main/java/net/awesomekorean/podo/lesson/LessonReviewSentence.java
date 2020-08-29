package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.flexbox.FlexboxLayout;

import net.awesomekorean.podo.MediaPlayerManager;
import net.awesomekorean.podo.PlaySoundPool;
import net.awesomekorean.podo.R;

import java.util.ArrayList;

public class LessonReviewSentence extends Fragment implements View.OnClickListener {

    View view;

    public static LessonReviewSentence newInstance() {
        return new LessonReviewSentence();
    }

    String[] syllables; // wordInIndex의 단어를 각 음절로 나눔
    TextView meaning;
    ConstraintLayout answerLayout;
    TextView tvAnswer; // 입력한 정답이 표시되는 텍스트뷰
    FlexboxLayout flexboxLayout; // 정답을 입력하는 버튼이 들어가는 layout
    Button btnSelector; // 정답을 입력하기 위해 만들어진 한글 버튼
    ImageView btnAudio;
    ImageView btnReset;
    PlaySoundPool playSoundPool;
    MediaPlayerManager mediaPlayerManager;
    View.OnClickListener selectorButtonClick; // 정답 입력 버튼 클릭 시 작동하는 함수
    String sentence;
    int quizIndex;
    ArrayList<Button> clickedBtns;

    LessonReviewFrame activity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_review_sentence, container, false);

        mediaPlayerManager = MediaPlayerManager.getInstance();

        meaning = view.findViewById(R.id.meaning);
        answerLayout = view.findViewById(R.id.answerLayout);
        tvAnswer = view.findViewById(R.id.tvAnswer);
        flexboxLayout = view.findViewById(R.id.flexboxLayout);
        btnAudio = view.findViewById(R.id.btnAudio);
        btnReset = view.findViewById(R.id.btnReset);
        btnAudio.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        clickedBtns = new ArrayList<>();
        playSoundPool = new PlaySoundPool(getContext());

        selectorButtonClick = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Button selectedBtn = (Button) view;
                clickedBtns.add(selectedBtn);
                selectedBtn.setVisibility(View.INVISIBLE);
                String selectedBtnText = selectedBtn.getText().toString();

                if(tvAnswer.getText().length() != sentence.length()) {
                    tvAnswer.append(selectedBtnText);
                }

                btnReset.setVisibility(View.VISIBLE);

                if(tvAnswer.getText().length() == sentence.length()) {
                    clickedBtns.clear();

                    if(tvAnswer.getText().toString().equals(sentence)) { // 정답
                        answered(0, R.drawable.bg_white_10_stroke_purple, true);

                    } else {  // 오답
                        answered(1, R.drawable.bg_white_10_stroke_red, false);
                    }
                }
            }
        };

        makeQuiz();

        return view;
    }

    private void answered(int sound, int outline, final boolean isCorrect) {
        playSoundPool.playSoundLesson(sound);
        answerLayout.setBackground(ContextCompat.getDrawable(getContext(), outline));
        flexboxLayout.removeAllViews();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvAnswer.setText("");
                btnReset.setVisibility(View.GONE);
                answerLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_white_10));
                activity.progressCount(isCorrect);
                if(activity.progressCount < 20) {
                    makeQuiz();
                } else {
                    activity.replaceFragment(LessonReviewWord.newInstance());
                }
            }
        }, 2000);

    }


    public int getRandomNum(int size) {
        int randomNum = (int) (Math.random() * size);
        return randomNum;
    }


    // 문장을 음절로 나누고 랜덤으로 섞어서 버튼으로 만들어 줌
    public void makeQuiz() {
        quizIndex = getRandomNum(activity.sentenceFront.size());
        sentence = activity.sentenceFront.get(quizIndex);
        syllables = new String[sentence.length()];

        for(int i=0; i<sentence.length(); i++) {
            syllables[i] = sentence.substring(i, i+1);
        }

        RandomArray.randomArrayString(syllables);

        for(int i=0; i<syllables.length; i++) {
            btnSelector = new Button(getContext());

            DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
            int width = dm.widthPixels;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width *18 /100, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = width / 100;
            params.rightMargin = width / 100;
            params.bottomMargin = width * 2 / 100;

            btnSelector.setLayoutParams(params);
            btnSelector.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.ripple_custom));
            btnSelector.setText(syllables[i]);
            btnSelector.setOnClickListener(selectorButtonClick);
            flexboxLayout.addView(btnSelector);
        }

        meaning.setText(activity.sentenceBack.get(quizIndex));
        mediaPlayerManager.setMediaPlayerByte(false, activity.sentenceAudioByte.get(quizIndex));
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnReset :
                if(clickedBtns.size() > 0) {
                    Button button = clickedBtns.get(clickedBtns.size() - 1);
                    button.setVisibility(View.VISIBLE);
                    clickedBtns.remove(clickedBtns.size() - 1);

                    String text = tvAnswer.getText().toString();
                    String newText = text.substring(0, text.length() - 1);
                    tvAnswer.setText(newText);

                    if(clickedBtns.size() == 0) {
                        btnReset.setVisibility(View.GONE);
                    }
                }
                break;


            case R.id.btnAudio :
                mediaPlayerManager.playMediaPlayer(false);
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
