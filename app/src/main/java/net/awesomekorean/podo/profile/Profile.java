package net.awesomekorean.podo.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.AdsManager;
import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.lesson.LessonFinish;
import net.awesomekorean.podo.login.SignIn;
import net.awesomekorean.podo.purchase.TopUp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ImageView btnBack;
    ImageView userImage;
    TextView userName;
    TextView userPoint;

    ImageView btnGetPoint;

    LinearLayout layoutEditName;
    LinearLayout layoutEditNameOpen;
    EditText editName;
    Button btnSave;

    LinearLayout evaluation;
    LinearLayout recommend;
    LinearLayout getPointByAd;
    LinearLayout getPointByPurchasing;
    LinearLayout logout;

    ImageView arrowEditProfile;

    Intent intent;

    AttendanceAdapter adapter;
    List<DayOfWeekItem> list;

    int attendanceCount = 0; // 출석체크 카운트 (연속출석 7번 했는지 확인)

    boolean btnExtendClicked = false;

    Bitmap bitmap;

    UserInformation userInformation;

    RecyclerView recyclerView;

    AdsManager adsManager;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        context = getApplicationContext();

        adsManager = AdsManager.getInstance();

        if(adsManager.rewardedAd == null || !adsManager.rewardedAd.isLoaded()) {

            adsManager.loadRewardAds(context);
        }

        btnBack = findViewById(R.id.btnBack);
        userImage = findViewById(R.id.userImage);
        userName = findViewById(R.id.userName);
        userPoint = findViewById(R.id.userPoint);

        btnGetPoint = findViewById(R.id.btnGetPoint);

        layoutEditName = findViewById(R.id.layoutEditName);
        layoutEditNameOpen = findViewById(R.id.layoutEditNameOpen);
        editName = findViewById(R.id.editName);
        btnSave = findViewById(R.id.btnSave);

        evaluation = findViewById(R.id.evaluation);
        recommend = findViewById(R.id.recommend);
        getPointByAd = findViewById(R.id.getPointsByAd);
        getPointByPurchasing = findViewById(R.id.getPointsByPurchasing);
        logout = findViewById(R.id.logout);

        arrowEditProfile = findViewById(R.id.arrowEditProfile);

        btnBack.setOnClickListener(this);
        btnGetPoint.setOnClickListener(this);
        layoutEditName.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        evaluation.setOnClickListener(this);
        recommend.setOnClickListener(this);
        getPointByAd.setOnClickListener(this);
        getPointByPurchasing.setOnClickListener(this);
        logout.setOnClickListener(this);

        userInformation = SharedPreferencesInfo.getUserInfo(context);

        userName.setText(MainActivity.userName);
        userPoint.setText(String.valueOf(userInformation.getPoints()));

        // 유저프로필사진 가져오기
        final Uri profileImage = MainActivity.userImage;
        if(profileImage != null) {
            // 프로필이미지 불러오기
            Thread mThread = new Thread() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(profileImage.toString());
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true);
                        conn.connect();

                        InputStream is = conn.getInputStream();
                        bitmap = BitmapFactory.decodeStream(is);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            mThread.start();

            try {
                mThread.join(3000);
                userImage.setImageBitmap(bitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        setAttendance(userInformation.getAttendance());
    }


    // 출석체크 리사이클러뷰 세팅
    private void setAttendance(List<Boolean> attendance) {

        list = new ArrayList<>();

        for(int i=0; i<7; i++) {
            boolean isChecked = attendance.get(i);

            DayOfWeekItem item = new DayOfWeekItem();
            switch (i) {
                case 0 :
                    setItems(item, "S", isChecked);
                    break;
                case 1 :
                    setItems(item, "M", isChecked);
                    break;
                case 2 :
                    setItems(item, "T", isChecked);
                    break;
                case 3 :
                    setItems(item, "W", isChecked);
                    break;
                case 4 :
                    setItems(item, "TH", isChecked);
                    break;
                case 5 :
                    setItems(item, "F", isChecked);
                    break;
                case 6 :
                    setItems(item, "S", isChecked);
                    break;
            }
        }

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AttendanceAdapter(list);
        recyclerView.setAdapter(adapter);
    }


    // 출석부 세팅하기
    private void setItems(DayOfWeekItem item, String day, boolean isChecked) {
        item.setDay(day);
        item.setChecked(isChecked);
        list.add(item);

        if(isChecked){
            attendanceCount++;
        }

        // 일주일 출석 개근일 때 Get 버튼 활성화
        if(attendanceCount == 7) {
            btnGetPoint.setImageResource(R.drawable.getpodo);
            btnGetPoint.setEnabled(true);
        } else {
            btnGetPoint.setImageResource(R.drawable.getpodo_grey);
            btnGetPoint.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnBack :
                finish();
                break;

            case R.id.btnGetPoint :
                intent = new Intent(this, LessonFinish.class);
                intent.putExtra("isReward", true);
                startActivityForResult(intent, 100);
                break;

            case R.id.layoutEditName :
                setExtendableButton(arrowEditProfile, layoutEditNameOpen);
                break;

            case R.id.btnSave :
                final String newName = editName.getText().toString();

                if(newName.getBytes().length > 0) {
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(newName)
                            .build();

                    if(user != null) {
                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            System.out.println("userName을 업데이트 했습니다");
                                            Toast.makeText(context, getString(R.string.UPDATED_USERNAME), Toast.LENGTH_SHORT).show();
                                            MainActivity.userName = user.getDisplayName();
                                            userName.setText(newName);
                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println("userName을 업데이트를 실패 했습니다" + e);
                                Toast.makeText(context, getString(R.string.UPDATED_USERNAME_FAILED), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                setExtendableButton(arrowEditProfile, layoutEditNameOpen);
                break;

            case R.id.evaluation :
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=net.awesomekorean.podo");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;

            case R.id.recommend :
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String title = "https://awesomekorean.page.link/Sohr";
                intent.putExtra(Intent.EXTRA_TEXT, title);

                Intent chooser = Intent.createChooser(intent, "Recommend podo to your friends");
                startActivity(chooser);
                break;


            case R.id.getPointsByAd :
                RewardedAdCallback adCallback = new RewardedAdCallback() {

                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        System.out.println("보상을 받습니다.");
                        intent = new Intent(Profile.this, LessonFinish.class);
                        intent.putExtra("isReward", true);
                        startActivityForResult(intent, 200);

                        // analytics 로그 이벤트 얻기
                        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                        Bundle bundle = new Bundle();
                        firebaseAnalytics.logEvent("reward_watch", bundle);
                    }

                    @Override
                    public void onRewardedAdFailedToShow(int i) {
                        Toast.makeText(context, getString(R.string.AD_LOAD_FAILED), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onRewardedAdClosed() {
                        adsManager.loadRewardAds(context);
                    }
                };
                adsManager.rewardedAd.show(Profile.this, adCallback);
                break;

            case R.id.getPointsByPurchasing :
                intent = new Intent(Profile.this, TopUp.class);
                startActivity(intent);
                break;

            case R.id.logout :
                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.SIGN_OUT)).setMessage(getString(R.string.SIGN_OUT_MESSAGE))
                        .setPositiveButton(getString(R.string.SIGN_OUT), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                SharedPreferencesInfo.setSignIn(context, false);
                                intent = new Intent(context, SignIn.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent);
                                FirebaseAuth.getInstance().signOut();
                                finish();
                            }
                        })
                        .setNegativeButton(getString(R.string.CANCEL), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();
                break;
        }
    }

    // 광고 보고 포인트 받아 왔을 때 userPoint 에 최신 포인트 반영
    @Override
    protected void onActivityResult(final int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {

            // 일주일 출석 보상일 때
            if(requestCode == 100) {
                // 오늘 출석만 남기고 다 초기화
                Calendar cal = Calendar.getInstance();
                int today = cal.get(Calendar.DAY_OF_WEEK) - 1; // 1:일요일 ~ 7:토요일
                userInformation.resetDays(today);
                setAttendance(userInformation.getAttendance());
                SharedPreferencesInfo.setUserInfo(context, userInformation);

                userInformation.updateDb(context);
                System.out.println("출석부를 초기화 했습니다");


            // 광고 보상일 때
            } else if(requestCode == 200) {

                userInformation = SharedPreferencesInfo.getUserInfo(context);
                userPoint.setText(String.valueOf(userInformation.getPoints()));
            }
        }
    }


    // editProfile 이랑 language 펼치기/접기 메소드
    private void setExtendableButton(ImageView arrow, LinearLayout closedLayout) {
        if(!btnExtendClicked) {
            arrow.setImageResource(R.drawable.arrow_down_grey);
            closedLayout.setVisibility(View.VISIBLE);
            btnExtendClicked = true;
        } else {
            arrow.setImageResource(R.drawable.arrow_right_grey);
            closedLayout.setVisibility(View.GONE);
            btnExtendClicked = false;
        }
    }
}
