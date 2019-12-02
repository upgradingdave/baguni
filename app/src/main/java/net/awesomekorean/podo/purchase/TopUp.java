package net.awesomekorean.podo.purchase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SettingStatusBar;

public class TopUp extends AppCompatActivity implements View.OnClickListener {

    ImageView btnClose;

    LinearLayout pointA;
    LinearLayout pointB;
    LinearLayout pointC;

    ImageView checkPointA;
    ImageView checkPointB;
    ImageView checkPointC;

    Button btnGetPoint;

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
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnClose :
                finish();
                break;

            case R.id.pointA :
                setPurchase(pointA, checkPointA);
                break;

            case R.id.pointB :
                setPurchase(pointB, checkPointB);
                break;

            case R.id.pointC :
                setPurchase(pointC, checkPointC);
                break;

            case R.id.btnGetPoint :
                break;
        }
    }

    private void setPurchase(LinearLayout purchase, ImageView check) {
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
