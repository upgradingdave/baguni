package net.awesomekorean.podo.writing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
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
import net.awesomekorean.podo.teachers.Teachers;

public class WritingReturned extends AppCompatActivity implements View.OnClickListener {

    TextView writingOriginal;
    EditText editText;

    TextView textCount;

    LinearLayout saveResult;

    ImageView btnBack;
    Button btnSave;
    Button btnCorrection;

    String guid;

    WritingEntity writingEntity;
    WritingRepository repository;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_returned);

        writingOriginal = findViewById(R.id.writingOriginal);
        editText = findViewById(R.id.editText);
        textCount = findViewById(R.id.textCount);
        saveResult = findViewById(R.id.saveResult);
        btnBack = findViewById(R.id.btnBack);
        btnSave = findViewById(R.id.btnSave);
        btnCorrection = findViewById(R.id.btnCorrection);
        btnBack.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnCorrection.setOnClickListener(this);

        intent = getIntent();

        writingEntity = (WritingEntity) intent.getSerializableExtra(getString(R.string.EXTRA_ENTITY));
        guid = writingEntity.getGuid();

        // 리턴된 글에서 이해가 되지 않는 부분에 빨간색 표시하기
        String originalText = writingEntity.getContents();
        String weirdSentence = writingEntity.getTeacherFeedback();
        String[] splitText = weirdSentence.split("\\n");

        if(originalText.indexOf(splitText[0]) != -1) {
            StringBuilder sb = new StringBuilder(originalText.substring(0, originalText.indexOf(splitText[0])));

            for (int i = 0; i < splitText.length; i++) {
                sb.append("<font color=\"#FF0000\">");
                sb.append(originalText.substring(originalText.indexOf(splitText[i]), originalText.indexOf(splitText[i]) + splitText[i].length()) + "</font>");

                if (i + 1 == splitText.length) {
                    sb.append(originalText.substring(originalText.indexOf(splitText[i]) + splitText[i].length(), originalText.length()));
                } else {
                    sb.append(originalText.substring(originalText.indexOf(splitText[i]) + splitText[i].length(), originalText.indexOf(splitText[i + 1])));
                }
            }
            writingOriginal.setText(Html.fromHtml(sb.toString()));

        } else {
            writingOriginal.setText(originalText);
        }

        if(writingEntity.getCorrection().trim().length() > 0 ) {
            editText.setText(writingEntity.getCorrection());
            textCount.setText(writingEntity.getCorrection().length() + " " + getString(R.string.LETTERS));
        } else {
            editText.setText(writingEntity.getContents());
            textCount.setText(writingEntity.getContents().length() + " " + getString(R.string.LETTERS));
        }


        // 글을 쓸 때 바로바로 글자수를 가져옴
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textCount.setText(editText.getText().toString().length() + " " + getString(R.string.LETTERS));
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }


    private void saveWriting() {
        String fixedText = editText.getText().toString();
        writingEntity.setCorrection(fixedText);
        writingEntity.setLetters(fixedText.length());
        repository = new WritingRepository(getApplicationContext());
        repository.editReturnedWriting(guid, fixedText, fixedText.length());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnBack :
                finish();
                break;

            case R.id.btnSave :
                saveWriting();
                saveResult.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        saveResult.setVisibility(View.GONE);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }, 1000);
                break;

            case R.id.btnCorrection :
                if(editText.getText().toString().length() > 19) {
                    saveWriting();
                    Toast.makeText(getApplicationContext(), getString(R.string.WRITING_SAVED), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, Teachers.class);
                    intent.putExtra(getString(R.string.EXTRA_ENTITY), writingEntity);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.WRITING_SHORT), Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
