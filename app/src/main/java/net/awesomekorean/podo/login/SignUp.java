package net.awesomekorean.podo.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SettingStatusBar;
import net.awesomekorean.podo.webService.RetrofitConnection;
import net.awesomekorean.podo.webService.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import retrofit2.Call;

public class SignUp extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

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
    Boolean userEmailDuplicateOk = true;
    Boolean userPassOk = false;

    TextView btnSignIn;

    int focused;

    RetrofitConnection retrofitConnection;
    Call<User> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        SettingStatusBar.setStatusBar(this);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        passwordConfirm = findViewById(R.id.passwordConfirm);
        email.addTextChangedListener(textWatcher);
        email.setOnFocusChangeListener(focusChangeListener);
        password.addTextChangedListener(textWatcher);
        password.setOnFocusChangeListener(focusChangeListener);
        passwordConfirm.addTextChangedListener(textWatcher);
        passwordConfirm.setOnFocusChangeListener(focusChangeListener);

        /*
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
*/
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userEmail = email.getText().toString();
                userPass = password.getText().toString();

                // 중복 이메일 체크
                DocumentReference doRef = db.collection("android/podo/users").document(userEmail);
                doRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        // 중복 이메일 있음
                        if (documentSnapshot.exists()) {
                            Toast.makeText(getApplicationContext(), R.string.EMAIL_EXIST, Toast.LENGTH_LONG).show();

                            // 중복 이메일 없음 -> 회원등록 성공
                        } else {
                            final User user = new User(userEmail, userPass);

                            CollectionReference users = db.collection("android/podo/users");
                            users.document(userEmail).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "Welcome to podo, " + user.getName(), Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    finish();
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                });
            }
        });

        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
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
                    if(userEmailOk) {
                        email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.successgreen, 0);
                    }else {
                        email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.successgrey, 0);
                    }
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
                    if(userPassOk) {
                        passwordConfirm.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.successgreen, 0);
                    }else {
                        passwordConfirm.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.successgrey, 0);
                    }
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
            editText.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_white_30_stroke_grey));
            return true;
        } else {
            editText.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_white_30_stroke_red));
            return false;
        }
    }
}
