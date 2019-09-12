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

    Button btnCancel;
    Button btnSave;

    TextView saveResult;

    EditText editFront;
    EditText editBack;

    String code;

    String textFront;
    String textBack;
    int index;

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

        code = intent.getExtras().getString(getString(R.string.REQUEST));

        // EDIT 때, 기존의 front, back 값을 받아서 출력
        if(code.equals(getString(R.string.REQUEST_EDIT))) {

            textFront = intent.getExtras().getString(getString(R.string.EXTRA_FRONT));
            textBack = intent.getExtras().getString(getString(R.string.EXTRA_BACk));
            index = intent.getExtras().getInt(getString(R.string.EXTRA_ID));
            editFront.setText(textFront);
            editBack.setText(textBack);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnCancel :
                // ADD 일 때는 result 값을 주고, EDIT 일 때는 그냥 activity 만 종료함.
                if(code.equals(getString(R.string.REQUEST_ADD))) {
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                }
                finish();
                break;

            case R.id.btnSave :

                String front = editFront.getText().toString();
                String back = editBack.getText().toString();

                CollectionRepository repository = new CollectionRepository(this);

                // ADD 일 때, save 를 눌러도 collection 으로 전환되지 않고 계속 단어를 추가 할 수 있다
                if(code.equals(getString(R.string.REQUEST_ADD))) {
                    repository.insert(front, back);

                // EDIT 일 때
                } else {
                    repository.editById(index, front, back);
                }
                saveResult.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        saveResult.setVisibility(View.GONE);
                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        editFront.setText("");
                        editBack.setText("");
                        if(code.equals(getString(R.string.REQUEST_EDIT))) {
                            finish();
                        }
                    }
                }, 1000);
                break;
        }
    }
}
