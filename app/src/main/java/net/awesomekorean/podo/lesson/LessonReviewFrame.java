package net.awesomekorean.podo.lesson;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.awesomekorean.podo.AdsManager;
import net.awesomekorean.podo.ConfirmQuit;
import net.awesomekorean.podo.MediaPlayerManager;
import net.awesomekorean.podo.PlaySoundPool;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.lesson.lessonReviewRewards.LessonReview;
import net.awesomekorean.podo.lesson.lessons.Lesson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LessonReviewFrame extends AppCompatActivity implements View.OnClickListener {

    FirebaseStorage storage = FirebaseStorage.getInstance();
    AdsManager adsManager;

    FragmentManager fm;
    FragmentTransaction ft;

    ImageView btnClose;
    int cutLine = 18;
    public static int totalQuizNo = 20;
    public static int progressCount;
    public static int correctCount;
    public static ProgressBar progressBar;
    public static TextView tvProgress;

    public static LessonReview lessonReview;

    public static List<String> wordFront = new ArrayList<>();
    public static List<String> wordBack = new ArrayList<>();
    public static List<String> wordImageString = new ArrayList<>();
    public static List<String> wordAudioString = new ArrayList<>();
    public static List<String> wordAudioFolder = new ArrayList<>();

    public static List<String> sentenceFront = new ArrayList<>();
    public static List<String> sentenceBack = new ArrayList<>();
    public static List<String> sentenceAudioString = new ArrayList<>();
    public static List<String> sentenceAudioFolder = new ArrayList<>();

    List<Integer> wordImage = new ArrayList<>();
    public static Map<Integer, byte[]> wordAudioByte = new HashMap<>();
    public static Map<Integer, byte[]> sentenceAudioByte = new HashMap<>();

    ConstraintLayout layoutLoading;
    ProgressBar loading;
    TemplateView templateView;
    Button btnStart;
    TextView loadingText;
    int downloadProgress;

    ConstraintLayout layoutPassed;
    ConstraintLayout layoutFailed;
    ProgressBar progressBarPassed;
    ProgressBar progressBarFailed;
    TextView tvScorePassed;
    TextView tvScoreFailed;
    Button btnComplete;
    Button btnYes;
    Button btnNo;

    PlaySoundPool playSoundPool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_review_frame);

        btnClose = findViewById(R.id.btnClose);
        progressBar = findViewById(R.id.progressBar);
        tvProgress = findViewById(R.id.tvProgress);
        layoutLoading = findViewById(R.id.layoutLoading);
        loading = findViewById(R.id.loading);
        templateView = findViewById(R.id.templateView);
        btnStart = findViewById(R.id.btnStart);
        loadingText = findViewById(R.id.loadingText);
        layoutPassed = findViewById(R.id.layoutPassed);
        layoutFailed = findViewById(R.id.layoutFailed);
        progressBarPassed = findViewById(R.id.progressBarPassed);
        progressBarFailed = findViewById(R.id.progressBarFailed);
        tvScorePassed = findViewById(R.id.tvScorePassed);
        tvScoreFailed = findViewById(R.id.tvScoreFailed);
        btnComplete = findViewById(R.id.btnComplete);
        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);
        btnClose.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        btnComplete.setOnClickListener(this);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);

        adsManager = AdsManager.getInstance();

        loadingText.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.GONE);
        setLayout(View.VISIBLE, View.GONE, View.GONE);

        // 네이티브 광고 출력
        if(adsManager.unifiedNativeAd != null) {
            templateView.setNativeAd(adsManager.unifiedNativeAd);
        }

        // 전면광고 로드 체크
        if(adsManager.interstitialAd == null || !adsManager.interstitialAd.isLoaded()) {
            adsManager.loadFullAds(getApplicationContext());
        }

        // analytics 로그 이벤트 얻기
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
        Bundle bundle = new Bundle();
        firebaseAnalytics.logEvent("lesson_review", bundle);

        playSoundPool = new PlaySoundPool(getApplicationContext());

        lessonReview = (LessonReview) getIntent().getSerializableExtra(getResources().getString(R.string.LESSON));
        reviewInit();
        setLessonReview();
    }


    // 리뷰데이터 초기화
    private void reviewInit() {
        progressCount = 0;
        correctCount = 0;
        progressBar.setProgress(0);
        tvProgress.setText(progressCount + " / " + totalQuizNo);
    }


    // 레이아웃 세팅
    private void setLayout(int layoutL, int layoutP, int layoutF) {
        layoutLoading.setVisibility(layoutL);
        layoutPassed.setVisibility(layoutP);
        layoutFailed.setVisibility(layoutF);
    }


    // 리뷰 세팅
    private void setLessonReview() {
        Lesson[] lessons = lessonReview.getLessons();

        // 리뷰 아이템 세팅
        for(int i=0; i<lessons.length; i++) {
            String lessonId = lessons[i].getLessonId().toLowerCase();

            for(int j=0; j<lessons[i].getWordFront().length; j++) {
                this.wordFront.add(lessons[i].getWordFront()[j]);
                this.wordBack.add(lessons[i].getWordBack()[j]);
                this.wordImageString.add(lessonId + "_word_" + j);
                this.wordAudioString.add(lessonId + "_word_" + j + ".mp3");
                this.wordAudioFolder.add("lesson/" + lessonId);
            }

            for(int j=0; j<lessons[i].getReviewId().length; j++) {
                int index = lessons[i].getReviewId()[j];
                this.sentenceFront.add(lessons[i].getSentenceFront()[index]);
                this.sentenceBack.add(lessons[i].getSentenceBack()[index]);
                this.sentenceAudioString.add(lessonId + "_sentence_" + index + ".mp3");
                this.sentenceAudioFolder.add("lesson/" + lessonId + "/");
            }
        }


        // 단어 이미지 & 오디오 다운로드
        downloadProgress = 0;
        loading.setMax(wordFront.size() + sentenceFront.size());

        for(int i=0; i<wordFront.size(); i++) {
            wordImage.add(getResources().getIdentifier(wordImageString.get(i), "drawable", getPackageName()));
            StorageReference storageRef = storage.getReference().child(wordAudioFolder.get(i)).child(wordAudioString.get(i));
            final long ONE_MEGABYTE = 1024 * 1024;
            final int index = i;
            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    wordAudioByte.put(index, bytes);
                    downloadProgress++;
                    loading.setProgress(downloadProgress);

                    // 단어 오디오 다운로드 끝나면
                    if(downloadProgress == wordFront.size()) {

                        // 문장 오디오 다운로드
                        for(int i=0; i<sentenceFront.size(); i++) {
                            StorageReference storageRef = storage.getReference().child(sentenceAudioFolder.get(i)).child(sentenceAudioString.get(i));
                            final long ONE_MEGABYTE = 1024 * 1024;
                            final int index = i;
                            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {
                                    sentenceAudioByte.put(index, bytes);
                                    downloadProgress++;
                                    loading.setProgress(downloadProgress);

                                    // 오디오 다운로드 끝나면
                                    if(downloadProgress == loading.getMax()) {
                                        loadingText.setVisibility(View.INVISIBLE);
                                        btnStart.setVisibility(View.VISIBLE);
                                    }
                                }
                            });
                        }
                    }
                }
            });
        }
    }


    public void replaceFragment(Fragment fragment) {
        if (progressCount != totalQuizNo) {
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.frameLayout, fragment);
            ft.commitAllowingStateLoss();

        // 리뷰 끝! 결과 보기
        } else {
            Animation aniScale = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_1000);
            ObjectAnimator animator;
            int score = correctCount*100/totalQuizNo;

            if(correctCount >= cutLine) {   // 합격
                setLayout(View.GONE, View.VISIBLE, View.GONE);
                progressBarPassed.setProgress(score);
                animator = ObjectAnimator.ofInt(progressBarPassed, "progress", 0, score);
                tvScorePassed.setText(correctCount + "/" + totalQuizNo);
                tvScorePassed.startAnimation(aniScale);
                playSoundPool.playSoundYay();

            } else {    // 불합격
                setLayout(View.GONE, View.GONE, View.VISIBLE);
                progressBarFailed.setProgress(score);
                animator = ObjectAnimator.ofInt(progressBarFailed, "progress", 0, score);
                tvScoreFailed.setText(correctCount + "/" + totalQuizNo);
                tvScoreFailed.startAnimation(aniScale);
                playSoundPool.playSoundDingDing();
            }

            animator.setDuration(1500);
            animator.setInterpolator(new DecelerateInterpolator());
            animator.start();
        }
    }


    public static void progressCount(boolean isCorrect) {
        progressCount++;
        progressBar.setProgress(progressCount*100/totalQuizNo);
        tvProgress.setText(progressCount + " / " + totalQuizNo);
        if(isCorrect) {
            correctCount++;
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnClose :
                openConfirmQuit();
                break;


            case R.id.btnStart :
                setLayout(View.GONE, View.GONE, View.GONE);
                replaceFragment(LessonReviewWord.newInstance());
                adsManager.loadNativeAds(getApplicationContext());
                break;


            case R.id.btnComplete :
                adsManager.playFullAds(getApplicationContext());
                UserInformation userInformation = SharedPreferencesInfo.getUserInfo(getApplicationContext());
                userInformation.updateCompleteList(getApplicationContext(), lessonReview.getLessonId(), false);
                finish();
                break;


            case R.id.btnYes :
                reviewInit();
                setLayout(View.GONE, View.GONE, View.GONE);
                replaceFragment(LessonReviewWord.newInstance());
                break;


            case R.id.btnNo :
                adsManager.playFullAds(getApplicationContext());
                finish();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            MediaPlayerManager mediaPlayerManager = MediaPlayerManager.getInstance();
            mediaPlayerManager.stopMediaPlayer();
            finish();
        }
    }


    public void openConfirmQuit() {
        MediaPlayerManager mediaPlayerManager = MediaPlayerManager.getInstance();
        mediaPlayerManager.stopMediaPlayer();
        Intent intent = new Intent(getApplicationContext(), ConfirmQuit.class);
        intent.putExtra(getResources().getString(R.string.LESSON_ID), lessonReview.getLessonId());
        startActivityForResult(intent, 200);
    }


    @Override
    public void onBackPressed() {
        openConfirmQuit();
    }
}
