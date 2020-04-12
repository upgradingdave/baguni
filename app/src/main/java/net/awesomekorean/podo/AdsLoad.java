package net.awesomekorean.podo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AdsLoad {

    Context context;

    Activity activity;

    InterstitialAd interstitialAd;

    private static AdsLoad instance = new AdsLoad();

    public static AdsLoad getInstance() {

        return instance;
    }


    public void setAds(final Context context) {

        this.context = context;

        MobileAds.initialize(context, context.getString(R.string.ADMOB_APP_ID));

        interstitialAd = new InterstitialAd(context);

        interstitialAd.setAdUnitId(context.getString(R.string.ADMOB_TEST_ID_FULL_SCREEN));

        interstitialAd.loadAd(new AdRequest.Builder().build());

        interstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                System.out.println("광고 로드에 성공했습니다.");
            }

            @Override
            public void onAdFailedToLoad(int i) {
                System.out.println("광고 로드에 실패했습니다.");
            }

            @Override
            public void onAdClosed() {

                activity.finish();

                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }


    public void playAds(Activity activity) {

        this.activity = activity;

        if(interstitialAd.isLoaded()) {

            interstitialAd.show();

            System.out.println("광고를 재생합니다.");

        } else {

            System.out.println("The interstitial ads wasn't loaded yet.");

            activity.finish();
        }
    }
}
