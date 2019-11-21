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
import net.awesomekorean.podo.myquestions.MyquestionsAdapter;
import net.awesomekorean.podo.myquestions.MyquestionsItems;

import java.util.ArrayList;

public class MainMyquestions extends Fragment {

    View view;
    Intent intent;



    public static MainMyquestions newInstance() {
        return new MainMyquestions();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_myquestions, container, false);

        ArrayList<MyquestionsItems> list = new ArrayList<>();

        MyquestionsItems item1 = new MyquestionsItems();
        item1.setCategory("Grammar");
        item1.setSummary("How to pronounce ....");
        item1.setUploadDate("19.11.21");
        item1.setIsConfirmed(1);

        MyquestionsItems item2 = new MyquestionsItems();
        item2.setCategory("Grammar");
        item2.setSummary("How to pronounce ....");
        item2.setUploadDate("19.11.21");
        item2.setIsConfirmed(null);

        MyquestionsItems item3 = new MyquestionsItems();
        item3.setCategory("Grammar");
        item3.setSummary("How to pronounce ....");
        item3.setUploadDate("19.11.21");
        item3.setIsConfirmed(0);



        list.add(item1);
        list.add(item2);
        list.add(item3);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MyquestionsAdapter adapter = new MyquestionsAdapter(list);
        recyclerView.setAdapter(adapter);


        return view;
    }
}
