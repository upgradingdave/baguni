package net.awesomekorean.baguni.login;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import net.awesomekorean.baguni.MainActivity;
import net.awesomekorean.baguni.R;
import net.awesomekorean.baguni.collection.CollectionRepository;
import net.awesomekorean.baguni.webService.RetrofitConnection;
import net.awesomekorean.baguni.webService.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity implements Button.OnClickListener {

    EditText email;
    EditText password;

    Button btnSignIn;
    TextView btnSignUp;
    Button btnSignInGoogle;
    Button btnSignInFacebook;
    LinearLayout selectLanguage;

    Intent intent;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignInGoogle = findViewById(R.id.btnSignInGoogle);
        btnSignInFacebook = findViewById(R.id.btnSignInFacebook);
        selectLanguage = findViewById(R.id.selectLanguage);
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnSignInGoogle.setOnClickListener(this);
        btnSignInFacebook.setOnClickListener(this);
        selectLanguage.setOnClickListener(this);

        ArrayList<SelectLanguageItem> list = new ArrayList<>();

        SelectLanguageItem english = new SelectLanguageItem();
        english.setFlag(getDrawable(R.drawable.english));
        english.setNation("English");
        english.setChecked(getDrawable(R.drawable.check));
        list.add(english);

        SelectLanguageItem chinese = new SelectLanguageItem();
        chinese.setFlag(getDrawable(R.drawable.chinese));
        chinese.setNation("Chinese");
        chinese.setChecked(getDrawable(R.drawable.check));
        list.add(chinese);

        SelectLanguageItem japanese = new SelectLanguageItem();
        japanese.setFlag(getDrawable(R.drawable.japanese));
        japanese.setNation("Japanese");
        japanese.setChecked(getDrawable(R.drawable.check));
        list.add(japanese);

        SelectLanguageItem thai = new SelectLanguageItem();
        thai.setFlag(getDrawable(R.drawable.thai));
        thai.setNation("Thai");
        thai.setChecked(getDrawable(R.drawable.check));
        list.add(thai);

        SelectLanguageItem korean = new SelectLanguageItem();
        korean.setFlag(getDrawable(R.drawable.korean));
        korean.setNation("Korean");
        korean.setChecked(getDrawable(R.drawable.check));
        list.add(korean);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SelectLanguageAdapter adapter = new SelectLanguageAdapter(list);
        recyclerView.setAdapter(adapter);


    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

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

            case R.id.btnSignInGoogle :
                break;

            case R.id.btnSignInFacebook :
                break;

        }

    }
}
