package net.awesomekorean.podo.lesson.lessonHangul;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import net.awesomekorean.podo.R;

public class LessonHangulMenu extends AppCompatActivity implements View.OnClickListener {

    ImageView btnBack;

    LinearLayout consonant;
    LinearLayout vowel;
    LinearLayout batchim;
    LinearLayout assembly;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_hangul_menu);

        btnBack = findViewById(R.id.btnBack);
        consonant = findViewById(R.id.consonant);
        vowel = findViewById(R.id.vowel);
        batchim = findViewById(R.id.batchim);
        assembly = findViewById(R.id.assembly);
        btnBack.setOnClickListener(this);
        consonant.setOnClickListener(this);
        vowel.setOnClickListener(this);
        batchim.setOnClickListener(this);
        assembly.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.consonant :
                intent = new Intent(getApplicationContext(), LessonHangul.class);
                intent.putExtra("conVowBat", "consonant");
                startActivity(intent);
                break;

            case R.id.vowel :
                intent = new Intent(getApplicationContext(), LessonHangul.class);
                intent.putExtra("conVowBat", "vowel");
                startActivity(intent);
                break;

            case R.id.batchim :
                intent = new Intent(getApplicationContext(), LessonHangul.class);
                intent.putExtra("conVowBat", "batchim");
                startActivity(intent);
                break;

            case R.id.assembly :
                intent = new Intent(getApplicationContext(), LessonHangulAssembly.class);
                startActivity(intent);
                break;

            case R.id.btnBack :
                finish();
                break;
        }
    }

}
