package net.awesomekorean.baguni.lesson;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.awesomekorean.baguni.R;
import net.awesomekorean.baguni.collection.CollectionRepository;

import static net.awesomekorean.baguni.lesson.LessonWord.lessonCount;

public class LessonSentence extends Fragment implements Button.OnClickListener {

    View view;

    Button btnCollect;

    static TextView textViewSentenceFront;
    static TextView textViewSentenceBack;
    static TextView textViewSentenceExplain;

    TextView collectResult;


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
        collectResult = view.findViewById(R.id.collectResult);
        btnCollect = view.findViewById(R.id.btnCollect);
        btnCollect.setOnClickListener(this);

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
                String front = LessonWord.sentenceFront[lessonCount];
                String back = LessonWord.sentenceBack[lessonCount];
                CollectionRepository repository = new CollectionRepository(getContext());
                repository.insert(front, back);

                collectResult.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        collectResult.setVisibility(View.GONE);
                    }
                }, 1000);
                break;

            case R.id.btnQnA :
                break;
        }

    }
}
