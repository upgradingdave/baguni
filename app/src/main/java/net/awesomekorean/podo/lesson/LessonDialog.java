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
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import net.awesomekorean.podo.R;

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

    ImageView btnReturn;
    ImageView btnPlay;
    ImageView btnStop;
    ImageView btnFinish;

    MediaPlayer mp;

    Context context;

    int index; // 전체 오디오 재생 카운트

    LinearLayout layoutPlay;
    LinearLayout layoutStop;

    ConstraintLayout totalPage;

    static Map<Integer, byte[]> audiosDialog;

    int dialogLength;

    public static LessonDialog newInstance() {
        return new LessonDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_dialog, container, false);
        context = getContext();
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
        totalPage = view.findViewById(R.id.totalPage);
        totalPage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        index = 0;

        readyForLesson();

        return view;
    }

    private void readyForLesson() {
        if(getActivity() != null) {
            ((LessonFrame)getActivity()).onLoadingLayout(true);
        }
        dialogLength = LessonWord.lessonDialogLength;
        String[] dialogAudio = new String[dialogLength];
        int[] peopleImage = LessonWord.lesson.getPeopleImage(); // 사람이미지 2개
        String lessonId = LessonWord.lessonId;
        String folder = LessonWord.folder;
        String packageName = context.getPackageName();

        String[] dialog =  LessonWord.lesson.getDialog();

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
                    if(getActivity() != null) {
                        ((LessonFrame)getActivity()).onLoadingLayout(false);
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
        adapter.setOnItemClickListener(new LessonDialogAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                final ToggleButton button = (ToggleButton) v;
                playAudio(button, audiosDialog.get(pos));
                layoutPlay.setVisibility(View.VISIBLE);
                layoutStop.setVisibility(View.GONE);

            }
        });

        recyclerView.setAdapter(adapter);
    }


    // 오디오 재생 메소드
    public void playAudio(final ToggleButton button, byte[] audioFile) {
        if (mp != null) {
            mp.release();
        }

        mp = new MediaPlayer();

        try {
            File tempMp3 = File.createTempFile("audio", "mp3");
            tempMp3.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tempMp3);
            BufferedOutputStream bfos = new BufferedOutputStream(fos);
            bfos.write(audioFile);
            fos.close();

            FileInputStream fis = new FileInputStream(tempMp3);
            BufferedInputStream bfis = new BufferedInputStream(fis);

            if(bfis.read(audioFile) != -1) {
                mp.setDataSource(fis.getFD());

                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                    }
                });

                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        button.setChecked(false);
                    }
                });

                mp.prepare();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // 전체 오디오 재생 메소드
    public void playAudioAll(byte[] audioFile) {
        if (mp != null) {
            mp.release();
        }

        mp = new MediaPlayer();

        try {
            File tempMp3 = File.createTempFile("audio", "mp3");
            tempMp3.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tempMp3);
            BufferedOutputStream bfos = new BufferedOutputStream(fos);
            bfos.write(audioFile);
            fos.close();

            FileInputStream fis = new FileInputStream(tempMp3);
            BufferedInputStream bfis = new BufferedInputStream(fis);

            if(bfis.read(audioFile) != -1) {
                mp.setDataSource(fis.getFD());

                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                    }
                });

                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        index++;
                        // 마지막 오디오 재생 끝났으면
                        if(index == dialogLength) {
                            mp.release();
                            index = 0;
                            layoutPlay.setVisibility(View.VISIBLE);
                            layoutStop.setVisibility(View.GONE);
                        }else{
                            mp.release();
                            playAudioAll(audiosDialog.get(index));
                        }
                    }
                });
                mp.prepare();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnReturn :
                if (mp != null) {
                    mp.release();
                }

                LessonFrame.progressCount = 0;
                LessonFrame.progressCount();
                ((LessonFrame)getActivity()).replaceFragment(LessonWord.newInstance());
                break;

            case R.id.btnPlay :
                if (mp != null) {
                    mp.release();
                }
                layoutPlay.setVisibility(View.GONE);
                layoutStop.setVisibility(View.VISIBLE);
                index = 0;
                if(!audiosDialog.isEmpty()) {
                    playAudioAll(audiosDialog.get(0));
                } else  {
                    Toast.makeText(context, "The audio is not loaded yet. Please try it again.", Toast.LENGTH_LONG).show();
                }
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
                if (mp != null) {
                    mp.release();
                }

                Intent intent = new Intent(getContext(), LessonFinish.class);
                startActivity(intent);
                getActivity().finish();
                break;
        }
    }
}
