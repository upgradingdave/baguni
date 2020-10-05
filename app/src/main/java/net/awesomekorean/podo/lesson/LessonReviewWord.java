package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.util.ArrayUtils;

import net.awesomekorean.podo.MediaPlayerManager;
import net.awesomekorean.podo.PlaySoundPool;
import net.awesomekorean.podo.R;

public class LessonReviewWord extends Fragment implements View.OnClickListener {

    public static LessonReviewWord newInstance() {
        return new LessonReviewWord();
    }

    View view;

    TextView answer;
    ImageView btn1;
    ImageView btn2;
    ImageView btn3;
    ImageView btn4;
    TextView btnText1;
    TextView btnText2;
    TextView btnText3;
    TextView btnText4;
    ImageView btnAudio;

    int[] answerList = new int[4]; // 정답 및 보기 리스트
    int quizIndex;

    MediaPlayerManager mediaPlayerManager;
    PlaySoundPool playSoundPool;

    LessonReviewFrame activity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_review_word, container, false);

        mediaPlayerManager = MediaPlayerManager.getInstance();

        answer = view.findViewById(R.id.answer);
        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);
        btnText1 = view.findViewById(R.id.btnText1);
        btnText2 = view.findViewById(R.id.btnText2);
        btnText3 = view.findViewById(R.id.btnText3);
        btnText4 = view.findViewById(R.id.btnText4);
        btnAudio = view.findViewById(R.id.btnAudio);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btnAudio.setOnClickListener(this);

        // 정답, 오답 오디오 미리 로드해놓기
        playSoundPool = new PlaySoundPool(getContext());
        makeQuiz();
        return view;
    }

    public int getRandomNum(int size) {
        int randomNum = (int) (Math.random() * size);
        return randomNum;
    }


    // 선택지에 들어갈 정답 및 보기 4개를 중복되지 않게 랜덤으로 뽑기
    public void getAnswerList(int size) {
        int rand = getRandomNum(size);
        while(quizIndex == rand) {
            System.out.println("같음! 다시!");
            rand = getRandomNum(size);
        }
        quizIndex = rand;
        answerList[0] = quizIndex;  // 문제정답번호 입력

        // 정답을 제외한 보기 3개를 중복되지 않게 찾기
        int count = 1;

        while (count <4) {
            int number = getRandomNum(size);

            // 새로 뽑은 랜덤 숫자가 answerList 에 없으면 추가
            if(!ArrayUtils.contains(answerList, number)) {
                answerList[count] = number;
                count++;
            }
        }
    }


    public void makeQuiz() {
        getAnswerList(activity.wordFront.size());
        answer.setText(activity.wordFront.get(answerList[0]));
        mediaPlayerManager.setMediaPlayerByte(false, activity.wordAudioByte.get(answerList[0]));
        RandomArray.randomArrayInt(answerList);

        btn1.setImageResource(activity.wordImage.get(answerList[0]));
        btn2.setImageResource(activity.wordImage.get(answerList[1]));
        btn3.setImageResource(activity.wordImage.get(answerList[2]));
        btn4.setImageResource(activity.wordImage.get(answerList[3]));

        btnText1.setText(activity.wordBack.get(answerList[0]));
        btnText2.setText(activity.wordBack.get(answerList[1]));
        btnText3.setText(activity.wordBack.get(answerList[2]));
        btnText4.setText(activity.wordBack.get(answerList[3]));

        setAllbtnEnable(true);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnAudio :
                mediaPlayerManager.playMediaPlayer(false);
                break;

            default:
                setAllbtnEnable(false);
                int selectedBtnNo;

                if(view.getId() == R.id.btn1) {
                    selectedBtnNo = 0;

                } else if(view.getId() == R.id.btn2) {
                    selectedBtnNo = 1;

                } else if(view.getId() == R.id.btn3) {
                    selectedBtnNo = 2;

                } else {
                    selectedBtnNo = 3;
                }

                if(quizIndex == answerList[selectedBtnNo]) {
                    answered(view, 0, R.drawable.bg_white_10_stroke_purple, true);

                } else {
                    answered(view, 1, R.drawable.bg_white_10_stroke_red, false);
                }
                break;
        }
    }

    private void setAllbtnEnable(boolean b) {
        btn1.setEnabled(b);
        btn2.setEnabled(b);
        btn3.setEnabled(b);
        btn4.setEnabled(b);
    }


    // 정답/오답 소리 출력하고 선택한 이미지에 파란색/빨간색 테두리
    private void answered(final View view, int sound, int outline, final boolean isCorrect) {
        playSoundPool.playSoundLesson(sound);
        view.setBackground(ContextCompat.getDrawable(getContext(), outline));
        answer.setVisibility(View.VISIBLE);
        btnAudio.setVisibility(View.GONE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    view.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.ripple_custom));

                } catch (NullPointerException e) {
                    System.out.println(e);
                }

                answer.setVisibility(View.GONE);
                btnAudio.setVisibility(View.VISIBLE);
                activity.progressCount(isCorrect);
                if(activity.progressCount < 10) {
                    makeQuiz();
                } else {
                    activity.replaceFragment(LessonReviewConjugate.newInstance());
                }
            }
        }, 2000);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof LessonReviewFrame) {
            activity = (LessonReviewFrame) context;
        }
    }
}
