package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.awesomekorean.podo.ConfirmQuit;
import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.MediaPlayerManager;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SettingStatusBar;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UnlockActivity;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.lesson.lessons.Lesson;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import org.w3c.dom.ls.LSException;

import java.util.HashMap;
import java.util.Map;

public class LessonFrame extends AppCompatActivity implements View.OnClickListener {

    FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseCrashlytics crashlytics = FirebaseCrashlytics.getInstance();

    static Lesson lesson;

    public static int progressCount;
    public static int totalPageNo = 0;

    public static ProgressBar progressBar;
    public static TextView progressTextView;

    ImageView btnClose;

    public static TextView navigationWord;
    public static TextView navigationQuiz;
    public static TextView navigationSentence;
    public static TextView navigationDialog;

    private GestureDetectorCompat gestureDetectorCompat = null;
    public static String swipePage;

    FragmentManager fm;
    FragmentTransaction ft;
    LessonWord lessonWord = LessonWord.newInstance();

    LinearLayout loadingLayout;

    Context context;

    static int[] wordImage;

    static String[] wordAudioString;

    static Map<Integer, byte[]> wordAudioByte = new HashMap<>();

    LessonSwipeListener gestureListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_frame);

        context = getApplicationContext();

        gestureListener = new LessonSwipeListener();
        gestureListener.setActivity(this);
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);

        navigationWord = findViewById(R.id.navigationWord);
        navigationQuiz = findViewById(R.id.navigationQuiz);
        navigationSentence = findViewById(R.id.navigationSentence);
        navigationDialog = findViewById(R.id.navigationDialog);
        navigationWord.setOnClickListener(this);
        navigationQuiz.setOnClickListener(this);
        navigationSentence.setOnClickListener(this);
        navigationDialog.setOnClickListener(this);

        progressCount = 1;
        progressBar = findViewById(R.id.progressBar);
        progressTextView = findViewById(R.id.progressTextView);
        btnClose = findViewById(R.id.btnClose);
        loadingLayout = findViewById(R.id.loadingLayout);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfirmQuit();
            }
        });

        lesson = (Lesson) getIntent().getSerializableExtra(getResources().getString(R.string.LESSON));

        crashlytics.log("lessonId : " + lesson.getLessonId());

        totalPageNo = lesson.getWordFront().length * 3 + 1 + lesson.getSentenceFront().length + 2;

        readyForImageAndAudio();
    }

    // DB에서 레슨 오디오랑 이미지 불러오기
    private void readyForImageAndAudio() {

        onLoadingLayout(true);

        int lessonWordLength = lesson.getWordFront().length;

        String lessonId = lesson.getLessonId();

        String folder = "lesson/" + lessonId.toLowerCase();

        wordImage = new int[lessonWordLength];


        for(int i=0; i<lessonWordLength; i++) {

            String stringWordImage = lessonId.toLowerCase() + "_word_" + i;

            int intWordImage = getResources().getIdentifier(stringWordImage, "drawable", getPackageName());

            wordImage[i] = intWordImage;
        }


        wordAudioString = new String[lessonWordLength];

        for(int i=0; i<lessonWordLength; i++) {

            final Integer index = i;

            wordAudioString[i] = lessonId.toLowerCase() + "_word_" + i + ".mp3";

            StorageReference storageRef = storage.getReference().child(folder).child(wordAudioString[i]);

            final long ONE_MEGABYTE = 1024 * 1024;

            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {

                    System.out.println("오디오를 로드했습니다.");

                    wordAudioByte.put(index, bytes);

                    if(index == 0) {

                        onLoadingLayout(false);

                        replaceFragment(lessonWord);
                    }
                }
            });
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Pass activity on touch event to the gesture detector
        gestureDetectorCompat.onTouchEvent(event);
        return true;
    }


    public void replaceFragment(Fragment fragment) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.lessonFrame, fragment);
        ft.commitAllowingStateLoss();
    }




    // 프로그레스바 및 카운트 표시, true:다음, false:이전
    public static void progressCount() {
        progressBar.setProgress(progressCount*100/totalPageNo);
        progressTextView.setText(progressCount + " / " + totalPageNo);
    }


    public void onLoadingLayout(boolean b) {
        if(b) {
            loadingLayout.setVisibility(View.VISIBLE);
        } else {
            loadingLayout.setVisibility(View.GONE);
        }
    }


    public void openConfirmQuit() {

        Intent intent = new Intent(context, ConfirmQuit.class);

        intent.putExtra(getResources().getString(R.string.PROGRESS), progressBar.getProgress());

        intent.putExtra(getResources().getString(R.string.LESSON_ID), lesson.getLessonId());

        startActivityForResult(intent, 200);
    }


    // 네비게이션 배경색 변경
    public static void setNavigationColor(Context context, TextView textView, int bg) {

        navigationWord.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_grey_light_10));
        navigationQuiz.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_grey_light_10));
        navigationSentence.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_grey_light_10));
        navigationDialog.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_grey_light_10));

        textView.setBackground(ContextCompat.getDrawable(context, bg));
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


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.navigationWord :
                setNavigationColor(context, navigationWord, R.drawable.bg_yellow_10);
                replaceFragment(LessonWord.newInstance());
                progressCount = 1;
                progressCount();
                break;

            case R.id.navigationQuiz :
                setNavigationColor(context, navigationQuiz, R.drawable.bg_green_10);
                replaceFragment(LessonWordQuiz1.newInstance());
                progressCount = 1 + lesson.getWordFront().length;
                progressCount();
                break;

            case R.id.navigationSentence :
                setNavigationColor(context, navigationSentence, R.drawable.bg_blue_10);
                replaceFragment(LessonSentence.newInstance());
                progressCount = 1 + lesson.getWordFront().length * 3 + 1;
                progressCount();
                break;

            case R.id.navigationDialog :
                setNavigationColor(context, navigationDialog, R.drawable.bg_purple_10);
                replaceFragment(LessonDialog.newInstance());
                progressCount = 1 + lesson.getWordFront().length * 3 + 1 + lesson.getSentenceFront().length;
                progressCount();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        openConfirmQuit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        crashlytics.log("LessonFrame Destroy!!");
        MediaPlayerManager mediaPlayerManager = MediaPlayerManager.getInstance();
        mediaPlayerManager.releaseMediaPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        crashlytics.log("LessonFrame Pause!!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        crashlytics.log("LessonFrame Stop!!");
    }
}

