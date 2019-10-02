package net.awesomekorean.baguni.writing;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.awesomekorean.baguni.R;
import net.awesomekorean.baguni.teachers.Teachers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WritingFrame extends AppCompatActivity implements View.OnClickListener {

    TextView textCount; // 글자 수 표시
    EditText editText; // 쓰기 입력
    TextView saveResult; //저장 메시지

    Button btnSave;
    Button btnCorrection;

    String code; // add 인지 edit 인지를 판별

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_frame);

        textCount = findViewById(R.id.textCount);
        editText = findViewById(R.id.editText);
        btnSave = findViewById(R.id.btnSave);
        btnCorrection = findViewById(R.id.btnCorrection);
        saveResult = findViewById(R.id.saveResult);
        btnSave.setOnClickListener(this);
        btnCorrection.setOnClickListener(this);

        intent = getIntent();
        code = intent.getExtras().getString(getString(R.string.REQUEST));

        // EDIT 일 때, 기존의 글을 출력
        if(code.equals(getString(R.string.REQUEST_EDIT))) {
            String article = intent.getExtras().getString(getString(R.string.EXTRA_ARTICLE));
            String letters = intent.getExtras().getString(getString(R.string.EXTRA_LETTERS));
            editText.setText(article);
            textCount.setText(letters);
        }

        // 글을 쓸 때 바로바로 글자수를 가져옴
       editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String input = editText.getText().toString();
                textCount.setText(input.length() + getString(R.string.TEXT_LETTERS));
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnSave :
                String date = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
                String letters = textCount.getText().toString();
                String article = editText.getText().toString();
                int id = intent.getExtras().getInt(getString(R.string.EXTRA_ID));

                WritingRepository repository = new WritingRepository(this);

                if(code.equals(getString(R.string.REQUEST_ADD))) {
                    repository.insert(date, letters, article);
                }else{
                    repository.editById(id, date, letters, article);
                }

                saveResult.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        saveResult.setVisibility(View.GONE);
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }, 1000);
                break;

            case R.id.btnCorrection :
                Intent intent = new Intent(this, Teachers.class);
                startActivity(intent);
                break;
        }
    }
}
