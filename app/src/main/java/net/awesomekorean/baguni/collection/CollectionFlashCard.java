package net.awesomekorean.baguni.collection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.awesomekorean.baguni.MainActivity;
import net.awesomekorean.baguni.MainCollection;
import net.awesomekorean.baguni.R;

public class CollectionFlashCard extends AppCompatActivity implements Button.OnClickListener {

    Intent intent;

    Button btnCancel;
    Button btnSave;

    EditText textFront;
    EditText textBack;

    String mode;

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

        mode = intent.getExtras().getString("Mode");

        if(mode.equals("edit")) {

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
                break;

            case R.id.btnSave :

                CollectionDb db = new CollectionDb();

                if(mode.equals("edit")) {
                    db.editCollection(index, textFront.getText().toString(), textBack.getText().toString());

                } else if (mode.equals("add")) {
                    db.addCollection(textFront.getText().toString(), textBack.getText().toString());
                }
                intent = new Intent(this, MainActivity.class);
                //intent.putExtra("viewPager", 3);
                startActivity(intent);
                break;
        }
    }
}
