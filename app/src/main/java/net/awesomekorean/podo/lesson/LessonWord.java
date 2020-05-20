package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.os.Handler;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.awesomekorean.podo.DownloadAudio;
import net.awesomekorean.podo.MediaPlayerManager;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.collection.CollectionRepository;
import net.awesomekorean.podo.lesson.lessons.Lesson;

import java.util.HashMap;
import java.util.Map;


public class LessonWord extends Fragment implements Button.OnClickListener{

    View view;

    Lesson lesson;
    String lessonId;
    String folder;

    Button btnCollect;

    LinearLayout collectResult;

    static View viewLeft;
    static View viewRight;
    static LinearLayout lessonLayout;
    static ImageView ivWordImage;
    static TextView tvWordFront;
    static TextView tvWordBack;
    static TextView tvWordPronunciation;
    static ImageView btnAudio;

    static int lessonCount;
    int lessonWordLength = 0;

    static String[] wordFront;
    static String[] wordBack;
    static String[] wordPronunciation;

    public static LessonWord newInstance() {
        return new LessonWord();
    }

    private GestureDetectorCompat gestureDetectorCompat = null;

    MediaPlayerManager mediaPlayerManager;

    private LessonFrame activity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_word, container, false);

        activity.swipePage = getString(R.string.LESSONWORD);
        lessonCount = 0; // 레슨진도초기화

        viewLeft = view.findViewById(R.id.viewLeft);
        viewRight = view.findViewById(R.id.viewRight);
        lessonLayout = view.findViewById(R.id.lessonLayout);
        btnAudio = view.findViewById(R.id.btnAudio);
        btnCollect = view.findViewById(R.id.btnCollect);
        collectResult = view.findViewById(R.id.collectResult);
        ivWordImage = view.findViewById(R.id.ivWordImage);
        tvWordFront = view.findViewById(R.id.tvWordFront);
        tvWordBack = view.findViewById(R.id.tvWordBack);
        tvWordPronunciation = view.findViewById(R.id.tvWordPronunciation);
        btnAudio.setOnClickListener(this);
        btnCollect.setOnClickListener(this);

        lesson = activity.lesson;

        lessonId = lesson.getLessonId();

        folder = "lesson/" + lessonId.toLowerCase();

        // analytics 로그 이벤트 얻기
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(activity);
        Bundle bundle = new Bundle();
        firebaseAnalytics.logEvent("lesson_word", bundle);

        LessonSwipeListener gestureListener = new LessonSwipeListener();

        gestureListener.setActivity(activity);

        gestureDetectorCompat = new GestureDetectorCompat(activity, gestureListener);

        lessonLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetectorCompat.onTouchEvent(event);
                return false;
            }
        });

        readyForLesson();

        activity.setNavigationColor(activity, activity.navigationWord, R.drawable.bg_yellow_10);

        return view;
    }

    public void readyForLesson() {

        lessonWordLength = lesson.getWordFront().length;

        wordFront = lesson.getWordFront();

        wordBack = lesson.getWordBack();

        wordPronunciation = lesson.getWordPronunciation();

        displayWord();
    }


    public void displayWord() {

        LessonFrame.progressCount();

        ivWordImage.setImageResource(LessonFrame.wordImage[lessonCount]);

        tvWordFront.setText(wordFront[lessonCount]);

        tvWordBack.setText(wordBack[lessonCount]);

        tvWordPronunciation.setText(wordPronunciation[lessonCount]);

        if(LessonFrame.wordAudioByte.get(lessonCount) != null && LessonFrame.wordAudioByte.get(lessonCount).length > 0) {

            mediaPlayerManager = MediaPlayerManager.getInstance();

            if(mediaPlayerManager != null) {

                mediaPlayerManager.stopMediaPlayer();
            }

            mediaPlayerManager.setMediaPlayerByte(false, LessonFrame.wordAudioByte.get(lessonCount));
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAudio :
                if(mediaPlayerManager != null) {
                    mediaPlayerManager.playMediaPlayer(false);
                }
                break;

            case R.id.btnCollect :
                String front = lesson.getWordFront()[lessonCount];
                String back = lesson.getWordBack()[lessonCount];
                String audio = activity.wordAudioString[lessonCount];

                DownloadAudio downloadAudio = new DownloadAudio(activity, folder, audio);
                downloadAudio.downloadAudio();

                CollectionRepository repository = new CollectionRepository(activity);
                repository.insert(front, back, audio);

                collectResult.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        collectResult.setVisibility(View.GONE);
                    }
                }, 1000);

                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof LessonFrame) {
            activity = (LessonFrame) context;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
    }
}
