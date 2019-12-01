package net.awesomekorean.podo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.awesomekorean.podo.lesson.LessonFrame;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    FragmentPagerAdapter adapterViewPager;

    ViewPager viewPager;

    TextView tvTitle;

    ImageView btnProfile;
    ImageView btnMessage;
    public static ImageView btnLesson;
    public static ImageView btnReading;
    public static ImageView btnWriting;
    public static ImageView btnCollection;
    public static ImageView btnQnA;

    Intent intent;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SettingStatusBar.setStatusBar(this);


        viewPager = findViewById(R.id.viewPager);
        adapterViewPager = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);

        tvTitle = findViewById(R.id.tvTitle);
        btnProfile = findViewById(R.id.btnProfile);
        btnMessage = findViewById(R.id.btnMessage);
        btnLesson = findViewById(R.id.btnLesson);
        btnReading = findViewById(R.id.btnReading);
        btnWriting = findViewById(R.id.btnWriting);
        btnCollection = findViewById(R.id.btnCollection);
        btnQnA = findViewById(R.id.btnQnA);
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
                intent = new Intent(this, Profile.class);
                startActivity(intent);
                break;

            case R.id.btnMessage:
                intent = new Intent(this, Message.class);
                startActivity(intent);
                break;

            case R.id.btnLesson:
                setMainBtns(btnLesson, R.drawable.lesson_active, R.string.LESSON);
                viewPager.setCurrentItem(0);
                break;

            case R.id.btnReading:
                setMainBtns(btnReading, R.drawable.reading_active, R.string.READING);
                viewPager.setCurrentItem(1);
                break;

            case R.id.btnWriting:
                setMainBtns(btnWriting, R.drawable.writting_active, R.string.WRITING);
                viewPager.setCurrentItem(2);
                break;

            case R.id.btnCollection:
                setMainBtns(btnCollection, R.drawable.collection_active, R.string.COLLECTION);
                viewPager.setCurrentItem(3);
                break;

            case R.id.btnQnA:
                setMainBtns(btnQnA, R.drawable.qna_active, R.string.QNA);
                viewPager.setCurrentItem(4);
                break;

        }
    }

    public void setMainBtns(ImageView btn, int active, int title) {
        btnLesson.setImageResource(R.drawable.lesson);
        btnReading.setImageResource(R.drawable.reading);
        btnWriting.setImageResource(R.drawable.writting);
        btnCollection.setImageResource(R.drawable.collection);
        btnQnA.setImageResource(R.drawable.qna);

        btn.setImageResource(active);
        tvTitle.setText(getString(title));
    }
}



