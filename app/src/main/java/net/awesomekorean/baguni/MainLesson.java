package net.awesomekorean.baguni;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import net.awesomekorean.baguni.lesson.LessonAdapter;
import net.awesomekorean.baguni.lesson.LessonFrame;
import net.awesomekorean.baguni.lesson.LessonItems;
import net.awesomekorean.baguni.lesson.lessonHangul.LessonHangulMenu;

import java.util.ArrayList;

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

        ArrayList<LessonItems> list = new ArrayList<>();

        LessonItems item = new LessonItems();
        item.setTitle("Sample title");
        item.setSubTitle("Sample subtitle");
        item.setLessonImage(R.drawable.illustration_lesson_sample);
        item.setIsCompleted(true);
        item.setIsLock(true);
        item.setIsCompleted(true);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LessonAdapter adapter = new LessonAdapter(list);
        recyclerView.setAdapter(adapter);


        return view;
    }

}


