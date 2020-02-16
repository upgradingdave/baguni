package net.awesomekorean.podo.lesson;

import android.content.Context;
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

import net.awesomekorean.podo.PlayAudioWithString;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.collection.CollectionRepository;

public class LessonWord extends Fragment implements Button.OnClickListener {

    View view;

    Lesson lesson;
    String lessonId;

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
    static String[] sentenceFront;
    static String[] sentenceBack;
    static String[] sentenceExplain;
    static String[] sentenceAudio;
    static String[] dialog;
    static String[] dialogAudio;
    static int[] peopleImage;

    Context context;
    PlayAudioWithString playAudioWithString = new PlayAudioWithString();


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
        readyForLesson();

        return view;
    }


    public void readyForLesson() {

        lessonWordLength = lesson.getWordFront().length;
        lessonSentenceLength = lesson.getSentenceFront().length;
        lessonDialogLength = lesson.getDialog().length;

        String packageName = context.getPackageName();
        wordBack = new String[lessonWordLength];
        for(int i=0; i<lessonWordLength; i++) {
            String stringWordBack = lessonId + "_WORD_BACK_" + i;
            int intWordBack = getResources().getIdentifier(stringWordBack, "string", packageName);
            wordBack[i] = getString(intWordBack);
        }

        sentenceBack = new String[lessonSentenceLength];
        for(int i=0; i<lessonSentenceLength; i++) {
            String stringSentenceBack = lessonId + "_SENTENCE_BACK_" + i;
            int intSentenceBack = getResources().getIdentifier(stringSentenceBack, "string", packageName);
            sentenceBack[i] = getString(intSentenceBack);
        }

        sentenceExplain = new String[lessonSentenceLength];
        for(int i=0; i<lessonSentenceLength; i++) {
            String stringSentenceExplain = lessonId + "_SENTENCE_EXPLAIN_" + i;
            int intSentenceExplain = getResources().getIdentifier(stringSentenceExplain, "string", packageName);
            sentenceExplain[i] = getString(intSentenceExplain);
        }

        wordFront = lesson.getWordFront();
        wordPronunciation = lesson.getWordPronunciation();
        wordSynonyms = lesson.getWordSynonyms();
        wordAntonyms = lesson.getWordAntonyms();

        sentenceFront = lesson.getSentenceFront();
        dialog = lesson.getDialog();
        peopleImage = lesson.getPeopleImage();


        LessonFrame.totalPageNo = lessonWordLength * 3 + 1 + lessonSentenceLength + 2;

        wordAudio = new String[lessonWordLength];
        for(int i=0; i<lessonWordLength; i++) {
            wordAudio[i] = lessonId.toLowerCase() + "_word_" + i + ".mp3";
        }
        sentenceAudio = new String[lessonSentenceLength];
        for(int j=0; j<lessonSentenceLength; j++) {
            sentenceAudio[j] = lessonId.toLowerCase() + "_sentence_" + j + ".mp3";
        }
        dialogAudio = new String[lessonDialogLength];
        for(int k=0; k<lessonDialogLength; k++) {
            dialogAudio[k] = lessonId.toLowerCase() + "_dialog_" + k + ".mp3";
        }

        displayWord();
    }


    public void displayWord() {
        LessonFrame.progressCount();
        tvWordFront.setText(wordFront[lessonCount]);
        tvWordBack.setText(wordBack[lessonCount]);
        tvWordPronunciation.setText(wordPronunciation[lessonCount]);
        tvWordSynonyms.setText(wordSynonyms[lessonCount]);
        tvWordAntonyms.setText(wordAntonyms[lessonCount]);
        playAudioWithString.playAudioLesson(wordAudio[lessonCount], MainLesson.lessonUnit.getLessonId().toLowerCase());
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAudio :
                String audioFile = wordAudio[lessonCount];
                playAudioWithString.repeatAudio();
                break;

            case R.id.btnCollect :
                String front = wordFront[lessonCount];
                String back = wordBack[lessonCount];
                String audio = wordAudio[lessonCount];

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
