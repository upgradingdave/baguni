package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.awesomekorean.podo.PlayAudioWithString;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.collection.CollectionRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LessonWord extends Fragment implements Button.OnClickListener {

    FirebaseStorage storage = FirebaseStorage.getInstance();

    View view;

    static Lesson lesson;
    static String lessonId;
    static String folder;

    Button btnCollect;

    LinearLayout collectResult;

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
    PlayAudioWithString playAudioWithString = PlayAudioWithString.getInstance();

    final long ONE_MEGABYTE = 1024 * 1024;

    static Map<Integer, byte[]> audiosWord = new HashMap<>();

    String dir;

    boolean isFirstAudio;


    public static LessonWord newInstance() {
        return new LessonWord();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_word, container, false);
        context = getContext();

        LessonFrame.swipePage = getString(R.string.LESSONWORD);
        lessonCount = 0; // 레슨진도초기화 -> 저장하고 exit 했을 때는 lessonCount 를 DB에 저장해야함

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

        lesson = (Lesson) MainLesson.lessonUnit;
        lessonId = MainLesson.lessonUnit.getLessonId();
        folder = "lesson/" + lessonId.toLowerCase();
        dir = "podo_collection";

        readyForLesson();

        return view;
    }


    public void readyForLesson() {

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
        LessonFrame.progressCount();
        tvWordFront.setText(wordFront[lessonCount]);
        tvWordBack.setText(wordBack[lessonCount]);
        tvWordPronunciation.setText(wordPronunciation[lessonCount]);
        tvWordSynonyms.setText(wordSynonyms[lessonCount]);
        tvWordAntonyms.setText(wordAntonyms[lessonCount]);
        if(audiosWord.get(lessonCount) != null && audiosWord.get(lessonCount).length > 0) {
            playAudioWithString.playAudioInByte(audiosWord.get(lessonCount));
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAudio :
                playAudioWithString.playAudioInByte(audiosWord.get(lessonCount));
                break;

            case R.id.btnCollect :
                String front = wordFront[lessonCount];
                String back = wordBack[lessonCount];
                String audio = wordAudio[lessonCount];

                // 오디오파일 다운로드 하기
                StorageReference storageRef = storage.getReference().child(folder).child(wordAudio[lessonCount]);

                try {
                    final File localFile = File.createTempFile(dir, wordAudio[lessonCount]);

                    storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            System.out.println("오디오파일 다운로드를 성공 했습니다.: " + localFile.getPath());
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println("오디오파일 다운로드를 실패 했습니다.: "+e);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


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
