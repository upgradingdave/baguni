package net.awesomekorean.baguni.lesson;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import net.awesomekorean.baguni.MainLesson;
import net.awesomekorean.baguni.R;
import net.awesomekorean.baguni.collection.CollectionRepository;

public class LessonWord extends Fragment implements Button.OnClickListener {

    View view;

    Lesson lesson;

    ImageButton btnAudio;
    Button btnCollect;

    static TextView textViewWordFront;
    static TextView textViewWordBack;

    public static int lessonCount = 0;
    public static int lessonWordLength = 0;
    public static int lessonSentenceLength = 0;
    public static int totalPageNo = 0;

    public static String[] wordFront;
    public static String[] wordBack;
    static String[] confusingWord;
    public static String[] sentenceFront;
    public static String[] sentenceBack;
    public static String[] sentenceExplain;


    public static LessonWord newInstance() {
        return new LessonWord();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_word, container, false);

        LessonFrame.swipePage = "lessonWord";

        btnAudio = view.findViewById(R.id.btnAudio);
        btnCollect = view.findViewById(R.id.btnCollect);
        btnAudio.setOnClickListener(this);
        btnCollect.setOnClickListener(this);


        textViewWordFront = view.findViewById(R.id.textViewWordFront);
        textViewWordBack = view.findViewById(R.id.textViewWordBack);

        switch (MainLesson.lessonUnit) {

            case 1:
                lesson = new Lesson1();
                readyForLesson();
                break;

        }

        return view;
    }

    public void readyForLesson() {

        wordFront = lesson.getWordFront();
        wordBack = lesson.getWordBack();
        confusingWord = lesson.getConfusingWord();

        sentenceFront = lesson.getSentenceFront();
        sentenceBack = lesson.getSentenceFront();
        sentenceExplain = lesson.getSentenceExplain();

        lessonWordLength = lesson.getWordFront().length;
        lessonSentenceLength = lesson.getSentenceFront().length;

        totalPageNo = lessonWordLength*4 + lessonSentenceLength +2;

        displayWord();
    }


    public static void displayWord() {

        textViewWordFront.setText(wordFront[lessonCount]);
        textViewWordBack.setText(wordBack[lessonCount]);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAudio :
                break;

            case R.id.btnCollect :
                break;

        }

    }

}
