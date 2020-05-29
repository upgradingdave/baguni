package net.awesomekorean.podo.lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.awesomekorean.podo.AdsManager;
import net.awesomekorean.podo.MediaPlayerManager;
import net.awesomekorean.podo.PlaySoundPool;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessons.LessonReview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LessonReviewWord extends AppCompatActivity implements View.OnClickListener {

    FirebaseStorage storage = FirebaseStorage.getInstance();

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
    ImageView btnClose;
    TextView countText;
    ConstraintLayout loadingLayout;
    ProgressBar loading;
    TemplateView templateView;
    Button btnStart;
    TextView loadingText;


    int[] answerList = new int[4]; // 정답 및 보기 리스트

    int quizCount = 0;

    int quizCorrect;

    MediaPlayerManager mediaPlayerManager;

    PlaySoundPool playSoundPool;

    LessonReview lesson;

    List<Integer> wordImage = new ArrayList<>();

    Map<Integer, byte[]> wordAudioByte = new HashMap<>();

    int audioDownloadProgress;

    AdsManager adsManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_review_word);

        mediaPlayerManager = MediaPlayerManager.getInstance();

        answer = findViewById(R.id.answer);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btnText1 = findViewById(R.id.btnText1);
        btnText2 = findViewById(R.id.btnText2);
        btnText3 = findViewById(R.id.btnText3);
        btnText4 = findViewById(R.id.btnText4);
        btnAudio = findViewById(R.id.btnAudio);
        btnClose = findViewById(R.id.btnClose);
        countText = findViewById(R.id.countText);
        loadingLayout = findViewById(R.id.loadingLayout);
        loading = findViewById(R.id.loading);
        templateView = findViewById(R.id.templateView);
        btnStart = findViewById(R.id.btnStart);
        loadingText = findViewById(R.id.loadingText);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btnAudio.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnStart.setOnClickListener(this);

        adsManager = AdsManager.getInstance();

        if (adsManager.unifiedNativeAd == null) {
            adsManager.loadNativeAds();
        }

        // analytics 로그 이벤트 얻기
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        firebaseAnalytics.logEvent("lesson_review_word", bundle);

        lesson = (LessonReview) getIntent().getSerializableExtra(getResources().getString(R.string.LESSON));

        // 정답, 오답 오디오 미리 로드해놓기
        playSoundPool = new PlaySoundPool(this);

        setImageAndAudio();
    }


    public int getRandomNum(int size) {

        int randomNum = (int) (Math.random() * size);

        return randomNum;
    }


    // 선택지에 들어갈 정답 및 보기 4개를 중복되지 않게 랜덤으로 뽑기
    public void getAnswerList(int size, boolean isCorrect) {

        if(isCorrect) {

            quizCorrect = getRandomNum(size); // 문제정답번호 입력
        }

        answerList[0] = quizCorrect;

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


    public void makeQuiz(boolean isCorrect) {

        getAnswerList(lesson.getFront().size(), isCorrect);

        answer.setText(lesson.getFront().get(answerList[0]));

        mediaPlayerManager.setMediaPlayerByte(false, wordAudioByte.get(answerList[0]));

        RandomArray.randomArrayInt(answerList);

        btn1.setImageResource(wordImage.get(answerList[0]));
        btn2.setImageResource(wordImage.get(answerList[1]));
        btn3.setImageResource(wordImage.get(answerList[2]));
        btn4.setImageResource(wordImage.get(answerList[3]));

        btnText1.setText(lesson.getBack().get(answerList[0]));
        btnText2.setText(lesson.getBack().get(answerList[1]));
        btnText3.setText(lesson.getBack().get(answerList[2]));
        btnText4.setText(lesson.getBack().get(answerList[3]));
    }


    public void setImageAndAudio() {

        setLoadingLayout(View.VISIBLE, View.VISIBLE, View.GONE);

        // 네이티브 광고 출력
        if(adsManager.unifiedNativeAd != null) {
            templateView.setNativeAd(adsManager.unifiedNativeAd);
        }

        audioDownloadProgress = 0;

        for(int i=0; i<lesson.getFront().size(); i++) {

            wordImage.add(getResources().getIdentifier(lesson.getImageString().get(i), "drawable", getPackageName()));

            StorageReference storageRef = storage.getReference().child(lesson.getAudioFolder().get(i)).child(lesson.getAudioString().get(i));

            final long ONE_MEGABYTE = 1024 * 1024;

            final int index = i;

            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {

                    wordAudioByte.put(index, bytes);

                    audioDownloadProgress++;

                    loading.setProgress(audioDownloadProgress * 100 / lesson.getFront().size());

                    // 오디오 다운로드 끝나면
                    if(audioDownloadProgress == lesson.getFront().size()) {

                        setLoadingLayout(View.VISIBLE, View.GONE, View.VISIBLE);
                    }
                }
            });
        }
    }


    public void setLoadingLayout(int layout, int text, int btn) {

        loadingLayout.setVisibility(layout);
        loadingText.setVisibility(text);
        btnStart.setVisibility(btn);
    }


    public void makeNextQuiz(final View selectedBtn, final boolean isCorrect) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                try {
                    selectedBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ripple_custom));

                } catch (NullPointerException e) {
                    System.out.println(e);
                }

                answer.setVisibility(View.GONE);

                btnAudio.setVisibility(View.VISIBLE);

                makeQuiz(isCorrect);
            }
        }, 2000);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnAudio :

                mediaPlayerManager.playMediaPlayer(false);

                break;

            case R.id.btnClose :

                finish();

                break;

            case R.id.btnStart :

                loadingLayout.setVisibility(View.GONE);

                adsManager.loadNativeAds();

                makeQuiz(true);

                break;

            default:

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

                if(quizCorrect == answerList[selectedBtnNo]) {

                    quizCount++;

                    countText.setText(quizCount + " words");

                    answered(view, 0, R.drawable.bg_white_10_stroke_purple, true);

                } else {

                    answered(view, 1, R.drawable.bg_white_10_stroke_red, false);
                }
                break;
        }
    }


    // 정답/오답 소리 출력하고 선택한 이미지에 파란색/빨간색 테두리
    private void answered(View view, int sound, int outline, boolean isCorrect) {

        playSoundPool.playSoundLesson(sound);

        view.setBackground(ContextCompat.getDrawable(this, outline));

        answer.setVisibility(View.VISIBLE);

        btnAudio.setVisibility(View.GONE);

        makeNextQuiz(view, isCorrect);
    }

}


