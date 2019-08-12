package net.awesomekorean.baguni.collection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.awesomekorean.baguni.R;

public class CollectionFlashCard extends AppCompatActivity implements Button.OnClickListener {

    public static final String REQUEST_EDIT = "edit";
    public static final String REQUEST_ADD = "add";

    Intent intent;

    Button btnCancel;
    Button btnSave;

    EditText textFront;
    EditText textBack;

    String code;

    String textKorean;
    String textEnglish;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_flash_card);

        textFront = findViewById(R.id.textFront);
        textBack = findViewById(R.id.textBack);

        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        Intent intent = getIntent();

        code = intent.getExtras().getString("request");

        if(code.equals(REQUEST_EDIT)) {

            textKorean = intent.getExtras().getString("Korean");
            textEnglish = intent.getExtras().getString("English");
            index = intent.getExtras().getInt("index");
            textFront.setText(textKorean);
            textBack.setText(textEnglish);
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnCancel :
                finish();
                break;

            case R.id.btnSave :

                CollectionDb db = new CollectionDb();

                if(code.equals(REQUEST_EDIT)) {
                    db.editCollection(index, textFront.getText().toString(), textBack.getText().toString());
                    System.out.println("DB: "+db.getCollectionKorean()[0]);
                    Toast.makeText(this, "Collection edited", Toast.LENGTH_LONG).show();

                } else if (code.equals(REQUEST_ADD)) {
                    db.addCollection(textFront.getText().toString(), textBack.getText().toString());
                    Toast.makeText(this, "Collection added", Toast.LENGTH_LONG).show();
                }
                intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
