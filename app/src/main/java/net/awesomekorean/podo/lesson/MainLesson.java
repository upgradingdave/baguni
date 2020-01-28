package net.awesomekorean.podo.lesson;

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
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessonHangul.LessonHangulMenu;
import net.awesomekorean.podo.lesson.lessonNumber.LessonNumberMenu;
import net.awesomekorean.podo.lesson.lessons.Lesson1;
import net.awesomekorean.podo.lesson.lessons.Lesson2;
import net.awesomekorean.podo.lesson.lessons.S_Lesson0;
import net.awesomekorean.podo.lesson.lessons.Lesson0;
import net.awesomekorean.podo.lesson.lessons.S_Lesson1;
import net.awesomekorean.podo.lesson.lessons.S_Lesson2;

import java.util.ArrayList;
import java.util.List;

import static net.awesomekorean.podo.MainActivity.btnLesson;

public class MainLesson extends Fragment implements View.OnClickListener {

    public static LessonItem lessonUnit;

    Context context;

    View view;

    Intent intent;

    MainActivity mainActivity;

    ArrayList<LessonItem> list;
    LessonAdapter adapter;

    LinearLayout unlockLayout;
    TextView pointHave;
    TextView pointNeed;
    Button btnUnlock;
    Button btnChargePoint;
    ImageView btnClose;


    public MainLesson(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    public MainLesson() {};

    public static MainLesson newInstance(MainActivity mainActivity) {
        return new MainLesson(mainActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_lesson, container, false);

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

        LessonItem[] items = {
                new S_Lesson0(), new Lesson0(), new S_Lesson1(), new Lesson1(), new Lesson2(), new S_Lesson2()
        };

        for(LessonItem item : items) {
            list.add(item);
        }


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LessonAdapter(list);

        System.out.println("완료된 레슨을 세팅합니다");
        setCompletedLessons();

        adapter.setOnItemClickListener(new LessonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                lessonUnit = list.get(pos);

                if(!lessonUnit.getIsLock()) {

                    if(lessonUnit.getIsSpecial()) {

                        switch (lessonUnit.getLessonId()) {
                            case "SL_00" :
                                intent = new Intent(context, LessonHangulMenu.class);
                                startActivity(intent);
                                break;

                            case "SL_02" :
                                intent = new Intent(context, LessonNumberMenu.class);
                                startActivity(intent);
                                break;

                            default :
                                intent = new Intent(context, LessonSpecialFrame.class);
                                startActivity(intent);
                                break;
                        }
                    } else {
                        intent = new Intent(context, LessonFrame.class);
                        startActivity(intent);
                    }

                } else {
                    // 포인트 사용 확인창 띄우기
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
                break;

            case R.id.btnChargePoint :
                break;

            case R.id.btnClose :
                unlockLayout.setVisibility(View.GONE);
                break;
        }
    }

    private void setCompletedLessons() {
        System.out.println("LESSONCOMPLETE:"+MainActivity.lessonComplete);
        List<String> lessonComplete = MainActivity.lessonComplete;

        if(lessonComplete != null) {
            for(int i=0; i<list.size(); i++) {
                if(lessonComplete.contains(list.get(i).getLessonId())) {
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
            mainActivity.setMainBtns(btnLesson, R.drawable.lesson_active, R.string.LESSON);
            //setCompletedLessons();
        }
    }
}


