package net.awesomekorean.podo.reading;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.awesomekorean.podo.DownloadAudio;
import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.MediaPlayerManager;
import net.awesomekorean.podo.PlayMediaPlayer;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.collection.CollectionRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReadingFrame extends AppCompatActivity implements Button.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();

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
    TextView btnSlow;
    Button btnFinish;

    boolean isPlaying = false;
    MediaPlayer mediaPlayer;
    Integer playingPosition = null; // 오디오 재생 멈춘 지점
    int playingTime; // 오디오 길이
    String unitId;

    Context context;

    SpannableStringBuilder span = new SpannableStringBuilder();

    PlayMediaPlayer playMediaPlayer = new PlayMediaPlayer();

    Map<Integer, byte[]> audiosWord = new HashMap<>();

    String url;

    MediaPlayerManager mediaPlayerManager;


    class MyThread extends Thread {
        @Override
        // 쓰레드가 시작되면 콜백되는 매서드, 시크바를 조금씩 움직이게 해줌
        public void run() {
            while(isPlaying) {
                if(mediaPlayer != null) {
                    Integer currentPosition = null;
                    try {
                        currentPosition = mediaPlayer.getCurrentPosition();
                    } catch (IllegalStateException e) {
                        System.out.println(e);
                        mediaPlayer.reset();
                        currentPosition = mediaPlayer.getCurrentPosition();
                    }
                    seekBar.setProgress(currentPosition);
                }
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
                if(mediaPlayer != null && mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(mediaPlayer != null) {
                    isPlaying = true;
                    int position = seekBar.getProgress(); // 유저가 움직여 놓은 위치
                    mediaPlayer.seekTo(position);
                    mediaPlayer.start();
                    new MyThread().start();
                }
            }
        });

        reading = MainReading.readingUnit;
        readyForReading();
    }

    public void readyForReading() {
        FirebaseCrashlytics.getInstance().log("readingId : " + reading.getReadingId());
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

                        // 단어 오디오 재생
                        if(mediaPlayer != null ) {
                            playingPosition = mediaPlayer.getCurrentPosition();
                            mediaPlayer.pause();
                            setVisibility(View.VISIBLE, View.GONE);
                            isPlaying = false;
                        }
                        audioFileWord = unitId + "_" + popUpIndex + ".mp3";
                        playMediaPlayer.playAudioInByte(audiosWord.get(popUpIndex));
                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {   // 하이라이트 디자인 설정
                        ds.setColor(ContextCompat.getColor(context, R.color.PURPLE));
//                        ds.bgColor = ContextCompat.getColor(context, R.color.PURPLE_TRANSPARENT);
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
                finish();
                break;

            case R.id.btnCollect:
                String folder = "reading/" + MainReading.readingUnit.getReadingId().toLowerCase();

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
                // 최초 플레이 or 다시 플레이 시
                mediaPlayerManager = MediaPlayerManager.getInstance();
                if (playingPosition == null || mediaPlayer == null) {
                    StorageReference storageRef = storage.getReference().child("reading/"+unitId).child(unitId+".mp3");
                    storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            url = uri.toString();
                            mediaPlayerManager.setMediaPlayer(url);
                            playingTime = mediaPlayerManager.getDuration(); // 노래 재생시간
                            seekBar.setMax(playingTime);
                            mediaPlayerManager.playMediaPlayer(seekBar);
                            setVisibility(View.GONE, View.VISIBLE);
                        }
                    });

                    // 뭠췄다가 플레이 시
                } else {
                    mediaPlayerManager.playMediaPlayer(seekBar);
                    setVisibility(View.GONE, View.VISIBLE);
                }
                break;

            case R.id.btnPause :
                mediaPlayerManager.stopMediaPlayer();
                setVisibility(View.VISIBLE, View.GONE);
                break;

            case R.id.btnNormal :
                btnSlow.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_white_20_stroke_purple));
                btnNormal.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_purple_20_transparent));
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(1f));
                }
                break;

            case R.id.btnSlow :
                btnSlow.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_purple_20_transparent));
                btnNormal.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_white_20_stroke_purple));
                if(mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(0.8f));
                }
            break;

            case R.id.btnFinish :
                if(mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }

                setVisibility(View.VISIBLE, View.GONE);
                seekBar.setProgress(0);

                // 읽기완료 정보 업데이트 하기
                String readingId = reading.getReadingId();
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
            mediaPlayer = null;
        }
        setVisibility(View.VISIBLE, View.GONE);
    }

    public void setVisibility(int a, int b) {
        btnPlay.setVisibility(a);
        btnPause.setVisibility(b);
    }
}
