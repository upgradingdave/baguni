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
import android.widget.LinearLayout;
import android.widget.TextView;
import net.awesomekorean.baguni.MainReading;
import net.awesomekorean.baguni.R;

public class ReadingFrame extends AppCompatActivity {

    TextView title; // reading 타이틀
    TextView article; // reading 본문

    LinearLayout popUpLayout; // 단어 클릭 시 팝업 레이아웃
    TextView popUpTextView;

    Reading reading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_frame);

        title = findViewById(R.id.title);
        article = findViewById(R.id.article);
        popUpLayout = findViewById(R.id.popUpLayout);
        popUpTextView = findViewById(R.id.popUpTextView);



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

            span.setSpan(new ClickableSpan() {
                @Override
                public void onClick(@NonNull View view) {  // 하이라이트 클릭 이벤트

                    System.out.println("CLICKED: "+view.getTag());

                    //popUpTextView.setText((Integer) view.getTag());
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

}
