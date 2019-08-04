package net.awesomekorean.baguni;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    FragmentPagerAdapter adapterViewPager;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        adapterViewPager = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);

        Button btnLesson = findViewById(R.id.btnLesson);
        Button btnReading = findViewById(R.id.btnReading);
        Button btnWriting = findViewById(R.id.btnWriting);
        Button btnCollection = findViewById(R.id.btnCollection);
        btnLesson.setOnClickListener(this);
        btnReading.setOnClickListener(this);
        btnWriting.setOnClickListener(this);
        btnCollection.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnProfile:
                break;

            case R.id.btnMessage:
                break;

            case R.id.btnLesson:
                viewPager.setCurrentItem(0);
                break;

            case R.id.btnReading:
                viewPager.setCurrentItem(1);
                break;

            case R.id.btnWriting:
                viewPager.setCurrentItem(2);
                break;

            case R.id.btnCollection:
                viewPager.setCurrentItem(3);
                break;
        }
    }

}
