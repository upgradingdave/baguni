package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.lesson.intermediateLessons.I_Lesson00;
import net.awesomekorean.podo.lesson.lessonReview.LessonReview00;
import net.awesomekorean.podo.lesson.lessonReview.Rewards00;
import net.awesomekorean.podo.lesson.lessons.Lesson01;
import net.awesomekorean.podo.lesson.lessons.Lesson02;
import net.awesomekorean.podo.lesson.lessons.Lesson00;
import net.awesomekorean.podo.lesson.lessons.Lesson03;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainLesson extends Fragment implements View.OnClickListener {

    Context context;
    View view;
    ImageView btnBack;
    TextView tvLevel;
    Button btnNextLevel;

    UserInformation userInformation;
    RecyclerView recyclerView;
    LessonAdapter adapter;
    SeekBar seekBar;

    LessonItem[] beginner = {
            new Lesson00(), new Lesson01(), new Lesson02(), new Lesson03(), new LessonReview00(), new Rewards00()
    };

    LessonItem[] intermediate = {
            new I_Lesson00()
    };

    LessonItem[] list;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_lesson, container, false);
        btnBack = view.findViewById(R.id.btnBack);
        tvLevel = view.findViewById(R.id.tvLevel);
        btnNextLevel = view.findViewById(R.id.btnNextLevel);
        btnBack.setOnClickListener(this);
        btnNextLevel.setOnClickListener(this);

        context = getContext();
        userInformation = SharedPreferencesInfo.getUserInfo(context);
        setCompletedLessons();
        adapter = new LessonAdapter(context, list);
        seekBar = view.findViewById(R.id.seekBar);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            }
        });

        seekBar.setMax(list.length);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                recyclerView.smoothScrollToPosition(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

//        int lastClickItem = SharedPreferencesInfo.getLastClickItem(context, true);
//        recyclerView.setSelection(lastClickItem);
//        recyclerView.expandGroup(lastClickItem);
        return view;
    }


    // 레슨 활성/완료 세팅하기
    private void setCompletedLessons() {
        int lastClickLevel = SharedPreferencesInfo.getLastClickLevel(context);
        if(lastClickLevel == 0) {
            list = beginner.clone();
        } else if(lastClickLevel == 1) {
            list = intermediate.clone();
        }
//        List<String> lessonComplete = userInformation.getLessonComplete();
//        System.out.println("LESSON_COMPLETE:" + lessonComplete);
        List<String> lessonComplete = new ArrayList<>(); //todo: 테스트용임. 삭제할 것
        lessonComplete.clear();
        lessonComplete.add("L_00");
        lessonComplete.add("L_01");
        lessonComplete.add("SL_01");

        if(lessonComplete.size() > 0) {

            for(int i=0; i<list.length; i++) {

                if(lessonComplete.contains(list[i].getLessonId())) {
                    list[i].setIsCompleted(true);
                    list[i].setIsActive(true);
                    list[i+1].setIsActive(true);
                    list[i].setIsCurrent(false);
                    list[i+1].setIsCurrent(true);

                    // 스페셜레슨 세팅
                    if(list[i].getSLesson() != null) {
                        list[i].getSLesson().setIsActive(true);

                        if(lessonComplete.contains(list[i].getSLesson().getLessonId())) {
                            list[i].getSLesson().setIsCompleted(true);
                        }
                    }
                }
            }

        // 완료한 레슨이 하나도 없는 상태
        } else {
            list[0].setIsActive(true);
            list[0].setIsCurrent(true);
        }
        setUnlockedLessons();
    }


    // 구매된 스페셜 레슨 세팅하기
    private void setUnlockedLessons() {
//        List<String> lessonUnlock = userInformation.getSpecialLessonUnlock();
//        System.out.println("LESSON_UNLOCK:" + lessonUnlock);
        List<String> lessonUnlock = new ArrayList<>(); //todo: 테스트용임. 삭제할 것
        lessonUnlock.clear();
        lessonUnlock.add("SL_01");

        if(lessonUnlock != null) {
            for(int i=0; i<list.length; i++) {

                if (list[i].getSLesson() != null && lessonUnlock.contains(list[i].getSLesson().getLessonId())) {
                    list[i].getSLesson().setIsLocked(false);
                }
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack :
                break;

            case R.id.btnNextLevel :
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        userInformation = SharedPreferencesInfo.getUserInfo(context);

        if(adapter != null) {
            System.out.println("메인레슨 보임!");
            setCompletedLessons();
            adapter.notifyDataSetChanged();
        }
    }
}


