package net.awesomekorean.podo.writing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.R;

public class WritingCorrected extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    TextView writingOriginal;
    TextView writingCorrected;
    TextView feedbackTeacher;
    EditText feedbackStudent;

    ImageView btnBack;
    Button btnGiveUsFeedback;
    Button btnSend;
    ImageView btnCloseFeedback;

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
        btnCloseFeedback = findViewById(R.id.btnCloseFeedback);
        writingOriginal.setMovementMethod(new ScrollingMovementMethod());
        writingCorrected.setMovementMethod(new ScrollingMovementMethod());
        feedbackTeacher.setMovementMethod(new ScrollingMovementMethod());
        btnBack.setOnClickListener(this);
        btnGiveUsFeedback.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        btnCloseFeedback.setOnClickListener(this);

        intent = getIntent();

        WritingEntity correctedWriting = (WritingEntity) intent.getSerializableExtra(getString(R.string.EXTRA_ENTITY));
        guid = correctedWriting.getGuid();
        article = correctedWriting.getContents();
        correction = correctedWriting.getCorrection();
        feedbackT = correctedWriting.getTeacherFeedback();
        feedbackS = correctedWriting.getStudentFeedback();

        if(feedbackS != null) {
            btnGiveUsFeedback.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_grey_30));
            btnGiveUsFeedback.setEnabled(false);
        }

        writingOriginal.setText(article);
        if(correction != null) {
            writingCorrected.setText(Html.fromHtml(correction));
        }
        if(feedbackT != null) {
            feedbackTeacher.setText(feedbackT);
        }
        if(feedbackS != null) {
            btnGiveUsFeedback.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple_25_transparent));
            btnGiveUsFeedback.setEnabled(false);
        }
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

            case R.id.btnSend :
                sendFeedbackResult.setVisibility(View.VISIBLE);

                final String studentFeedback = feedbackStudent.getText().toString();

                DocumentReference reference = db.collection(getString(R.string.DB_WRITINGS)).document(guid);
                reference
                        .update("studentFeedback", studentFeedback, "status", 3)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                System.out.println("학생이 피드백을 보냈습니다");
                                WritingRepository repository = new WritingRepository(getApplicationContext());
                                repository.updateStudentFeedbackByGuid(guid, studentFeedback);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed to send a student feedback: " + e, Toast.LENGTH_LONG).show();
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

            case R.id.btnCloseFeedback :
                sendFeedback.setVisibility(View.GONE);
                break;
        }
    }
}
