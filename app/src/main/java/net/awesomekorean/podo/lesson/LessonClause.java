package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
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

import net.awesomekorean.podo.R;

import java.util.ArrayList;

public class LessonClause extends Fragment implements Button.OnClickListener {

    View view;

    RecyclerView recyclerView;

    ImageView btnReturn;
    ImageView btnPlay;
    ImageView btnFinish;

    MediaPlayer mediaPlayer;

    ArrayList<LessonClauseItems> list = new ArrayList<>();

    public static LessonClause newInstance() {
        return new LessonClause();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_clause, container, false);

        String[] clauses =  LessonWord.sentenceClause;

        btnReturn = view.findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);
        btnPlay = view.findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);
        btnFinish = view.findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        LessonClauseItems item = new LessonClauseItems();
        item.setPeopleImage(R.drawable.people5);
        item.setClause("안녕하세요");
        item.setAOrB(0);
        list.add(item);

        LessonClauseItems item1 = new LessonClauseItems();
        item1.setPeopleImage(R.drawable.people5);
        item1.setClause("빠이");
        item1.setAOrB(0);
        list.add(item1);

        LessonClauseItems item2 = new LessonClauseItems();
        item2.setPeopleImage(R.drawable.people6);
        item2.setClause("안녕하세요");
        item2.setAOrB(1);
        list.add(item2);

        LessonClauseItems item3 = new LessonClauseItems();
        item3.setPeopleImage(R.drawable.people6);
        item3.setClause("빠이");
        item3.setAOrB(1);
        list.add(item3);
        list.add(item3);
        list.add(item3);
        list.add(item3);
        list.add(item3);
        list.add(item3);
        list.add(item3);
        list.add(item3);
        list.add(item3);
        list.add(item3);
        list.add(item3);
        list.add(item3);


        LessonClauseAdapter adapter = new LessonClauseAdapter(list);
        adapter.setOnItemClickListener(new LessonClauseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                final ToggleButton button = (ToggleButton) v;
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.correct);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        button.setChecked(false);
                    }
                });
            }
        });

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnReturn :
                break;

            case R.id.btnPlay :
                break;

            case R.id.btnFinish :
                LessonFrame.progressCount++;
                LessonFrame.progressCount();
                ((LessonFrame)getActivity()).replaceFragment(LessonFinish.newInstance());
                break;
        }
    }
}
