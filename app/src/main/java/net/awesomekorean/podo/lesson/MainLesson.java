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
import net.awesomekorean.podo.lesson.lessonReview.LessonReview00;
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
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberPractice;
import net.awesomekorean.podo.lesson.lessonReview.R_Conjugation_Lesson00;
import net.awesomekorean.podo.lesson.lessonReview.R_Ranking_Lesson00;
import net.awesomekorean.podo.lesson.lessonReview.R_Sentence_Lesson00;
import net.awesomekorean.podo.lesson.lessonReview.R_Sentence_Lesson01;
import net.awesomekorean.podo.lesson.lessonReview.R_Sentence_Lesson02;
import net.awesomekorean.podo.lesson.lessonReview.R_Sentence_Lesson03;
import net.awesomekorean.podo.lesson.lessonReview.R_Sentence_Lesson04;
import net.awesomekorean.podo.lesson.lessonReview.R_Word_Lesson00;
import net.awesomekorean.podo.lesson.lessonReview.R_Word_Lesson01;
import net.awesomekorean.podo.lesson.lessonReview.R_Word_Lesson02;
import net.awesomekorean.podo.lesson.lessonReview.R_Word_Lesson03;
import net.awesomekorean.podo.lesson.lessonReview.R_Word_Lesson04;
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
import net.awesomekorean.podo.lesson.lessons.S_Lesson14;
import net.awesomekorean.podo.lesson.lessons.S_Lesson15;
import net.awesomekorean.podo.lesson.lessons.S_Lesson16;
import net.awesomekorean.podo.lesson.lessons.S_Lesson17;

import java.util.List;

public class MainLesson extends Fragment{

    Context context;

    View view;

    UserInformation userInformation;

    TextView userPoint;

    ExpandableListView listView;

    LessonAdapterGroup adapter;

    int lastExpandedPosition = -1;

    String[] groupTitle = {
            "Hangul", "Greetings", "Conjugation", "Numbers", "Tenses", "Review", "Negative", "Reason",
            "Range", "Listing/Contrast", "Review", "Ability", "Time", "Review", "Hope", "Requests",
            "Supposition", "Review", "Experience", "Guess", "Permission", "Review", "Prohibition"
    };

    String[] groupSubTitle = {
            "Lesson 0", "Lesson 1", "Lesson 2", "Lesson 3", "Lesson 4", "Lesson 1~4", "Lesson 5", "Lesson 6",
            "Lesson 7", "Lesson 8", "Lesson 5~8", "Lesson 9", "Lesson 10", "Lesson 9~10",
            "Lesson 11", "Lesson 12", "Lesson 13", "Lesson 11~13", "Lesson 14", "Lesson 15", "Lesson 16",
            "Lesson 14~16", "Coming soon!"
    };

    int[] groupProcess = new int[groupTitle.length];

    LessonItem[][] childList = {
            {new LessonHangulConsonant(), new LessonHangulVowel(), new LessonHangulBatchim(), new LessonHangulAssembly()},
            {new Lesson00(), new Lesson19()},
            {new S_Lesson01(), new Lesson01(), new S_Lesson03(), new Lesson02(), new Lesson03()},
            {new NumberSino(), new NumberNative(), new NumberPractice()},
            {new S_Lesson04(), new Lesson04(), new Lesson05(), new Lesson06(), new S_Lesson06()},
            {new LessonReview00()},
            {new Lesson07(), new Lesson08()},
            {new Lesson27(), new S_Lesson08(), new S_Lesson12()},
            {new Lesson09(), new Lesson10(), new S_Lesson05()},
            {new Lesson11(), new Lesson12(), new S_Lesson10()},
            {new LessonReview00()},
            {new Lesson15(), new Lesson16()},
            {new Lesson13(), new Lesson14(), new S_Lesson11(), new Lesson22(), new Lesson28(), new Lesson29(), new S_Lesson15()},
            {new LessonReview00()},
            {new Lesson17(), new Lesson20(), new S_Lesson13()},
            {new Lesson18(), new Lesson21(), new S_Lesson07()},
            {new Lesson23(), new Lesson35(), new Lesson24(), new S_Lesson09()},
            {new LessonReview00()},
            {new Lesson25(), new Lesson26()},
            {new Lesson30(), new Lesson31(), new S_Lesson14()},
            {new Lesson32(), new Lesson33(), new Lesson34(), new S_Lesson16(), new S_Lesson17()},
            {new LessonReview00()},
            {}
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_lesson, container, false);

        context = getContext();

        userPoint = view.findViewById(R.id.userPoint);

//        setUserPoint();
        userInformation = SharedPreferencesInfo.getUserInfo(context);


        setCompletedLessons();

        setUnlockedLessons();

        adapter = new LessonAdapterGroup(context, groupTitle, groupSubTitle, groupProcess, childList);

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


/*

    // 유저 포인트 세팅하기
    private void setUserPoint() {

        userInformation = SharedPreferencesInfo.getUserInfo(context);

        userPoint.setText(String.valueOf(userInformation.getPoints()));
    }


 */

    // 레슨 진도율 세팅하기
    private void setCompletedLessons() {
        List<String> lessonComplete = userInformation.getLessonComplete();
        System.out.println("LESSON_COMPLETE:" + lessonComplete);

        if(lessonComplete != null) {

            // 레슨 진도율 가져오기
            UnitProgressInfo unitProgressInfo = new UnitProgressInfo(context, false);

            String lessonId;

            int sumOfComplete = 0;  // 카테고리 진도율


            for(int i=0; i<groupTitle.length; i++) {

                for(int j=0; j<childList[i].length; j++) {

                    lessonId = childList[i][j].getLessonId();

                    int progress = unitProgressInfo.getProgress(lessonId);

                    if(progress != -1) {

                        childList[i][j].setLessonProgress(progress);

                        sumOfComplete += progress;
                    }
                }

                // 단어 테스트일 때
                if(childList[i].length == 0) {

                    groupProcess[i] = 0;

                } else {

                    groupProcess[i] = Math.round(sumOfComplete / childList[i].length);
                }

                sumOfComplete = 0;
            }
        }
    }


    // 구매된 스페셜 레슨 세팅하기
    private void setUnlockedLessons() {

        List<String> lessonUnlock = userInformation.getSpecialLessonUnlock();

        System.out.println("LESSON_UNLOCK:" + lessonUnlock);

        if(lessonUnlock != null) {

            for(int i=0; i<groupTitle.length; i++) {

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
            //setUserPoint();
            setCompletedLessons();
            setUnlockedLessons();
            adapter.notifyDataSetChanged();
        }
    }
}


