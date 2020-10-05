package net.awesomekorean.podo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import net.awesomekorean.podo.login.SignIn;

import java.util.List;

public class Logo extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private FirebaseAnalytics firebaseAnalytics;
    private FirebaseCrashlytics crashlytics;
    private AdsManager adsManager;
    private Intent intent;
    private LinearLayout layoutUpdatingDB;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        layoutUpdatingDB = findViewById(R.id.layoutUpdatingDB);
        layoutUpdatingDB.setVisibility(View.VISIBLE);

        // Crashlytics 초기화
        crashlytics = FirebaseCrashlytics.getInstance();
        crashlytics.setCrashlyticsCollectionEnabled(BuildConfig.CRASHLYTICS);
        System.out.println("크레쉴리틱스 : " + BuildConfig.CRASHLYTICS);

        // 애널리스트 초기화
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        firebaseAnalytics.setAnalyticsCollectionEnabled(BuildConfig.ANALYTICS);
        System.out.println("애널리틱스 : " + BuildConfig.ANALYTICS);


        // 애드몹 초기화
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                System.out.println("애드몹을 초기화했습니다");
                // 광고 미리 로드하기
                adsManager = AdsManager.getInstance();
                adsManager.loadFullAds(getApplicationContext());
                adsManager.loadRewardAds(getApplicationContext());
                adsManager.loadNativeAds(getApplicationContext());
            }
        });

        userEmail = SharedPreferencesInfo.getUserEmail(getApplicationContext());

        // 기기 토큰 얻기
        String token = SharedPreferencesInfo.getUserToken(getApplicationContext());
        if(token == null) {
            FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                @Override
                public void onComplete(@NonNull Task<InstanceIdResult> task) {
                    if (task.isSuccessful()) {
                        String token = task.getResult().getToken();
                        SharedPreferencesInfo.setUserToken(getApplicationContext(), token);
                        System.out.println("토큰을 저장했습니다 : " + token);
                    }
                }
            });
        } else {
            System.out.println("토큰 : " + token);
        }


        boolean isSignIn = SharedPreferencesInfo.getSignIn(getApplicationContext());
        if(isSignIn && userEmail!=null) {

            if (IsOnline.isOnline(getApplicationContext())) {
                DocumentReference docRef = db.collection(getString(R.string.DB_USERS)).document(userEmail);
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()) {
                            System.out.println("신DB 있음!");
                            UserInformation information = documentSnapshot.toObject(UserInformation.class);
                            goToMain(information);

                        } else {
                            System.out.println("신DB 없음!");
                            // 구DB에서 신DB로 옮기고 삭제
                            DocumentReference reference = db.collection(getString(R.string.DB_USERS)).document(userEmail).collection(getString(R.string.DB_INFORMATION)).document(getString(R.string.DB_INFORMATION));
                            reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                        final UserInformation information = documentSnapshot.toObject(UserInformation.class);
                                        final UserInformation newInformation = removeProgress(information);

                                        db.collection(getString(R.string.DB_USERS)).document(userEmail).set(newInformation).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                System.out.println("유저정보를 신DB로 복사했습니다.");

                                                db.collection(getString(R.string.DB_USERS)).document(userEmail).collection(getString(R.string.DB_INFORMATION)).document(getString(R.string.DB_INFORMATION))
                                                        .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        System.out.println("구DB를 삭제했습니다.");

                                                        db.collection(getString(R.string.DB_USERS)).document(userEmail).collection(getString(R.string.DB_MESSAGES))
                                                                .get()
                                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                        if(task.isSuccessful()) {
                                                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                                                document.getReference().delete();
                                                                            }
                                                                            System.out.println("메시지DB를 삭제했습니다.");
                                                                            goToMain(newInformation);
                                                                        }
                                                                    }
                                                                });
                                                    }
                                                });
                                            }
                                        });
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    System.out.println("실패!");
                                }
                            });
                        }
                    }
                });

            } else {
                System.out.println("온라인 상태가 아닙니다. 로그인 페이지로 이동합니다.");
                Toast.makeText(getApplicationContext(), "Internet connection required.", Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
                finish();
            }

        } else {
            System.out.println("로그인 상태가 아닙니다. 로그인 페이지로 이동합니다.");
            intent = new Intent(getApplicationContext(), SignIn.class);
            startActivity(intent);
            finish();
        }


/*
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;

                boolean isSignIn = SharedPreferencesInfo.getSignIn(getApplicationContext());
                if(isSignIn) {
                    if(IsOnline.isOnline(getApplicationContext())) {
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
        }, 2);
*/
    }

    // lessonComplete 에서 progress 제거
    private UserInformation removeProgress(UserInformation userInformation) {
        List<String> lessonComplete = userInformation.getLessonComplete();
        for (int i=0; i<lessonComplete.size(); i++) {
            String[] split = lessonComplete.get(i).split("%");
            lessonComplete.set(i, split[0]);
        }

        List<String> readingComplete = userInformation.getReadingComplete();
        for (int i=0; i<readingComplete.size(); i++) {
            String[] split = readingComplete.get(i).split("%");
            readingComplete.set(i, split[0]);
        }

        userInformation.setLessonComplete(lessonComplete);
        userInformation.setReadingComplete(readingComplete);
        return userInformation;
    }


    private void goToMain(UserInformation userInformation) {
        SharedPreferencesInfo.setUserInfo(getApplicationContext(), userInformation);
        System.out.println("앱DB에 유저 정보를 저장했습니다.");
        intent = new Intent(getApplicationContext(), MainActivity.class);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutUpdatingDB.setVisibility(View.GONE);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
