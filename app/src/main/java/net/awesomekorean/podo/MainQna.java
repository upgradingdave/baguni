package net.awesomekorean.podo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.qna.QnaAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainQna extends Fragment {

    View view;
    Intent intent;

    RecyclerView category;
    RecyclerView recyclerView;

    public static MainQna newInstance() {
        return new MainQna();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_qna, container, false);

        category = view.findViewById(R.id.recyclerViewCategory);
        recyclerView = view.findViewById(R.id.recyclerViewFaq);

        category.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        List<QnaAdapter.Item> data = new ArrayList<>();

        data.add(new QnaAdapter.Item(QnaAdapter.HEADER, "Category"));
        data.add(new QnaAdapter.Item(QnaAdapter.CHILD, "Grammar"));
        data.add(new QnaAdapter.Item(QnaAdapter.CHILD, "Culture"));
        data.add(new QnaAdapter.Item(QnaAdapter.CHILD, "Pronunciation"));

        category.setAdapter(new QnaAdapter(data));

        return view;
    }
}
