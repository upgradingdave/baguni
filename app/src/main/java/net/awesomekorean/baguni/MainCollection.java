package net.awesomekorean.baguni;

import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

import net.awesomekorean.baguni.collection.CollectionDb;
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
    public static final int REQUEST_CODE = 100;

    public static final String REQUEST_EDIT = "edit";
    public static final String REQUEST_ADD = "add";
    public static final String REQUEST = "request";
    public static final String TEXT_FRONT = "front";
    public static final String TEXT_BACK = "back";
    public static final String TEXT_INDEX = "index";


    View view;

    CheckBox selectAll; // 전체 선택/해제 체크박스

    ListView listView;  // 리스트뷰
    CollectionItems items = new CollectionItems();
    ArrayList<CollectionItems> list = new ArrayList<>();    // 리스트뷰 생성을 위한 arrayList (20개 씩 끊어서 로드함)
    ArrayList<CollectionItems> listAllData; // 리스트뷰에 들어갈 모든 데이터를 불러옴
    ArrayList<CollectionItems> listCopy;// 검색 기능을 위해 리스트뷰 복사함
    CollectionListViewAdapter adapter;  // 리스트뷰 어뎁터

    ProgressBar progressBar;

    Button btnStudy;    // 컬렉션을 flash card 로 공부
    Button btnDelete;   // 컬렉션 삭제 버튼
    Button btnRecord;   // 녹음 요청 버튼
    FloatingActionButton btnAdd;    // 컬렉션 추가용 floating 버튼

    EditText searchEdit;    // 검색 입력
    ImageButton searchCancel;   // 검색 취소

    Intent intent;

    CollectionDb db;

    CollectionRepository repository;

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
        selectAll.setOnClickListener(this);
        btnStudy.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnRecord.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        searchCancel.setOnClickListener(this);

        listView = view.findViewById(R.id.listViewCollection);
        //setListViewFooter();

        repository = new CollectionRepository(getContext());

        repository.deleteAll();

        repository.getAll().observe(this, new Observer<List<CollectionEntity>>() {
            @Override
            public void onChanged(@Nullable List<CollectionEntity> collectionEntities) {

                for (CollectionEntity entity : collectionEntities) {

                    items.setCollectionFront(entity.getFront());
                    items.setCollectionBack(entity.getBack());
                    System.out.println("FRONT: " +entity.getFront());
                    list.add(items);
                }
                adapter = new CollectionListViewAdapter(getContext(), list);
                listView.setAdapter(adapter);

            }
        });


        //String front = "22";
        //String back = "22";
        //repository.insert(front, back);


        //list = new ArrayList<>(listAllData.subList(0,10));

        //listCopy = new ArrayList<>();
        //listCopy.addAll(list);  //  검색 기능을 위해 list 내용 복사



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
                    //progressBar.setVisibility(View.VISIBLE);
                    //loadMoreItems();
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
                intent = new Intent(getContext(), CollectionFlashCard.class);
                //intent.putExtra("Korean", item.getCollectionKorean());
                //intent.putExtra("English", item.getCollectionEnglish());
                intent.putExtra(TEXT_INDEX, i);
                intent.putExtra(REQUEST, REQUEST_EDIT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });


        // 리스트뷰 아이템 롱 클릭 이벤트
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(selectAll.getVisibility()==View.INVISIBLE) {
                    selectAll.setVisibility(View.VISIBLE);
                    adapter.longClickOnOff("On");
                    btnStudy.setVisibility(View.GONE);
                    btnDelete.setVisibility(View.VISIBLE);
                    btnRecord.setVisibility(View.VISIBLE);
                } else {
                    selectAll.setVisibility(View.INVISIBLE);
                    adapter.longClickOnOff("Off");
                    btnStudy.setVisibility(View.VISIBLE);
                    btnDelete.setVisibility(View.GONE);
                    btnRecord.setVisibility(View.GONE);
                }
                adapter.notifyDataSetChanged();
                return true;
            }
        });


        return view;
    }

    // 리스트뷰 맨 마지막까지 스크롤 하면 아이템 더 불러옴
    public void loadMoreItems() {
        int size = list.size();
        for(int i=1;i<=10;i++){
            if((size + i) < listAllData.size()){
                list.add(listAllData.get(size + i));
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

            String front = data.getStringExtra(TEXT_FRONT);
            String back = data.getStringExtra(TEXT_BACK);

            repository.insert(front, back);
            //db.collectionDao().insert(new CollectionEntity(front, back));



            //adapter = new CollectionListViewAdapter(getContext(), list);
            //listView.setAdapter(adapter);

            /*
            listAllData = getCollection();
            list = new ArrayList<>(listAllData.subList(0,10));
            adapter = new CollectionListViewAdapter(getContext(), list);
            listView.setAdapter(adapter);
            */
        }
    }


    // 검색 뷰에 입력한 내용을 list 와 비교해서 출력
    public void search(String text) {

        /*

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
        */
    }


    // DataBase 에서 collection 불러오기
    public ArrayList<CollectionItems> getCollectionsFromDb() {

        final ArrayList<CollectionItems> list = new ArrayList<>();
        final CollectionItems items = new CollectionItems();

        repository.getAll().observe(this, new Observer<List<CollectionEntity>>() {
            @Override
            public void onChanged(@Nullable List<CollectionEntity> collectionEntities) {
                for(CollectionEntity collections : collectionEntities) {
                    items.setCollectionFront(collections.getFront());
                    items.setCollectionBack(collections.getBack());
                    System.out.println("FRONT: " +collections.getFront());
                    System.out.println("BACK: " +collections.getBack());
                    list.add(items);
                }
            }
        });

        return list;

        /*

        ArrayList<CollectionItems> list = new ArrayList<>();

        int count = db.collectionDao().getCount();

        for(int i=0; i<count; i++) {

            CollectionItems items = new CollectionItems();
            items.setCollectionFront(db.collectionDao().getFrontById(i));
            items.setCollectionBack(db.collectionDao().getBackById(i));
            list.add(items);
        }
        return list;
        */
    }


    // 버튼들 클릭 이벤트
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.checkBoxSelectAll :
                if(selectAll.isChecked()) {
                    adapter.checkAll(true);
                } else {
                    adapter.checkAll(false);
                }

                adapter.notifyDataSetChanged();
                break;

            case R.id.btnStudy :
                intent = new Intent(getContext(), CollectionStudy.class);
                startActivity(intent);
                break;

            case R.id.btnDelete :
                break;

            case R.id.btnRecord :
                break;

            case R.id.btnAddCollection :
                intent = new Intent(getContext(), CollectionFlashCard.class);
                intent.putExtra(REQUEST, REQUEST_ADD);
                startActivityForResult(intent,REQUEST_CODE);
                break;

            case R.id.searchCancel :
                searchEdit.setText("");
                break;
        }
    }
}
