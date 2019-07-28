package net.awesomekorean.baguni.lesson;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import net.awesomekorean.baguni.R;

public class LessonEnd extends Fragment implements Button.OnClickListener {

    ImageView imageView;
    TextView textView;

    ImageButton imageBtn1;
    ImageButton imageBtn2;
    ImageButton imageBtn3;

    View view;

    public static LessonEnd newInstance() {
        return new LessonEnd();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.lesson_end, container, false);

        imageView = view.findViewById(R.id.imageView);
        textView = view.findViewById(R.id.textView);

        imageBtn1 = view.findViewById(R.id.imageBtn1);
        imageBtn2 = view.findViewById(R.id.imageBtn2);
        imageBtn3 = view.findViewById(R.id.imageBtn3);
        imageBtn1.setOnClickListener(this);
        imageBtn2.setOnClickListener(this);
        imageBtn3.setOnClickListener(this);

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
        }

    }

    public void bonusBtnClicked() {
        imageView.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
    }
}
