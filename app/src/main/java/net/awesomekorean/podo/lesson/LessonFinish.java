package net.awesomekorean.podo.lesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.MainLesson;
import net.awesomekorean.podo.R;

public class LessonFinish extends AppCompatActivity implements View.OnClickListener {

    LinearLayout selectBox;
    ConstraintLayout selectResult;

    ImageView box1;
    ImageView box2;
    ImageView box3;

    TextView tvPoints;

    Button btnGetPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_finish);

        selectBox = findViewById(R.id.selectBox);
        selectResult = findViewById(R.id.selectResult);
        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        tvPoints = findViewById(R.id.tvPoints);
        btnGetPoint = findViewById(R.id.btnGetPoint);
        box1.setOnClickListener(this);
        box2.setOnClickListener(this);
        box3.setOnClickListener(this);
        btnGetPoint.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnGetPoint :
                // Room 에 포인트 합산하기, 레슨 완료 표시하기
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            default:
                // 포인트 랜덤으로 가져오기
                tvPoints.setText("100");
                selectBox.setVisibility(View.GONE);
                selectResult.setVisibility(View.VISIBLE);
                break;
        }
    }
}
