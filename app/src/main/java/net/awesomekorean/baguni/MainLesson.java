package net.awesomekorean.baguni;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import net.awesomekorean.baguni.lesson.LessonFrame;
import net.awesomekorean.baguni.lesson.lessonHangul.LessonHangulMenu;

public class MainLesson extends Fragment {

    public static int lessonUnit = 0;

    static final String[] lessonList = {"Hangul", "Lesson1", "Lesson2"};

    View view;

    Intent intent;

    public static MainLesson newInstance() {
        return new MainLesson();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_lesson, container, false);

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, lessonList);

        ListView listView = view.findViewById(R.id.listViewLesson);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {

                    case 0 :
                        intent = new Intent(getContext(), LessonHangulMenu.class);
                        startActivity(intent);
                        break;

                    default :
                        lessonUnit = i;
                        intent = new Intent(getContext(), LessonFrame.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        return view;
    }

}


