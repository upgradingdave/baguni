package net.awesomekorean.podo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.awesomekorean.podo.purchase.Subscribe;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    ImageView btnBack;
    ImageView userImage;
    TextView userName;
    TextView userPoint;

    LinearLayout layoutMon;
    TextView tvMon;
    ImageView checkMon;
    LinearLayout layoutTue;
    TextView tvTue;
    ImageView checkTue;
    LinearLayout layoutWed;
    TextView tvWed;
    ImageView checkWed;
    LinearLayout layoutThur;
    TextView tvThur;
    ImageView checkThur;
    LinearLayout layoutFri;
    TextView tvFri;
    ImageView checkFri;
    LinearLayout layoutSat;
    TextView tvSat;
    ImageView checkSat;
    LinearLayout layoutSun;
    TextView tvSun;
    ImageView checkSun;

    LinearLayout btnGetPoint;

    LinearLayout editProfile;
    LinearLayout editProfileOpen;
    EditText editName;
    EditText editEmail;
    EditText editPassword;
    Button btnSave;

    LinearLayout purchase;

    LinearLayout layoutLanguage;
    ImageView ivFlag;
    TextView tvLanguage;

    LinearLayout layoutLanguageOpen;
    LinearLayout layoutEnglish;
    LinearLayout layoutKorean;
    LinearLayout layoutChinese;
    LinearLayout layoutJapanese;
    LinearLayout layoutThai;

    LinearLayout evaluation;
    LinearLayout recommend;
    LinearLayout logout;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnBack = findViewById(R.id.btnBack);
        userImage = findViewById(R.id.userImage);
        userName = findViewById(R.id.userName);
        userPoint = findViewById(R.id.userPoint);

        layoutMon = findViewById(R.id.layoutMon);
        tvMon = findViewById(R.id.tvMon);
        checkMon = findViewById(R.id.checkMon);
        layoutTue = findViewById(R.id.layoutTue);
        tvTue = findViewById(R.id.tvTue);
        checkTue = findViewById(R.id.checkTue);
        layoutWed = findViewById(R.id.layoutWed);
        tvWed = findViewById(R.id.tvWed);
        checkWed = findViewById(R.id.checkWed);
        layoutThur = findViewById(R.id.layoutThur);
        tvThur = findViewById(R.id.tvThur);
        checkThur = findViewById(R.id.checkThur);
        layoutFri = findViewById(R.id.layoutFri);
        tvFri = findViewById(R.id.tvFri);
        checkFri = findViewById(R.id.checkFri);
        layoutSat = findViewById(R.id.layoutSat);
        tvSat = findViewById(R.id.tvSat);
        checkSat = findViewById(R.id.checkSat);
        layoutSun = findViewById(R.id.layoutSun);
        tvSun = findViewById(R.id.tvSun);
        checkSun = findViewById(R.id.checkSun);

        btnGetPoint = findViewById(R.id.btnGetPoint);

        editProfile = findViewById(R.id.editProfile);
        editProfileOpen = findViewById(R.id.editProfileOpen);
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnSave = findViewById(R.id.btnSave);

        purchase = findViewById(R.id.purchase);

        layoutLanguage = findViewById(R.id.layoutLanguage);
        ivFlag = findViewById(R.id.ivFlag);
        tvLanguage = findViewById(R.id.tvLanguage);

        layoutLanguageOpen = findViewById(R.id.layoutLanguageOpen);
        layoutEnglish = findViewById(R.id.layoutEnglish);
        layoutKorean = findViewById(R.id.layoutKorean);
        layoutChinese = findViewById(R.id.layoutChinese);
        layoutJapanese = findViewById(R.id.layoutJapanese);
        layoutThai = findViewById(R.id.layoutThai);

        evaluation = findViewById(R.id.evaluation);
        recommend = findViewById(R.id.recommend);
        logout = findViewById(R.id.logout);

        btnBack.setOnClickListener(this);
        btnGetPoint.setOnClickListener(this);
        editProfile.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        purchase.setOnClickListener(this);
        layoutLanguage.setOnClickListener(this);
        layoutEnglish.setOnClickListener(this);
        layoutKorean.setOnClickListener(this);
        layoutChinese.setOnClickListener(this);
        layoutJapanese.setOnClickListener(this);
        layoutThai.setOnClickListener(this);
        evaluation.setOnClickListener(this);
        recommend.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnBack :
                finish();
                break;

            case R.id.btnGetPoint :
                break;

            case R.id.editProfile :
                editProfileOpen.setVisibility(View.VISIBLE);
                break;

            case R.id.btnSave :
                editProfileOpen.setVisibility(View.GONE);
                break;

            case R.id.purchase :
                intent = new Intent(this, Subscribe.class);
                startActivity(intent);
                break;

            case R.id.layoutLanguage :
                layoutLanguageOpen.setVisibility(View.VISIBLE);
                break;

            case R.id.layoutEnglish :
                setLanguage(R.drawable.en, R.string.ENGLISH);
                break;

            case R.id.layoutKorean :
                setLanguage(R.drawable.kr, R.string.KOREAN);
                break;

            case R.id.layoutChinese :
                setLanguage(R.drawable.cn, R.string.CHINESE);
                break;

            case R.id.layoutJapanese :
                setLanguage(R.drawable.jp, R.string.JAPANESE);
                break;

            case R.id.layoutThai :
                setLanguage(R.drawable.th, R.string.THAI);
                break;

            case R.id.evaluation :
                break;

            case R.id.recommend :
                break;

            case R.id.logout :
                break;
        }
    }

    private void setLanguage(int flag, int language) {
        ivFlag.setImageResource(flag);
        tvLanguage.setText(getString(language));
        layoutLanguageOpen.setVisibility(View.GONE);
    }

}
