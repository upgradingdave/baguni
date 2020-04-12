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

import net.awesomekorean.podo.AdsLoad;
import net.awesomekorean.podo.DownloadAudio;
import net.awesomekorean.podo.PlayMediaPlayer;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.collection.CollectionRepository;
import net.awesomekorean.podo.lesson.lessons.Lesson;

import java.util.HashMap;
import java.util.Map;


public class LessonWord extends Fragment implements Button.OnClickListener{

    FirebaseStorage storage = FirebaseStorage.getInstance();


    View view;

    static Lesson lesson;
    static String lessonId;
    static String folder;

    Button btnCollect;

    LinearLayout collectResult;

    static View viewLeft;
    static View viewRight;
    static LinearLayout lessonLayout;
    static TextView tvWordFront;
    static TextView tvWordBack;
    static TextView tvWordPronunciation;
    static TextView tvWordSynonyms;
    static TextView tvWordAntonyms;
    static ImageView btnAudio;

    static int lessonCount;
    static int lessonWordLength = 0;
    static int lessonSentenceLength = 0;
    static int lessonDialogLength = 0;

    static String[] wordFront;
    static String[] wordBack;
    static String[] wordPronunciation;
    static String[] wordAntonyms;
    static String[] wordSynonyms;
    static String[] wordAudio;

    Context context;
    PlayMediaPlayer playMediaPlayer = PlayMediaPlayer.getInstance();

    static Map<Integer, byte[]> audiosWord = new HashMap<>();

    boolean isFirstAudio;

    public static LessonWord newInstance() {
        return new LessonWord();
    }

    private GestureDetectorCompat gestureDetectorCompat = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_word, container, false);
        context = getContext();

        LessonFrame.swipePage = getString(R.string.LESSONWORD);
        lessonCount = 0; // 레슨진도초기화 -> 저장하고 exit 했을 때는 lessonCount 를 DB에 저장해야함

        viewLeft = view.findViewById(R.id.viewLeft);
        viewRight = view.findViewById(R.id.viewRight);
        lessonLayout = view.findViewById(R.id.lessonLayout);
        btnAudio = view.findViewById(R.id.btnAudio);
        btnCollect = view.findViewById(R.id.btnCollect);
        collectResult = view.findViewById(R.id.collectResult);
        tvWordFront = view.findViewById(R.id.tvWordFront);
        tvWordBack = view.findViewById(R.id.tvWordBack);
        tvWordPronunciation = view.findViewById(R.id.tvWordPronunciation);
        tvWordSynonyms = view.findViewById(R.id.tvWordSynonyms);
        tvWordAntonyms = view.findViewById(R.id.tvWordAntonyms);
        btnAudio.setOnClickListener(this);
        btnCollect.setOnClickListener(this);

        // analytics 로그 이벤트 얻기
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
        Bundle bundle = new Bundle();
        firebaseAnalytics.logEvent("lesson_word", bundle);


        lesson = (Lesson) LessonAdapterChild.lessonItem;

        lessonId = LessonAdapterChild.lessonItem.getLessonId();
        FirebaseCrashlytics.getInstance().log("lessonId : " + lessonId);

        folder = "lesson/" + lessonId.toLowerCase();

        LessonSwipeListener gestureListener = new LessonSwipeListener();
        LessonFrame lessonFrame = (LessonFrame)getActivity();
        gestureListener.setActivity(lessonFrame);
        gestureDetectorCompat = new GestureDetectorCompat(lessonFrame.getApplicationContext(), gestureListener);
        lessonLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetectorCompat.onTouchEvent(event);
                return false;
            }
        });

        readyForLesson();

        return view;
    }

    public void readyForLesson() {
        if(getActivity() != null) {
            ((LessonFrame)getActivity()).onLoadingLayout(true);
        }

        lessonWordLength = lesson.getWordFront().length;
        lessonSentenceLength = lesson.getSentenceFront().length;
        lessonDialogLength = lesson.getDialog().length;

        LessonFrame.totalPageNo = lessonWordLength * 3 + 1 + lessonSentenceLength + 2;

        String packageName = context.getPackageName();

        wordFront = lesson.getWordFront();

        wordBack = new String[lessonWordLength];
        for(int i=0; i<lessonWordLength; i++) {
            String stringWordBack = lessonId + "_WORD_BACK_" + i;
            int intWordBack = getResources().getIdentifier(stringWordBack, "string", packageName);
            wordBack[i] = getString(intWordBack);
        }

        wordPronunciation = lesson.getWordPronunciation();
        wordSynonyms = lesson.getWordSynonyms();
        wordAntonyms = lesson.getWordAntonyms();

        wordAudio = new String[lessonWordLength];
        for(int i=0; i<lessonWordLength; i++) {
            final Integer audioIndexWord = i;
            wordAudio[i] = lessonId.toLowerCase() + "_word_" + i + ".mp3";
            StorageReference storageRef = storage.getReference().child(folder).child(wordAudio[i]);
            final long ONE_MEGABYTE = 1024 * 1024;
            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    System.out.println("오디오를 로드했습니다.");
                    audiosWord.put(audioIndexWord, bytes);
                    if(audioIndexWord == 0) {
                        displayWord();
                        isFirstAudio = false;
                    }
                }
            });
        }
    }


    public void displayWord() {
        if(getActivity() != null) {
            ((LessonFrame)getActivity()).onLoadingLayout(false);
        }
        LessonFrame.progressCount();
        tvWordFront.setText(wordFront[lessonCount]);
        tvWordBack.setText(wordBack[lessonCount]);
        tvWordPronunciation.setText(wordPronunciation[lessonCount]);
        tvWordSynonyms.setText(wordSynonyms[lessonCount]);
        tvWordAntonyms.setText(wordAntonyms[lessonCount]);
        if(audiosWord.get(lessonCount) != null && audiosWord.get(lessonCount).length > 0) {
            playMediaPlayer.playAudioInByte(audiosWord.get(lessonCount));
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAudio :
                playMediaPlayer.playAudioInByte(audiosWord.get(lessonCount));
                break;

            case R.id.btnCollect :
                String front = wordFront[lessonCount];
                String back = wordBack[lessonCount];
                String audio = wordAudio[lessonCount];

                DownloadAudio downloadAudio = new DownloadAudio(context, folder, audio);
                downloadAudio.downloadAudio();

                CollectionRepository repository = new CollectionRepository(getContext());
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
}
