package net.awesomekorean.podo.collection;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.analytics.FirebaseAnalytics;

import net.awesomekorean.podo.PlaySoundPool;
import net.awesomekorean.podo.R;

public class CollectionStudy extends AppCompatActivity implements View.OnClickListener {


    CollectionRepository repository;

    public static TextView studyFront;
    public static TextView studyBack;
    public static String studyAudio;

    ImageView btnBack;
    TextView btnRandom;
    TextView btnRecentWord;
    public static ImageView btnAudio;
    Button btnNext;

    boolean randomBtnClicked = true;

    int index = 0; // 최신 플래시 카드부터 공부할 때의 인덱스

    PlaySoundPool playSoundPool;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_study);

        btnBack = findViewById(R.id.btnBack);
        btnRandom = findViewById(R.id.btnRandom);
        btnRecentWord = findViewById(R.id.btnRecentWord);
        btnAudio = findViewById(R.id.btnAudio);
        btnNext = findViewById(R.id.btnNext);
        studyFront = findViewById(R.id.studyFront);
        studyBack = findViewById(R.id.studyBack);
        btnBack.setOnClickListener(this);
        btnRandom.setOnClickListener(this);
        btnRecentWord.setOnClickListener(this);
        btnAudio.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        context = getApplicationContext();
        playSoundPool = new PlaySoundPool(context);

        // analytics 로그 이벤트 얻기
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
        Bundle bundle = new Bundle();
        firebaseAnalytics.logEvent("collection_study", bundle);

        repository = new CollectionRepository(getApplicationContext());
        studyBack.setVisibility(View.INVISIBLE);
        randomStudy(); // 디폴트로 랜덤모드 실행
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

            case R.id.btnBack :
                finish();
                break;

            case R.id.btnRandom :
                btnRandom.setTextColor(ContextCompat.getColor(this, R.color.WHITE));
                btnRecentWord.setTextColor(ContextCompat.getColor(this, R.color.GREY_TEXT));
                btnRandom.setBackgroundResource(R.drawable.bg_purple_20_left);
                btnRecentWord.setBackgroundResource(R.drawable.bg_white_20_right_stroke_purple);
                randomBtnClicked = true;
                randomStudy();
                break;

            case R.id.btnRecentWord :
                btnRecentWord.setTextColor(ContextCompat.getColor(this, R.color.WHITE));
                btnRandom.setTextColor(ContextCompat.getColor(this, R.color.GREY_TEXT));
                btnRandom.setBackgroundResource(R.drawable.bg_white_20_left_stroke_purple);
                btnRecentWord.setBackgroundResource(R.drawable.bg_purple_20_right);
                index = 0;
                randomBtnClicked = false;
                newCollectionFirstStudy();
                break;

            case R.id.btnAudio :
                if(studyAudio != null) {
                    playSoundPool.playSoundCollection(context.getFilesDir() + "/" + studyAudio);
                }
                break;

            case R.id.btnNext :
                if(studyBack.getVisibility()==View.INVISIBLE) {
                    studyBack.setVisibility(View.VISIBLE);
                    btnNext.setText(getString(R.string.NEXT));
                } else {
                    studyBack.setVisibility(View.INVISIBLE);
                    btnNext.setText(getString(R.string.ANSWER));

                    if(randomBtnClicked) {
                        randomStudy();
                    } else {
                        newCollectionFirstStudy();
                    }
                }
                break;

        }
    }
}
