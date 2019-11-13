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
import android.widget.ListView;

import net.awesomekorean.baguni.lesson.LessonAdapter;
import net.awesomekorean.baguni.lesson.LessonFrame;
import net.awesomekorean.baguni.lesson.lessonHangul.LessonHangulMenu;
import net.awesomekorean.baguni.reading.ReadingAdapter;
import net.awesomekorean.baguni.reading.ReadingFrame;
import net.awesomekorean.baguni.reading.ReadingItems;

import java.util.ArrayList;

public class MainReading extends Fragment {

    public static int readingUnit = 0;

    View view;

    Intent intent;

    public static MainReading newInstance() {
        return new MainReading();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_reading, container, false);

        ArrayList<ReadingItems> list = new ArrayList<>();

        ReadingItems item = new ReadingItems();
        item.setTitle("Sample title");
        item.setSubTitle("Sample subtitle");
        item.setReadingImage(R.drawable.hangul);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ReadingAdapter adapter = new ReadingAdapter(list);
        recyclerView.setAdapter(adapter);

        return view;
    }

}


