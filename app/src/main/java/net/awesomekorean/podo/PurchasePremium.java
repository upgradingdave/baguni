package net.awesomekorean.podo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.awesomekorean.podo.purchasePremium.PurchasePremiumViewPagerAdapter;

import me.relex.circleindicator.CircleIndicator;

public class PurchasePremium extends AppCompatActivity implements Button.OnClickListener{

    FragmentPagerAdapter adapterViewPager;

    ViewPager viewPager;

    ImageView btnClose;
    LinearLayout purchaseMonthly;
    LinearLayout purchaseYearly;
    LinearLayout purchaseLifetime;

    ImageView checkMonthly;
    ImageView checkYearly;
    ImageView checkLifetime;

    Button btnPremium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_premium);

        SettingStatusBar.setStatusBar(this);

        btnClose = findViewById(R.id.btnClose);
        purchaseMonthly = findViewById(R.id.purchaseMonthly);
        purchaseYearly = findViewById(R.id.purchaseYearly);
        purchaseLifetime = findViewById(R.id.purchaseLifetime);
        checkMonthly = findViewById(R.id.checkMonthly);
        checkYearly = findViewById(R.id.checkYearly);
        checkLifetime = findViewById(R.id.checkLifetime);
        btnPremium = findViewById(R.id.btnPremium);
        btnClose.setOnClickListener(this);
        purchaseMonthly.setOnClickListener(this);
        purchaseYearly.setOnClickListener(this);
        purchaseLifetime.setOnClickListener(this);
        btnPremium.setOnClickListener(this);


        viewPager = findViewById(R.id.viewPager);
        adapterViewPager = new PurchasePremiumViewPagerAdapter(getSupportFragmentManager());
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

            case R.id.purchaseMonthly :
                setPurchase(purchaseMonthly, checkMonthly);
                break;

            case R.id.purchaseYearly :
                setPurchase(purchaseYearly, checkYearly);
                break;

            case R.id.purchaseLifetime :
                setPurchase(purchaseLifetime, checkLifetime);
                break;

            case R.id.btnPremium :
                break;
        }

    }

    private void setPurchase(LinearLayout purchase, ImageView check) {
        purchaseMonthly.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_grey_10));
        purchaseYearly.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_grey_10));
        purchaseLifetime.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_grey_10));

        checkMonthly.setVisibility(View.GONE);
        checkYearly.setVisibility(View.GONE);
        checkLifetime.setVisibility(View.GONE);

        purchase.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_grey_10_stroke_navy));
        check.setVisibility(View.VISIBLE);
    }
}


