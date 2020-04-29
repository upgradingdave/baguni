package net.awesomekorean.podo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import net.awesomekorean.podo.lesson.LessonAdapterChild;
import net.awesomekorean.podo.lesson.lessonNumber.LessonNumberMenu;
import net.awesomekorean.podo.reading.MainReading;

public class ConfirmQuit extends AppCompatActivity implements View.OnClickListener {

    Context context;

    Button btnYes;

    Button btnNo;

    Intent intent;

    Integer progress = null;

    boolean isHangul = false;

    boolean isNumber = false;

    boolean isNumberPractice = false;

    boolean isReading = false;

    AdsLoad adsLoad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_confirm_quit);

        context = getApplicationContext();

        adsLoad = AdsLoad.getInstance();

        if(adsLoad.interstitialAd == null || !adsLoad.interstitialAd.isLoaded()) {
            adsLoad.setAds(context);
        }

        btnYes = findViewById(R.id.btnYes);

        btnNo = findViewById(R.id.btnNo);

        btnYes.setOnClickListener(this);

        btnNo.setOnClickListener(this);

        intent = getIntent();

        progress = intent.getExtras().getInt("progress");

        isHangul = intent.getExtras().getBoolean("isHangul");

        isNumber = intent.getExtras().getBoolean("isNumber");

        isNumberPractice = intent.getExtras().getBoolean("isNumberPractice");

        isReading = intent.getExtras().getBoolean("isReading");
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

                if(progress != null) {

                    if(isNumberPractice) {

                        unitId = LessonNumberMenu.numberPractice.getLessonId();

                    } else if (isReading) {

                        unitId = MainReading.readingUnit.getReadingId();

                    } else {

                        unitId = LessonAdapterChild.lessonItem.getLessonId();
                    }

                    // 완료리스트에 업데이트
                    UserInformation userInformation = SharedPreferencesInfo.getUserInfo(context);

                    userInformation.updateCompleteList(context, unitId, progress, isReading);


                    // 진도율 50% 이상이면 광고 재생 (한글)
                    if(isHangul) {

                        if(progress > 50) {

                            adsLoad.playAds(this);
                        }
                    }


                    // 진도율 30% 이상이면 광고 재생 (숫자)
                    if(isNumber) {

                        if(progress > 30) {

                            adsLoad.playAds(this);
                        }
                    }
                }

                sendResultOk();

                break;


            case R.id.btnNo :

                finish();

                break;
        }
    }
}
