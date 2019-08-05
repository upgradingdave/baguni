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
import android.widget.LinearLayout;

import net.awesomekorean.baguni.lesson.LessonFrame;
import net.awesomekorean.baguni.lesson.LessonWordQuiz1;
import net.awesomekorean.baguni.lesson.LessonWordQuiz2;
import net.awesomekorean.baguni.lessonHangul.LessonHangulMenu;

public class MainLesson extends Fragment implements Button.OnClickListener {

    public static int lessonUnit = 0;

    int lessonNumber = 2;

    LinearLayout layout;

    View view;

    Intent intent;

    public static MainLesson newInstance() {
        return new MainLesson();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_lesson, container, false);

        layout = view.findViewById(R.id.layoutLessonMain);

        makeLessonBtns();

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case 0 :
                intent = new Intent(getContext(), LessonHangulMenu.class);
                startActivity(intent);
                break;

            default :
                lessonUnit = v.getId();
                setIntentForLesson();
                break;
        }
    }

    public void setIntentForLesson() {

        intent = new Intent(getContext(), LessonFrame.class);
        startActivity(intent);
    }

    public void makeLessonBtns() {

        for(int i=0; i<lessonNumber; i++) {

            Button button = new Button(getContext());

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DpToPx.getDpToPx(getResources(), 100));

            button.setLayoutParams(params);
            button.setOnClickListener(this);
            button.setText("Lesson"+i);
            button.setId(i);

            layout.addView(button);
        }
    }
}


