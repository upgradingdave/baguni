package net.awesomekorean.podo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import net.awesomekorean.podo.login.SignIn;

public class Logo extends AppCompatActivity {

    public static final String WIFI_STATE = "WIFE";
    public static final String MOBILE_STATE = "MOBILE";
    public static final String NONE_STATE = "NONE";

    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        // Crashlytics 초기화
        FirebaseCrashlytics crashlytics = FirebaseCrashlytics.getInstance();
        crashlytics.setCrashlyticsCollectionEnabled(true);

        // 애널리스트 초기화
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // 애드몹 초기화
        MobileAds.initialize(this, getString(R.string.ADMOB_APP_ID));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();

                boolean isSignIn = SharedPreferencesInfo.getSignIn(getApplicationContext());
                if(isSignIn) {
                    if(getWhatKindOfNetwork(getApplicationContext()) != NONE_STATE) {
                        intent = new Intent(getApplicationContext(), MainActivity.class);

                        // 재접속 로그
                        Bundle bundle = new Bundle();
                        firebaseAnalytics.logEvent("revisit", bundle);

                    } else {
                        intent = new Intent(getApplicationContext(), SignIn.class);
                        Toast.makeText(getApplicationContext(), "Internet connection required.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    intent = new Intent(getApplicationContext(), SignIn.class);
                }
                startActivity(intent);
                finish();
            }
        }, 2000);

    }

    // 인터넷 연결상태 확인하기
    public static String getWhatKindOfNetwork(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return WIFI_STATE;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return MOBILE_STATE;
            }
        }
        return NONE_STATE;
    }
}
