package net.awesomekorean.podo.lesson;

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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
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
import net.awesomekorean.podo.purchase.TopUp;

import java.util.ArrayList;
import java.util.List;

import static net.awesomekorean.podo.MainActivity.btnLesson;

public class MainLesson extends Fragment implements View.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    RewardedAd rewardedAd;


    public static LessonItem lessonUnit;

    int specialLessonPrice = 100;

    Context context;

    View view;

    Intent intent;

    MainActivity mainActivity;

    ArrayList<LessonItem> list;
    LessonAdapter adapter;

    LinearLayout unlockLayout;
    LinearLayout unlockFirst;
    LinearLayout unlockSecond;
    TextView pointHave;
    TextView pointNeed;
    Button btnYes;
    Button btnNo;
    Button btnPurchasePoints;
    Button btnWatchAds;
    ImageView btnClose;

    UserInformation userInformation = MainActivity.userInformation;
    int point; //유저 포인트


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
        btnYes = view.findViewById(R.id.btnYes);
        btnNo = view.findViewById(R.id.btnNo);
        btnPurchasePoints = view.findViewById(R.id.btnPurchasePoints);
        btnWatchAds = view.findViewById(R.id.btnWatchAds);
        btnClose = view.findViewById(R.id.btnClose);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
        btnPurchasePoints.setOnClickListener(this);
        btnWatchAds.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        list = new ArrayList<>();

        LessonItem[] items = {
                new S_Lesson00(), new Lesson00(), new S_Lesson01(), new Lesson01(), new S_Lesson02(),
                new Lesson02(), new S_Lesson03(), new Lesson03(), new S_Lesson04(), new Lesson04(),
                new S_Lesson05(), new Lesson05(), new S_Lesson06(), new Lesson06(), new S_Lesson07(),
                new Lesson07(), new Lesson08(), new S_Lesson08(), new Lesson09(), new Lesson10(), new Lesson11(),
                new Lesson12(), new Lesson13(), new Lesson14()
        };

        for(LessonItem item : items) {
            list.add(item);
        }


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LessonAdapter(list);

        setCompletedLessons();
        setUnlockedLessons();

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
                    point = userInformation.getPoints();
                    if(point < specialLessonPrice) {
                        pointHave.setTextColor(Color.RED);
                        btnYes.setEnabled(false);
                    }
                    pointHave.setText(String.valueOf(point));
                    unlockLayout.setVisibility(View.VISIBLE);
                    unlockFirst.setVisibility(View.VISIBLE);
                    unlockSecond.setVisibility(View.GONE);
                }

            }
        });

        recyclerView.setAdapter(adapter);

        context = getContext();

        rewardedAd = createAndLoadRewardedAd();

        return view;
    }

    // 리워드 광고 로드하기
    public RewardedAd createAndLoadRewardedAd() {
        rewardedAd = new RewardedAd(context, getString(R.string.ADMOB_TEST_ID_REWARDED));
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                System.out.println("광고를 로드했습니다");
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
        return rewardedAd;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnYes :
                if(point >= specialLessonPrice) {
                    unlockLayout.setVisibility(View.GONE);

                    // 포인트 차감하고 specialLessonUnlock 에 레슨아이디 추가, 해당 레슨에 unlock = true 세팅
                    int newPoint = userInformation.getPoints() - specialLessonPrice;
                    String lessonId = lessonUnit.getLessonId();
                    userInformation.setPoints(newPoint);
                    userInformation.addSpecialLessonUnlock(lessonId);

                    DocumentReference informationRef = db.collection(getString(R.string.DB_USERS)).document(MainActivity.userEmail).collection(getString(R.string.DB_INFORMATION)).document(getString(R.string.DB_INFORMATION));
                    informationRef.set(userInformation).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            SharedPreferencesInfo.setUserInfo(context, userInformation);
                            setUnlockedLessons();
                            System.out.println("스페셜 레슨을 포인트로 구매했습니다.");
                            Toast.makeText(context, getString(R.string.SUCCEEDED_UNLOCK_LESSON), Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, getString(R.string.FAILED_UNLOCK_LESSON), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    unlockFirst.setVisibility(View.GONE);
                    unlockSecond.setVisibility(View.VISIBLE);
                }

                break;

            case R.id.btnPurchasePoints :
                unlockLayout.setVisibility(View.GONE);
                intent = new Intent(context, TopUp.class);
                startActivity(intent);
                break;

            case R.id.btnWatchAds :
                break;

            default: // btnNo, btnClose
                unlockLayout.setVisibility(View.GONE);
                break;
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
            mainActivity.setMainBtns(btnLesson, R.drawable.lesson_active, R.string.LESSON);
        }
    }
}


