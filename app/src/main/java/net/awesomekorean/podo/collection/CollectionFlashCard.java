package net.awesomekorean.podo.collection;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.awesomekorean.podo.R;

public class CollectionFlashCard extends AppCompatActivity implements Button.OnClickListener {

    ImageView btnBack;
    Button btnSave;

    TextView saveResult;

    EditText editFront;
    EditText editBack;

    String code;

    String textFront;
    String textBack;
    String guid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_flash_card);

        editFront = findViewById(R.id.textFront);
        editBack = findViewById(R.id.textBack);
        saveResult = findViewById(R.id.saveResult);

        btnBack = findViewById(R.id.btnBack);
        btnSave = findViewById(R.id.btnSave);
        btnBack.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        Intent intent = getIntent();

        code = intent.getExtras().getString(getString(R.string.REQUEST));

        // EDIT 때, 기존의 front, icon_back 값을 받아서 출력
        if(code.equals(getString(R.string.REQUEST_EDIT))) {

            textFront = intent.getExtras().getString(getString(R.string.EXTRA_FRONT));
            textBack = intent.getExtras().getString(getString(R.string.EXTRA_BACk));
            guid = intent.getExtras().getString(getString(R.string.EXTRA_GUID));
            editFront.setText(textFront);
            editBack.setText(textBack);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnBack :
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
                    repository.editByGuid(guid, front, back);
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