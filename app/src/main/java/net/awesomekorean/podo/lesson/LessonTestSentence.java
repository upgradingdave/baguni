package net.awesomekorean.podo.lesson;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.flexbox.FlexboxLayout;
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
import java.util.HashMap;
import java.util.Map;

public class LessonTestSentence extends Fragment implements View.OnClickListener {

    View view;

    public static LessonTestSentence newInstance() {
        return new LessonTestSentence();
    }

    FirebaseStorage storage = FirebaseStorage.getInstance();
    LessonReview lesson;
    String[] syllables; // wordInIndex의 단어를 각 음절로 나눔
    TextView meaning;
    ConstraintLayout answerLayout;
    TextView tvAnswer; // 입력한 정답이 표시되는 텍스트뷰
    ImageView btnTranslate;
    FlexboxLayout flexboxLayout; // 정답을 입력하는 버튼이 들어가는 layout
    Button btnSelector; // 정답을 입력하기 위해 만들어진 한글 버튼
    ImageView btnAudio;
    ImageView btnReset;
    int quizCount = 0;
    PlaySoundPool playSoundPool;
    MediaPlayerManager mediaPlayerManager;
    View.OnClickListener selectorButtonClick; // 정답 입력 버튼 클릭 시 작동하는 함수
    String sentence;
    Map<Integer, byte[]> sentenceAudioByte = new HashMap<>();
    int audioDownloadProgress;
    int reviewIndex;
    ConstraintLayout loadingLayout;
    ProgressBar loading;
    TemplateView templateView;
    Button btnStart;
    TextView loadingText;
    AdsManager adsManager;
    ArrayList<Button> clickedBtns;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_test_conjugate, container, false);

        //lesson = (LessonReview) getIntent().getSerializableExtra(getResources().getString(R.string.LESSON));

        mediaPlayerManager = MediaPlayerManager.getInstance();

        meaning = view.findViewById(R.id.meaning);
        answerLayout = view.findViewById(R.id.answerLayout);
        tvAnswer = view.findViewById(R.id.tvAnswer);
        btnTranslate = view.findViewById(R.id.btnTranslate);
        flexboxLayout = view.findViewById(R.id.flexboxLayout);
        loadingLayout = view.findViewById(R.id.loadingLayout);
        loading = view.findViewById(R.id.loading);
        templateView = view.findViewById(R.id.templateView);
        btnStart = view.findViewById(R.id.btnStart);
        loadingText = view.findViewById(R.id.loadingText);
        btnAudio = view.findViewById(R.id.btnAudio);
        btnReset = view.findViewById(R.id.btnReset);
        btnAudio.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnTranslate.setOnClickListener(this);
        btnStart.setOnClickListener(this);

        adsManager = AdsManager.getInstance();

        if (adsManager.unifiedNativeAd == null) {
            adsManager.loadNativeAds(getContext());
        }

        clickedBtns = new ArrayList<>();


        // analytics 로그 이벤트 얻기
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
        Bundle bundle = new Bundle();
        firebaseAnalytics.logEvent("lesson_review_sentence", bundle);
        firebaseAnalytics.logEvent("native_watch", bundle);


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

                        answered(0, R.drawable.bg_white_10_stroke_purple);

                    } else {  // 오답
                        answered(1, R.drawable.bg_white_10_stroke_red);
                    }

                    flexboxLayout.removeAllViews();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if(tvAnswer.getText().toString().equals(sentence)) { // 정답

                                quizCount++;

                                makeQuiz(false);

                            } else {

                                makeQuiz(true);
                            }

                            tvAnswer.setText("");

                            btnReset.setVisibility(View.GONE);

                            answerLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_white_10));


                        }
                    }, 2000);
                }
            }
        };

        setImageAndAudio();

        return view;
    }

    private void answered(int sound, int outline) {
        playSoundPool.playSoundLesson(sound);
        meaning.setText(lesson.getFront().get(reviewIndex));
        answerLayout.setBackground(ContextCompat.getDrawable(getContext(), outline));
    }


    public int getRandomNum(int size) {

        int randomNum = (int) (Math.random() * size);

        return randomNum;
    }


    // word의 단어를 음절로 나누고 램덤으로 섞어서 버튼으로 만들어 줌
    public void makeQuiz(boolean isRepeat) {

        if(!isRepeat) {
            reviewIndex = getRandomNum(lesson.getFront().size());
        }

        sentence = lesson.getFront().get(reviewIndex);

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

        meaning.setText(lesson.getBack().get(reviewIndex));

        mediaPlayerManager.setMediaPlayerByte(false, sentenceAudioByte.get(reviewIndex));
    }


    public void setImageAndAudio() {

        setLoadingLayout(View.VISIBLE, View.VISIBLE, View.GONE);

        // 네이티브 광고 출력
        if(adsManager.unifiedNativeAd != null) {
            templateView.setNativeAd(adsManager.unifiedNativeAd);
        }

        audioDownloadProgress = 0;

        for(int i=0; i<lesson.getFront().size(); i++) {

            StorageReference storageRef = storage.getReference().child(lesson.getAudioFolder().get(i)).child(lesson.getAudioString().get(i));

            final long ONE_MEGABYTE = 1024 * 1024;

            final int index = i;

            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {

                    sentenceAudioByte.put(index, bytes);

                    audioDownloadProgress++;

                    loading.setProgress(audioDownloadProgress * 100 / lesson.getFront().size());

                    // 오디오 다운로드 끝나면
                    if(audioDownloadProgress == lesson.getFront().size()) {

                        setLoadingLayout(View.VISIBLE, View.INVISIBLE, View.VISIBLE);
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

            case R.id.btnTranslate :

                if(meaning.getText().equals(lesson.getBack().get(reviewIndex))) {

                    meaning.setText(lesson.getFront().get(reviewIndex));

                } else {

                    meaning.setText(lesson.getBack().get(reviewIndex));
                }
                break;

            case R.id.btnStart :

                loadingLayout.setVisibility(View.GONE);

                adsManager.loadNativeAds(getContext());

                makeQuiz(false);

                break;
        }
    }
}
