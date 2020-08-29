package net.awesomekorean.podo.lesson;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessonReview.LessonReview;
import net.awesomekorean.podo.lesson.lessons.Lesson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LessonReviewFrame extends AppCompatActivity {

    FirebaseStorage storage = FirebaseStorage.getInstance();

    FragmentManager fm;
    FragmentTransaction ft;

    ImageView btnClose;
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

    Lesson[] lessons;

    AdsManager adsManager;

    ConstraintLayout loadingLayout;
    ProgressBar loading;
    TemplateView templateView;
    Button btnStart;
    TextView loadingText;
    int downloadProgress;

    List<Integer> wordImage = new ArrayList<>();
    public static Map<Integer, byte[]> wordAudioByte = new HashMap<>();
    public static Map<Integer, byte[]> sentenceAudioByte = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_review_frame);

        btnClose = findViewById(R.id.btnClose);
        progressBar = findViewById(R.id.progressBar);
        tvProgress = findViewById(R.id.tvProgress);
        loadingLayout = findViewById(R.id.loadingLayout);
        loading = findViewById(R.id.loading);
        templateView = findViewById(R.id.templateView);
        btnStart = findViewById(R.id.btnStart);
        loadingText = findViewById(R.id.loadingText);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfirmQuit();
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingLayout.setVisibility(View.GONE);
                replaceFragment(LessonReviewWord.newInstance());
                adsManager.loadNativeAds(getApplicationContext());
            }
        });

        adsManager = AdsManager.getInstance();

        setLoadingLayout(View.VISIBLE, View.VISIBLE, View.GONE);

        // 네이티브 광고 출력
        if(adsManager.unifiedNativeAd != null) {
            templateView.setNativeAd(adsManager.unifiedNativeAd);
        }

        // analytics 로그 이벤트 얻기
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
        Bundle bundle = new Bundle();
        firebaseAnalytics.logEvent("lesson_review", bundle);

        progressCount = 0;
        correctCount = 0;
        tvProgress.setText(progressCount + " / " + totalQuizNo);

        lessonReview = (LessonReview) getIntent().getSerializableExtra(getResources().getString(R.string.LESSON));
        lessons = lessonReview.getLessons();

        setLessonReview();
    }


    // 리뷰 세팅
    private void setLessonReview() {

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
                                        setLoadingLayout(View.VISIBLE, View.INVISIBLE, View.VISIBLE);
                                    }
                                }
                            });
                        }
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


    public void replaceFragment(Fragment fragment) {
        if (progressCount != totalQuizNo) {
            fm = getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.frameLayout, fragment);
            ft.commitAllowingStateLoss();

        } else {
            //todo: 결과표시
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            MediaPlayerManager mediaPlayerManager = MediaPlayerManager.getInstance();
            mediaPlayerManager.stopMediaPlayer();
            finish();
        }
    }


    public void openConfirmQuit() {
        Intent intent = new Intent(getApplicationContext(), ConfirmQuit.class);
        intent.putExtra(getResources().getString(R.string.PROGRESS), progressBar.getProgress());
        intent.putExtra(getResources().getString(R.string.LESSON_ID), lessonReview.getLessonId());
        startActivityForResult(intent, 200);
    }
}
