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

import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UnlockActivity;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.reading.readings.Reading08;
import net.awesomekorean.podo.reading.readings.Reading01;
import net.awesomekorean.podo.reading.readings.Reading02;
import net.awesomekorean.podo.reading.readings.Reading03;
import net.awesomekorean.podo.reading.readings.Reading04;
import net.awesomekorean.podo.reading.readings.Reading05;
import net.awesomekorean.podo.reading.readings.Reading06;
import net.awesomekorean.podo.reading.readings.Reading07;
import net.awesomekorean.podo.reading.readings.Reading00;
import net.awesomekorean.podo.reading.readings.Reading09;
import net.awesomekorean.podo.reading.readings.Reading10;
import net.awesomekorean.podo.reading.readings.Reading11;
import net.awesomekorean.podo.reading.readings.Reading12;
import net.awesomekorean.podo.reading.readings.Reading13;
import net.awesomekorean.podo.reading.readings.Reading14;
import net.awesomekorean.podo.reading.readings.Reading15;
import net.awesomekorean.podo.reading.readings.Reading16;
import net.awesomekorean.podo.reading.readings.Reading17;
import net.awesomekorean.podo.reading.readings.Reading18;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static net.awesomekorean.podo.MainActivity.btnReading;
import static net.awesomekorean.podo.MainActivity.textReading;

public class MainReading extends Fragment {

    public static Reading readingUnit;

    Context context;

    View view;

    Intent intent;

    MainActivity mainActivity;

    ArrayList<Reading> list;
    ReadingAdapter adapter;

    UserInformation userInformation;


    public MainReading(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    public MainReading() {}

    public static MainReading newInstance(MainActivity mainActivity) {
        return new MainReading(mainActivity);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_reading, container, false);

        context = getContext();

        userInformation = SharedPreferencesInfo.getUserInfo(context);

        list = new ArrayList<>();

        Reading[] items = {
                new Reading00(), new Reading01(), new Reading02(), new Reading03(), new Reading04(), new Reading05(), new Reading06(), new Reading07(), new Reading08(), new Reading09(), new Reading10(), new Reading11(), new Reading12(), new Reading13(), new Reading14(), new Reading15(), new Reading16(), new Reading17(), new Reading18()
        };

        for(Reading item : items) {
            list.add(item);
        }

        list.get(0).setIsLocked(false);
        list.get(1).setIsLocked(false);
        list.get(2).setIsLocked(false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ReadingAdapter(getContext(), list);

        setCompletedReadings();
        setUnlockedReadings();

        adapter.setOnItemClickListener(new ReadingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                readingUnit = list.get(pos);

                if(!readingUnit.getIsLock()) {
                    intent = new Intent(context, ReadingFrame.class);
                    startActivity(intent);

                } else {
                    // 포인트 사용 확인창 띄우기
                    intent = new Intent(context, UnlockActivity.class);
                    intent.putExtra("unlock", "reading");
                    startActivityForResult(intent, 200);

                }
            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }

    // 읽기 구매 성공
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200 && resultCode == RESULT_OK) {
            userInformation = SharedPreferencesInfo.getUserInfo(context);
            setUnlockedReadings();
        }
    }



    // 완료된 읽기 세팅하기
    private void setCompletedReadings() {
        List<String> readingComplete = userInformation.getReadingComplete();
        System.out.println("READING_COMPLETE:" + readingComplete);

        if(readingComplete != null) {
            for(int i=0; i<list.size(); i++) {
                if(readingComplete.contains(list.get(i).getReadingId())) {
                    list.get(i).setIsCompleted(true);
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    // 구매된 읽기 세팅하기
    private void setUnlockedReadings() {
        List<String> readingUnlock = userInformation.getReadingUnlock();
        System.out.println("READING_UNLOCK:" + readingUnlock);

        if(readingUnlock != null) {
            for(int i=0; i<list.size(); i++) {
                if(readingUnlock.contains(list.get(i).getReadingId())) {
                    list.get(i).setIsLocked(false);
                }
            }
            adapter.notifyDataSetChanged();
        }
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            if(mainActivity != null) {
                mainActivity.setMainBtns(btnReading, textReading, R.drawable.reading_active, R.string.READING);
            } else {
                if(getActivity() != null) {
                    ((MainActivity)getActivity()).setMainBtns(btnReading, textReading, R.drawable.reading_active, R.string.READING);
                } else {
                    System.out.println("MainActivity is null inside mainReading.");
                }
            }
        }
    }
}


