package net.awesomekorean.baguni.collection;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import net.awesomekorean.baguni.R;

import java.util.List;

public class CollectionStudy extends AppCompatActivity implements View.OnClickListener {

    RadioGroup radioGroup;

    CollectionRepository repository;

    public static TextView studyFront;
    public static TextView studyBack;

    public static int size; //  컬렉션 개수
    public static List<CollectionEntity> entityDesc;

    Button btnNext;

    int index = 0; // 최신 플래시 카드부터 공부할 때의 인덱스
    int radioBtnNo = 0; // 라디오버튼 번호, 0: Random, 1: New word first

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_study);

        studyFront = findViewById(R.id.studyFront);
        studyBack = findViewById(R.id.studyBack);
        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);

        repository = new CollectionRepository(getApplicationContext());
        repository.getRandomForStudy();
        repository.getCount();

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.radio1) {
                    radioBtnNo = 0;
                    randomStudy();
                } else {
                    radioBtnNo = 1;
                    newCollectionFirstStudy();
                }
            }
        });
    }

    public void randomStudy() {
        repository.getRandomForStudy();
    }

    public void newCollectionFirstStudy() {
        if(index < size) {
            repository.getDescForStudy(index);
            index++;
        } else {
            index = 0;
            repository.getDescForStudy(index);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnNext :
                if(studyBack.getVisibility()==View.INVISIBLE) {
                    studyBack.setVisibility(View.VISIBLE);
                }else {
                    studyBack.setVisibility(View.INVISIBLE);
                    if(radioBtnNo == 0) {
                        randomStudy();
                    } else {
                        newCollectionFirstStudy();
                    }
                }
        }
    }
}
