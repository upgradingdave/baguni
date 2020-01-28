package net.awesomekorean.podo.reading;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.reading.readings.Reading00;
import net.awesomekorean.podo.reading.readings.Reading01;
import net.awesomekorean.podo.reading.readings.Reading02;
import net.awesomekorean.podo.reading.readings.Reading03;
import net.awesomekorean.podo.reading.readings.Reading04;
import net.awesomekorean.podo.reading.readings.Reading05;
import net.awesomekorean.podo.reading.readings.Reading06;
import net.awesomekorean.podo.reading.readings.Reading07;
import net.awesomekorean.podo.reading.readings.Reading08;
import net.awesomekorean.podo.reading.readings.Reading09;
import net.awesomekorean.podo.reading.readings.Reading10;
import net.awesomekorean.podo.reading.readings.Reading11;
import net.awesomekorean.podo.reading.readings.Reading12;
import net.awesomekorean.podo.reading.readings.Reading13;
import net.awesomekorean.podo.reading.readings.Reading14;
import net.awesomekorean.podo.reading.readings.Reading15;
import net.awesomekorean.podo.reading.readings.Reading16;
import net.awesomekorean.podo.reading.readings.Reading18;
import net.awesomekorean.podo.reading.readings.Reading19;

import java.util.ArrayList;
import java.util.List;

import static net.awesomekorean.podo.MainActivity.btnReading;

public class MainReading extends Fragment implements View.OnClickListener {

    public static Reading readingUnit;

    Context context;

    View view;

    Intent intent;

    MainActivity mainActivity;

    ArrayList<Reading> list;
    ReadingAdapter adapter;

    LinearLayout unlockLayout;
    TextView pointHave;
    TextView pointNeed;
    Button btnUnlock;
    Button btnChargePoint;
    ImageView btnClose;


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

        unlockLayout = view.findViewById(R.id.unlockLayout);
        pointHave = view.findViewById(R.id.pointHave);
        pointNeed = view.findViewById(R.id.pointNeed);
        btnUnlock = view.findViewById(R.id.btnUnlock);
        btnChargePoint = view.findViewById(R.id.btnChargePoint);
        btnClose = view.findViewById(R.id.btnClose);
        btnUnlock.setOnClickListener(this);
        btnChargePoint.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        list = new ArrayList<>();

        Reading[] items = {
                new Reading00(), new Reading01(), new Reading02(), new Reading03(), new Reading04(), new Reading05(), new Reading06(), new Reading07(), new Reading08(), new Reading09(), new Reading10(), new Reading11(), new Reading12(), new Reading13(), new Reading14(), new Reading15(), new Reading16(), new Reading18(), new Reading19()
        };

        for(Reading item : items) {
            list.add(item);
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ReadingAdapter(list);

        System.out.println("완료된 읽기를 세팅합니다");
        setCompletedReadings();

        adapter.setOnItemClickListener(new ReadingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                readingUnit = list.get(pos);

                if(!readingUnit.getIsLock()) {
                    intent = new Intent(context, ReadingFrame.class);
                    startActivity(intent);
                }
            }
        });

        recyclerView.setAdapter(adapter);

        context = getContext();

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnUnlock :
                break;

            case R.id.btnChargePoint :
                break;

            case R.id.btnClose :
                unlockLayout.setVisibility(View.GONE);
                break;
        }
    }

    private void setCompletedReadings() {
        System.out.println("READINGCOMPLETE:"+MainActivity.readingComplete);
        List<String> readingComplete = MainActivity.readingComplete;

        if(readingComplete != null) {
            for(int i=0; i<list.size(); i++) {
                if(readingComplete.contains(list.get(i).getReadingId())) {
                    list.get(i).setIsCompleted(true);
                }
            }
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mainActivity.setMainBtns(btnReading, R.drawable.reading_active, R.string.READING);
        }
    }
}


