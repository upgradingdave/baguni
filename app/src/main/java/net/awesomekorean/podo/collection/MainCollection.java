package net.awesomekorean.podo.collection;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_OK;

public class MainCollection extends Fragment implements Button.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    View view;

    public static CheckBox selectAll; // 전체 선택/해제 체크박스

    ListView listView;  // 리스트뷰
    ArrayList<CollectionItems> list = new ArrayList<>();    // 리스트뷰 생성을 위한 arrayList (20개 씩 끊어서 로드함)
    ArrayList<CollectionItems> listAllData; // 리스트뷰에 들어갈 모든 데이터를 불러옴
    ArrayList<CollectionItems> listCopy;// 검색 기능을 위해 리스트뷰 복사함
    CollectionAdapter adapter;  // 리스트뷰 어뎁터

    ProgressBar progressBar;

    ImageView btnSync; // DB와 동기화 버튼
    Button btnStudy;    // 컬렉션을 flash card 로 공부
    static Button btnDelete;   // 컬렉션 삭제 버튼
    static Button btnRecord;   // 녹음 요청 버튼
    ImageView btnAdd;    // 컬렉션 추가용 floating 버튼

    EditText searchEdit;    // 검색 입력
    ImageView searchCancel;   // 검색 취소

    Boolean longItemClicked = false;

    Intent intent;

    CollectionRepository repository;

    Observer<List<CollectionEntity>> observer;

    LinearLayout msgDelete;
    Button btnYes;
    Button btnNo;

    TextView msgNoCollection;

    List<CollectionEntity> copyListAllData = new ArrayList<>(); // 동기화를 위해 아이템 목록 복사
    String copyDateLastSync = new String(); // 동기화를 위해 마지막 동기화 날짜 가져옴

    public static TextView collectionNo;

    int index;  // 클릭 한 리스트 뷰의 인덱스
    public static int isChecked = 0; // 클릭된 아이템이 있는지 확인하기 위한 변수
    public static int size; //  컬렉션 개수

    String userEmail;


    public static MainCollection newInstance() {
        return new MainCollection();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_collection, container, false);


        userEmail = MainActivity.userEmail;
        //listenToDocumentLocal();

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
        btnSync = view.findViewById(R.id.btnSync);
        selectAll.setOnClickListener(this);
        btnStudy.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnRecord.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        searchCancel.setOnClickListener(this);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
        btnSync.setOnClickListener(this);

        listView = view.findViewById(R.id.listViewCollection);

        repository = new CollectionRepository(getContext());

        repository.deleteAll();
        repository.deleteDateLastSync();

        setListViewFooter();

        // DB 의 getAll() 에 변화가 감지되면 listView 로 값들을 가져옴
        observer = new Observer<List<CollectionEntity>>() {
            @Override
            public void onChanged(@Nullable List<CollectionEntity> collectionEntities) {

                copyListAllData = collectionEntities;

                repository.getCount();
                listAllData = new ArrayList<>();

                // 컬렉션이 하나도 없을 때 메시지 표시
                if (collectionEntities.size() == 0) {
                    msgNoCollection.setVisibility(View.VISIBLE);
                } else {
                    msgNoCollection.setVisibility(View.GONE);
                }

                DescendingObj descendingObj = new DescendingObj();
                Collections.sort(collectionEntities, descendingObj);

                for (CollectionEntity entity : collectionEntities) {
                    if (entity.getDeleted() != 1) {
                        CollectionItems items = new CollectionItems();
                        items.setFront(entity.getFront());
                        items.setBack(entity.getBack());
                        items.setGuid(entity.getGuid());
                        listAllData.add(items);
                    }
                }

                if (collectionEntities.size() > 10) {
                    list = new ArrayList<>(listAllData.subList(0, 10));
                } else {
                    list = listAllData;
                    progressBar.setVisibility(View.GONE);
                }

                adapter = new CollectionAdapter(getContext(), list);
                listView.setAdapter(adapter);

                listCopy = new ArrayList<>();
                listCopy.addAll(list);  //  검색 기능을 위해 list 내용 복사
            }
        };

        // DB의 플래쉬 카드를 listView 로 가져오기
        repository.getAll().observe(this, observer);

        repository.getDateSync().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s == null) {
                    repository.initDateLasySync();
                }
                copyDateLastSync = s;
                System.out.println("LASTDATE : " + copyDateLastSync);
            }
        });


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

                if (scrollState == SCROLL_STATE_IDLE && listView.getLastVisiblePosition() == list.size()) {
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

                if (longItemClicked == true) {
                    if (item.getChecked()) {
                        item.setChecked(false);
                        isChecked--;
                        selectAll.setChecked(false);

                        if (isChecked == 0) { // 체크된 아이템이 하나도 없을 때
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
                    intent.putExtra(getString(R.string.EXTRA_GUID), item.getGuid());
                    intent.putExtra(getString(R.string.EXTRA_FRONT), item.getFront());
                    intent.putExtra(getString(R.string.EXTRA_BACk), item.getBack());
                    intent.putExtra(getString(R.string.REQUEST), getString(R.string.REQUEST_EDIT));
                    startActivityForResult(intent, getResources().getInteger(R.integer.REQUEST_CODE_EDIT));
                }
            }
        });


        // 리스트뷰 아이템 롱 클릭 이벤트
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (selectAll.getVisibility() == View.INVISIBLE) {
                    ItemLongClicked(true, View.VISIBLE, View.GONE, View.VISIBLE);
                    adapter.longClickOnOff(getString(R.string.LONGCLICK_ON));
                    isChecked = 0;
                    selectAll.setChecked(false);
                    btnEnabled(false);

                } else {
                    ItemLongClicked(false, View.INVISIBLE, View.VISIBLE, View.GONE);
                    adapter.longClickOnOff(getString(R.string.LONGCLICK_OFF));
                }

                adapter.notifyDataSetChanged();
                return true;
            }
        });
        return view;
    }


    // Delete, Record 버튼 켜기/끄기
    public static void btnEnabled(boolean b) {
        btnDelete.setEnabled(b);
        btnRecord.setEnabled(b);
    }


    // 아이템을 롱 클릭 했을 때
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

        for (int i = 0; i < 10; i++) {
            if ((size + i + 1) <= listAllData.size()) {
                list.add(listAllData.get(size + i));
                if (selectAll.getVisibility() == View.VISIBLE) { // 체크박스 on 상태에서 아이템을 더 불러올 경우
                    CollectionItems items;
                    items = list.get(list.size() - 1);
                    items.setVisible(true);
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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_footer, null);
        progressBar = view.findViewById(R.id.progressBar);
        listView.addFooterView(progressBar);
    }


    // Flash Card 수정/추가 후 결과 받기
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            repository.getAll().observe(this, observer);
            ItemLongClicked(false, View.INVISIBLE, View.VISIBLE, View.GONE);
        }
    }


    // 검색 뷰에 입력한 내용을 listAllData 와 비교해서 출력
    public void search(String text) {

        list.clear(); // 입력이 발생하면 리스트를 지움.

        if (text.length() == 0) {
            searchCancel.setVisibility(View.GONE);
            list.addAll(listCopy); // 입력을 지우면 원래 리스트 출력

        } else {

            for (int i = 0; i < listAllData.size(); i++) {
                String front = listAllData.get(i).getFront();
                String back = listAllData.get(i).getBack();
                Pattern pattern = Pattern.compile("^" + text, Pattern.CASE_INSENSITIVE);
                Matcher matcherFront = pattern.matcher(front);
                Matcher matcherBack = pattern.matcher(back);
                if (matcherFront.find() || matcherBack.find()) {
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

            case R.id.checkBoxSelectAll:
                if (selectAll.isChecked()) {
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

            case R.id.btnStudy:
                intent = new Intent(getContext(), CollectionStudy.class);
                startActivity(intent);
                break;

            case R.id.btnDelete:
                msgDelete.setVisibility(View.VISIBLE);
                break;

            case R.id.btnYes:
                ArrayList<String> checkedList = new ArrayList<>();

                for (CollectionItems items : listAllData) {
                    if (items.getChecked()) {
                        checkedList.add(items.getGuid());
                    }
                }
                if (checkedList != null) {

                    for (String guid : checkedList) {

                        repository.setDeletedByGuid(guid);
                    }
                }
                ItemLongClicked(false, View.INVISIBLE, View.VISIBLE, View.GONE);
                msgDelete.setVisibility(View.GONE);
                break;

            case R.id.btnNo:
                msgDelete.setVisibility(View.GONE);
                break;

            case R.id.btnRecord:
                break;

            case R.id.btnAddCollection:
                intent = new Intent(getContext(), CollectionFlashCard.class);
                intent.putExtra(getString(R.string.REQUEST), getString(R.string.REQUEST_ADD));
                startActivityForResult(intent, getResources().getInteger(R.integer.REQUEST_CODE_ADD));
                break;

            case R.id.searchCancel:
                searchEdit.setText("");
                break;

            case R.id.btnSync:

                // Room 에서 dateEdit 가 dateLastSync 보다 뒤에 있는 아이템들 가져오기
                final List<CollectionEntity> itemsToUpload = new ArrayList<>();

                for (CollectionEntity entity : copyListAllData) {
                    if (entity.getDateEdit().compareTo(copyDateLastSync) > 0) {
                        itemsToUpload.add(entity);
                        System.out.println("업로드 할 아이템을 찾았습니다: " + entity.getFront());
                    }
                }
                if (itemsToUpload.isEmpty()) {
                    System.out.println("업로드 할 아이템이 없습니다");
                }

                // DB 에서 dateEdit 가 dateLastSync 보다 뒤에 있는 아이템들 다운로드
                db.collection("android/podo/collections")
                        .whereGreaterThan("dateEdit", copyDateLastSync)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (!task.getResult().isEmpty()) {
                                        System.out.println("다운로드 할 아이템을 찾았습니다");
                                        for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                            CollectionEntity download = snapshot.toObject(CollectionEntity.class);
                                            repository.insertDownloadItem(download);
                                            System.out.println("아이템을 다운로드 했습니다 : " + download.getFront());
                                        }
                                    } else {
                                        System.out.println("다운로드 할 아이템이 없습니다.");
                                    }

                                    // 업로드
                                    for (CollectionEntity upload : itemsToUpload) {
                                        db.collection("android/podo/collections").document().set(upload);
                                        System.out.println("아이템을 업로드 했습니다: " + upload.getFront());
                                    }
                                }
                            }
                        });

                // 동기화 날짜 업데이트
                String now = getDateNow();
                DateSyncEntity dateSyncEntity = new DateSyncEntity();
                dateSyncEntity.setDateSync(now);
                repository.updateLastSync(dateSyncEntity);
                break;
        }
    }

    public String getDateNow() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date time = new Date();
        String dateNow = format.format(time);

        return dateNow;
    }

    // 컬렉션 리스트를 내림차순으로 정렬하기
    class DescendingObj implements Comparator<CollectionEntity> {
        @Override
        public int compare(CollectionEntity c1, CollectionEntity c2) {
            return c2.getDateNew().compareTo(c1.getDateNew());
        }
    }
}

