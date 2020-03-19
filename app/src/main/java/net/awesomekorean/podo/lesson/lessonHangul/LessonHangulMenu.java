package net.awesomekorean.podo.lesson.lessonHangul;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;

public class LessonHangulMenu extends AppCompatActivity implements View.OnClickListener {

    ImageView btnBack;

    LinearLayout consonant;
    LinearLayout vowel;
    LinearLayout batchim;
    LinearLayout assembly;

    Intent intent;

    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_hangul_menu);

        btnBack = findViewById(R.id.btnBack);
        consonant = findViewById(R.id.consonant);
        vowel = findViewById(R.id.vowel);
        batchim = findViewById(R.id.batchim);
        assembly = findViewById(R.id.assembly);
        btnBack.setOnClickListener(this);
        consonant.setOnClickListener(this);
        vowel.setOnClickListener(this);
        batchim.setOnClickListener(this);
        assembly.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.consonant :
                loadAds();
                intent = new Intent(getApplicationContext(), LessonHangul.class);
                intent.putExtra("conVowBat", "consonant");
                startActivity(intent);
                break;

            case R.id.vowel :
                loadAds();
                intent = new Intent(getApplicationContext(), LessonHangul.class);
                intent.putExtra("conVowBat", "vowel");
                startActivity(intent);
                break;

            case R.id.batchim :
                loadAds();
                intent = new Intent(getApplicationContext(), LessonHangul.class);
                intent.putExtra("conVowBat", "batchim");
                startActivity(intent);
                break;

            case R.id.assembly :
                loadAds();
                intent = new Intent(getApplicationContext(), LessonHangulAssembly.class);
                startActivity(intent);
                break;

            case R.id.btnBack :
                runAds();
                break;
        }
    }

    // 애드몹 전면광고 로드하기
    private void loadAds() {
        if(interstitialAd == null) {
            MobileAds.initialize(getApplicationContext(), getString(R.string.ADMOB_APP_ID));
            interstitialAd = new InterstitialAd(getApplicationContext());
            interstitialAd.setAdUnitId(getString(R.string.ADMOB_ID_FULL_SCREEN));
            interstitialAd.loadAd(new AdRequest.Builder().build());
            interstitialAd.setAdListener(new AdListener() {

                @Override
                public void onAdLoaded() {
                    System.out.println("광고가 로드되었습니다");
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    System.out.println("광고 로드에 실패했습니다.");
                }

                @Override
                public void onAdClosed() {
                    finish();
                }
            });
        }
    }

    // 애드몹 광고 보여주기
    private void runAds() {
        if(interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            finish();
            System.out.println("The interstitial ads wasn't loaded yet.");
        }
    }

    @Override
    public void onBackPressed() {
        runAds();
        super.onBackPressed();
    }
}
