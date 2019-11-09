package net.awesomekorean.baguni;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    FragmentPagerAdapter adapterViewPager;

    ViewPager viewPager;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SettingStatusBar.setStatusBar(this);


        viewPager = findViewById(R.id.viewPager);
        adapterViewPager = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);

        ImageView btnProfile = findViewById(R.id.btnProfile);
        ImageView btnMessage = findViewById(R.id.btnMessage);
        ImageView btnLesson = findViewById(R.id.btnLesson);
        ImageView btnReading = findViewById(R.id.btnReading);
        ImageView btnWriting = findViewById(R.id.btnWriting);
        ImageView btnCollection = findViewById(R.id.btnCollection);
        ImageView btnQnA = findViewById(R.id.btnQnA);
        btnProfile.setOnClickListener(this);
        btnMessage.setOnClickListener(this);
        btnLesson.setOnClickListener(this);
        btnReading.setOnClickListener(this);
        btnWriting.setOnClickListener(this);
        btnCollection.setOnClickListener(this);
        btnQnA.setOnClickListener(this);

        // 프로필 이미지 동그랗게 만들기
        btnProfile.setBackground(new ShapeDrawable(new OvalShape()));
        if(Build.VERSION.SDK_INT >= 21) {
            btnProfile.setClipToOutline(true);
        }
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

            case R.id.btnQnA:
                viewPager.setCurrentItem(4);
                break;

        }
    }

    private Bitmap getMaskedBitmap(int _srcResId, float _roundInPixel)
    {
        Bitmap srcBitmap = BitmapFactory.decodeResource( getResources(), _srcResId);

        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), srcBitmap);

        roundedDrawable.setCornerRadius( _roundInPixel );
        roundedDrawable.setAntiAlias(true);

        return roundedDrawable.getBitmap();
    }
}


