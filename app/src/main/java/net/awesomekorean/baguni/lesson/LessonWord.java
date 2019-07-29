package net.awesomekorean.baguni.lesson;

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
import net.awesomekorean.baguni.Strings;

public class LessonWord extends Fragment implements Button.OnClickListener {

    View view;

    ImageButton btnAudio;

    static TextView textViewWordInKorean;
    static TextView textViewWordInEnglish;

    public static int lessonCount = 0;
    public static int lessonWordLength = 0;
    public static int lessonSentenceLength = 0;
    public static int totalPageNo = 0;

    public static String[] wordInKorean;
    public static String[] wordInEnglish;
    static String[] confusingWord;
    public static String[] sentenceInKorean;
    public static String[] sentenceInEnglish;
    public static String[] sentenceExplain;


    public static LessonWord newInstance() {
        return new LessonWord();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_word, container, false);

        LessonFrame.swipePage = Strings.LESSONWORD;

        MainLesson.lessonUnit = 1; // 빠른 테스트를 위해 추가함.

        btnAudio = view.findViewById(R.id.btnAudio);
        btnAudio.setOnClickListener(this);

        textViewWordInKorean = view.findViewById(R.id.textViewWordInKorean);
        textViewWordInEnglish = view.findViewById(R.id.textViewWordInEnglish);

        switch (MainLesson.lessonUnit) {

            case 1:

                wordInKorean = Lesson1.wordInKorean;
                wordInEnglish = Lesson1.wordInEnglish;
                confusingWord = Lesson1.confusingWord;

                sentenceInKorean = Lesson1.sentenceInKorean;
                sentenceInEnglish = Lesson1.sentenceInEnglish;
                sentenceExplain = Lesson1.sentenceExplain;

                lessonWordLength = Lesson1.wordInKorean.length;
                lessonSentenceLength = Lesson1.sentenceInKorean.length;

                totalPageNo = lessonWordLength*3 + lessonSentenceLength +1;

                displayWord();

        }

        return view;
    }


    public static void displayWord() {

        textViewWordInKorean.setText(wordInKorean[lessonCount]);
        textViewWordInEnglish.setText(wordInEnglish[lessonCount]);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAudio :
                break;

        }

    }

}
