package net.awesomekorean.podo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import net.awesomekorean.podo.writing.WritingEntity;
import net.awesomekorean.podo.writing.WritingFrame;
import net.awesomekorean.podo.writing.WritingItems;
import net.awesomekorean.podo.writing.WritingAdapter;
import net.awesomekorean.podo.writing.WritingRepository;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class MainWriting extends Fragment implements View.OnClickListener {

    View view;

    ListView listView;
    ArrayList<WritingItems> list = new ArrayList<>(); // 리스트뷰에 표시될 아이템들 people10 개씩 끊음
    ArrayList<WritingItems> listAllData;
    WritingAdapter adapter;

    WritingRepository repository;
    Observer<List<WritingEntity>> observer;

    ProgressBar progressBar;

    TextView msgNoWriting; // 아직 작성한 글이 없습니다.

    ImageButton btnAddWriting; // 플로팅 버튼

    public static LinearLayout msgDelete; // 삭제 확인 메시지 창
    Button btnYes;
    Button btnNo;

    public static MainWriting newInstance() {
        return new MainWriting();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_writing, container, false);

        msgNoWriting = view.findViewById(R.id.msgNoWriting);
        btnAddWriting = view.findViewById(R.id.btnAddWriting);
        listView = view.findViewById(R.id.listViewWriting);
        msgDelete = view.findViewById(R.id.msgDelete);
        btnYes = view.findViewById(R.id.btnYes);
        btnNo = view.findViewById(R.id.btnNo);
        btnAddWriting.setOnClickListener(this);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);


        setListViewFooter();

        repository = new WritingRepository(getContext());

        // 리스트에 변화가 생기면 작동
        observer = new Observer<List<WritingEntity>>() {
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
                    items.setId(entity.getId());
                    items.setFirstDate(entity.getFirstDate());
                    items.setLastDate(entity.getLastDate());
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
        };

        repository.getAll().observe(this, observer);

        // 리스트의 아이템 클릭 이벤트
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("Hello");
                WritingItems item = (WritingItems) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getContext(), WritingFrame.class);
                intent.putExtra(getString(R.string.EXTRA_ID), item.getId());
                intent.putExtra(getString(R.string.EXTRA_LETTERS), item.getLetters());
                intent.putExtra(getString(R.string.EXTRA_ARTICLE), item.getArticle());
                intent.putExtra(getString(R.string.REQUEST), getString(R.string.REQUEST_EDIT));
                startActivityForResult(intent, getResources().getInteger(R.integer.REQUEST_CODE_EDIT));
            }
        });


        // 리스트뷰 스크롤 이벤트
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {

                if(scrollState == SCROLL_STATE_IDLE && listView.getLastVisiblePosition() == list.size()) {
                    progressBar.setVisibility(View.VISIBLE);
                    loadMoreItems();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

        return view;
    }


    // 리스트뷰 맨 마지막까지 스크롤 하면 아이템 더 불러옴
    public void loadMoreItems() {
        int size = list.size();

        for(int i=0;i<10;i++){
            if((size + i+1) <= listAllData.size()){
                list.add(listAllData.get(size + i));
            } else {
                break;
            }
        }
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
    }


    // 리스트뷰 footer 로 progressbar 설정함
    public void setListViewFooter() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_footer, null);
        progressBar = view.findViewById(R.id.progressBar);
        listView.addFooterView(progressBar);
    }

    // 글쓰기 추가/수정 후 결과 받기
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK) {
            repository.getAll().observe(this, observer);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnAddWriting :
                Intent intent = new Intent(getContext(), WritingFrame.class);
                intent.putExtra(getString(R.string.REQUEST), getString(R.string.REQUEST_ADD));
                startActivityForResult(intent, getResources().getInteger(R.integer.REQUEST_CODE_ADD));
                break;

            case R.id.btnYes :
                int id = WritingAdapter.id;
                WritingRepository repository = new WritingRepository(getContext());
                repository.deleteById(id);
                repository.getAll();
                repository.getAll().observe(this, observer);
                msgDelete.setVisibility(View.GONE);
                break;

            case R.id.btnNo :
                msgDelete.setVisibility(View.GONE);
                break;
        }
    }
}