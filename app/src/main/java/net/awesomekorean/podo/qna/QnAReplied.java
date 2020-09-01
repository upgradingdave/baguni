package net.awesomekorean.podo.qna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.awesomekorean.podo.R;

public class QnAReplied extends AppCompatActivity {

    ImageView btnQuit;
    TextView tvQuestion;
    TextView tvAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_replied);

        btnQuit = findViewById(R.id.btnQuit);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvAnswer = findViewById(R.id.tvAnswer);
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        QnAEntity entity = (QnAEntity) getIntent().getSerializableExtra(getString(R.string.EXTRA_ENTITY));
        if(entity != null) {
            tvQuestion.setText(entity.getQuestion());
            tvAnswer.setText(entity.getAnswer());
        }
    }
}
