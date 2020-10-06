package net.awesomekorean.podo.lesson;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.awesomekorean.podo.ConfirmQuit;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.lesson.lessons.LessonSpecial;

public class LessonSpecialFrame extends AppCompatActivity implements Button.OnClickListener{

    TextView title;
    TextView subTitle;
    TextView contents;
    Button btnFinish;

    LinearLayout confirmQuit;
    Button btnNo;
    Button btnYes;

    LessonSpecial lessonSpecial;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_special_frame);

        context = getApplicationContext();

        title = findViewById(R.id.title);
        subTitle = findViewById(R.id.subTitle);
        contents = findViewById(R.id.contents);
        btnFinish = findViewById(R.id.btnFinish);
        confirmQuit = findViewById(R.id.confirmQuit);
        btnNo = findViewById(R.id.btnNo);
        btnYes = findViewById(R.id.btnYes);
        btnFinish.setOnClickListener(this);
        btnNo.setOnClickListener(this);
        btnYes.setOnClickListener(this);

        lessonSpecial = (LessonSpecial) getIntent().getSerializableExtra(getResources().getString(R.string.LESSON));

        readyForLesson();
    }

    private void readyForLesson() {
        title.setText(lessonSpecial.getLessonTitle());
        subTitle.setText(lessonSpecial.getLessonSubTitle());
        contents.setText(lessonSpecial.getContents());
    }


    public void openConfirmQuit() {
        Intent intent = new Intent(context, ConfirmQuit.class);
        intent.putExtra(getResources().getString(R.string.LESSON_ID), lessonSpecial.getLessonId());
        intent.putExtra(getResources().getString(R.string.FINISH), true);
        startActivityForResult(intent, 200);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            finish();
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnFinish :
                openConfirmQuit();
            break;

            case R.id.btnNo :
                confirmQuit.setVisibility(View.GONE);
                break;

            case R.id.btnYes :
                // 레슨완료 정보 업데이트 하기
                String lessonId = lessonSpecial.getLessonId();
                UserInformation userInformation = SharedPreferencesInfo.getUserInfo(context);
                userInformation.updateCompleteList(context, lessonId, false);
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        openConfirmQuit();
    }
}
