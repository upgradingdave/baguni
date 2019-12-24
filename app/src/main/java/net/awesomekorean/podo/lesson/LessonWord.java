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

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.collection.CollectionRepository;
import net.awesomekorean.podo.lesson.lessons.Lesson0;
import net.awesomekorean.podo.lesson.lessons.Lesson1;
import net.awesomekorean.podo.lesson.lessons.Lesson2;

public class LessonWord extends Fragment implements Button.OnClickListener {

    View view;

    Lesson lesson;

    Button btnCollect;

    LinearLayout collectResult;

    static TextView tvWordFront;
    static TextView tvWordBack;
    static TextView tvWordPronunciation;
    static TextView tvWordSynonyms;
    static TextView tvWordAntonyms;
    static TextView tvWordApplication;
    static ImageView btnAudio;

    int generalLessonUnit;

    static int lessonCount;
    static int lessonWordLength = 0;
    static int lessonSentenceLength = 0;
    static int lessonClauseLength = 0;

    static String[] wordFront;
    static String[] wordBack;
    static String[] wordPronunciation;
    static String[] wordAntonyms;
    static String[] wordSynonyms;
    static String[] wordApplication;
    static String[] wordAudio;
    static String[] sentenceFront;
    static String[] sentenceBack;
    static String[] sentenceExplain;
    static String[] sentenceAudio;
    static String[] sentenceClause;
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
        tvWordApplication = view.findViewById(R.id.tvWordApplication);

        btnAudio.setOnClickListener(this);
        btnCollect.setOnClickListener(this);

        switch (MainLesson.lessonUnit) {


            // 어뎁터에 연결된 일반 레슨의 position 번호임. 번호가 맞는지 확인할 것
            case 1:
                lesson = new Lesson0();
                readyForLesson(0);
                break;

            case 2:
                lesson = new Lesson1();
                readyForLesson(1);
                break;

            case 3:
                lesson = new Lesson2();
                readyForLesson(2);
                break;


        }

        return view;
    }


    public void readyForLesson(int unit) {

        generalLessonUnit = unit;

        lessonWordLength = lesson.getWordFront().length;
        lessonSentenceLength = lesson.getSentenceFront().length;
        lessonClauseLength = lesson.getSentenceClause().length;

        String packageName = context.getPackageName();
        wordBack = new String[lessonWordLength];
        for(int i=0; i<lessonWordLength; i++) {
            String stringWordBack = "L" + generalLessonUnit + "_WORD_BACK_" + i;
            int intWordBack = getResources().getIdentifier(stringWordBack, "string", packageName);
            wordBack[i] = getString(intWordBack);
        }

        sentenceBack = new String[lessonSentenceLength];
        for(int i=0; i<lessonSentenceLength; i++) {
            String stringSentenceBack = "L" + generalLessonUnit + "_SENTENCE_BACK_" + i;
            int intSentenceBack = getResources().getIdentifier(stringSentenceBack, "string", packageName);
            sentenceBack[i] = getString(intSentenceBack);
        }

        sentenceExplain = new String[lessonSentenceLength];
        for(int i=0; i<lessonSentenceLength; i++) {
            String stringSentenceExplain = "L" + generalLessonUnit + "_SENTENCE_EXPLAIN_" + i;
            int intSentenceExplain = getResources().getIdentifier(stringSentenceExplain, "string", packageName);
            sentenceExplain[i] = getString(intSentenceExplain);
        }

        wordFront = lesson.getWordFront();
        wordPronunciation = lesson.getWordPronunciation();
        wordSynonyms = lesson.getWordSynonyms();
        wordAntonyms = lesson.getWordAntonyms();
        wordApplication = lesson.getWordApplication();

        sentenceFront = lesson.getSentenceFront();
        sentenceClause = lesson.getSentenceClause();
        peopleImage = lesson.getPeopleImage();




        LessonFrame.totalPageNo = lessonWordLength * 3 + 1 + lessonSentenceLength + 2;

        wordAudio = new String[lessonWordLength];
        for(int i=0; i<lessonWordLength; i++) {
            wordAudio[i] = "word_" + MainLesson.lessonUnit + "_" + i;
        }
        sentenceAudio = new String[lessonSentenceLength];
        for(int j=0; j<lessonSentenceLength; j++) {
            sentenceAudio[j] = "sentence_" + MainLesson.lessonUnit + "_" + j;
        }


            displayWord(context);
    }


    public void displayWord(Context context) {
        LessonFrame.progressCount();
        tvWordFront.setText(wordFront[lessonCount]);
        tvWordBack.setText(wordBack[lessonCount]);
        tvWordPronunciation.setText(wordPronunciation[lessonCount]);
        tvWordSynonyms.setText(wordSynonyms[lessonCount]);
        tvWordAntonyms.setText(wordAntonyms[lessonCount]);
        tvWordApplication.setText(wordApplication[lessonCount]);
        playAudioWithString.playAudio(context, wordAudio[lessonCount]);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAudio :
                String audioFile = wordAudio[lessonCount];
                playAudioWithString.playAudio(context, audioFile);
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
