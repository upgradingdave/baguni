package net.awesomekorean.podo.qna;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.writing.WritingAdapter;
import net.awesomekorean.podo.writing.WritingCorrected;
import net.awesomekorean.podo.writing.WritingEntity;
import net.awesomekorean.podo.writing.WritingFrame;
import net.awesomekorean.podo.writing.WritingRepository;
import net.awesomekorean.podo.writing.WritingReturned;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class MainQnA extends Fragment implements View.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    View view;

    ListView listView;
    LinearLayout layoutQnAInfo;
    public static LinearLayout layoutDelete;
    Button btnNo;
    Button btnYes;
    Button btnAskQuestion;

    ProgressBar progressBar;
    QnARepository repository;
    Observer<List<QnAEntity>> observer;
    ArrayList<QnAEntity> list = new ArrayList<>(); // 리스트뷰에 표시될 아이템들 10 개씩 끊음
    ArrayList<QnAEntity> listAllData;
    QnAAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_qna, container, false);

        listView = view.findViewById(R.id.listView);
        layoutQnAInfo = view.findViewById(R.id.layoutQnAInfo);
        layoutDelete = view.findViewById(R.id.layoutDelete);
        btnNo = view.findViewById(R.id.btnNo);
        btnYes = view.findViewById(R.id.btnYes);
        btnAskQuestion = view.findViewById(R.id.btnAskQuestion);
        btnNo.setOnClickListener(this);
        btnYes.setOnClickListener(this);
        btnAskQuestion.setOnClickListener(this);

        setListViewFooter();

        repository = new QnARepository(getContext());

        // 리스트에 변화가 생기면 작동
        observer = new Observer<List<QnAEntity>>() {
            @Override
            public void onChanged(@Nullable List<QnAEntity> entities) {
                listAllData = new ArrayList<>();

                if(entities.size() == 0) {
                    layoutQnAInfo.setVisibility(View.VISIBLE);
                }else{
                    layoutQnAInfo.setVisibility(View.GONE);
                }

                for(QnAEntity entity : entities) {
                    QnAEntity items = new QnAEntity();
                    items.setGuid(entity.getGuid());
                    items.setUserEmail(entity.getUserEmail());
                    items.setUserName(entity.getUserName());
                    items.setQuestion(entity.getQuestion());
                    items.setAnswer(entity.getAnswer());
                    items.setStatus(entity.getStatus());
                    items.setDateQuestion(entity.getDateQuestion());
                    items.setDateQuestion(entity.getDateQuestion());
                    items.setUserToken(entity.getUserToken());
                    listAllData.add(items);

                    // 질문요청이 있으면, DB 접속
                    if (items.getStatus() == 1) {
                        DocumentReference docRef = db.collection(getString(R.string.DB_QNA)).document(items.getGuid());
                        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                QnAEntity download = documentSnapshot.toObject(QnAEntity.class);

                                if (download != null && download.getStatus() > 1) {
                                    System.out.println("질문에 대한 답변이 있습니다");
                                    QnARepository repository = new QnARepository(getContext());
                                    repository.update(download);
                                    repository.getAll().observe(getViewLifecycleOwner(), observer);
                                }
                            }
                        });
                    }
                }

                if(entities.size()>10) {
                    list = new ArrayList<>(listAllData.subList(0,10));
                }else {
                    list = new ArrayList<>();
                    list = listAllData;
                    progressBar.setVisibility(View.GONE);
                }

                adapter = new QnAAdapter(getContext(), list);
                listView.setAdapter(adapter);
            }
        };

        repository.getAll().observe(this, observer);


        // 리스트의 아이템 클릭 이벤트
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                QnAEntity item = (QnAEntity) adapterView.getItemAtPosition(i);
                Intent intent;

                if(item.getStatus() == 0) {
                    intent = new Intent(getContext(), QnAFrame.class);

                } else {
                    intent = new Intent(getContext(), QnAReplied.class);
                }

                intent.putExtra(getString(R.string.EXTRA_ENTITY), item);
                startActivity(intent);

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
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnNo :
                layoutDelete.setVisibility(View.GONE);
                break;

            case R.id.btnYes :
                String guid = QnAAdapter.guid;
                QnARepository repository = new QnARepository(getContext());
                repository.deleteByGuid(guid);
                repository.getAll();
                repository.getAll().observe(this, observer);
                layoutDelete.setVisibility(View.GONE);
                break;

            case R.id.btnAskQuestion :
                Intent intent = new Intent(getContext(), QnAFrame.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if(adapter != null) {
            repository.getAll().observe(this, observer);
        }
    }
}
