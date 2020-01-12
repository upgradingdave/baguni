package net.awesomekorean.podo.reading;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.collection.CollectionRepository;
import net.awesomekorean.podo.reading.readings.Reading0;

public class ReadingFrame extends AppCompatActivity implements Button.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

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

    LinearLayout collectResult;

    SeekBar seekBar;
    TextView btnNormal;
    ImageView btnPlay;
    ImageView btnPause;
    TextView btnSlow;
    Button btnFinish;

    boolean isPlaying = false;
    MediaPlayer mediaPlayer;
    Integer playingPosition = null; // 오디오 재생 멈춘 지점
    int playingTime; // 오디오 길이
    String audio; // 오디오 파일 경로

    Context context;


    class MyThread extends Thread {
        @Override
        // 쓰레드가 시작되면 콜백되는 매서드, 시크바를 조금씩 움직이게 해줌
        public void run() {
            while(isPlaying) {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_frame);

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
        btnSlow = findViewById(R.id.btnSlow);
        btnFinish = findViewById(R.id.btnFinish);
        btnBack.setOnClickListener(this);
        btnCollect.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnSlow.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        context = getApplicationContext();

        // 시크바 이벤트
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(seekBar.getMax()==progress) {
                    setVisibility(View.VISIBLE, View.GONE);
                    isPlaying = false;
                    mediaPlayer.stop();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isPlaying = false;
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                isPlaying = true;
                int position = seekBar.getProgress(); // 유저가 움직여 놓은 위치
                mediaPlayer.seekTo(position);
                mediaPlayer.start();
                new MyThread().start();
            }
        });


        switch (MainReading.readingUnit) {

            case 0 :
                reading = new Reading0();
                readyForReading();
                break;

        }
    }

    public void readyForReading() {  // 글 생성

        readingTitle.setText(reading.getTitle());

        SpannableStringBuilder span = new SpannableStringBuilder(reading.getArticle());

        for(int i=0; i<reading.getStart().length; i++) {

            final int finalI = i;
            span.setSpan(new ClickableSpan() {
                @Override
                public void onClick(@NonNull View view) {  // 하이라이트 클릭 이벤트
                    front = reading.getPopUpFront()[finalI];
                    back = reading.getPopUpBack()[finalI];
                    popUpFront.setText(reading.getPopUpFront()[finalI]);
                    popUpBack.setText(reading.getPopUpBack()[finalI]);
                    popUpLayout.setVisibility(View.VISIBLE);

                    // 단어 오디오 재생
                    if(mediaPlayer != null ) {
                        playingPosition = mediaPlayer.getCurrentPosition();
                        mediaPlayer.pause();
                        setVisibility(View.VISIBLE, View.GONE);
                        isPlaying = false;
                    }
                    String audioPopUp = "android.resource://" + context.getPackageName() + "/raw/" + audio + "_" + finalI;
                    Uri uri = Uri.parse(audioPopUp);
                    MediaPlayer mpPopUp = MediaPlayer.create(context, uri);
                    mpPopUp.start();
                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {   // 하이라이트 디자인 설정
                    ds.setColor(ContextCompat.getColor(context, R.color.PURPLE));
                    ds.bgColor = ContextCompat.getColor(context, R.color.PURPLE_TRANSPARENT);
                }
            }, reading.getStart()[i], reading.getEnd()[i], 0);  // 하이라이트 위치 설정

            readingArticle.setTag(i);
            readingArticle.setText(span);
            readingArticle.setMovementMethod(LinkMovementMethod.getInstance());
        }

        readingArticle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(popUpLayout.getVisibility()==View.VISIBLE) {
                    popUpLayout.setVisibility(View.GONE);
                }
                return false;
            }
        });

        audio = reading.getReadingId().toLowerCase();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnBack :
                finish();
                break;

            case R.id.btnCollect:
                CollectionRepository repository = new CollectionRepository(this);
                repository.insert(front, back, null);

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
                // 뭠췄다가 플레이 시
                if (playingPosition != null) {
                    mediaPlayer.seekTo(playingPosition);
                    mediaPlayer.start();
                    setVisibility(View.GONE, View.VISIBLE);
                    isPlaying = true;
                    new MyThread().start();
                    playingPosition = null;

                    // 최초 플레이 or 다시 플레이 시
                } else {
                    String uriPath = "android.resource://" + context.getPackageName() + "/raw/" + audio;
                    Uri uri = Uri.parse(uriPath);
                    mediaPlayer = MediaPlayer.create(context, uri);
                    mediaPlayer.setLooping(false); // 무한반복 false
                    mediaPlayer.start();

                    playingTime = mediaPlayer.getDuration(); // 노래 재생시간
                    seekBar.setMax(playingTime);
                    new MyThread().start();
                    isPlaying = true;

                    setVisibility(View.GONE, View.VISIBLE);
                }
                break;

            case R.id.btnPause :
                playingPosition = mediaPlayer.getCurrentPosition();
                mediaPlayer.pause();
                setVisibility(View.VISIBLE, View.GONE);
                isPlaying = false;
                break;

            case R.id.btnNormal :
                btnSlow.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_white_20_stroke_purple));
                btnNormal.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_purple_20_transparent));
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(1f));
                }
                break;

            case R.id.btnSlow :
                btnSlow.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_purple_20_transparent));
                btnNormal.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_white_20_stroke_purple));
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(0.8f));
                }
            break;

            case R.id.btnFinish :
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }

                setVisibility(View.VISIBLE, View.GONE);
                seekBar.setProgress(0);

                // 읽기완료 정보 업데이트 하기
                String readingId = MainReading.readingId;
                UserInformation userInformation = SharedPreferencesInfo.getUserInfo(context);
                if(!userInformation.getReadingComplete().contains(readingId)) {
                    userInformation.addReadingComplete(readingId);
                    SharedPreferencesInfo.setUserInfo(context, userInformation);
                    db.collection(getString(R.string.DB_USERS)).document(MainActivity.userEmail).collection(getString(R.string.DB_INFORMATION)).document(getString(R.string.DB_INFORMATION)).set(userInformation);
                    System.out.println("Reading 완료 리스트를 업데이트 했습니다.");

                } else {
                    System.out.println("이미 완료된 Reading 입니다.");
                }


                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPlaying = false;
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
        setVisibility(View.VISIBLE, View.GONE);
    }

    public void setVisibility(int a, int b) {
        btnPlay.setVisibility(a);
        btnPause.setVisibility(b);
    }
}
