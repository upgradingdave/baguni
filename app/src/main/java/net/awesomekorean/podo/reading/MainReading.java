package net.awesomekorean.podo.reading;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.lesson.LessonItem;
import net.awesomekorean.podo.purchase.TopUp;
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

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    int readingPrice = 100;

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

    UserInformation userInformation = MainActivity.userInformation;


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
                    int point = userInformation.getPoints();
                    if(point < readingPrice) {
                        pointHave.setTextColor(Color.RED);
                        btnUnlock.setEnabled(false);
                    }
                    pointHave.setText(String.valueOf(point));
                    unlockLayout.setVisibility(View.VISIBLE);
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
                unlockLayout.setVisibility(View.GONE);
                // 포인트 차감하고 readingUnlock 에 읽기아이디 추가, 해당 읽기에 unlock = true 세팅
                int newPoint = userInformation.getPoints() - readingPrice;
                String readingId = readingUnit.getReadingId();
                userInformation.setPoints(newPoint);
                userInformation.addReadingUnlock(readingId);

                DocumentReference informationRef = db.collection(getString(R.string.DB_USERS)).document(MainActivity.userEmail).collection(getString(R.string.DB_INFORMATION)).document(getString(R.string.DB_INFORMATION));
                informationRef.set(userInformation).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        SharedPreferencesInfo.setUserInfo(context, userInformation);
                        setUnlockedReadings();
                        System.out.println("읽기를 포인트로 구매했습니다.");
                        Toast.makeText(context, getString(R.string.SUCCEEDED_UNLOCK_READING), Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, getString(R.string.FAILED_UNLOCK_READING), Toast.LENGTH_LONG).show();
                    }
                });

                break;

            case R.id.btnChargePoint :
                unlockLayout.setVisibility(View.GONE);
                intent = new Intent(context, TopUp.class);
                startActivity(intent);
                break;

            case R.id.btnClose :
                unlockLayout.setVisibility(View.GONE);
                break;
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
            mainActivity.setMainBtns(btnReading, R.drawable.reading_active, R.string.READING);
        }
    }
}


