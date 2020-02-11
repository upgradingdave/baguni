package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.awesomekorean.podo.R;

import java.io.IOException;
import java.util.ArrayList;

public class LessonDialog extends Fragment implements Button.OnClickListener {

    FirebaseStorage storage = FirebaseStorage.getInstance();

    View view;

    RecyclerView recyclerView;

    ImageView btnReturn;
    ImageView btnPlay;
    ImageView btnStop;
    ImageView btnFinish;

    MediaPlayer mp;

    String[] dialog =  LessonWord.dialog;
    String[] dialogAudio = LessonWord.dialogAudio;
    int[] peopleImage = LessonWord.peopleImage; // 사람이미지 2개
    int[] arrayPeopleImage; // 대화 개수에 맞게 사람이미지를 array 로 만듦

    ArrayList<LessonDialogItems> list = new ArrayList<>();

    Context context;

    int index; // 전체 오디오 재생 카운트
    int length; // 전체 오디오 개수

    LinearLayout layoutPlay;
    LinearLayout layoutStop;

    String folder;

    public static LessonDialog newInstance() {
        return new LessonDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_dialog, container, false);
        context = getContext();

        index = 0;
        length = dialogAudio.length;

        layoutPlay = view.findViewById(R.id.layoutPlay);
        layoutStop = view.findViewById(R.id.layoutStop);
        btnReturn = view.findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);
        btnPlay = view.findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);
        btnStop = view.findViewById(R.id.btnStop);
        btnStop.setOnClickListener(this);
        btnFinish = view.findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        int clausesLength = LessonWord.lessonDialogLength;

        getPeopleImage(peopleImage);

        for(int i=0; i<clausesLength; i++) {
            LessonDialogItems item = new LessonDialogItems();
            item.setPeopleImage(arrayPeopleImage[i]);
            item.setClause(dialog[i]);
            if(i % 2 == 0) {
                item.setAOrB(1);
            }else {
                item.setAOrB(0);
            }
            item.setClauseAudio(dialogAudio[i]);
            list.add(item);
        }


        LessonDialogAdapter adapter = new LessonDialogAdapter(list);
        adapter.setOnItemClickListener(new LessonDialogAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                final ToggleButton button = (ToggleButton) v;
                playAudio(button, dialogAudio[pos]);
                layoutPlay.setVisibility(View.VISIBLE);
                layoutStop.setVisibility(View.GONE);

            }
        });

        recyclerView.setAdapter(adapter);

        folder = "lesson/" + MainLesson.lessonUnit.getLessonId().toLowerCase();

        return view;
    }

    public void getPeopleImage(int[] peopleImage) {
        String packageName = context.getPackageName();
        arrayPeopleImage = new int[dialog.length];
        int count = 0;
        for(int i=0; i<dialog.length; i++) {
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
    }

    // 오디오 재생 메소드
    public void playAudio(final ToggleButton button, String audioFile) {

        StorageReference storageRef = storage.getReference().child(folder).child(audioFile);
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(mp != null) {
                    mp.release();
                }
                String url = uri.toString();
                mp = new MediaPlayer();
                try {
                    mp.setDataSource(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        button.setChecked(false);
                    }
                });
            }
        });
    }

    // 전체 오디오 재생 메소드
    public void playAudioAll(String audioFile) {

        StorageReference storageRef = storage.getReference().child(folder).child(audioFile);
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(mp != null) {
                    mp.release();
                }
                String url = uri.toString();
                mp = new MediaPlayer();
                try {
                    mp.setDataSource(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        index++;
                        // 마지막 오디오 재생 끝났으면
                        if(index == length) {
                            mp.release();
                            index = 0;
                            layoutPlay.setVisibility(View.VISIBLE);
                            layoutStop.setVisibility(View.GONE);
                        }else{
                            mp.release();
                            playAudioAll(dialogAudio[index]);
                        }
                    }
                });
            }
        });
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnReturn :
                LessonFrame.progressCount = 0;
                LessonFrame.progressCount();
                ((LessonFrame)getActivity()).replaceFragment(LessonWord.newInstance());
                break;

            case R.id.btnPlay :
                layoutPlay.setVisibility(View.GONE);
                layoutStop.setVisibility(View.VISIBLE);
                index = 0;
                playAudioAll(dialogAudio[0]);
                break;

            case R.id.btnStop :
                if(mp.isPlaying()) {
                    mp.stop();
                    mp.release();
                }
                index = 0;

                layoutPlay.setVisibility(View.VISIBLE);
                layoutStop.setVisibility(View.GONE);
                break;

            case R.id.btnFinish :
                Intent intent = new Intent(getContext(), LessonFinish.class);
                startActivity(intent);
                break;
        }
    }
}
