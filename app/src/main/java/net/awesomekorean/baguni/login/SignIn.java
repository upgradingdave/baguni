package net.awesomekorean.baguni.login;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import net.awesomekorean.baguni.MainActivity;
import net.awesomekorean.baguni.R;
import net.awesomekorean.baguni.SettingStatusBar;
import net.awesomekorean.baguni.collection.CollectionRepository;
import net.awesomekorean.baguni.webService.RetrofitConnection;
import net.awesomekorean.baguni.webService.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    TextView forget;

    Button btnSignIn;
    TextView btnSignUp;
    Button btnSignInGoogle;
    Button btnSignInFacebook;

    Intent intent;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SettingStatusBar.setStatusBar(this);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }
//
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
        forget = findViewById(R.id.forget);
        btnSignInGoogle = findViewById(R.id.btnSignInGoogle);
        btnSignInFacebook = findViewById(R.id.btnSignInFacebook);
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        forget.setOnClickListener(this);
        btnSignInGoogle.setOnClickListener(this);
        btnSignInFacebook.setOnClickListener(this);

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
                flag.setImageDrawable(getDrawable(R.drawable.flag_america));
                nation.setText(R.string.LOGIN_ENGLISH);
                selectLanguageList.setVisibility(View.GONE);
                break;

            case R.id.selectChinese :
                flag.setImageDrawable(getDrawable(R.drawable.flag_china));
                nation.setText(R.string.LOGIN_CHINESE);
                selectLanguageList.setVisibility(View.GONE);
                break;

            case R.id.selectJapanese :
                flag.setImageDrawable(getDrawable(R.drawable.flag_japan));
                nation.setText(R.string.LOGIN_JAPANESE);
                selectLanguageList.setVisibility(View.GONE);
                break;

            case R.id.selectThai :
                flag.setImageDrawable(getDrawable(R.drawable.flag_thailand));
                nation.setText(R.string.LOGIN_THAI);
                selectLanguageList.setVisibility(View.GONE);
                break;

            case R.id.selectKorean :
                flag.setImageDrawable(getDrawable(R.drawable.flag_korea));
                nation.setText(R.string.LOGIN_KOREAN);
                selectLanguageList.setVisibility(View.GONE);
                break;


            case R.id.btnSignIn :
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
                break;

            case R.id.forget :

                break;

            case R.id.btnSignInGoogle :
                break;

            case R.id.btnSignInFacebook :
                break;

            case R.id.btnSignInWechat :
                break;

            case R.id.btnSignUp :
                intent = new Intent(this, SignUp.class);
                startActivity(intent);
                break;

        }

    }
}
