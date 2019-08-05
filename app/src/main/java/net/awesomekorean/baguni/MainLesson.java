package net.awesomekorean.baguni;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.awesomekorean.baguni.lesson.LessonFrame;
import net.awesomekorean.baguni.lesson.LessonWordQuiz1;
import net.awesomekorean.baguni.lesson.LessonWordQuiz2;
import net.awesomekorean.baguni.lessonHangul.LessonHangulMenu;

public class MainLesson extends Fragment implements Button.OnClickListener {

    public static int lessonUnit = 0;

    int lessonNumber = 2;

    View view;

    Intent intent;

    public static MainLesson newInstance() {
        return new MainLesson();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_lesson, container, false);

        Button hangul = view.findViewById(R.id.btn_hangul);
        hangul.setOnClickListener(this);
        Button lesson1 = view.findViewById(R.id.btn_lesson1);
        lesson1.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_hangul :
                intent = new Intent(getContext(), LessonHangulMenu.class);
                startActivity(intent);
                break;

            case R.id.btn_lesson1 :
                lessonUnit = 1;
                setIntentForLesson();
                break;
        }

    }

    public void setIntentForLesson() {

        intent = new Intent(getContext(), LessonFrame.class);
        startActivity(intent);
    }

    public void makeLessonBtns() {

        Button button = new Button(getContext());



    }



}


