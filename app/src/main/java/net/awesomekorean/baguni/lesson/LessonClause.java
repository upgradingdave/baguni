package net.awesomekorean.baguni.lesson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.awesomekorean.baguni.R;

public class LessonClause extends Fragment implements Button.OnClickListener {

    View view;

    Button btnReturn;
    Button btnPlay;
    Button btnFinish;

    LinearLayout linearLayout;

    public static LessonClause newInstance() {
        return new LessonClause();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_clause, container, false);

        linearLayout = view.findViewById(R.id.linearLayout);

        setTextView(LessonWord.sentenceInKorean);

        btnReturn = view.findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);
        btnPlay = view.findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);
        btnFinish = view.findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(this);

        return view;
    }

    public void setTextView(String[] sentenceInKorean) {

        for(String sentence : sentenceInKorean) {

            TextView textView = new TextView(getContext());
            textView.setText(sentence);
            textView.setTextSize(30);
            textView.setPadding(30,30,30,30);

            linearLayout.addView(textView);

        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnReturn :
                break;

            case R.id.btnPlay :
                break;

            case R.id.btnFinish :

                LessonFrame.progressCount++;
                LessonFrame.setProgressNow(LessonFrame.progressCount*100/LessonWord.totalPageNo);
                ((LessonFrame)getActivity()).replaceFragment(LessonEnd.newInstance());

                break;
        }

    }
}
