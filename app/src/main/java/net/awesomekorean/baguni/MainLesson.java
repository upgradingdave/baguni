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
import net.awesomekorean.baguni.lessonHangul.LessonHangulMenu;

public class MainLesson extends Fragment implements Button.OnClickListener {

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_lesson, container, false);

        Button hangul = (Button) view.findViewById(R.id.btn_hangul);
        hangul.setOnClickListener(this);
        Button lesson1 = (Button) view.findViewById(R.id.btn_lesson1);
        lesson1.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_hangul :
                Intent intent = new Intent(getContext(), LessonHangulMenu.class);
                startActivity(intent);
        }

        switch (v.getId()) {
            case R.id.btn_lesson1 :
                Intent intent = new Intent(getContext(), LessonFrame.class);
                startActivity(intent);
        }

    }

}


