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
import android.widget.ListView;

import net.awesomekorean.baguni.lesson.LessonFrame;
import net.awesomekorean.baguni.lesson.lessonHangul.LessonHangulMenu;
import net.awesomekorean.baguni.reading.ReadingFrame;

public class MainQnA extends Fragment {

    static final String[] readingList = {"Reading0", "Reading1"};
    View view;

    Intent intent;

    public static MainQnA newInstance() {
        return new MainQnA();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_reading, container, false);

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, readingList);

        ListView listView = view.findViewById(R.id.listViewReading);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                intent = new Intent(getContext(), ReadingFrame.class);
                startActivity(intent);
            }
        });

        return view;
    }

}


