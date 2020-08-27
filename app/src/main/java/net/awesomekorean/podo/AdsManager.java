package net.awesomekorean.podo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.firebase.analytics.FirebaseAnalytics;

public class AdsManager {

    private String ADMOB_APP_ID = "ca-app-pub-7371634469098812~7867291008";
    private String ADMOB_TEST_ID_FULL_SCREEN = "ca-app-pub-3940256099942544/1033173712";
    private String ADMOB_TEST_ID_REWARDED = "ca-app-pub-3940256099942544/5224354917";
    private String ADMOB_TEST_ID_NATIVE = "ca-app-pub-3940256099942544/2247696110";

    private String ADMOB_ID_FULL_SCREEN = "ca-app-pub-7371634469098812/6205713214";
    private String ADMOB_ID_REWARDED = "ca-app-pub-7371634469098812/2074896515";
    private String ADMOB_ID_NATIVE = "ca-app-pub-7371634469098812/4870095522";

    public InterstitialAd interstitialAd;
    public RewardedAd rewardedAd;
    public UnifiedNativeAd unifiedNativeAd;

    private static AdsManager instance = new AdsManager();
    public static AdsManager getInstance() {
        return instance;
    }


    // 전면광고 로드
    public void loadFullAds(Context context) {
        MobileAds.initialize(context, ADMOB_APP_ID);
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(ADMOB_TEST_ID_FULL_SCREEN);
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                System.out.println("전면 광고 로드에 성공했습니다.");
            }

            @Override
            public void onAdFailedToLoad(int i) {
                System.out.println("전면 광고 로드에 실패했습니다.");
            }

            @Override
            public void onAdClosed() {
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
    }


    // 전면광고 재생
    public void playFullAds(Context context) {

        if(interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
            System.out.println("전면 광고를 재생합니다.");

            // analytics 로그 이벤트 얻기
            FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context.getApplicationContext());
            Bundle bundle = new Bundle();
            firebaseAnalytics.logEvent("full_watch", bundle);

        } else {
            System.out.println("The interstitial ads wasn't loaded yet.");
        }
    }


    // 리워드 광고 로드하기
    public void loadRewardAds(Context context) {
        MobileAds.initialize(context, ADMOB_APP_ID);
        rewardedAd = new RewardedAd(context, ADMOB_TEST_ID_REWARDED);
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {

            @Override
            public void onRewardedAdLoaded() {
                System.out.println("보상형 광고 로드에 성공했습니다");
            }

            @Override
            public void onRewardedAdFailedToLoad(int i) {
                System.out.println("보상형 광고 로드에 실패했습니다.");
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
    }


    // 리워드 광고 재생
    public void playRewardAds(final Activity activity) {
        RewardedAdCallback adCallback = new RewardedAdCallback() {

            @Override
            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
            }

            @Override
            public void onRewardedAdFailedToShow(int i) {
                Toast.makeText(activity, "Failed to load ad. Please try it again.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRewardedAdClosed() {
                Intent intent = new Intent(activity, GetRandomPoint.class);
                activity.startActivityForResult(intent, 200);
                loadRewardAds(activity);

                // analytics 로그 이벤트 얻기
                FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(activity);
                Bundle bundle = new Bundle();
                firebaseAnalytics.logEvent("reward_watch", bundle);
            }
        };
        rewardedAd.show(activity, adCallback);
    }


    // 네이티브 광고 로드하기
    public void loadNativeAds(Context context) {
        MobileAds.initialize(context, ADMOB_APP_ID);
        AdLoader adLoader = new AdLoader.Builder(context, ADMOB_TEST_ID_NATIVE)
                .forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd ad) {
                        System.out.println("네이티브 광고 로드에 성공했습니다.");
                        unifiedNativeAd = ad;
                    }
                })

                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        System.out.println("네이티브 광고 로드에 실패했습니다.");
                    }
                })

                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }
}
