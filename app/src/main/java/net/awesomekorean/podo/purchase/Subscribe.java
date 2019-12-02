package net.awesomekorean.podo.purchase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SettingStatusBar;
import net.awesomekorean.podo.purchase.SubscribeViewPagerAdapter;

import me.relex.circleindicator.CircleIndicator;

public class Subscribe extends AppCompatActivity implements Button.OnClickListener{

    FragmentPagerAdapter adapterViewPager;

    ViewPager viewPager;

    ImageView btnClose;
    LinearLayout subscribeA;
    LinearLayout subscribeB;
    LinearLayout subscribeC;

    ImageView checkSubscribeA;
    ImageView checkSubscribeB;
    ImageView checkSubscribeC;

    Button btnPremium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        SettingStatusBar.setStatusBar(this);

        btnClose = findViewById(R.id.btnClose);
        subscribeA = findViewById(R.id.subscribeA);
        subscribeB = findViewById(R.id.subscribeB);
        subscribeC = findViewById(R.id.subscribeC);
        checkSubscribeA = findViewById(R.id.checkSubscribeA);
        checkSubscribeB = findViewById(R.id.checkSubscribeB);
        checkSubscribeC = findViewById(R.id.checkSubscribeC);
        btnPremium = findViewById(R.id.btnPremium);
        btnClose.setOnClickListener(this);
        subscribeA.setOnClickListener(this);
        subscribeB.setOnClickListener(this);
        subscribeC.setOnClickListener(this);
        btnPremium.setOnClickListener(this);


        viewPager = findViewById(R.id.viewPager);
        adapterViewPager = new SubscribeViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);

        CircleIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnClose :
                finish();
                break;

            case R.id.subscribeA :
                setPurchase(subscribeA, checkSubscribeA);
                break;

            case R.id.subscribeB :
                setPurchase(subscribeB, checkSubscribeB);
                break;

            case R.id.subscribeC :
                setPurchase(subscribeC, checkSubscribeC);
                break;

            case R.id.btnPremium :
                break;
        }

    }

    private void setPurchase(LinearLayout purchase, ImageView check) {
        subscribeA.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_grey_10));
        subscribeB.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_grey_10));
        subscribeC.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_grey_10));

        checkSubscribeA.setVisibility(View.GONE);
        checkSubscribeB.setVisibility(View.GONE);
        checkSubscribeC.setVisibility(View.GONE);

        purchase.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_grey_10_stroke_navy));
        check.setVisibility(View.VISIBLE);
    }
}


