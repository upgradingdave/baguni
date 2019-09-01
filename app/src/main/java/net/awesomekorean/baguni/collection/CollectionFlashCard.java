package net.awesomekorean.baguni.collection;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.awesomekorean.baguni.R;

public class CollectionFlashCard extends AppCompatActivity implements Button.OnClickListener {

    public static final String REQUEST_EDIT = "edit";
    public static final String REQUEST_ADD = "add";
    public static final String REQUEST = "request";
    public static final String TEXT_FRONT = "front";
    public static final String TEXT_BACK = "back";
    public static final String TEXT_INDEX = "index";



    Intent intent;

    Button btnCancel;
    Button btnSave;

    TextView saveResult;

    EditText editFront;
    EditText editBack;

    String code;

    String textFront;
    String textBack;
    int index;

    CollectionDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_flash_card);

        editFront = findViewById(R.id.textFront);
        editBack = findViewById(R.id.textBack);
        saveResult = findViewById(R.id.saveResult);

        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        Intent intent = getIntent();

        code = intent.getExtras().getString(REQUEST);

        // EDIT 때, 기존의 front, back 값을 받아서 출력
        if(code.equals(REQUEST_EDIT)) {

            textFront = intent.getExtras().getString(TEXT_FRONT);
            textBack = intent.getExtras().getString(TEXT_BACK);
            index = intent.getExtras().getInt(TEXT_INDEX);
            editFront.setText(textFront);
            editBack.setText(textBack);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnCancel :
                // ADD 일 때는 result 값을 주고, EDIT 일 때는 그냥 activity 만 종료함.
                if(code.equals(REQUEST_ADD)) {
                    intent = new Intent();
                    setResult(RESULT_OK, intent);
                }
                finish();
                break;

            case R.id.btnSave :

                String front = editFront.getText().toString();
                String back = editBack.getText().toString();

                // ADD 일 때, save 를 눌러도 collection 으로 전환되지 않고 계속 단어를 추가 할 수 있다
                if(code.equals(REQUEST_ADD)) {
                    CollectionRepository repository = new CollectionRepository(this);
                    repository.insert(front, back);

                    saveResult.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            saveResult.setVisibility(View.GONE);
                            editFront.setText("");
                            editBack.setText("");
                        }
                    }, 1000);

                // EDIT 일 때, 수정 된 front, back 값을 collection 으로 넘긴다.
                } else {
                    intent = new Intent();
                    intent.putExtra(TEXT_FRONT, front);
                    intent.putExtra(TEXT_BACK, back);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
        }
    }
}
