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

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.readings.Reading0;

import java.util.ArrayList;
import java.util.List;

import static net.awesomekorean.podo.MainActivity.btnLesson;
import static net.awesomekorean.podo.MainActivity.btnReading;

public class MainReading extends Fragment {

    public static int readingUnit = 0;

    Context context;

    View view;

    Intent intent;

    MainActivity mainActivity;

    ArrayList<Reading> list;
    ReadingAdapter adapter;

    public MainReading(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public static MainReading newInstance(MainActivity mainActivity) {
        return new MainReading(mainActivity);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_reading, container, false);

        list = new ArrayList<>();

        Reading item0 = new Reading0();
        Reading item1 = new Reading0();

        list.add(item0);
        list.add(item1);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ReadingAdapter(list);

        System.out.println("완료된 읽기를 세팅합니다");
        setCompletedReadings();

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

    private void setCompletedReadings() {
        System.out.println("READINGCOMPLETE:"+MainActivity.readingComplete);
        List<Integer> readingComplete = MainActivity.readingComplete;

        if(readingComplete != null) {
            for(int i=0; i<readingComplete.size(); i++) {
                list.get(readingComplete.get(i)).setIsCompleted(true);
            }
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mainActivity.setMainBtns(btnReading, R.drawable.reading_active, R.string.READING);
            System.out.println("Reading Fragment is Visible!!");
        }
    }

}


