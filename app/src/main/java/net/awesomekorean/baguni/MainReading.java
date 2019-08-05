package net.awesomekorean.baguni;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import net.awesomekorean.baguni.reading.ReadingFrame;

public class MainReading extends Fragment implements Button.OnClickListener {

    public static int readingUnit = 0;

    int readingNumber = 2;

    LinearLayout layout;

    View view;

    Intent intent;

    public static MainReading newInstance() {
        return new MainReading();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_reading, container, false);

        layout = view.findViewById(R.id.layoutReadingMain);

        makeReadingBtns();

        return view;
    }

    @Override
    public void onClick(View v) {

        readingUnit = v.getId();
        setIntentForReading();
    }

    public void setIntentForReading() {

        intent = new Intent(getContext(), ReadingFrame.class);
        startActivity(intent);
    }

    public void makeReadingBtns() {

        for(int i=0; i<readingNumber; i++) {

            Button button = new Button(getContext());

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DpToPx.getDpToPx(getResources(), 100));

            button.setLayoutParams(params);
            button.setOnClickListener(this);
            button.setText("Reading"+i);
            button.setId(i);

            layout.addView(button);
        }
    }
}


