package net.awesomekorean.baguni;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import net.awesomekorean.baguni.collection.CollectionDb;
import net.awesomekorean.baguni.collection.CollectionFlashCard;
import net.awesomekorean.baguni.collection.CollectionItems;
import net.awesomekorean.baguni.collection.CollectionListViewAdapter;
import net.awesomekorean.baguni.collection.CollectionStudy;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class MainCollection extends Fragment implements Button.OnClickListener{
    public static final int REQUEST_CODE = 100;
    public static final String REQUEST_EDIT = "edit";
    public static final String REQUEST_ADD = "add";


    View view;

    CheckBox selectAll; // 전체 선택/해제 체크박스

    ListView listView;  // 리스트뷰
    ArrayList<CollectionItems> list;    // 리스트뷰 생성을 위한 arrayList
    ArrayList<CollectionItems> listCopy;// 검색 기능을 위해 리스트뷰 복사함
    CollectionListViewAdapter adapter;  // 리스트뷰 어뎁터

    Button btnWord; // 단어 컬렉션 선택 버튼
    Button btnSentence; // 문장 컬렉션 선택 버튼
    Button btnStudy;    // 컬렉션을 flash card 로 공부
    Button btnDelete;   // 컬렉션 삭제 버튼
    Button btnRecord;   // 녹음 요청 버튼
    FloatingActionButton btnAdd;    // 컬렉션 추가용 floating 버튼

    EditText searchEdit;    // 검색 입력
    ImageButton searchCancel;   // 검색 취소

    Intent intent;

    CollectionDb db = new CollectionDb();

    public static MainCollection newInstance() {
        return new MainCollection();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_collection, container, false);

        selectAll = view.findViewById(R.id.checkBoxSelectAll);
        btnWord = view.findViewById(R.id.btnWord);
        btnSentence = view.findViewById(R.id.btnSentence);
        btnStudy = view.findViewById(R.id.btnStudy);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnRecord = view.findViewById(R.id.btnRecord);
        btnAdd = view.findViewById(R.id.btnAddCollection);
        searchEdit = view.findViewById(R.id.searchCollection);
        searchCancel = view.findViewById(R.id.searchCancel);
        selectAll.setOnClickListener(this);
        btnWord.setOnClickListener(this);
        btnSentence.setOnClickListener(this);
        btnStudy.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnRecord.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        searchCancel.setOnClickListener(this);

        listView = view.findViewById(R.id.listViewCollection);
        list = getCollection(false);    // 컬렉션 아이템 불러오기

        listCopy = new ArrayList<>();
        listCopy.addAll(list);  //  검색 기능을 위해 list 내용 복사

        adapter = new CollectionListViewAdapter(getContext(), list);
        listView.setAdapter(adapter);

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


        // 리스트의 아이템 클릭 이벤트
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                CollectionItems item = (CollectionItems) adapterView.getItemAtPosition(i);
                intent = new Intent(getContext(), CollectionFlashCard.class);
                intent.putExtra("Korean", item.getCollectionKorean());
                intent.putExtra("English", item.getCollectionEnglish());
                intent.putExtra("index", i);
                intent.putExtra("request", REQUEST_EDIT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });


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


    // Flash Card 수정/추가 후 결과 받기
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            adapter.notifyDataSetChanged();

        }
    }


    // 검색 뷰에 입력한 내용을 list 와 비교해서 출력
    public void search(String text) {

        list.clear(); // 입력이 발생하면 리스트를 지움.

        if(text.length() == 0) {
            searchCancel.setVisibility(View.GONE);
            list.addAll(listCopy); // 입력을 지우면 원래 리스트 출력

        } else {

            for(int i=0; i<listCopy.size(); i++) {

                if(listCopy.get(i).equals(text)) { // 조건에 맞는 리스트만 출력
                    list.add(listCopy.get(i));
                }
            }
        }

        adapter.notifyDataSetChanged();
    }


    // 최초 activity 실행 시 DataBase 에서 collection 불러오기
    private ArrayList<CollectionItems> getCollection(boolean isChecked) {

        ArrayList<CollectionItems> list = new ArrayList<>();

        for(int i=0; i<db.getCollectionKorean().length; i++) {

            CollectionItems items = new CollectionItems();
            items.setChecked(isChecked);
            items.setCollectionKorean(db.getCollectionKorean()[i]);
            items.setCollectionEnglish(db.getCollectionEnglish()[i]);
            list.add(items);
        }
        return list;
    }


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

            case R.id.btnWord :
                break;

            case R.id.btnSentence :
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
                intent.putExtra("request", REQUEST_ADD);
                startActivityForResult(intent,REQUEST_CODE);
                break;

            case R.id.searchCancel :
                searchEdit.setText("");
                break;
        }
    }
}
