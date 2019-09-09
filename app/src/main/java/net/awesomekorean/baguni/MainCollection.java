package net.awesomekorean.baguni;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.awesomekorean.baguni.collection.CollectionEntity;
import net.awesomekorean.baguni.collection.CollectionFlashCard;
import net.awesomekorean.baguni.collection.CollectionItems;
import net.awesomekorean.baguni.collection.CollectionListViewAdapter;
import net.awesomekorean.baguni.collection.CollectionRepository;
import net.awesomekorean.baguni.collection.CollectionStudy;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class MainCollection extends Fragment implements Button.OnClickListener{
    public static final int REQUEST_CODE_ADD = 100;
    public static final int REQUEST_CODE_EDIT = 200;
    public static final int VISIBLE_ON = 0;
    public static final String REQUEST_EDIT = "edit";
    public static final String REQUEST_ADD = "add";
    public static final String REQUEST = "request";
    public static final String TEXT_FRONT = "front";
    public static final String TEXT_BACK = "back";
    public static final String TEXT_ON = "On";
    public static final String TEXT_OFF = "Off";


    View view;

    public static CheckBox selectAll; // 전체 선택/해제 체크박스

    ListView listView;  // 리스트뷰
    ArrayList<CollectionItems> list = new ArrayList<>();    // 리스트뷰 생성을 위한 arrayList (20개 씩 끊어서 로드함)
    ArrayList<CollectionItems> listAllData; // 리스트뷰에 들어갈 모든 데이터를 불러옴
    ArrayList<CollectionItems> listCopy;// 검색 기능을 위해 리스트뷰 복사함
    CollectionListViewAdapter adapter;  // 리스트뷰 어뎁터

    ProgressBar progressBar;

    Button btnStudy;    // 컬렉션을 flash card 로 공부
    static Button btnDelete;   // 컬렉션 삭제 버튼
    static Button btnRecord;   // 녹음 요청 버튼
    FloatingActionButton btnAdd;    // 컬렉션 추가용 floating 버튼

    EditText searchEdit;    // 검색 입력
    ImageButton searchCancel;   // 검색 취소

    Boolean longItemClicked = false;

    Intent intent;

    CollectionRepository repository;

    Observer<List<CollectionEntity>> observer;

    LinearLayout msgDelete;
    Button btnYes;
    Button btnNo;

    TextView msgNoCollection;

    public static TextView collectionNo;

    int index;  // 클릭 한 리스트 뷰의 인덱스
    public static int isChecked = 0; // 클릭된 아이템이 있는지 확인하기 위한 변수
    public static int size; //  컬렉션 개수


    public static MainCollection newInstance() {
        return new MainCollection();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_collection, container, false);

        selectAll = view.findViewById(R.id.checkBoxSelectAll);
        btnStudy = view.findViewById(R.id.btnStudy);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnRecord = view.findViewById(R.id.btnRecord);
        btnAdd = view.findViewById(R.id.btnAddCollection);
        searchEdit = view.findViewById(R.id.searchCollection);
        searchCancel = view.findViewById(R.id.searchCancel);
        msgDelete = view.findViewById(R.id.msgDelete);
        btnYes = view.findViewById(R.id.btnYes);
        btnNo = view.findViewById(R.id.btnNo);
        collectionNo = view.findViewById(R.id.collectionNo);
        msgNoCollection = view.findViewById(R.id.msgNoCollection);
        selectAll.setOnClickListener(this);
        btnStudy.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnRecord.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        searchCancel.setOnClickListener(this);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);

        listView = view.findViewById(R.id.listViewCollection);

        repository = new CollectionRepository(getContext());
        //repository.deleteAll();

        setListViewFooter();

        // DB 의 getAll() 에 변화가 감지되면 listView 로 값들을 가져옴
        observer = new Observer<List<CollectionEntity>>() {
            @Override
            public void onChanged(@Nullable List<CollectionEntity> collectionEntities) {

                repository.getCount();
                listAllData = new ArrayList<>();

                if(collectionEntities.size() == 0) {
                    msgNoCollection.setVisibility(View.VISIBLE);
                }else{
                    msgNoCollection.setVisibility(View.GONE);
                }

                for (CollectionEntity entity : collectionEntities) {

                    CollectionItems items = new CollectionItems();
                    items.setCollectionFront(entity.getFront());
                    items.setCollectionBack(entity.getBack());
                    items.setId(entity.getId());
                    listAllData.add(items);
                }
                if(collectionEntities.size()>10) {
                    list = new ArrayList<>(listAllData.subList(0,10));
                } else {
                    list = listAllData;
                    progressBar.setVisibility(View.GONE);
                }

                adapter = new CollectionListViewAdapter(getContext(), list);
                listView.setAdapter(adapter);

                listCopy = new ArrayList<>();
                listCopy.addAll(list);  //  검색 기능을 위해 list 내용 복사
            }
        };

        // DB의 플래쉬 카드를 listView 로 가져오기
        repository.getAll().observe(this, observer);


        // 검색 뷰에 입력을 하는지 확인하는 리스너
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchCancel.setVisibility(View.VISIBLE);
                String text = searchEdit.getText().toString();  // 검색 뷰에 입력이 있을 때마다 실행
                search(text);
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


        // 리스트의 아이템 클릭 이벤트
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                CollectionItems item = (CollectionItems) adapterView.getItemAtPosition(i);

                if(longItemClicked == true) {
                    if(item.getChecked()){
                        item.setChecked(false);
                        isChecked--;
                        selectAll.setChecked(false);

                        if(isChecked == 0) { // 체크된 아이템이 하나도 없을 때
                            btnEnabled(false);
                        }

                    } else {
                        btnEnabled(true);
                        item.setChecked(true);
                        isChecked++;
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    intent = new Intent(getContext(), CollectionFlashCard.class);
                    intent.putExtra(TEXT_FRONT, item.getCollectionFront());
                    //intent.putExtra("ITEM", item.getId());
                    intent.putExtra(TEXT_BACK, item.getCollectionBack());
                    //intent.putExtra(TEXT_INDEX, i);
                    intent.putExtra(REQUEST, REQUEST_EDIT);
                    index = item.getId();
                    startActivityForResult(intent, REQUEST_CODE_EDIT);
                }

            }
        });


        // 리스트뷰 아이템 롱 클릭 이벤트
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                checkBoxOnOff();
                return true;
            }
        });
        return view;
    }

    public void checkBoxOnOff() {
        if(selectAll.getVisibility()==View.INVISIBLE) {
            ItemLongClicked(true, View.VISIBLE, View.GONE, View.VISIBLE);
            adapter.longClickOnOff(TEXT_ON);
            isChecked = 0;
            selectAll.setChecked(false);
            btnEnabled(false);

        } else {
            ItemLongClicked(false, View.INVISIBLE, View.VISIBLE, View.GONE);
            adapter.longClickOnOff(TEXT_OFF);
        }

        adapter.notifyDataSetChanged();
    }

    // Delete, Record 버튼 켜기/끄기
    public static void btnEnabled(boolean b) {
        btnDelete.setEnabled(b);
        btnRecord.setEnabled(b);
    }

    public void ItemLongClicked(boolean b, int setSelectAll, int setStudy, int setBtns) {
        longItemClicked = b;
        selectAll.setVisibility(setSelectAll);
        btnStudy.setVisibility(setStudy);
        btnDelete.setVisibility(setBtns);
        btnRecord.setVisibility(setBtns);
    }


    // 리스트뷰 맨 마지막까지 스크롤 하면 아이템 더 불러옴
    public void loadMoreItems() {
        int size = list.size();

        for(int i=0;i<10;i++){
            if((size + i+1) <= listAllData.size()){
                list.add(listAllData.get(size + i));
                if(selectAll.getVisibility() == View.VISIBLE) { // 체크박스 on 상태에서 아이템을 더 불러올 경우
                    CollectionItems items;
                    items = list.get(list.size()-1);
                    items.setVisible(VISIBLE_ON);
                }
            } else {
                break;
            }
        }
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
    }


    // 리스트뷰 footer 로 progressbar 설정함
    public void setListViewFooter() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_collection_footer, null);
        progressBar = view.findViewById(R.id.progressBar);
        listView.addFooterView(progressBar);
    }


    // Flash Card 수정/추가 후 결과 받기
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK) {

            // 추가 된 플래시 카드를 리스트뷰에 반영
            if(requestCode == REQUEST_CODE_ADD) {
                repository.getAll().observe(this, observer);

            // 수정 된 플래시 카드를 리스트뷰에 반영
            } else {
                final String front = data.getStringExtra(TEXT_FRONT);
                final String back = data.getStringExtra(TEXT_BACK);
                repository.editById(index, front, back);
            }
        }
    }


    // 검색 뷰에 입력한 내용을 listAllData 와 비교해서 출력
    public void search(String text) {

        list.clear(); // 입력이 발생하면 리스트를 지움.

        if(text.length() == 0) {
            searchCancel.setVisibility(View.GONE);
            list.addAll(listCopy); // 입력을 지우면 원래 리스트 출력

        } else {

            for(int i=0; i<listAllData.size(); i++) {

                if(listAllData.get(i).equals(text)) { // 조건에 맞는 리스트만 출력
                    list.add(listAllData.get(i));
                }
            }
        }
        adapter.notifyDataSetChanged();
    }


    // 버튼들 클릭 이벤트
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.checkBoxSelectAll :
                if(selectAll.isChecked()) {
                    adapter.checkAll(true);
                    isChecked = list.size();
                    btnEnabled(true);
                } else {
                    adapter.checkAll(false);
                    isChecked = 0;
                    btnEnabled(false);
                }

                adapter.notifyDataSetChanged();
                break;

            case R.id.btnStudy :
                intent = new Intent(getContext(), CollectionStudy.class);
                startActivity(intent);
                break;

            case R.id.btnDelete :
                msgDelete.setVisibility(View.VISIBLE);
                break;

            case R.id.btnYes :
                ArrayList<Integer> checkedList = new ArrayList<>();

                for(CollectionItems items : listAllData) {
                    if(items.getChecked()) {
                        checkedList.add(items.getId());
                    }
                }
                if(checkedList != null) {

                    for(int id : checkedList) {

                        repository.deleteById(id);
                    }
                }
                ItemLongClicked(false, View.INVISIBLE, View.VISIBLE, View.GONE);
                msgDelete.setVisibility(View.GONE);
                break;

            case R.id.btnNo :
                msgDelete.setVisibility(View.GONE);
                break;

            case R.id.btnRecord :
                break;

            case R.id.btnAddCollection :
                intent = new Intent(getContext(), CollectionFlashCard.class);
                intent.putExtra(REQUEST, REQUEST_ADD);
                startActivityForResult(intent,REQUEST_CODE_ADD);
                break;

            case R.id.searchCancel :
                searchEdit.setText("");
                break;
        }
    }
}
