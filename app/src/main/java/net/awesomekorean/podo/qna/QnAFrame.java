package net.awesomekorean.podo.qna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UnixTimeStamp;

public class QnAFrame extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ImageView btnQuit;
    EditText textQuestion;
    Button btnSend;
    QnAEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_frame);

        btnQuit = findViewById(R.id.btnQuit);
        textQuestion = findViewById(R.id.textQuestion);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQuestion();
                sendToDB();
                finish();
            }
        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQuestion();
                finish();
            }
        });

        entity = (QnAEntity) getIntent().getSerializableExtra(getString(R.string.EXTRA_ENTITY));
        if(entity != null) {
            textQuestion.setText(entity.getQuestion());
        }
    }


    // 룸에 저장하기
    private void saveQuestion() {
        String question = textQuestion.getText().toString();
        QnARepository repository = new QnARepository(getApplicationContext());

        if(entity != null) {
            entity.setQuestion(question);
            entity.setDateQuestion(UnixTimeStamp.getTimeNow());
            repository.update(entity);

        } else {
            entity = new QnAEntity(question);
            entity.setUserEmail(SharedPreferencesInfo.getUserEmail(getApplicationContext()));
            entity.setUserName(SharedPreferencesInfo.getUserName(getApplicationContext()));
            entity.setUserToken(SharedPreferencesInfo.getUserToken(getApplicationContext()));
            repository.insert(entity);
        }
    }


    // DB에 올리기
    private void sendToDB() {
        entity.setStatus(1);
        db.collection(getString(R.string.DB_QNA)).document(entity.getGuid()).set(entity);
    }
}
