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
import net.awesomekorean.podo.lesson.lessonHangul.LessonHangulBatchim;
import net.awesomekorean.podo.lesson.lessonHangul.LessonHangulConsonant;
import net.awesomekorean.podo.lesson.lessonHangul.LessonHangulVowel;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberNative;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberSino;
import net.awesomekorean.podo.lesson.lessonReviewRewards.LessonReview00;
import net.awesomekorean.podo.lesson.lessonReviewRewards.LessonReview01;
import net.awesomekorean.podo.lesson.lessonReviewRewards.LessonReview02;
import net.awesomekorean.podo.lesson.lessonReviewRewards.LessonReview03;
import net.awesomekorean.podo.lesson.lessonReviewRewards.LessonReview04;
import net.awesomekorean.podo.lesson.lessonReviewRewards.Rewards00;
import net.awesomekorean.podo.lesson.lessonReviewRewards.Rewards01;
import net.awesomekorean.podo.lesson.lessonReviewRewards.Rewards02;
import net.awesomekorean.podo.lesson.lessonReviewRewards.Rewards03;
import net.awesomekorean.podo.lesson.lessonReviewRewards.Rewards04;
import net.awesomekorean.podo.lesson.lessons.Lesson01;
import net.awesomekorean.podo.lesson.lessons.Lesson02;
import net.awesomekorean.podo.lesson.lessons.Lesson00;
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
import net.awesomekorean.podo.lesson.lessons.Lesson19;
import net.awesomekorean.podo.lesson.lessons.Lesson20;
import net.awesomekorean.podo.lesson.lessons.Lesson21;
import net.awesomekorean.podo.lesson.lessons.Lesson22;
import net.awesomekorean.podo.lesson.lessons.Lesson23;
import net.awesomekorean.podo.lesson.lessons.Lesson24;
import net.awesomekorean.podo.lesson.lessons.Lesson25;
import net.awesomekorean.podo.lesson.lessons.Lesson26;
import net.awesomekorean.podo.lesson.lessons.Lesson27;
import net.awesomekorean.podo.lesson.lessons.Lesson28;
import net.awesomekorean.podo.lesson.lessons.Lesson29;
import net.awesomekorean.podo.lesson.lessons.Lesson30;
import net.awesomekorean.podo.lesson.lessons.Lesson31;
import net.awesomekorean.podo.lesson.lessons.Lesson32;
import net.awesomekorean.podo.lesson.lessons.Lesson33;
import net.awesomekorean.podo.lesson.lessons.Lesson34;
import net.awesomekorean.podo.lesson.lessons.Lesson35;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import java.util.ArrayList;
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
            new LessonHangulConsonant(), new LessonHangulVowel(), new LessonHangulBatchim(), new Lesson00(),
            new Lesson19(), new Lesson01(), new Lesson02(), new Lesson03(), new LessonReview00(), new Rewards00(),
            new NumberSino(), new NumberNative(), new Lesson04(), new Lesson05(), new Lesson06(), new Lesson07(),
            new Lesson08(), new Lesson27(), new LessonReview01(), new Rewards01(), new Lesson09(), new Lesson10(),
            new Lesson11(), new Lesson12(), new Lesson13(), new Lesson14(), new Lesson15(), new Lesson16(),
            new LessonReview02(), new Rewards02(), new Lesson22(), new Lesson28(), new Lesson29(), new Lesson17(),
            new Lesson20(), new Lesson18(), new Lesson21(), new Lesson23(), new Lesson35(), new LessonReview03(),
            new Rewards03(), new Lesson24(), new Lesson25(), new Lesson26(), new Lesson30(), new Lesson31(),
            new Lesson32(), new Lesson33(), new Lesson34(), new LessonReview04(), new Rewards04()
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
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                System.out.println("엑스 : " + dx);
                System.out.println("와 : " + dy);
            }
        });

        seekBar.setMax(list.length);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                recyclerView.scrollToPosition(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        int lastClickItem = SharedPreferencesInfo.getLastClickItem(context, true);
        recyclerView.scrollToPosition(lastClickItem);
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
        List<String> lessonComplete = userInformation.getLessonComplete();
        System.out.println("LESSON_COMPLETE:" + lessonComplete);

        if(lessonComplete.size() > 0) {

            for (int i = 0; i < list.length; i++) {
                if (lessonComplete.contains(list[i].getLessonId())) {
                    list[i].setIsCompleted(true);
                    list[i].setIsActive(true);
                    list[i + 1].setIsActive(true);
                    list[i].setIsCurrent(false);
                    list[i + 1].setIsCurrent(true);

                    // 스페셜레슨 세팅
                    if (list[i].getSLesson() != null) {
                        list[i].getSLesson().setIsActive(true);

                        if (lessonComplete.contains(list[i].getSLesson().getLessonId())) {
                            list[i].getSLesson().setIsCompleted(true);
                        }
                    }
                }
            }

        } else {
            list[0].setIsCurrent(true);
        }
        list[0].setIsActive(true);
        setUnlockedLessons();
    }


    // 구매된 스페셜 레슨 세팅하기
    private void setUnlockedLessons() {
        List<String> lessonUnlock = userInformation.getSpecialLessonUnlock();
        System.out.println("LESSON_UNLOCK:" + lessonUnlock);

        if(lessonUnlock != null) {

            for(int i=0; i<list.length; i++) {
                if (list[i].getSLesson() != null && lessonUnlock.contains(list[i].getSLesson().getLessonId())) {
                    list[i].getSLesson().setIsLocked(false);
                    list[i].getSLesson().setIsActive(true);
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


