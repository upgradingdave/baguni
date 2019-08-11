package net.awesomekorean.baguni.collection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import net.awesomekorean.baguni.R;

public class CollectionFlashCard extends AppCompatActivity {

    EditText textFront;
    EditText textBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_flash_card);

        Intent intent = getIntent();

        String textKorean = intent.getExtras().getString("Korean");
        String textEnglish = intent.getExtras().getString("English");

        System.out.println("TK:"+textKorean);
        System.out.println("TE:"+textEnglish);

        textFront = findViewById(R.id.textFront);
        textBack = findViewById(R.id.textBack);

        textFront.setText(textKorean);
        textBack.setText(textEnglish);
    }
}
