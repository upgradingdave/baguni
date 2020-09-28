package net.awesomekorean.podo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import net.awesomekorean.podo.lesson.LessonFinish;
import net.awesomekorean.podo.lesson.LessonFrame;
import net.awesomekorean.podo.lesson.RandomRewards;
import net.awesomekorean.podo.lesson.lessonReviewRewards.LessonReview;
import net.awesomekorean.podo.lesson.lessons.Lesson;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

public class GetRandomPoint extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout selectResult;

    ImageView box1;
    ImageView box2;
    ImageView box3;

    ImageView finishPodo;
    TextView tvPoint;
    ImageView imageCoin;

    Button btnGetPoint;

    int reward;
    PlaySoundPool playSoundPool;
    Context context;
    Animation animation;

    LessonItem lessonItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_random_point);

        context = getApplicationContext();

        selectResult = findViewById(R.id.selectResult);
        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        finishPodo = findViewById(R.id.finishPodo);
        tvPoint = findViewById(R.id.tvPoint);
        imageCoin = findViewById(R.id.imageCoin);
        btnGetPoint = findViewById(R.id.btnGetPoint);
        box1.setOnClickListener(this);
        box2.setOnClickListener(this);
        box3.setOnClickListener(this);
        btnGetPoint.setOnClickListener(this);

        lessonItem = (LessonItem) getIntent().getSerializableExtra(getResources().getString(R.string.LESSON));

        playSoundPool = new PlaySoundPool(context);
        playSoundPool.playSoundYay();

        Animation aniScale = AnimationUtils.loadAnimation(context, R.anim.scale_1000);
        finishPodo.startAnimation(aniScale);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_200);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnGetPoint :

                // 포인트 합산하기
                UserInformation userInformation = SharedPreferencesInfo.getUserInfo(context);
                userInformation.addRewardPointsWithoutDB(reward);
                userInformation.updateCompleteList(context, lessonItem.getLessonId(), false);

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                break;

            default:
                v.startAnimation(animation);
                // 포인트 랜덤으로 가져오기
                reward = RandomRewards.randomRewards();
                tvPoint.setText("+ " + String.valueOf(reward));
                selectResult.setVisibility(View.VISIBLE);
                playSoundPool.playSoundLesson(2);

                Animation aniSelectResult = AnimationUtils.loadAnimation(context, R.anim.move_up);
                selectResult.startAnimation(aniSelectResult);
                aniSelectResult.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Animation aniPoint = AnimationUtils.loadAnimation(context, R.anim.move_up_small);
                        tvPoint.startAnimation(aniPoint);
                        imageCoin.startAnimation(aniPoint);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
        }
    }
}
