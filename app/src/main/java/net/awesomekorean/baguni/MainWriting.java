package net.awesomekorean.baguni;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.awesomekorean.baguni.writing.WritingEntity;
import net.awesomekorean.baguni.writing.WritingFrame;
import net.awesomekorean.baguni.writing.WritingItems;
import net.awesomekorean.baguni.writing.WritingAdapter;
import net.awesomekorean.baguni.writing.WritingRepository;

import java.util.ArrayList;
import java.util.List;

public class MainWriting extends Fragment {

    View view;

    ListView listView;
    ArrayList<WritingItems> list; // 리스트뷰에 표시될 아이템들
    ArrayList<WritingItems> listAllData;
    WritingAdapter adapter;

    ProgressBar progressBar;

    TextView msgNoWriting;

    FloatingActionButton btnAddWriting; // 플로팅 버튼

   public static MainWriting newInstance() {
        return new MainWriting();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_writing, container, false);

        listView = view.findViewById(R.id.listViewWriting);
        msgNoWriting = view.findViewById(R.id.msgNoWriting);
        btnAddWriting = view.findViewById(R.id.btnAddWriting);
        btnAddWriting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), WritingFrame.class);
                startActivity(intent);
            }
        });

        setListViewFooter();

        WritingRepository repository = new WritingRepository(getContext());
        repository.getAll().observe(this, new Observer<List<WritingEntity>>() {
            @Override
            public void onChanged(@Nullable List<WritingEntity> entities) {

                listAllData = new ArrayList<>();

                if(entities.size() == 0) {
                    msgNoWriting.setVisibility(View.VISIBLE);
                }else{
                    msgNoWriting.setVisibility(View.GONE);
                }

                for(WritingEntity entity : entities) {

                    WritingItems items = new WritingItems();
                    items.setDate(entity.getDate());
                    items.setLetters(entity.getLetters());
                    items.setArticle(entity.getArticle());
                    items.setIsCorrected(entity.getIsCorrected());
                    listAllData.add(items);
                }
                if(entities.size()>10) {
                    list = new ArrayList<>(listAllData.subList(0,10));
                }else {
                    list = new ArrayList<>();
                    list = listAllData;
                    progressBar.setVisibility(View.GONE);
                }

                adapter = new WritingAdapter(getContext(), list);
                listView.setAdapter(adapter);
            }
        });

        return view;
    }

    // 리스트뷰 footer 로 progressbar 설정함
    public void setListViewFooter() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_footer, null);
        progressBar = view.findViewById(R.id.progressBar);
        listView.addFooterView(progressBar);
    }

}