package net.awesomekorean.baguni;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.awesomekorean.baguni.reading.ReadingFrame;
import net.awesomekorean.baguni.writing.WritingFrame;

public class MainWriting extends Fragment {

    View view;

    FloatingActionButton btnAddWriting; // 플로팅 버튼

   public static MainWriting newInstance() {
        return new MainWriting();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_writing, container, false);

        btnAddWriting = view.findViewById(R.id.btnAddWriting);
        btnAddWriting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), WritingFrame.class);
                startActivity(intent);
            }
        });

        return view;
    }
}