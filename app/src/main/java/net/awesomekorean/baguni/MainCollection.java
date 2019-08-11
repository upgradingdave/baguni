package net.awesomekorean.baguni;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;

import net.awesomekorean.baguni.collection.CollectionFlashCard;
import net.awesomekorean.baguni.collection.CollectionItems;
import net.awesomekorean.baguni.collection.CollectionListViewAdapter;
import net.awesomekorean.baguni.lesson.LessonFrame;

import java.util.ArrayList;

public class MainCollection extends Fragment implements Button.OnClickListener{

    View view;

    CheckBox selectAll;

    ListView listView;
    ArrayList<CollectionItems> list;
    CollectionListViewAdapter adapter;

    Button btnWord;
    Button btnSentence;
    Button btnStudy;
    Button btnDelete;
    Button btnRecord;

    Intent intent;

    String[] collectionListKorean = {"사과", "바나나", "망고"};
    String[] collectionListEnglish = {"apple", "banana", "mango"};

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
        selectAll.setOnClickListener(this);
        btnWord.setOnClickListener(this);
        btnSentence.setOnClickListener(this);
        btnStudy.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnRecord.setOnClickListener(this);

        listView = view.findViewById(R.id.listViewCollection);
        list = getCollection(false);
        adapter = new CollectionListViewAdapter(getContext(), list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                CollectionItems item = (CollectionItems) adapterView.getItemAtPosition(i);
                intent = new Intent(getContext(), CollectionFlashCard.class);
                intent.putExtra("Korean", item.getCollectionKorean());
                intent.putExtra("English", item.getCollectionEnglish());
                startActivity(intent);
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

    private ArrayList<CollectionItems> getCollection(boolean isChecked) {

        ArrayList<CollectionItems> list = new ArrayList<>();

        for(int i=0; i<collectionListKorean.length; i++) {

            CollectionItems items = new CollectionItems();
            items.setChecked(isChecked);
            items.setCollectionKorean(collectionListKorean[i]);
            items.setCollectionEnglish(collectionListEnglish[i]);
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
                break;

            case R.id.btnDelete :
                break;

            case R.id.btnRecord :
                break;

            case R.id.btnAddCollection :
                break;
        }
    }
}
