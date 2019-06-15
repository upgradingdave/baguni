package net.awesomekorean.baguni;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt1, bt2, bt3, bt4;
    FragmentManager fm;
    FragmentTransaction ft;
    Word word = new Word();
    Lesson lesson = new Lesson();
    Reading reading = new Reading();
    Writing writing = new Writing();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = (Button) findViewById(R.id.button1);
        bt2 = (Button) findViewById(R.id.button2);
        bt3 = (Button) findViewById(R.id.button3);
        bt4 = (Button) findViewById(R.id.button4);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);

        setFragment(0);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                setFragment(0);
                break;
            case R.id.button2:
                setFragment(1);
                break;
            case R.id.button3:
                setFragment(2);
                break;
            case R.id.button4:
                setFragment(3);
                break;
        }
    }

    private void setFragment(int i) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        switch (i) {
            case 0:
                ft.replace(R.id.main_frame, word);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, lesson);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, reading);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame, writing);
                ft.commit();
                break;
        }
    }
}
