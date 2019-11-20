package net.awesomekorean.podo.lesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;

public class LessonFinish extends Fragment implements Button.OnClickListener {

    TextView textView;

    ImageButton imageBtn1;
    ImageButton imageBtn2;
    ImageButton imageBtn3;
    ImageButton imageBtn4;

    View view;

    public static LessonFinish newInstance() {
        return new LessonFinish();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_finish, container, false);

        textView = view.findViewById(R.id.textView);

        imageBtn1 = view.findViewById(R.id.imageBtn1);
        imageBtn2 = view.findViewById(R.id.imageBtn2);
        imageBtn3 = view.findViewById(R.id.imageBtn3);
        imageBtn4 = view.findViewById(R.id.imageBtn4);

        imageBtn1.setOnClickListener(this);
        imageBtn2.setOnClickListener(this);
        imageBtn3.setOnClickListener(this);
        imageBtn4.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.imageBtn1 :
                bonusBtnClicked();
                break;

            case R.id.imageBtn2 :
                bonusBtnClicked();
                break;

            case R.id.imageBtn3 :
                bonusBtnClicked();
                break;

            case R.id.imageBtn4 :
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                break;
        }

    }

    public void bonusBtnClicked() {
        imageBtn4.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
    }
}
