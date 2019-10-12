package net.awesomekorean.baguni.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import net.awesomekorean.baguni.MainActivity;
import net.awesomekorean.baguni.R;
import net.awesomekorean.baguni.webService.RetrofitConnection;
import net.awesomekorean.baguni.webService.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity implements Button.OnClickListener {

    EditText email;
    EditText password;

    Button btnSignIn;
    Button btnSignUp;
    Button btnSignInGoogle;
    Button btnSignInFacebook;
    Button btnSelectLanguage;

    ListView listView;
    SignInListViewAdapter adapter;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSingUp);
        btnSignInGoogle = findViewById(R.id.btnSignInGoogle);
        btnSignInFacebook = findViewById(R.id.btnSignInFacebook);
        btnSelectLanguage = findViewById(R.id.btnSelectLanguage);
        listView = findViewById(R.id.listView);
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnSignInGoogle.setOnClickListener(this);
        btnSignInFacebook.setOnClickListener(this);
        btnSelectLanguage.setOnClickListener(this);

        adapter = new SignInListViewAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String language = (String) adapterView.getItemAtPosition(i);
                btnSelectLanguage.setText(language);
                listView.setVisibility(View.GONE);

                switch (i) {    // 선택한 언어에 따라 메뉴 언어 변경

                    case 0 :
                        // 한국어
                        break;

                    case 1 :
                        // 영어
                        break;

                    case 2 :
                        // 중국어
                        break;

                    case 3 :
                        // 일본어
                        break;

                    case 4 :
                        // 태국어
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnSignIn :
                String userEmail = email.getText().toString();
                String userPass = password.getText().toString();

                RetrofitConnection retrofitConnection = new RetrofitConnection();
                Call<User> call = retrofitConnection.service().getByEmail(userEmail);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()) {
                            System.out.println("Succeed " + response);
                        }else {
                            System.out.println("FAILED!");
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        System.out.println("Failed to connect");
                    }
                });


                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.btnSingUp :
                intent = new Intent(this, SignUp.class);
                startActivity(intent);
                break;

            case R.id.btnSignInGoogle :
                break;

            case R.id.btnSignInFacebook :
                break;

            case R.id.btnSelectLanguage :
                listView.setVisibility(View.VISIBLE);
                break;
        }

    }
}
