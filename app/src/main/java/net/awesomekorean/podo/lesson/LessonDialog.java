package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.awesomekorean.podo.MediaPlayerManager;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.lesson.lessons.Lesson;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LessonDialog extends Fragment implements Button.OnClickListener {

    FirebaseStorage storage = FirebaseStorage.getInstance();

    View view;

    RecyclerView recyclerView;

    static ImageView btnPlay;
    static ImageView btnPause;
    Button btnFinish;


    int index; // 전체 오디오 재생 카운트

    ConstraintLayout totalPage;

    static Map<Integer, byte[]> audiosDialog;

    int dialogLength;

    public static LessonDialog newInstance() {
        return new LessonDialog();
    }

    MediaPlayerManager mediaPlayerManager;

    static ToggleButton toggleButton;

    private LessonFrame activity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_dialog, container, false);

        mediaPlayerManager = MediaPlayerManager.getInstance();

        btnPlay = view.findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);
        btnPause = view.findViewById(R.id.btnPause);
        btnPause.setOnClickListener(this);
        btnFinish = view.findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(this);
        totalPage = view.findViewById(R.id.totalPage);
        totalPage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        index = 0;

        // analytics 로그 이벤트 얻기
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(activity);
        Bundle bundle = new Bundle();
        firebaseAnalytics.logEvent("lesson_dialog", bundle);

        setPlayBtn(View.VISIBLE, View.GONE);

        readyForLesson();

        LessonFrame.setNavigationColor(activity, LessonFrame.navigationDialog, R.drawable.bg_purple_10);

        return view;
    }

    private void readyForLesson() {
        if(activity != null) {
            activity.onLoadingLayout(true);
        }
        dialogLength = activity.lesson.getDialog().length;
        String[] dialogAudio = new String[dialogLength];
        int[] peopleImage = activity.lesson.getPeopleImage(); // 사람이미지 2개
        String lessonId = activity.lesson.getLessonId();
        String folder = "lesson/" + lessonId.toLowerCase();

        String packageName = activity.getPackageName();
        String[] dialog = activity.lesson.getDialog();


        // 저장소에서 대화오디오 가져오기
        audiosDialog = new HashMap<>();
        for(int i=0; i<dialogLength; i++) {
            final Integer audioIndexDialog = i;
            dialogAudio[i] = lessonId.toLowerCase() + "_dialog_" + i + ".mp3";
            StorageReference storageRef = storage.getReference().child(folder).child(dialogAudio[i]);
            final long ONE_MEGABYTE = 1024 * 1024;
            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    System.out.println("오디오를 로드했습니다.");
                    audiosDialog.put(audioIndexDialog, bytes);
                    if(audiosDialog.size() == dialogLength) {
                        if(activity != null) {
                            activity.onLoadingLayout(false);
                        }
                    }
                }
            });
        }


        // 대화 개수에 맞게 사람이미지를 array 로 만듦
        int[] arrayPeopleImage = new int[dialogLength];
        int count = 0;
        for(int i=0; i<dialogLength; i++) {
            String stringPeopleImage;
            if(count == 0) {
                stringPeopleImage = "people" + peopleImage[0];
                count = 1;
            } else {
                stringPeopleImage = "people" + peopleImage[1];
                count = 0;
            }
            int intPeopleImage = getResources().getIdentifier(stringPeopleImage, "drawable", packageName);
            arrayPeopleImage[i] = intPeopleImage;
        }


        // 대화아이템 세팅
        ArrayList<LessonDialogItems> list = new ArrayList<>();
        for(int i=0; i<dialogLength; i++) {
            LessonDialogItems item = new LessonDialogItems();
            item.setPeopleImage(arrayPeopleImage[i]);
            item.setDialog(dialog[i]);
            if(i % 2 == 0) {
                item.setAOrB(1);
            }else {
                item.setAOrB(0);
            }
            list.add(item);
        }

        LessonDialogAdapter adapter = new LessonDialogAdapter(list);

        // 대화 아이템 클릭 이벤트
        adapter.setOnItemClickListener(new LessonDialogAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                setToggleBtnUnChecked();

                toggleButton = (ToggleButton) v;
                mediaPlayerManager.stopMediaPlayer();
                mediaPlayerManager.setMediaPlayerByte(true, audiosDialog.get(pos));
                setPlayBtn(View.VISIBLE, View.GONE);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    public static void setToggleBtnUnChecked() {
        if(toggleButton != null) {
            toggleButton.setChecked(false);
        }
    }


    public static void setPlayBtn(int btnPlayVisible, int btnPauseVisible) {
        btnPlay.setVisibility(btnPlayVisible);
        btnPause.setVisibility(btnPauseVisible);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnPlay :
                setToggleBtnUnChecked();
                setPlayBtn(View.GONE, View.VISIBLE);
                mediaPlayerManager.setAndPlayAllDialog(audiosDialog);
                break;

            case R.id.btnPause :
                setPlayBtn(View.VISIBLE, View.GONE);
                break;

            case R.id.btnFinish :

                // 완료리스트에 업데이트
                UserInformation userInformation = SharedPreferencesInfo.getUserInfo(activity);
                userInformation.updateCompleteList(activity, activity.lesson.getLessonId(), 100, false);

                Intent intent = new Intent(activity, LessonFinish.class);
                startActivity(intent);
                activity.finish();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof LessonFrame) {
            activity = (LessonFrame) context;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mediaPlayerManager.stopMediaPlayer();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
    }
}
