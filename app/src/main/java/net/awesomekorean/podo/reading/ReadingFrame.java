package net.awesomekorean.podo.reading;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.awesomekorean.podo.ConfirmQuit;
import net.awesomekorean.podo.DownloadAudio;
import net.awesomekorean.podo.MediaPlayerManager;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.collection.CollectionRepository;

import java.util.HashMap;
import java.util.Map;

public class ReadingFrame extends AppCompatActivity implements Button.OnClickListener {

    FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseCrashlytics crashlytics = FirebaseCrashlytics.getInstance();

    Reading reading; // Reading 인스턴스

    ImageView btnBack;
    TextView readingTitle; // reading 타이틀
    TextView readingArticle; // reading 본문

    LinearLayout popUpLayout; // 단어 클릭 시 팝업 레이아웃
    TextView popUpFront;  // 단어 클릭 시 팝업 단어
    TextView popUpBack;  // 단어 클릭 시 팝업 단어 뜻
    ImageView btnCollect; // 단어 클릭 시 collect 버튼

    // 단어를 클릭하면 컬렉션 할 때 들어갈 문자열 저장
    String front;
    String back;
    String audioFileWord;

    LinearLayout collectResult;

    SeekBar seekBar;
    TextView btnNormal;
    ImageView btnPlay;
    ImageView btnPause;
    ProgressBar loading;
    TextView btnSlow;
    Button btnFinish;

    String unitId;

    Context context;

    SpannableStringBuilder span = new SpannableStringBuilder();

    Map<Integer, byte[]> audiosWord = new HashMap<>();

    String url;

    MediaPlayerManager mediaPlayerManager;

    int readingProgress;

    boolean slowBtnClicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_frame);

        readingProgress = 0;

        btnBack = findViewById(R.id.btnBack);
        readingTitle = findViewById(R.id.readingTitle);
        readingArticle = findViewById(R.id.readingArticle);
        popUpLayout = findViewById(R.id.popUpLayout);
        popUpFront = findViewById(R.id.popUpFront);
        popUpBack = findViewById(R.id.popUpBack);
        btnCollect = findViewById(R.id.btnCollect);
        collectResult = findViewById(R.id.collectResult);
        seekBar = findViewById(R.id.seekBar);
        btnNormal = findViewById(R.id.btnNormal);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        loading = findViewById(R.id.loading);
        btnSlow = findViewById(R.id.btnSlow);
        btnFinish = findViewById(R.id.btnFinish);
        btnBack.setOnClickListener(this);
        btnCollect.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnSlow.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        btnSetting(View.VISIBLE, View.GONE, View.GONE);

        context = getApplicationContext();

        // 시크바 이벤트
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(seekBar.getMax() <= 0) {

                    readingProgress = 0;

                } else {

                    readingProgress = Math.max(progress * 100 / seekBar.getMax(), readingProgress);
                }

                if(mediaPlayerManager != null && seekBar.getMax()==progress) {
                    btnSetting(View.VISIBLE, View.GONE, View.GONE);
                    mediaPlayerManager.pauseMediaPlayer();
                    mediaPlayerManager.resetPlayPosition();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(mediaPlayerManager != null) {
                    mediaPlayerManager.pauseMediaPlayer();
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(mediaPlayerManager != null) {
                    mediaPlayerManager.seek(seekBar.getProgress());
                    mediaPlayerManager.playMediaPlayer(true);
                    btnSetting(View.GONE, View.VISIBLE, View.GONE);
                }
            }
        });



        reading = (Reading) getIntent().getSerializableExtra(getResources().getString(R.string.READING));

        readyForReading();
    }


    public void readyForReading() {

        crashlytics.log("readingId : " + reading.getReadingId());
        unitId = reading.getReadingId().toLowerCase();
        int wordLength = reading.getPopUpFront().length;
        String wordAudio[] = new String[wordLength];
        String folder = "reading/" + unitId;


        // 단어오디오 미리 가져오기
        for(int i=0; i<wordLength; i++) {
            final Integer audioIndexWord = i;
            wordAudio[i] = unitId + "_" + i + ".mp3";
            StorageReference storageRef = storage.getReference().child(folder).child(wordAudio[i]);
            final long ONE_MEGABYTE = 1024 * 1024;
            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    System.out.println("오디오를 로드했습니다.");
                    audiosWord.put(audioIndexWord, bytes);
                }
            });
        }


        // 글 생성
        readingTitle.setText(reading.getTitle());
        for(int i=0; i<reading.getArticle().length; i++) {
            SpannableStringBuilder ssb = new SpannableStringBuilder(reading.getArticle()[i]);

            // 짝수번째에 있는 단어에 setSpan 하기
            if(i%2 == 1) {
                final int popUpIndex = i/2;
                ssb.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View view) {  // 하이라이트 클릭 이벤트
                        front = reading.getPopUpFront()[popUpIndex];
                        back = reading.getPopUpBack()[popUpIndex];
                        popUpFront.setText(front);
                        popUpBack.setText(back);
                        popUpLayout.setVisibility(View.VISIBLE);
                        audioFileWord = unitId + "_" + popUpIndex + ".mp3";

                        // 단어 오디오 재생
                        mediaPlayerManager = MediaPlayerManager.getInstance();

                        mediaPlayerManager.pauseMediaPlayer();

                        mediaPlayerManager.setMediaPlayerByte(false, audiosWord.get(popUpIndex));

                        btnSetting(View.VISIBLE, View.GONE, View.GONE);

                        // 프로그레스바 잠그기
                        seekBar.setEnabled(false);
                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {   // 하이라이트 디자인 설정
                        ds.setColor(ContextCompat.getColor(context, R.color.PURPLE));
                    }
                }, 0, reading.getArticle()[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  // 하이라이트 위치 설정

            }
            span.append(ssb);
        }
        readingArticle.setText(span);
        readingArticle.setMovementMethod(LinkMovementMethod.getInstance());
        readingArticle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(popUpLayout.getVisibility()==View.VISIBLE) {
                    popUpLayout.setVisibility(View.GONE);
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnBack :
                if(mediaPlayerManager != null) {
                    mediaPlayerManager.stopMediaPlayer();
                }
                btnSetting(View.VISIBLE, View.GONE, View.GONE);
                seekBar.setProgress(0);
                finish();
                break;

            case R.id.btnCollect:
                String folder = "reading/" + reading.getReadingId().toLowerCase();

                DownloadAudio downloadAudio = new DownloadAudio(context, folder, audioFileWord);
                downloadAudio.downloadAudio();

                CollectionRepository repository = new CollectionRepository(this);
                repository.insert(front, back, audioFileWord);

                collectResult.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        collectResult.setVisibility(View.GONE);
                    }
                }, 1000);
                break;

            case R.id.btnPlay:

                btnSetting(View.INVISIBLE, View.GONE, View.VISIBLE);

                // 최초 플레이 or 다시 플레이 시
                if (mediaPlayerManager == null || url == null) {
                    StorageReference storageRef = storage.getReference().child("reading/"+unitId).child(unitId+".mp3");
                    storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            btnSetting(View.GONE, View.VISIBLE, View.GONE);
                            mediaPlayerManager = MediaPlayerManager.getInstance(seekBar);
                            url = uri.toString();
                            mediaPlayerManager.resetPlayPosition();
                            mediaPlayerManager.setMediaPlayerUrl(true, url);
                            if(slowBtnClicked) {
                                mediaPlayerManager.setSpeed(0.8f);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            btnSetting(View.VISIBLE, View.GONE, View.GONE);
                            Toast.makeText(getApplicationContext(), getString(R.string.FAIL_LOAD_AUDIO), Toast.LENGTH_LONG).show();
                        }
                    });


                    // 뭠췄다가 플레이 시
                } else {
                    btnSetting(View.INVISIBLE, View.GONE, View.VISIBLE);

                    mediaPlayerManager.setMediaPlayerUrl(true, url);
                    if(slowBtnClicked) {
                        mediaPlayerManager.setSpeed(0.8f);
                    }

                    btnSetting(View.GONE, View.VISIBLE, View.GONE);
                }

                seekBar.setEnabled(true);

                break;

            case R.id.btnPause :
                mediaPlayerManager.pauseMediaPlayer();
                btnSetting(View.VISIBLE, View.GONE, View.GONE);
                break;

            case R.id.btnNormal :
                btnSlow.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_white_20_stroke_purple));
                btnNormal.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_purple_20_transparent));
                if(mediaPlayerManager != null) {
                    mediaPlayerManager.setSpeed(1f);
                }
                slowBtnClicked = false;
                break;

            case R.id.btnSlow :
                btnSlow.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_purple_20_transparent));
                btnNormal.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_white_20_stroke_purple));
                if(mediaPlayerManager != null) {
                    mediaPlayerManager.setSpeed(0.8f);
                }
                slowBtnClicked = true;
                break;

            case R.id.btnFinish :

                btnSetting(View.VISIBLE, View.GONE, View.GONE);
                seekBar.setProgress(0);

                openConfirmQuit();
                break;
        }
    }

    private void btnSetting(int setBtnPlay, int setBtnPause, int setProgressBar) {

        btnPlay.setVisibility(setBtnPlay);

        btnPause.setVisibility(setBtnPause);

        loading.setVisibility(setProgressBar);
    }


    public void openConfirmQuit() {

        if(mediaPlayerManager != null) {

            mediaPlayerManager.pauseMediaPlayer();
        }

        Intent intent = new Intent(context, ConfirmQuit.class);

        intent.putExtra(getResources().getString(R.string.PROGRESS), readingProgress);

        intent.putExtra(getResources().getString(R.string.IS_READING), true);

        intent.putExtra(getResources().getString(R.string.READING_ID), reading.getReadingId());

        startActivityForResult(intent, 200);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            if(mediaPlayerManager != null ) {
                mediaPlayerManager.stopMediaPlayer();
            }

            // analytics 로그 이벤트 얻기
            FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
            Bundle bundle = new Bundle();
            bundle.putString("readingId", reading.getReadingId());
            firebaseAnalytics.logEvent("reading_finish", bundle);

            finish();
        }
    }


    @Override
    public void onBackPressed() {
        openConfirmQuit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mediaPlayerManager != null) {
            mediaPlayerManager.releaseMediaPlayer();
        }
    }
}
