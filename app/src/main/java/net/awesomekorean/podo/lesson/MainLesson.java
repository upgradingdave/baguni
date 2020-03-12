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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UnlockActivity;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.lesson.lessonHangul.LessonHangulMenu;
import net.awesomekorean.podo.lesson.lessonNumber.LessonNumberMenu;
import net.awesomekorean.podo.lesson.lessons.Lesson01;
import net.awesomekorean.podo.lesson.lessons.Lesson02;
import net.awesomekorean.podo.lesson.lessons.Lesson03;
import net.awesomekorean.podo.lesson.lessons.Lesson04;
import net.awesomekorean.podo.lesson.lessons.Lesson05;
import net.awesomekorean.podo.lesson.lessons.Lesson06;
import net.awesomekorean.podo.lesson.lessons.Lesson07;
import net.awesomekorean.podo.lesson.lessons.Lesson08;
import net.awesomekorean.podo.lesson.lessons.Lesson09;
import net.awesomekorean.podo.lesson.lessons.Lesson10;
import net.awesomekorean.podo.lesson.lessons.Lesson11;
import net.awesomekorean.podo.lesson.lessons.Lesson12;
import net.awesomekorean.podo.lesson.lessons.Lesson13;
import net.awesomekorean.podo.lesson.lessons.Lesson14;
import net.awesomekorean.podo.lesson.lessons.Lesson15;
import net.awesomekorean.podo.lesson.lessons.Lesson16;
import net.awesomekorean.podo.lesson.lessons.Lesson17;
import net.awesomekorean.podo.lesson.lessons.Lesson18;
import net.awesomekorean.podo.lesson.lessons.S_Lesson00;
import net.awesomekorean.podo.lesson.lessons.Lesson00;
import net.awesomekorean.podo.lesson.lessons.S_Lesson01;
import net.awesomekorean.podo.lesson.lessons.S_Lesson02;
import net.awesomekorean.podo.lesson.lessons.S_Lesson03;
import net.awesomekorean.podo.lesson.lessons.S_Lesson04;
import net.awesomekorean.podo.lesson.lessons.S_Lesson05;
import net.awesomekorean.podo.lesson.lessons.S_Lesson06;
import net.awesomekorean.podo.lesson.lessons.S_Lesson07;
import net.awesomekorean.podo.lesson.lessons.S_Lesson08;
import net.awesomekorean.podo.lesson.lessons.S_Lesson10;
import net.awesomekorean.podo.lesson.lessons.S_Lesson11;
import net.awesomekorean.podo.lesson.lessons.S_Lesson12;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static net.awesomekorean.podo.MainActivity.btnLesson;
import static net.awesomekorean.podo.MainActivity.textLesson;

public class MainLesson extends Fragment{

    public static LessonItem lessonUnit;


    Context context;

    View view;

    Intent intent;

    MainActivity mainActivity;

    ArrayList<LessonItem> list;
    LessonAdapter adapter;

    UserInformation userInformation;


    public MainLesson(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    public MainLesson() {}

    public static MainLesson newInstance(MainActivity mainActivity) {
        return new MainLesson(mainActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_lesson, container, false);

        context = getContext();

        userInformation = SharedPreferencesInfo.getUserInfo(context);

        list = new ArrayList<>();

        LessonItem[] items = {
                new S_Lesson00(), new Lesson00(), new S_Lesson01(), new Lesson01(), new S_Lesson02(),
                new Lesson02(), new S_Lesson03(), new Lesson03(), new S_Lesson04(), new Lesson04(),
                new S_Lesson05(), new Lesson05(), new S_Lesson06(), new Lesson06(), new S_Lesson07(),
                new Lesson07(), new Lesson08(), new S_Lesson08(), new Lesson09(), new Lesson10(), new Lesson11(),
                new S_Lesson10(), new Lesson12(), new Lesson13(), new Lesson14(), new S_Lesson11(), new Lesson15(),
                new Lesson16(), new S_Lesson12(), new Lesson17(), new Lesson18()
        };

        for(LessonItem item : items) {
            list.add(item);
        }


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LessonAdapter(list);

        // 가장 최근 클릭한 아이템 위치로 이동
        recyclerView.scrollToPosition(SharedPreferencesInfo.getLastClickItem(context, true));

        setCompletedLessons();
        setUnlockedLessons();

        adapter.setOnItemClickListener(new LessonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                lessonUnit = list.get(pos);
                SharedPreferencesInfo.setLastClickItem(context, true, pos);

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
                    intent = new Intent(context, UnlockActivity.class);
                    intent.putExtra("unlock", "specialLesson");
                    startActivityForResult(intent, 200);
                }

            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }

    // 스페셜 레슨 구매 성공
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200 && resultCode == RESULT_OK) {
            userInformation = SharedPreferencesInfo.getUserInfo(context);
            setUnlockedLessons();
        }
    }

    // 완료된 레슨 세팅하기
    private void setCompletedLessons() {
        List<String> lessonComplete = userInformation.getLessonComplete();
        System.out.println("LESSON_COMPLETE:" + lessonComplete);

        if(lessonComplete != null) {
            for(int i=0; i<list.size(); i++) {
                if(lessonComplete.contains(list.get(i).getLessonId())) {
                    list.get(i).setIsCompleted(true);
                }
            }
            adapter.notifyDataSetChanged();
        }
    }

    // 구매된 스페셜 레슨 세팅하기
    private void setUnlockedLessons() {
        List<String> lessonUnlock = userInformation.getSpecialLessonUnlock();
        System.out.println("LESSON_UNLOCK:" + lessonUnlock);

        if(lessonUnlock != null) {
            for(int i=0; i<list.size(); i++) {
                if(lessonUnlock.contains(list.get(i).getLessonId())) {
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
                mainActivity.setMainBtns(btnLesson, textLesson, R.drawable.lesson_active, R.string.LESSON);
            } else {
                if(getActivity() != null) {
                    ((MainActivity)getActivity()).setMainBtns(btnLesson, textLesson, R.drawable.lesson_active, R.string.LESSON);
                } else {
                    System.out.println("MainActivity is null inside mainLesson.");
                }
            }
        }
    }
}


