package net.awesomekorean.podo.lesson.lessonHangul;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.awesomekorean.podo.AdsLoad;
import net.awesomekorean.podo.ConfirmQuit;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.PlayMediaPlayer;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.lesson.LessonAdapterChild;

import java.util.HashMap;
import java.util.Map;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class LessonHangul extends AppCompatActivity implements Button.OnClickListener {

    FirebaseStorage storage = FirebaseStorage.getInstance();

    Hangul thisHangul;

    ProgressBar progressBar;
    TextView progressTextView;

    LinearLayout loadingLayout;
    LinearLayout layoutHangul;
    TextView textViewHangul;
    TextView textViewHangulExplain;
    ConstraintLayout layoutIntro; // 인트로 버튼 눌렀을 때 뷰
    TextView textViewIntro; // 인트로 텍스트
    ImageView btnCloseIntro; // 인트로뷰 닫기 버튼

    ImageView imageViewHangul;

    String[] hangul;
    String[] hangulExplain;
    int hangulLength;
    String hangulIntro;
    String conVowBat;

    int currentHangul = 0;
    int writingBtnClicked = 0;
    int hintBtnClicked = 0;

    boolean[] checkProgress;

    ImageView btnAudio;
    LinearLayout btnWriting;
    LinearLayout btnHint;
    ImageView iconWriting;
    ImageView iconHint;
    Button btnIntro;
    ImageView btnClose;
    ScrollView scrollView;

    PlayMediaPlayer playMediaPlayer =  new PlayMediaPlayer();

    Context context;

    int resIDWriting;
    int resIDHint;

    Map<Integer, byte[]> audiosHangul = new HashMap<>();

    Intent intent;

    View swipeView;

    Animation animation1;
    Animation animation2;
    String stringLeft = "left";
    String stringRight = "right";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_hangul);

        context = getApplicationContext();

        loadingLayout = findViewById(R.id.loadingLayout);
        progressBar = findViewById(R.id.progressBar);
        progressTextView = findViewById(R.id.progressTextView);
        layoutHangul = findViewById(R.id.layoutHangul);
        textViewHangul = findViewById(R.id.textViewHangul);
        textViewHangulExplain = findViewById(R.id.textViewHangulExplain);
        layoutIntro = findViewById(R.id.layoutIntro);
        textViewIntro = findViewById(R.id.textViewIntro);
        btnCloseIntro = findViewById(R.id.btnCloseIntro);
        btnAudio = findViewById(R.id.btnAudio);
        btnWriting = findViewById(R.id.btnWriting);
        btnHint = findViewById(R.id.btnHint);
        iconWriting = findViewById(R.id.iconWriting);
        iconHint = findViewById(R.id.iconHint);
        btnIntro = findViewById(R.id.btnIntro);
        btnClose = findViewById(R.id.btnClose);
        scrollView = findViewById(R.id.svHangulExplain);
        swipeView = findViewById(R.id.swipeView);
        btnAudio.setOnClickListener(this);
        btnWriting.setOnClickListener(this);
        btnHint.setOnClickListener(this);
        btnIntro.setOnClickListener(this);
        btnCloseIntro.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        onLoadingLayout(true);

        intent = getIntent();
        String hangulName = intent.getExtras().getString("conVowBat").toLowerCase();

        switch (hangulName) {

            case "consonant" :
                thisHangul = new LessonHangulConsonant();
                getThisHangul("con", hangulName);
                break;

            case "vowel" :
                thisHangul = new LessonHangulVowel();
                getThisHangul("vow", hangulName);
                break;

            case "batchim" :
                thisHangul = new LessonHangulBatchim();
                getThisHangul("bat", hangulName);
                break;
        }

        View.OnTouchListener swipeListener = new HangulSwipeListener(this) {
            @Override
            public void onSwipeRight() {
                if(currentHangul == 0) {
                    currentHangul = hangul.length-1;
                } else {
                    currentHangul--;
                }
                swipeAnimationStart(stringRight);
            }

            @Override
            public void onSwipeLeft() {
                if(currentHangul == hangul.length-1) {
                    currentHangul = 0;
                } else {
                    currentHangul++;
                }
                swipeAnimationStart(stringLeft);
            }
        };

        swipeView.setOnTouchListener(swipeListener);
        scrollView.setOnTouchListener(swipeListener);

        setHintIcons();
    }

    // 스와이프 애니메이션 실행
    public void swipeAnimationStart(final String swipeDirection) {
        if(swipeDirection == stringLeft) {
            animation1 = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.move_left1);
            animation2 = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.move_left2);

        }else {
            animation1 = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.move_right1);
            animation2 = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.move_right2);
        }

        layoutHangul.startAnimation(animation1);

        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                setHangul();
                setHintIcons();
                if(imageViewHangul != null) {
                    visible(VISIBLE, GONE);
                }
                layoutHangul.startAnimation(animation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }


    public void getThisHangul(String comVowBat, String hangulName) {

        conVowBat = comVowBat;
        hangul = thisHangul.getHangul();
        hangulExplain = thisHangul.getHangulExplain();
        hangulLength = hangul.length;
        hangulIntro = thisHangul.getHangulIntro();
        textViewIntro.setText(hangulIntro);
        textViewIntro.setMovementMethod(new ScrollingMovementMethod());

        checkProgress = new boolean[hangulLength];

        // 한글오디오 불러오기
        String[] hangulAudio = new String[hangulLength];
        String folder = "hangul/" + conVowBat;

        for(int i=0; i<hangulLength; i++) {
            final Integer audioIndexHangul = i;
            hangulAudio[i] = hangulName + "_" + i + ".mp3";
            StorageReference storageRef = storage.getReference().child(folder).child(hangulAudio[i]);
            final long ONE_MEGABYTE = 1024 * 1024;
            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    System.out.println("오디오를 로드했습니다.");
                    audiosHangul.put(audioIndexHangul, bytes);
                    if(audioIndexHangul == 0) {
                        setHangul();
                        onLoadingLayout(false);
                    }
                }
            });
        }
    }


    public void setHangul() {

        checkProgress[currentHangul] = true;

        int count = 0;

        for(int i=0; i<hangulLength; i++) {

            if(checkProgress[i]) {

                count++;
            }
        }

        progressBar.setProgress(count * 100 /hangulLength);

        textViewHangul.setText(hangul[currentHangul]);

        textViewHangulExplain.setText(hangulExplain[currentHangul]);

        progressTextView.setText(count + "/" + hangulLength);

        if(audiosHangul.get(currentHangul) != null && audiosHangul.get(currentHangul).length > 0) {

            playMediaPlayer.playAudioInByte(audiosHangul.get(currentHangul));
        }
    }


    // writing 이랑 hint 아이콘 설정하기
    private void setHintIcons() {
        String packName = getApplicationContext().getPackageName();
        String resNameWriting = "@drawable/w" + conVowBat + currentHangul;
        resIDWriting = getResources().getIdentifier(resNameWriting, "drawable", packName);

        System.out.println("NAME:"+resNameWriting);

        if(resIDWriting == 0) {
            iconWriting.setImageResource(R.drawable.hangul_writing_grey);
            btnWriting.setEnabled(false);
        } else {
            iconWriting.setImageResource(R.drawable.hangul_writing);
            btnWriting.setEnabled(true);
        }


        String resNameHint = "@drawable/h" + conVowBat + currentHangul;
        resIDHint = getResources().getIdentifier(resNameHint, "drawable", packName);

        if(resIDHint == 0) {
            iconHint.setImageResource(R.drawable.hint_grey);
            btnHint.setEnabled(false);
        } else {
            iconHint.setImageResource(R.drawable.hint);
            btnHint.setEnabled(true);
        }
    }

    private void visible(int textView, int imageView) {
        imageViewHangul.setVisibility(imageView);
        textViewHangul.setVisibility(textView);
    }


    private void onLoadingLayout(boolean b) {
        if(b) {
            loadingLayout.setVisibility(View.VISIBLE);
        } else {
            loadingLayout.setVisibility(View.GONE);
        }
    }


    public void openConfirmQuit() {

        // 오디오 끄기

        Intent intent = new Intent(context, ConfirmQuit.class);

        intent.putExtra("isHangul", true);

        intent.putExtra("progress", progressBar.getProgress());

        startActivityForResult(intent, 200);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnAudio :
                playMediaPlayer.playAudioInByte(audiosHangul.get(currentHangul));
                break;

            case R.id.btnWriting :
                if(writingBtnClicked == 0) {
                    imageViewHangul = findViewById(R.id.imageViewHangul);
                    imageViewHangul.setImageResource(resIDWriting);

                    visible(GONE, VISIBLE);
                    writingBtnClicked = 1;
                    hintBtnClicked = 0;

                } else {
                    visible(VISIBLE, GONE);
                    writingBtnClicked = 0;
                }
                break;

            case R.id.btnHint :
                if(hintBtnClicked == 0) {
                    imageViewHangul = findViewById(R.id.imageViewHangul);
                    imageViewHangul.setImageResource(resIDHint);

                    visible(GONE, VISIBLE);
                    hintBtnClicked = 1;
                    writingBtnClicked = 0;

                } else {
                    visible(VISIBLE, GONE);
                    hintBtnClicked = 0;
                }
                break;

            case R.id.btnIntro :
                layoutIntro.setVisibility(VISIBLE);
                break;

            case R.id.btnCloseIntro :
                layoutIntro.setVisibility(GONE);
                break;


            case R.id.btnClose :
                openConfirmQuit();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            finish();
        }
    }


    @Override
    public void onBackPressed() {
        openConfirmQuit();
    }
}
