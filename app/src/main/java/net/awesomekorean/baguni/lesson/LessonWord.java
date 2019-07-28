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

public class LessonWord extends Fragment implements Button.OnClickListener {

    View view;

    Button btnAudio;
    Button btnNext;

    static TextView textViewInKorean;
    static TextView textViewInEnglish;

    public static int lessonCount = 0;
    public static int lessonLength = 0;

    public static String[] wordInKorean;
    public static String[] wordInEnglish;
    static String[] confusingWord;

    public static LessonWord newInstance() {
        return new LessonWord();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_word, container, false);

        MainLesson.lessonUnit = 1; // 빠른 테스트를 위해 추가함.

        ImageButton btn_audio = view.findViewById(R.id.btn_audio);
        btn_audio.setOnClickListener(this);

        textViewInKorean = view.findViewById(R.id.textViewInKorean);
        textViewInEnglish = view.findViewById(R.id.textViewInEnglish);

        switch (MainLesson.lessonUnit) {

            case 1:

                wordInKorean = Lesson1.wordInKorean;
                wordInEnglish = Lesson1.wordInEnglish;
                confusingWord = Lesson1.confusingWord;
                lessonLength = Lesson1.wordInKorean.length;

                displayWord();

        }

        return view;
    }


    public static void displayWord() {

        textViewInKorean.setText(wordInKorean[lessonCount]);
        textViewInEnglish.setText(wordInEnglish[lessonCount]);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_audio :
                break;

        }

    }

}
