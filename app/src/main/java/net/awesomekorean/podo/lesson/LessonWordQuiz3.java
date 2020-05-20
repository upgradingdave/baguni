package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.google.firebase.analytics.FirebaseAnalytics;

import net.awesomekorean.podo.MediaPlayerManager;
import net.awesomekorean.podo.PlaySoundPool;
import net.awesomekorean.podo.lesson.lessonHangul.DpToPx;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessons.Lesson;

public class LessonWordQuiz3 extends Fragment implements Button.OnClickListener{

    View view;

    Lesson lesson;

    int[] wordImage;

    String word; // 퀴즈용 단어 묶음에 있는 각 단어

    String[] syllables; // wordInIndex의 단어를 각 음절로 나눔

    TextView answer;
    ImageView answerImage;
    ConstraintLayout answerLayout;

    TextView tvAnswer; // 입력한 정답이 표시되는 텍스트뷰

    FlexboxLayout flexboxLayout; // 정답을 입력하는 버튼이 들어가는 layout

    Button btnSelector; // 정답을 입력하기 위해 만들어진 한글 버튼

    ImageView btnAudio;
    ImageView btnReset;

    LinearLayout totalPage;

    int quizCount = 0; // 단어퀴즈 순서

    PlaySoundPool playSoundPool;
    MediaPlayerManager mediaPlayerManager;

    View.OnClickListener selectorButtonClick; // 정답 입력 버튼 클릭 시 작동하는 함수


    // fragment 간 전환을 위해 만듦
    public static LessonWordQuiz3 newInstance() {
        return new LessonWordQuiz3();
    }

    private LessonFrame activity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_word_quiz3, container, false);

        lesson = LessonFrame.lesson;

        wordImage = activity.wordImage;

        mediaPlayerManager = MediaPlayerManager.getInstance();

        answer = view.findViewById(R.id.answer);
        answerImage = view.findViewById(R.id.answerImage);
        answerLayout = view.findViewById(R.id.answerLayout);
        tvAnswer = view.findViewById(R.id.tvAnswer);
        flexboxLayout = view.findViewById(R.id.flexboxLayout);
        btnAudio = view.findViewById(R.id.btnAudio);
        btnReset = view.findViewById(R.id.btnReset);
        totalPage = view.findViewById(R.id.totalPage);
        btnAudio.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        totalPage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        // analytics 로그 이벤트 얻기
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(activity);
        Bundle bundle = new Bundle();
        firebaseAnalytics.logEvent("lesson_quiz3", bundle);


        playSoundPool = new PlaySoundPool(activity);

        selectorButtonClick = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Button selectedBtn = (Button) view;

                String selectedBtnText = selectedBtn.getText().toString();

                if(tvAnswer.getText().length() != word.length()) {

                    tvAnswer.append(selectedBtnText);
                }

                btnReset.setVisibility(View.VISIBLE);

                if(tvAnswer.getText().length() == word.length()) {

                    if(tvAnswer.getText().toString().equals(word)) { // 정답

                        answered(selectedBtn, 0, R.drawable.bg_white_10_stroke_purple);

                    } else {  // 오답
                        answered(selectedBtn, 1, R.drawable.bg_white_10_stroke_red);
                    }

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if(tvAnswer.getText().toString().equals(word)) {
                                quizCount++;

                                LessonFrame.progressCount++;
                                LessonFrame.progressCount();

                                if (quizCount != lesson.getWordFront().length) {  // 문제가 남아있을 때
                                    flexboxLayout.removeAllViews();
                                    makeQuiz();

                                } else {  // 문제 다 풀었을 때
                                    quizCount = 0;
                                    activity.replaceFragment(LessonSentence.newInstance());
                                }
                            }
                            tvAnswer.setText("");
                            btnReset.setVisibility(View.GONE);
                            try {
                                answerLayout.setBackground(ContextCompat.getDrawable(activity, R.drawable.bg_white_10));
                            } catch (NullPointerException e) {
                                System.out.println("NullPointerException inside LWQ3");
                            }
                        }
                    }, 2000);
                }
            }
        };
        makeQuiz();

        LessonFrame.setNavigationColor(activity, LessonFrame.navigationQuiz, R.drawable.bg_green_10);

        return view;
    }


    private void answered(Button selectedBtn, int sound, int outline) {
        playSoundPool.playSoundLesson(sound);
        answerLayout.setBackground(ContextCompat.getDrawable(activity, outline));
    }


    // word의 단어를 음절로 나누고 램덤으로 섞어서 버튼으로 만들어 줌
    public void makeQuiz() {

        word = lesson.getWordFront()[quizCount];

        syllables = new String[word.length()];

        for(int i=0; i<word.length(); i++) {
            syllables[i] = word.substring(i, i+1);
        }

        RandomArray.randomArrayString(syllables);

        for(int i=0; i<syllables.length; i++) {

            btnSelector = new Button(activity);

            int gap10 = DpToPx.getDpToPx(activity.getResources(), 10);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.rightMargin = gap10;
            params.bottomMargin = gap10;

            btnSelector.setLayoutParams(params);
            btnSelector.setBackground(ContextCompat.getDrawable(activity, R.drawable.ripple_custom));
            btnSelector.setText(syllables[i]);
            btnSelector.setOnClickListener(selectorButtonClick);
            flexboxLayout.addView(btnSelector);
        }

        answer.setText(lesson.getWordBack()[quizCount]);
        answerImage.setImageResource(wordImage[quizCount]);

        mediaPlayerManager.setMediaPlayerByte(false, activity.wordAudioByte.get(quizCount));
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnReset :
                tvAnswer.setText("");
                btnReset.setVisibility(View.GONE);
                break;

            case R.id.btnAudio :
                mediaPlayerManager.playMediaPlayer(false);
                break;
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof LessonFrame) {
            activity = (LessonFrame) context;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
    }
}
