package net.awesomekorean.baguni.lesson;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.awesomekorean.baguni.R;

public class LessonWordQuiz1 extends Fragment implements Button.OnClickListener {

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_word_quiz1, container, false);

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
