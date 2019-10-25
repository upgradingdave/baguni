package net.awesomekorean.baguni.login;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.awesomekorean.baguni.MainActivity;
import net.awesomekorean.baguni.R;
import net.awesomekorean.baguni.webService.RetrofitConnection;
import net.awesomekorean.baguni.webService.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText passwordConfirm;
    Button btnDuplicateCheck;
    Button btnSignUp;

    String userEmail;
    String userPass;
    String userPassConfirm;

    Boolean condition = false;
    Boolean userEmailOk = false;
    Boolean userEmailDuplicateOk = false;
    Boolean userPassOk = false;

    int focused;

    RetrofitConnection retrofitConnection;
    Call<User> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        passwordConfirm = findViewById(R.id.passwordConfirm);
        email.addTextChangedListener(textWatcher);
        email.setOnFocusChangeListener(focusChangeListener);
        password.addTextChangedListener(textWatcher);
        password.setOnFocusChangeListener(focusChangeListener);
        passwordConfirm.addTextChangedListener(textWatcher);
        passwordConfirm.setOnFocusChangeListener(focusChangeListener);

        btnDuplicateCheck = findViewById(R.id.btnDuplicateCheck);
        btnDuplicateCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEmail = email.getText().toString();

                retrofitConnection = new RetrofitConnection();
                call = retrofitConnection.service().getUserByEmail(userEmail);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        System.out.println("CODE :" +response.code());
                        if(response.code()==404) {
                            userEmailDuplicateOk = true;
                            System.out.println(getString(R.string.DUPLICATE_FALSE));

                        } else {
                            userEmailDuplicateOk = false;
                            System.out.println(getString(R.string.DUPLICATE_TRUE));
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        System.out.println("FAIL");
                    }
                });
            }
        });

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userEmail = email.getText().toString();
                userPass = password.getText().toString();

                User newUser = new User(userEmail, userPass);
                System.out.println("USERNAME : " + newUser.getName());

                retrofitConnection = new RetrofitConnection();
                call = retrofitConnection.service().createUser(newUser);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()) {
                            System.out.println(getString(R.string.SIGNUP_SUCCEED));
                            Toast.makeText(getApplicationContext(), R.string.SIGNUP_SUCCEED, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }else {
                            System.out.println(getString(R.string.SIGNUP_FAILED));
                            Toast.makeText(getApplicationContext(), R.string.SIGNUP_FAILED, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        System.out.println("Failed to connect the server!!");
                        System.out.println(t);
                    }
                });
            }
        });


    }


    View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            focused = view.getId();
        }
    };

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            switch (focused) {

                case R.id.email :
                    userEmail = email.getText().toString();
                    condition = Patterns.EMAIL_ADDRESS.matcher(userEmail).matches();
                    userEmailOk = conditionCheck(condition, email);
                    break;

                case R.id.password :
                    userPass = password.getText().toString();
                    condition = userPass.equals(userPassConfirm);
                    userPassOk = conditionCheck(condition, passwordConfirm);
                    break;

                case R.id.passwordConfirm :
                    userPassConfirm = passwordConfirm.getText().toString();
                    condition = userPassConfirm.equals(userPass);
                    userPassOk = conditionCheck(condition, passwordConfirm);
                    break;
            }

            System.out.println("EMAIL : " + userEmailOk);
            System.out.println("EMAIL CHECK: " + userEmailDuplicateOk);
            System.out.println("PASS : " + userPassOk);
            if(userEmailOk.equals(true) && userEmailDuplicateOk.equals(true) && userPassOk.equals(true)) {
                btnSignUp.setEnabled(true);
            }else {
                btnSignUp.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public Boolean conditionCheck(Boolean condition, EditText editText) {

        if(condition) {
            editText.setBackgroundColor(Color.WHITE);
            return true;
        } else {
            editText.setBackgroundColor(Color.RED);
            return false;
        }
    }
}
