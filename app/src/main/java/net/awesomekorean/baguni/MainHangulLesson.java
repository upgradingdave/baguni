package net.awesomekorean.baguni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainHangulLesson extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hangul_lesson);
    }

    public void onButtonClick(View v) {
        Intent intent = new Intent(getApplicationContext(), HangulLesson.class);
        startActivity(intent);
    }
}
