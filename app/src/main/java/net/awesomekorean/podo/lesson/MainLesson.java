package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import net.awesomekorean.podo.lesson.lessons.Lesson1;
import net.awesomekorean.podo.lesson.lessons.Lesson2;
import net.awesomekorean.podo.lesson.lessons.S_Lesson0;
import net.awesomekorean.podo.lesson.lessons.Lesson0;
import net.awesomekorean.podo.lesson.lessons.S_Lesson1;

import java.util.ArrayList;
import java.util.List;

import static net.awesomekorean.podo.MainActivity.btnLesson;

public class MainLesson extends Fragment {

    public static String lessonId;

    Context context;

    View view;

    Intent intent;

    MainActivity mainActivity;

    ArrayList<LessonItem> list;
    LessonAdapter adapter;

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

        list = new ArrayList<>();

        LessonItem item0 = new S_Lesson0();
        LessonItem item1 = new Lesson0();
        LessonItem item2 = new S_Lesson1();
        LessonItem item3 = new Lesson1();
        LessonItem item4 = new Lesson2();

        list.add(item0);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LessonAdapter(list);

        System.out.println("완료된 레슨을 세팅합니다");
        setCompletedLessons();

        adapter.setOnItemClickListener(new LessonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                lessonId = list.get(pos).getLessonId();
                System.out.println("LESSONID:"+ lessonId);

                if(!list.get(pos).getIsLock()) {
                    switch (lessonId) {

                        // 스페셜 레슨만 case 분리해서 설정할 것
                        case "SL_00" :
                            intent = new Intent(context, LessonHangulMenu.class);
                            startActivity(intent);
                            break;

                        case "SL_01" :
                            intent = new Intent(context, LessonSpecialFrame.class);
                            startActivity(intent);
                            break;

                        default :
                            intent = new Intent(context, LessonFrame.class);
                            startActivity(intent);
                            break;
                    }

                } else {
                    // 포인트 사용 확인창 띄우기
                }

            }
        });

        recyclerView.setAdapter(adapter);

        context = getContext();

        return view;
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


