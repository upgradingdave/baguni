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

public class ConfirmQuit extends AppCompatActivity implements View.OnClickListener {

    Context context;

    Button btnYes;

    Button btnNo;

    Intent intent;

    Integer progress = null;

    boolean isHangul = false;

    boolean isNumberPractice = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_confirm_quit);

        context = getApplicationContext();

        btnYes = findViewById(R.id.btnYes);

        btnNo = findViewById(R.id.btnNo);

        btnYes.setOnClickListener(this);

        btnNo.setOnClickListener(this);

        intent = getIntent();

        progress = intent.getExtras().getInt("progress");

        isHangul = intent.getExtras().getBoolean("isHangul");

        isNumberPractice = intent.getExtras().getBoolean("isNumberPractice");
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

                String lessonId;

                if(progress != null) {

                    if(isNumberPractice) {

                        lessonId = LessonNumberMenu.numberPractice.getLessonId();

                    } else {

                        lessonId = LessonAdapterChild.lessonItem.getLessonId();
                    }

                    // 레슨완료리스트에 업데이트
                    UserInformation userInformation = SharedPreferencesInfo.getUserInfo(context);

                    userInformation.updateLessonComplete(context, lessonId, progress);


                    // 진도율 50% 이상이면 광고 재생 (한글)
                    if(isHangul) {

                        if(progress > 50) {

                            AdsLoad.getInstance().playAds(this);

                        } else {

                            sendResultOk();
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
