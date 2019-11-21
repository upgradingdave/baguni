package net.awesomekorean.podo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.faq.FaqAdapter;
import net.awesomekorean.podo.faq.FaqItems;

import java.util.ArrayList;

public class MainFaq extends Fragment {

    View view;
    Intent intent;



    public static MainFaq newInstance() {
        return new MainFaq();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_faq, container, false);

        ArrayList<FaqItems> list = new ArrayList<>();

        FaqItems item = new FaqItems();
        item.setUserImage(R.drawable.people1);
        item.setUserName("Danny");
        item.setUploadDate("19.11.21");
        item.setSummary("How to pronounce ....");

        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FaqAdapter adapter = new FaqAdapter(list);
        recyclerView.setAdapter(adapter);


        return view;
    }
}
