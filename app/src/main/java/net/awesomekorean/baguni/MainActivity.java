package net.awesomekorean.baguni;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    FragmentManager fm;
    FragmentTransaction ft;

    MainLesson mainLesson = new MainLesson();
    MainReading mainReading = new MainReading();
    MainWriting mainWriting = new MainWriting();

    Button btnReading;
    Button btnWriting;
    Button btnCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLesson = findViewById(R.id.btnLesson);
        Button btnReading = findViewById(R.id.btnReading);
        Button btnWriting = findViewById(R.id.btnWriting);
        Button btnCollection = findViewById(R.id.btnCollection);
        btnLesson.setOnClickListener(this);
        btnReading.setOnClickListener(this);
        btnWriting.setOnClickListener(this);
        btnCollection.setOnClickListener(this);

        setFrag(mainLesson);

    }

    public void setFrag(Fragment page) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.mainFrame, page);
        ft.commit();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnProfile:
                setFrag(mainLesson);
                break;

            case R.id.btnMessage:
                setFrag(mainLesson);
                break;

            case R.id.btnLesson:
                setFrag(mainLesson);
                break;

            case R.id.btnReading:
                setFrag(mainReading);
                break;

            case R.id.btnWriting:
                setFrag(mainWriting);
                break;

            case R.id.btnCollection:
                setFrag(mainLesson);
                break;
        }
    }

}
