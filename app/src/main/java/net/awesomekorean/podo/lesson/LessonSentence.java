package net.awesomekorean.podo.lesson;

import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.awesomekorean.podo.DownloadAudio;
import net.awesomekorean.podo.PlayMediaPlayer;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.collection.CollectionRepository;

import java.util.HashMap;
import java.util.Map;

import static net.awesomekorean.podo.lesson.LessonWord.lessonCount;

public class LessonSentence extends Fragment implements Button.OnClickListener {

    FirebaseStorage storage = FirebaseStorage.getInstance();

    View view;

    ImageView btnAudio;
    Button btnCollect;

    static TextView tvSentenceFront;
    static TextView tvSentenceBack;
    static TextView tvSentenceExplain;

    static String[] sentenceFront;
    static String[] sentenceBack;
    static String[] sentenceExplain;
    String[] sentenceAudio;
    static Map<Integer, byte[]> audiosSentence;


    static PlayMediaPlayer playMediaPlayer = new PlayMediaPlayer();

    LinearLayout collectResult;

    boolean isFirstAudio = true;



    // fragment 간 전환을 위해 만듦
    public static LessonSentence newInstance() {
        return new LessonSentence();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_sentence, container, false);

        LessonFrame.swipePage = getString(R.string.LESSON_SENTENCE);

        tvSentenceFront = view.findViewById(R.id.tvSentenceFront);
        tvSentenceBack = view.findViewById(R.id.tvSentenceBack);
        tvSentenceExplain = view.findViewById(R.id.tvSentenceExplain);
        tvSentenceExplain.setMovementMethod(new ScrollingMovementMethod());
        collectResult = view.findViewById(R.id.collectResult);
        btnAudio = view.findViewById(R.id.btnAudio);
        btnCollect = view.findViewById(R.id.btnCollect);
        btnAudio.setOnClickListener(this);
        btnCollect.setOnClickListener(this);

        readyForLesson();

        return view;
    }

    private void readyForLesson() {
        int sentenceLength = LessonWord.lessonSentenceLength;
        sentenceAudio = new String[sentenceLength];
        String lessonId = LessonWord.lessonId;
        String folder = LessonWord.folder;
        String packageName = getContext().getPackageName();

        sentenceFront = LessonWord.lesson.getSentenceFront();

        sentenceBack = new String[sentenceLength];
        for(int i=0; i<sentenceLength; i++) {
            String stringSentenceBack = lessonId + "_SENTENCE_BACK_" + i;
            int intSentenceBack = getResources().getIdentifier(stringSentenceBack, "string", packageName);
            sentenceBack[i] = getString(intSentenceBack);
        }

        sentenceExplain = new String[sentenceLength];
        for(int i=0; i<sentenceLength; i++) {
            String stringSentenceExplain = lessonId + "_SENTENCE_EXPLAIN_" + i;
            int intSentenceExplain = getResources().getIdentifier(stringSentenceExplain, "string", packageName);
            sentenceExplain[i] = getString(intSentenceExplain);
        }

        audiosSentence = new HashMap<>();
        for(int i=0; i<sentenceLength; i++) {
            final Integer audioIndexSentence = i;
            sentenceAudio[i] = lessonId.toLowerCase() + "_sentence_" + i + ".mp3";
            StorageReference storageRef = storage.getReference().child(folder).child(sentenceAudio[i]);
            final long ONE_MEGABYTE = 1024 * 1024;
            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    System.out.println("오디오를 로드했습니다.");
                    audiosSentence.put(audioIndexSentence, bytes);
                    if(audioIndexSentence == 0) {
                        displaySentence();
                        isFirstAudio = false;
                    }
                }
            });
        }
    }

    public static void displaySentence() {
        tvSentenceFront.setText(sentenceFront[lessonCount]);
        tvSentenceBack.setText(sentenceBack[lessonCount]);
        tvSentenceExplain.setText(sentenceExplain[lessonCount]);
        playMediaPlayer.playAudioInByte(audiosSentence.get(lessonCount));
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnAudio :
                playMediaPlayer.playAudioInByte(audiosSentence.get(lessonCount));
                break;

            case R.id.btnCollect :
                String front = sentenceFront[lessonCount];
                String back = sentenceBack[lessonCount];
                String audio = sentenceAudio[lessonCount];
                String folder = "lesson/" + MainLesson.lessonUnit.getLessonId().toLowerCase();;

                DownloadAudio downloadAudio = new DownloadAudio(getContext(), folder, audio);
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
