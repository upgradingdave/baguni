package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.lesson.lessons.HangulAssembly;
import net.awesomekorean.podo.lesson.lessons.HangulBatchim;
import net.awesomekorean.podo.lesson.lessons.HangulConsonant;
import net.awesomekorean.podo.lesson.lessons.HangulVowel;
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
import net.awesomekorean.podo.lesson.lessons.Lesson15;
import net.awesomekorean.podo.lesson.lessons.Lesson16;
import net.awesomekorean.podo.lesson.lessons.Lesson17;
import net.awesomekorean.podo.lesson.lessons.Lesson18;
import net.awesomekorean.podo.lesson.lessons.Lesson00;
import net.awesomekorean.podo.lesson.lessons.LessonItem;
import net.awesomekorean.podo.lesson.lessons.NumberNative;
import net.awesomekorean.podo.lesson.lessons.NumberPractice;
import net.awesomekorean.podo.lesson.lessons.NumberSino;
import net.awesomekorean.podo.lesson.lessons.S_Lesson01;
import net.awesomekorean.podo.lesson.lessons.S_Lesson03;
import net.awesomekorean.podo.lesson.lessons.S_Lesson04;
import net.awesomekorean.podo.lesson.lessons.S_Lesson05;
import net.awesomekorean.podo.lesson.lessons.S_Lesson06;
import net.awesomekorean.podo.lesson.lessons.S_Lesson07;
import net.awesomekorean.podo.lesson.lessons.S_Lesson08;
import net.awesomekorean.podo.lesson.lessons.S_Lesson10;
import net.awesomekorean.podo.lesson.lessons.S_Lesson11;
import net.awesomekorean.podo.lesson.lessons.S_Lesson12;

import java.util.Arrays;
import java.util.List;

import static net.awesomekorean.podo.MainActivity.btnLesson;
import static net.awesomekorean.podo.MainActivity.textLesson;

public class MainLesson extends Fragment{

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Context context;

    View view;

    UserInformation userInformation;

    TextView userPoint;

    ExpandableListView listView;

    LessonAdapterGroup adapter;

    int lastExpandedPosition = -1;

    String[] groupList = {
            "Hangul", "Greetings", "Conjugation", "Numbers", "Tenses", "Negative", "Range", "Listing/Contrast",
            "Ability", "Hope", "Requests"
    };

    int[] groupProcess = new int[groupList.length];

    LessonItem[][] childList = {
            {new HangulConsonant(), new HangulVowel(), new HangulBatchim(), new HangulAssembly()},
            {new Lesson00()},
            {new S_Lesson01(), new Lesson01(), new S_Lesson03(), new Lesson02(), new Lesson03()},
            {new NumberSino(), new NumberNative(), new NumberPractice()},
            {new S_Lesson04(), new Lesson04(), new Lesson05(), new Lesson06(), new S_Lesson06()},
            {new Lesson07(), new Lesson08(), new S_Lesson08()},
            {new Lesson09(), new Lesson10(), new S_Lesson05()},
            {new Lesson11(), new Lesson12(), new S_Lesson10()},
            {new Lesson15(), new Lesson16(), new S_Lesson11()},
            {new Lesson17(), new S_Lesson12()},
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


    // 완료된 레슨 세팅하기
    private void setCompletedLessons() {
        List<String> lessonComplete = userInformation.getLessonComplete();
        System.out.println("LESSON_COMPLETE:" + lessonComplete);

        if(lessonComplete != null) {

            // 완료레슨 번호 변경 (L_00 -> L_00%100)
            if(!lessonComplete.get(0).contains("%")) {

                for(int i=0; i<lessonComplete.size(); i++) {

                    String newLessonComplete = lessonComplete.get(i) + "%100";

                    lessonComplete.set(i, newLessonComplete);
                }

                userInformation.setLessonComplete(lessonComplete);

                SharedPreferencesInfo.setUserInfo(context, userInformation);

                db.collection(getString(R.string.DB_USERS)).document(MainActivity.userEmail).collection(getString(R.string.DB_INFORMATION)).document(getString(R.string.DB_INFORMATION))
                        .set(userInformation)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                System.out.println("DB에 lessonComplete 를 신규 번호로 변경했습니다.");
                            }
                        });

            }

            // 레슨 진도율 계산하기
            LessonProgressInfo lessonProgressInfo = new LessonProgressInfo(context);

            String lessonId;

            int sumOfComplete = 0;  // 카테고리 진도율


            for(int i=0; i<groupList.length; i++) {

                for(int j=0; j<childList[i].length; j++) {

                    lessonId = childList[i][j].getLessonId();

                    int progress = lessonProgressInfo.getProgress(lessonId);

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


