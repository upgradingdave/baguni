package net.awesomekorean.podo.writing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.R;

import java.util.HashMap;
import java.util.Map;

public class WritingCorrected extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    TextView writingOriginal;
    TextView writingCorrected;
    TextView feedbackTeacher;
    EditText feedbackStudent;

    ImageView btnBack;
    Button btnGiveUsFeedback;
    Button btnSend;

    LinearLayout sendFeedback;
    LinearLayout sendFeedbackResult;

    String guid;
    String article;
    String correction;
    String feedbackT;
    String feedbackS;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_corrected);

        writingOriginal = findViewById(R.id.writingOriginal);
        writingCorrected = findViewById(R.id.writingCorrected);
        feedbackTeacher = findViewById(R.id.feedbackTeacher);
        feedbackStudent = findViewById(R.id.feedbackStudent);
        sendFeedback = findViewById(R.id.sendFeedback);
        sendFeedbackResult = findViewById(R.id.sendFeedbackResult);
        btnBack = findViewById(R.id.btnBack);
        btnGiveUsFeedback = findViewById(R.id.btnGiveUsFeedback);
        btnSend = findViewById(R.id.btnSend);
        writingOriginal.setMovementMethod(new ScrollingMovementMethod());
        writingCorrected.setMovementMethod(new ScrollingMovementMethod());
        feedbackTeacher.setMovementMethod(new ScrollingMovementMethod());
        btnBack.setOnClickListener(this);
        btnGiveUsFeedback.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        sendFeedback.setOnClickListener(this);

        intent = getIntent();
        guid = intent.getExtras().getString(getString(R.string.EXTRA_GUID));
        article = intent.getExtras().getString(getString(R.string.EXTRA_ARTICLE));
        correction = intent.getExtras().getString(getString(R.string.EXTRA_CORRECTION));
        feedbackT = intent.getExtras().getString(getString(R.string.EXTRA_TEACHER_FEEDBACK));
        feedbackS = intent.getExtras().getString(getString(R.string.EXTRA_STUDENT_FEEDBACK));
        System.out.println("FEEDBACK"+feedbackS);
        if(feedbackS != null) {
            btnGiveUsFeedback.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_grey_30));
            btnGiveUsFeedback.setEnabled(false);
        }

        writingOriginal.setText(article);
        writingCorrected.setText(correction);
        feedbackTeacher.setText(feedbackT);
        feedbackStudent.setText(feedbackS);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnBack :
                finish();
                break;

            case R.id.btnGiveUsFeedback :
                sendFeedback.setVisibility(View.VISIBLE);
                break;

            case R.id.sendFeedback :
                sendFeedback.setVisibility(View.GONE);
                break;

            case R.id.btnSend :
                sendFeedbackResult.setVisibility(View.VISIBLE);

                final String studentFeedback = feedbackStudent.getText().toString();

                DocumentReference reference = db.collection(getString(R.string.DB_WRITINGS)).document(guid);
                reference
                        .update("studentFeedback", studentFeedback)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                System.out.println("학생이 피드백을 보냈습니다");
                                WritingRepository repository = new WritingRepository(getApplicationContext());
                                repository.updateStudentFeedbackByGuid(guid, studentFeedback);
                            }
                        });


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendFeedbackResult.setVisibility(View.GONE);
                        finish();
                    }
                }, 1000);

                break;
        }
    }
}
