package net.awesomekorean.podo.qna;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.R;

import java.util.ArrayList;

public class MyQuestions extends Fragment {

    View view;

    LinearLayout noQuestion;


    public static MyQuestions newInstance() {
        return new MyQuestions();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_qna_myquestions, container, false);

        noQuestion = view.findViewById(R.id.noQuestion);

        ArrayList<MyQuestionsItems> list = new ArrayList<>();

        MyQuestionsItems item1 = new MyQuestionsItems();
        item1.setCategory("Grammar");
        item1.setSummary("How to pronounce ....");
        item1.setUploadDate("19.11.21");
        item1.setIsConfirmed(1);

        MyQuestionsItems item2 = new MyQuestionsItems();
        item2.setCategory("Grammar");
        item2.setSummary("How to pronounce ....");
        item2.setUploadDate("19.11.21");
        item2.setIsConfirmed(null);

        MyQuestionsItems item3 = new MyQuestionsItems();
        item3.setCategory("Grammar");
        item3.setSummary("How to pronounce ....");
        item3.setUploadDate("19.11.21");
        item3.setIsConfirmed(0);



        list.add(item1);
        list.add(item2);
        list.add(item3);

        if(list.size() == 0) {
            noQuestion.setVisibility(View.VISIBLE);
        }else {
            noQuestion.setVisibility(View.GONE);
        }


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MyQuestionsAdapter adapter = new MyQuestionsAdapter(list);
        recyclerView.setAdapter(adapter);


        return view;
    }
}
