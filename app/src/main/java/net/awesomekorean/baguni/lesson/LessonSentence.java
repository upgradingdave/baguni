package net.awesomekorean.baguni.lesson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.awesomekorean.baguni.R;

import static net.awesomekorean.baguni.lesson.LessonWord.lessonCount;

public class LessonSentence extends Fragment implements Button.OnClickListener {

    View view;

    static TextView textViewSentenceFront;
    static TextView textViewSentenceBack;
    static TextView textViewSentenceExplain;


        // fragment 간 전환을 위해 만듦
    public static LessonSentence newInstance() {
        return new LessonSentence();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_sentence, container, false);

        LessonFrame.swipePage = "lessonSentence";

        textViewSentenceFront = view.findViewById(R.id.textViewSentenceFront);
        textViewSentenceBack = view.findViewById(R.id.textViewSentenceBack);
        textViewSentenceExplain = view.findViewById(R.id.textViewSentenceExplain);

        displaySentence();

        return view;

    }

    public static void displaySentence() {

        textViewSentenceFront.setText(LessonWord.sentenceFront[lessonCount]);
        textViewSentenceBack.setText(LessonWord.sentenceBack[lessonCount]);
        textViewSentenceExplain.setText(LessonWord.sentenceExplain[lessonCount]);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnAudio :
                break;

            case R.id.btnCollect :
                break;

            case R.id.btnQnA :
                break;
        }

    }
}
