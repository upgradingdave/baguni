package net.awesomekorean.baguni;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;

import net.awesomekorean.baguni.collection.CollectionItems;
import net.awesomekorean.baguni.collection.CollectionListViewAdapter;

import java.util.ArrayList;

public class MainCollection extends Fragment {

    View view;

    CheckBox selectAll;

    ListView listView;
    ArrayList<CollectionItems> list;
    CollectionListViewAdapter adapter;

    String[] collectionListKorean = {"사과", "바나나", "망고"};
    String[] collectionListEnglish = {"apple", "banana", "mango"};

    public static MainCollection newInstance() {
        return new MainCollection();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_collection, container, false);

        listView = view.findViewById(R.id.listViewCollection);
        selectAll = view.findViewById(R.id.checkBoxSelectAll);

        list = getCollection(false);
        adapter = new CollectionListViewAdapter(getContext(), list);

        listView.setAdapter(adapter);

        /*

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CollectionItems item = (CollectionItems) adapterView.getItemAtPosition(i);
                System.out.println("CollectionKorean: "+item.getCollectionKorean());
                System.out.println("CollectionEnglish: "+item.getCollectionEnglish());
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.longClickOnOff();
                System.out.println("HELLO");
                return true;
            }
        });

        */

        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectAll.isChecked()) {
                    list = getCollection(true);
                } else {
                    list = getCollection(false);
                }

                adapter = new CollectionListViewAdapter(getContext(), list);
                listView.setAdapter(adapter);

            }
        });


        return view;
    }

    private ArrayList<CollectionItems> getCollection(boolean isChecked) {

        ArrayList<CollectionItems> list = new ArrayList<>();

        for(int i=0; i<3; i++) {

            CollectionItems items = new CollectionItems();
            items.setChecked(isChecked);
            items.setCollectionKorean(collectionListKorean[i]);
            items.setCollectionEnglish(collectionListEnglish[i]);
            list.add(items);
        }
        return list;
    }

}
