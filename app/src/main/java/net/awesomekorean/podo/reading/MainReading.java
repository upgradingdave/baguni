package net.awesomekorean.podo.reading;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.readings.Reading0;

import java.util.ArrayList;

public class MainReading extends Fragment {

    public static int readingUnit = 0;

    Context context;

    View view;

    Intent intent;

    public static MainReading newInstance() {
        return new MainReading();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_reading, container, false);

        ArrayList<Reading> list = new ArrayList<>();

        Reading item0 = new Reading0();
        list.add(item0);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ReadingAdapter adapter = new ReadingAdapter(list);
        adapter.setOnItemClickListener(new ReadingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                readingUnit = pos;

                intent = new Intent(context, ReadingFrame.class);
                startActivity(intent);

            }
        });

        recyclerView.setAdapter(adapter);

        context = getContext();

        return view;
    }

}


