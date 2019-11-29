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

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.collection.CollectionRepository;

import static net.awesomekorean.podo.lesson.LessonWord.lessonCount;

public class LessonSentence extends Fragment implements Button.OnClickListener {

    View view;

    ImageView btnAudio;
    Button btnCollect;

    static TextView tvSentenceFront;
    static TextView tvSentenceBack;
    static TextView tvSentenceExplain;

    LinearLayout collectResult;


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

        displaySentence();

        return view;

    }

    public static void displaySentence() {

        tvSentenceFront.setText(LessonWord.sentenceFront[lessonCount]);
        tvSentenceBack.setText(LessonWord.sentenceBack[lessonCount]);
        tvSentenceExplain.setText(LessonWord.sentenceExplain[lessonCount]);
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
        }

    }
}
