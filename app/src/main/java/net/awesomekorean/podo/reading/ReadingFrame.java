package net.awesomekorean.podo.reading;

import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.awesomekorean.podo.MainReading;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.collection.CollectionRepository;

public class ReadingFrame extends AppCompatActivity implements Button.OnClickListener {

    Reading reading; // Reading 인스턴스

    TextView title; // reading 타이틀
    TextView article; // reading 본문

    LinearLayout popUpLayout; // 단어 클릭 시 팝업 레이아웃
    TextView popUpTextView;  // 단어 클릭 시 팝업 텍스트뷰
    ImageButton btnCollect; // 단어 클릭 시 collect 버튼

    Button finish;
    Button btnPlayStop;

    // 단어를 클릭하면 컬렉션 할 때 들어갈 문자열 저장
    String front;
    String back;

    TextView collectResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_frame);

        title = findViewById(R.id.title);
        article = findViewById(R.id.article);
        popUpLayout = findViewById(R.id.popUpLayout);
        popUpTextView = findViewById(R.id.popUpTextView);
        btnCollect = findViewById(R.id.btnCollect);
        collectResult = findViewById(R.id.collectResult);
        btnCollect.setOnClickListener(this);


        switch (MainReading.readingUnit) {

            case 0 :
                reading = new Reading0();
                readyForReading();
                break;

        }
    }

    public void readyForReading() {  // 글 생성

        title.setText(reading.getTitle());


        SpannableStringBuilder span = new SpannableStringBuilder(reading.getArticle());

        for(int i=0; i<reading.getStart().length; i++) {

            final int finalI = i;
            span.setSpan(new ClickableSpan() {
                @Override
                public void onClick(@NonNull View view) {  // 하이라이트 클릭 이벤트
                    front = reading.getArticle().substring(reading.getStart()[finalI], reading.getEnd()[finalI]);
                    back = reading.getPopUpText()[finalI];
                    popUpTextView.setText(reading.getPopUpText()[finalI]);
                    popUpLayout.setVisibility(View.VISIBLE);

                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {   // 하이라이트 디자인 설정
                    ds.setColor(Color.rgb(243,110,84));
                    ds.setUnderlineText(true);
                    ds.setFakeBoldText(true);
                }

            }, reading.getStart()[i], reading.getEnd()[i], 0);  // 하이라이트 위치 설정

            article.setTag(i);
            article.setText(span);
            article.setMovementMethod(LinkMovementMethod.getInstance());
        }
        article.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(popUpLayout.getVisibility()==View.VISIBLE) {
                    popUpLayout.setVisibility(View.GONE);
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnCollect :

                CollectionRepository repository = new CollectionRepository(this);
                repository.insert(front, back);

                collectResult.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        collectResult.setVisibility(View.GONE);
                    }
                }, 1000);
                break;

        }
    }
}
