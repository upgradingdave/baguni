package net.awesomekorean.baguni.reading;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.awesomekorean.baguni.MainReading;
import net.awesomekorean.baguni.R;

public class ReadingFrame extends AppCompatActivity implements Button.OnClickListener {

    Reading reading; // Reading 인스턴스

    TextView title; // reading 타이틀
    TextView article; // reading 본문

    LinearLayout popUpLayout; // 단어 클릭 시 팝업 레이아웃
    TextView popUpTextView;  // 단어 클릭 시 팝업 텍스트뷰
    ImageButton btnCollect; // 단어 클릭 시 collect 버튼
    ImageButton btnClose; // 단어 클릭 시 close 버튼

    Button finish;
    Button btnPlayStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_frame);

        title = findViewById(R.id.title);
        article = findViewById(R.id.article);
        popUpLayout = findViewById(R.id.popUpLayout);
        popUpTextView = findViewById(R.id.popUpTextView);
        btnCollect = findViewById(R.id.btnCollect);
        btnCollect.setOnClickListener(this);
        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);



        switch (MainReading.readingUnit) {

            case 0:
                reading = new Reading0();
                readyForReading();
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

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnCollect :
                System.out.println("Collect button clicked");
                break;

            case R.id.btnClose :
                popUpLayout.setVisibility(View.GONE);
                break;

        }
    }
}
