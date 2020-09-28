package net.awesomekorean.podo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import net.awesomekorean.podo.lesson.LessonAdapterChild;
import net.awesomekorean.podo.lesson.LessonFinish;
import net.awesomekorean.podo.lesson.lessonNumber.LessonNumberMenu;
import net.awesomekorean.podo.reading.MainReading;

public class ConfirmQuit extends AppCompatActivity implements View.OnClickListener {

    Context context;
    Button btnYes;
    Button btnNo;
    TextView confirmText;
    Intent intent;

    boolean isFinish = false;
    boolean isReading = false;
    boolean isMain = false;

    AdsManager adsManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_confirm_quit);

        context = getApplicationContext();
        adsManager = AdsManager.getInstance();

        if(adsManager.interstitialAd == null || !adsManager.interstitialAd.isLoaded()) {
            adsManager.loadFullAds(context);
        }

        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);
        confirmText = findViewById(R.id.confirmText);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);

        intent = getIntent();
        isFinish = intent.getExtras().getBoolean(getResources().getString(R.string.FINISH));
        isReading = intent.getExtras().getBoolean(getResources().getString(R.string.IS_READING));
        isMain = intent.getExtras().getBoolean(getResources().getString(R.string.IS_MAIN));

        if(isMain) {
            confirmText.setText(getResources().getString(R.string.CONFIRM_EXIT));
        } else {
            confirmText.setText(getResources().getString(R.string.CONFIRM_QUIT));
        }
    }


    private void sendResultOk() {
        intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnYes :
                String unitId;

                if(isMain) {
                    finishAffinity();
                    System.runFinalization();
                    System.exit(0);

                } else {
                    if(isFinish) {

                        if (isReading) {
                            unitId = intent.getStringExtra(getResources().getString(R.string.READING_ID));

                        } else {
                            unitId = intent.getStringExtra(getResources().getString(R.string.LESSON_ID));
                            if (adsManager.interstitialAd.isLoaded()) {
                                adsManager.playFullAds(context);
                            }
                        }

                        // 완료리스트에 업데이트
                        UserInformation userInformation = SharedPreferencesInfo.getUserInfo(context);
                        userInformation.updateCompleteList(context, unitId, isReading);
                        sendResultOk();

                    } else {
                        System.out.println("레슨/읽기를 완료하지 않고 메인으로 나갑니다.");
                        sendResultOk();
                    }
                }
                break;


            case R.id.btnNo :
                finish();
                break;
        }
    }
}
