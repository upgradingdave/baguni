package net.awesomekorean.podo.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SettingStatusBar;

public class SignIn extends AppCompatActivity implements Button.OnClickListener {

    LinearLayout selectLanguage;
    ImageView flag;
    TextView nation;

    LinearLayout selectLanguageList;
    LinearLayout selectEnglish;
    LinearLayout selectChinese;
    LinearLayout selectJapanese;
    LinearLayout selectThai;
    LinearLayout selectKorean;

    EditText email;
    EditText password;
    TextView forgotPassword;

    Button btnSignIn;
    TextView btnSignUp;
    Button btnSignInGoogle;
    Button btnSignInFacebook;

    LinearLayout findPassword;
    View findPasswordBg;
    EditText sendEmail;
    Button btnSend;

    Intent intent;

    private static final int RC_SITN_IN = 100;
    private FirebaseAuth firebaseAuth;
    private GoogleApiClient googleApiClient;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Firebase 구글, 페이스북 로그인 연동
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();




        SettingStatusBar.setStatusBar(this);

        selectLanguage = findViewById(R.id.selectLanguage);
        flag = findViewById(R.id.flag);
        nation = findViewById(R.id.nation);

        selectLanguageList = findViewById(R.id.selectLanguageList);
        selectEnglish = findViewById(R.id.selectEnglish);
        selectChinese = findViewById(R.id.selectChinese);
        selectJapanese = findViewById(R.id.selectJapanese);
        selectThai = findViewById(R.id.selectThai);
        selectKorean = findViewById(R.id.selectKorean);

        selectLanguage.setOnClickListener(this);
        selectEnglish.setOnClickListener(this);
        selectChinese.setOnClickListener(this);
        selectJapanese.setOnClickListener(this);
        selectThai.setOnClickListener(this);
        selectKorean.setOnClickListener(this);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        forgotPassword = findViewById(R.id.forgotPassword);
        btnSignInGoogle = findViewById(R.id.btnSignInGoogle);
        btnSignInFacebook = findViewById(R.id.btnSignInFacebook);
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        btnSignInGoogle.setOnClickListener(this);
        btnSignInFacebook.setOnClickListener(this);

        findPasswordBg = findViewById(R.id.findPasswordBg);
        findPassword = findViewById(R.id.findPassword);
        sendEmail = findViewById(R.id.sendEmail);
        btnSend = findViewById(R.id.btnSend);
        findPasswordBg.setOnClickListener(this);
        btnSend.setOnClickListener(this);


    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.selectLanguage :
                if(selectLanguageList.getVisibility() == View.GONE) {
                    selectLanguageList.setVisibility(View.VISIBLE);
                } else {
                    selectLanguageList.setVisibility(View.GONE);
                }
                break;

            case R.id.selectEnglish :
                flag.setImageDrawable(getDrawable(R.drawable.en));
                nation.setText(R.string.ENGLISH);
                selectLanguageList.setVisibility(View.GONE);
                break;

            case R.id.selectChinese :
                flag.setImageDrawable(getDrawable(R.drawable.cn));
                nation.setText(R.string.CHINESE);
                selectLanguageList.setVisibility(View.GONE);
                break;

            case R.id.selectJapanese :
                flag.setImageDrawable(getDrawable(R.drawable.jp));
                nation.setText(R.string.JAPANESE);
                selectLanguageList.setVisibility(View.GONE);
                break;

            case R.id.selectThai :
                flag.setImageDrawable(getDrawable(R.drawable.th));
                nation.setText(R.string.THAI);
                selectLanguageList.setVisibility(View.GONE);
                break;

            case R.id.selectKorean :
                flag.setImageDrawable(getDrawable(R.drawable.kr));
                nation.setText(R.string.KOREAN);
                selectLanguageList.setVisibility(View.GONE);
                break;


            case R.id.btnSignIn :
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                /*
                String userEmail = email.getText().toString();
                String userPass = password.getText().toString();

                // 서버에 로그인 입력정보 보내기
                RetrofitConnection retrofitConnection = new RetrofitConnection();
                Call<User> call = retrofitConnection.service().logInCheck(userEmail, userPass);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        // 로그인 결과 받기
                        if(response.isSuccessful()) {

                            User user = response.body();
                            String msgFromServer = user.getMsgFromServer();
                            System.out.println(msgFromServer);

                            if(!msgFromServer.contains("ERROR")) {
                                Toast.makeText(getApplicationContext(), "Hello, "+ user.getName(), Toast.LENGTH_LONG).show();
                                CollectionRepository.userId = user.getId(); // MainCollection 에서 동기화를 위해 userId 입력
                                intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), msgFromServer, Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        System.out.println("Failed to connect");
                    }
                });
                */
                break;

            case R.id.forgotPassword :
                findPasswordBg.setVisibility(View.VISIBLE);
                findPassword.setVisibility(View.VISIBLE);
                break;

            case R.id.findPasswordBg :
                findPasswordBg.setVisibility(View.GONE);
                findPassword.setVisibility(View.GONE);
                break;

            case R.id.btnSend :
                // 서버에서 비밀번호 랜덤으로 바꾸고 이메일 보내기
                findPasswordBg.setVisibility(View.GONE);
                findPassword.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), R.string.MSG_CHECK_EMAIL, Toast.LENGTH_LONG).show();
                break;

            case R.id.btnSignInGoogle :

                break;

            case R.id.btnSignInFacebook :
                break;

            case R.id.btnSignUp :
                intent = new Intent(this, SignUp.class);
                startActivity(intent);
                break;

        }

    }
}
