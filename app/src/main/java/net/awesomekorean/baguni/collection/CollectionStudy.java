package net.awesomekorean.baguni.collection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import net.awesomekorean.baguni.MainCollection;
import net.awesomekorean.baguni.R;

public class CollectionStudy extends AppCompatActivity implements View.OnClickListener {


    CollectionRepository repository;

    public static TextView studyFront;
    public static TextView studyBack;

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
        repository.getRandomForStudy(); // 디폴트로 랜덤모드 실행
        repository.getCount(); // 컬렉션 개수 가져오기

    }

    // 랜덤 학습 모드
    public void randomStudy() {
        repository.getRandomForStudy();
    }


    // 최신부터 학습 모드
    public void newCollectionFirstStudy() {
        if(index < MainCollection.size) {
            repository.getDescForStudy(index);
            index++;
        } else {
            repository.getDescForStudy(0);
            index = 1;
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

            case R.id.btnAudio :
                break;
        }
    }
}
