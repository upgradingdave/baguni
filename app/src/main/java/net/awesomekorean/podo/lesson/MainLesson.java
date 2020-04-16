package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.UnitProgressInfo;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.lesson.lessonHangul.LessonHangulAssembly;
import net.awesomekorean.podo.lesson.lessonHangul.LessonHangulBatchim;
import net.awesomekorean.podo.lesson.lessonHangul.LessonHangulConsonant;
import net.awesomekorean.podo.lesson.lessonHangul.LessonHangulVowel;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberNative;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberSino;
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
import net.awesomekorean.podo.lesson.lessons.Lesson00;
import net.awesomekorean.podo.lesson.lessons.LessonItem;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberPractice;
import net.awesomekorean.podo.lesson.lessons.S_Lesson01;
import net.awesomekorean.podo.lesson.lessons.S_Lesson03;
import net.awesomekorean.podo.lesson.lessons.S_Lesson04;
import net.awesomekorean.podo.lesson.lessons.S_Lesson05;
import net.awesomekorean.podo.lesson.lessons.S_Lesson06;
import net.awesomekorean.podo.lesson.lessons.S_Lesson07;
import net.awesomekorean.podo.lesson.lessons.S_Lesson08;
import net.awesomekorean.podo.lesson.lessons.S_Lesson09;
import net.awesomekorean.podo.lesson.lessons.S_Lesson10;
import net.awesomekorean.podo.lesson.lessons.S_Lesson11;
import net.awesomekorean.podo.lesson.lessons.S_Lesson12;
import net.awesomekorean.podo.lesson.lessons.S_Lesson13;

import java.util.List;

public class MainLesson extends Fragment{

    Context context;

    View view;

    UserInformation userInformation;

    TextView userPoint;

    ExpandableListView listView;

    LessonAdapterGroup adapter;

    int lastExpandedPosition = -1;

    String[] groupList = {
            "Hangul", "Greetings", "Conjugation", "Numbers", "Tenses", "Negative", "Range", "Listing/Contrast",
            "Time", "Ability", "Hope", "Requests"
    };

    int[] groupProcess = new int[groupList.length];

    LessonItem[][] childList = {
            {new LessonHangulConsonant(), new LessonHangulVowel(), new LessonHangulBatchim(), new LessonHangulAssembly()},
            {new Lesson00()},
            {new S_Lesson01(), new Lesson01(), new S_Lesson03(), new Lesson02(), new Lesson03()},
            {new NumberSino(), new NumberNative(), new NumberPractice()},
            {new S_Lesson04(), new Lesson04(), new Lesson05(), new Lesson06(), new S_Lesson06()},
            {new Lesson07(), new Lesson08(), new S_Lesson08()},
            {new Lesson09(), new Lesson10(), new S_Lesson05()},
            {new Lesson11(), new Lesson12(), new S_Lesson10()},
            {new Lesson13(), new Lesson14(), new S_Lesson11()},
            {new Lesson15(), new Lesson16(), new S_Lesson12()},
            {new Lesson17(), new S_Lesson13()},
            {new Lesson18(), new S_Lesson07()}
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_lesson, container, false);

        context = getContext();

        userInformation = SharedPreferencesInfo.getUserInfo(context);

        userPoint = view.findViewById(R.id.userPoint);

        userPoint.setText(String.valueOf(userInformation.getPoints()));


        setCompletedLessons();

        setUnlockedLessons();

        adapter = new LessonAdapterGroup(context, groupList, groupProcess, childList);

        listView = view.findViewById(R.id.listView);

        listView.setAdapter(adapter);

        listView.setDivider(null);

        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

                if(lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {

                    listView.collapseGroup(lastExpandedPosition);
                }

                SharedPreferencesInfo.setLastClickItem(context, true, groupPosition);

                lastExpandedPosition = groupPosition;
            }
        });

        int lastClickItem = SharedPreferencesInfo.getLastClickItem(context, true);

        listView.setSelection(lastClickItem);

        listView.expandGroup(lastClickItem);

        return view;
    }


    // 레슨 진도율 세팅하기
    private void setCompletedLessons() {
        List<String> lessonComplete = userInformation.getLessonComplete();
        System.out.println("LESSON_COMPLETE:" + lessonComplete);

        if(lessonComplete != null) {

            // 레슨 진도율 가져오기
            UnitProgressInfo unitProgressInfo = new UnitProgressInfo(context, false);

            String lessonId;

            int sumOfComplete = 0;  // 카테고리 진도율


            for(int i=0; i<groupList.length; i++) {

                for(int j=0; j<childList[i].length; j++) {

                    lessonId = childList[i][j].getLessonId();

                    int progress = unitProgressInfo.getProgress(lessonId);

                    if(progress != -1) {

                        childList[i][j].setLessonProgress(progress);

                        sumOfComplete += progress;
                    }
                }

                groupProcess[i] = Math.round(sumOfComplete / childList[i].length);

                sumOfComplete = 0;
            }
        }
    }


    // 구매된 스페셜 레슨 세팅하기
    private void setUnlockedLessons() {

        List<String> lessonUnlock = userInformation.getSpecialLessonUnlock();

        System.out.println("LESSON_UNLOCK:" + lessonUnlock);

        if(lessonUnlock != null) {

            for(int i=0; i<groupList.length; i++) {

                for(int j=0; j<childList[i].length; j++) {

                    if(lessonUnlock.contains(childList[i][j].getLessonId())) {

                        childList[i][j].setIsLocked(false);
                    }
                }
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        userInformation = SharedPreferencesInfo.getUserInfo(context);

        if(adapter != null) {
            setCompletedLessons();
            setUnlockedLessons();
            adapter.notifyDataSetChanged();
        }
    }
}


