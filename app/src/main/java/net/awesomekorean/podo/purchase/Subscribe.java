package net.awesomekorean.podo.purchase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SettingStatusBar;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.purchase.SubscribeViewPagerAdapter;

import me.relex.circleindicator.CircleIndicator;

public class Subscribe extends AppCompatActivity implements Button.OnClickListener, BillingProcessor.IBillingHandler {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    BillingProcessor bp;
    private String KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjttlMN7KIO22BMxNl1H2oN6FjRAUqQPvrlOKKybEwj0f2mXNreqpt5n/6/SV4+TAnADJSJO1j9MoN1fvVkYtr0zJtEe62hBcBouyqRKt/uWGhcy6ToMUlNjl9Wxf9UaSrJ3c0IePZvRtlGhd9y2OpK99uMfLjfqxY7+UIjnqBIO8qXSiy+E1jUrlR6AhZoBrwSfVSVPjOXya5K2uEngttMWaYwrnVhmBeEmjdIAjt0021plp4t7bYP5zSvwQp3dbomgnwE33njXWhn3ohla8m6wxZUPpZzvtCWKRo+SegyXx+wX2OVKcIkK27IrK9NEmrJzzamL2DLj/QhXKnk6aAQIDAQAB";

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

        bp = new BillingProcessor(this, KEY, this);
        bp.initialize();
    }

    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {
        // * 구매 완료시 호출
        // productId: 구매한 sku (ex) no_ads)
        // details: 결제 관련 정보
        if(productId.equals("Sku아이디넣기")) {
            Toast.makeText(getApplicationContext(), getString(R.string.THANKS_SUBSCRIBE), Toast.LENGTH_LONG).show();

            long unixTime = System.currentTimeMillis() / 1000L;
            final UserInformation userInformation = SharedPreferencesInfo.getUserInfo(getApplicationContext());
            userInformation.setIsPremium(true);
            userInformation.setDatePurchase(unixTime);

            db.collection(getString(R.string.DB_USERS)).document(MainActivity.userEmail).collection(getString(R.string.DB_INFORMATION)).document(getString(R.string.DB_INFORMATION))
                    .set(userInformation)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            SharedPreferencesInfo.setUserInfo(getApplicationContext(), userInformation);
                        }
                    });

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onPurchaseHistoryRestored() {
        // * 구매 정보가 복원되었을때 호출
        // bp.loadOwnedPurchasesFromGoogle() 하면 호출 가능

    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {
        // * 구매 오류시 호출
        // errorCode == Constants.BILLING_RESPONSE_RESULT_USER_CANCELED 일때는
        // 사용자가 단순히 구매 창을 닫은것임으로 이것 제외하고 핸들링하기.
        Toast.makeText(getApplicationContext(), getString(R.string.THANKS_SUBSCRIBE), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBillingInitialized() {
        // * 처음에 초기화됬을때.
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        if(bp != null) {
            bp.release();
        }
        super.onDestroy();
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
                if(MainActivity.userInformation.getIsPremium()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.ALREADY_SUBSCRIBE), Toast.LENGTH_LONG).show();
                } else {
                    bp.purchase(this, "Sku아이디넣기");
                }
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


