package net.awesomekorean.podo.purchase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

public class TopUp extends AppCompatActivity implements View.OnClickListener, BillingProcessor.IBillingHandler {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    BillingProcessor bp;
    private String KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjttlMN7KIO22BMxNl1H2oN6FjRAUqQPvrlOKKybEwj0f2mXNreqpt5n/6/SV4+TAnADJSJO1j9MoN1fvVkYtr0zJtEe62hBcBouyqRKt/uWGhcy6ToMUlNjl9Wxf9UaSrJ3c0IePZvRtlGhd9y2OpK99uMfLjfqxY7+UIjnqBIO8qXSiy+E1jUrlR6AhZoBrwSfVSVPjOXya5K2uEngttMWaYwrnVhmBeEmjdIAjt0021plp4t7bYP5zSvwQp3dbomgnwE33njXWhn3ohla8m6wxZUPpZzvtCWKRo+SegyXx+wX2OVKcIkK27IrK9NEmrJzzamL2DLj/QhXKnk6aAQIDAQAB";

    ImageView btnClose;

    LinearLayout pointA;
    LinearLayout pointB;
    LinearLayout pointC;

    ImageView checkPointA;
    ImageView checkPointB;
    ImageView checkPointC;

    Button btnGetPoint;

    String skuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        SettingStatusBar.setStatusBar(this);

        btnClose = findViewById(R.id.btnClose);
        pointA = findViewById(R.id.pointA);
        pointB = findViewById(R.id.pointB);
        pointC = findViewById(R.id.pointC);
        checkPointA = findViewById(R.id.checkPointA);
        checkPointB = findViewById(R.id.checkPointB);
        checkPointC = findViewById(R.id.checkPointC);
        btnGetPoint = findViewById(R.id.btnGetPoint);
        btnClose.setOnClickListener(this);
        pointA.setOnClickListener(this);
        pointB.setOnClickListener(this);
        pointC.setOnClickListener(this);
        btnGetPoint.setOnClickListener(this);

        bp = new BillingProcessor(this, KEY, this);
        bp.initialize();

    }

    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {
        // * 구매 완료시 호출
        // productId: 구매한 sku (ex) no_ads)
        // details: 결제 관련 정보

        final UserInformation userInformation = SharedPreferencesInfo.getUserInfo(getApplicationContext());
        int havePoint = userInformation.getPoints();
        int newPoint = 0;

        if(productId.equals(getString(R.string.SKU_100))) {
            newPoint = havePoint + 100;
        } else if(productId.equals(getString(R.string.SKU_500))) {
            newPoint = havePoint + 500;
        } else if(productId.equals(getString(R.string.SKU_1000))) {
            newPoint = havePoint + 1000;
        }

        userInformation.setPoints(newPoint);

        db.collection(getString(R.string.DB_USERS)).document(MainActivity.userEmail).collection(getString(R.string.DB_INFORMATION)).document(getString(R.string.DB_INFORMATION))
                .set(userInformation)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        SharedPreferencesInfo.setUserInfo(getApplicationContext(), userInformation);
                    }
                });

        Toast.makeText(getApplicationContext(), getString(R.string.THANKS_PURCHASING), Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnClose :
                finish();
                break;

            case R.id.pointA :
                setPurchase(pointA, checkPointA, getString(R.string.SKU_100));
                break;

            case R.id.pointB :
                setPurchase(pointB, checkPointB, getString(R.string.SKU_500));
                break;

            case R.id.pointC :
                setPurchase(pointC, checkPointC, getString(R.string.SKU_1000));
                break;

            case R.id.btnGetPoint :
                bp.purchase(this, skuId);
                break;
        }
    }

    private void setPurchase(LinearLayout purchase, ImageView check, String id) {

        skuId = id;
        pointA.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_grey_10));
        pointB.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_grey_10));
        pointC.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_grey_10));

        checkPointA.setVisibility(View.GONE);
        checkPointB.setVisibility(View.GONE);
        checkPointC.setVisibility(View.GONE);

        purchase.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_grey_10_stroke_navy));
        check.setVisibility(View.VISIBLE);
    }

}
