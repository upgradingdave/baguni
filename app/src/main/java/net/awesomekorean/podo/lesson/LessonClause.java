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
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;

import java.util.ArrayList;

public class LessonClause extends Fragment implements Button.OnClickListener {

    View view;

    RecyclerView recyclerView;

    ImageView btnReturn;
    ImageView btnPlay;
    ImageView btnStop;
    ImageView btnFinish;

    MediaPlayer mp;

    String[] clauses =  LessonWord.sentenceClause;
    String[] clausesAudio = LessonWord.sentenceAudio;
    int[] clausesAorB = LessonWord.sentenceClauseAorB;
    int[] peopleImage = LessonWord.peopleImage;

    ArrayList<LessonClauseItems> list = new ArrayList<>();

    Context context;

    int index; // 전체 오디오 재생 카운트
    int length; // 전체 오디오 개수

    LinearLayout layoutPlay;
    LinearLayout layoutStop;

    public static LessonClause newInstance() {
        return new LessonClause();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_clause, container, false);
        context = getContext();

        index = 0;
        length = clausesAudio.length;


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

        int clausesLength = LessonWord.lessonSentenceLength;

        for(int i=0; i<clausesLength; i++) {
            LessonClauseItems item = new LessonClauseItems();
            item.setPeopleImage(peopleImage[i]);
            item.setClause(clauses[i]);
            item.setAOrB(clausesAorB[i]);
            item.setClauseAudio(clausesAudio[i]);
            list.add(item);
        }


        LessonClauseAdapter adapter = new LessonClauseAdapter(list);
        adapter.setOnItemClickListener(new LessonClauseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                final ToggleButton button = (ToggleButton) v;
                playAudio(button, context, clausesAudio[pos]);
                layoutPlay.setVisibility(View.VISIBLE);
                layoutStop.setVisibility(View.GONE);

            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }

    // 오디오 재생 메소드
    public void playAudio(final ToggleButton button, Context context, String audioFile) {
        if(context != null) {
            if(mp != null) {
                mp.release();
            }
            String uriPath = "android.resource://" + context.getPackageName() + "/raw/" + audioFile;
            Uri uri = Uri.parse(uriPath);
            mp = MediaPlayer.create(context, uri);
            try {
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        button.setChecked(false);
                    }
                });
            }
            catch (Exception e) {}
        }
    }

    // 전체 오디오 재생 메소드
    public void playAudioAll(String audioFile) {
        if(context != null) {
            if(mp != null) {
                mp.release();
            }
            String uriPath = "android.resource://" + context.getPackageName() + "/raw/" + audioFile;
            Uri uri = Uri.parse(uriPath);
            mp = MediaPlayer.create(context, uri);
            try {
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
                            playAudioAll(clausesAudio[index]);
                        }
                    }
                });
            }
            catch (Exception e) {}
        }
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
                playAudioAll(clausesAudio[0]);
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
