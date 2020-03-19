package net.awesomekorean.podo.login;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SettingStatusBar;
import net.awesomekorean.podo.UserInformation;

public class SignUp extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    ImageView btnBack;
    EditText email;
    EditText password;
    EditText passwordConfirm;
    TextView warningPass;

    Button btnSignUp;

    String userEmail;
    String userPass;
    String userPassConfirm;

    Boolean condition = false;
    Boolean userEmailOk = false;
    Boolean userPassOk = false;
    Boolean userPassConfirmOk = false;

    TextView btnSignIn;

    LinearLayout progressBarLayout;
    ProgressBar progressBar;

    int focused;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        
        firebaseAuth = FirebaseAuth.getInstance();

        SettingStatusBar.setStatusBar(this);

        btnBack = findViewById(R.id.btnBack);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        passwordConfirm = findViewById(R.id.passwordConfirm);
        warningPass = findViewById(R.id.warningPass);
        progressBarLayout = findViewById(R.id.progressBarLayout);
        progressBar = findViewById(R.id.progressBar);

        email.addTextChangedListener(textWatcher);
        email.setOnFocusChangeListener(focusChangeListener);
        password.addTextChangedListener(textWatcher);
        password.setOnFocusChangeListener(focusChangeListener);
        passwordConfirm.addTextChangedListener(textWatcher);
        passwordConfirm.setOnFocusChangeListener(focusChangeListener);

        int color = ContextCompat.getColor(this, R.color.PURPLE);
        progressBar.setIndeterminate(true);
        progressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);


        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userEmailOk.equals(true) && userPassConfirmOk.equals(true) && userPassOk.equals(true)) {

                    userEmail = email.getText().toString();
                    userPass = password.getText().toString();

                    firebaseAuth.createUserWithEmailAndPassword(userEmail, userPass)
                            .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        System.out.println("회원가입에 성공했습니다");
                                        progressBarLayout.setVisibility(View.VISIBLE);

                                        MakeNewDb makeNewDb = new MakeNewDb();
                                        makeNewDb.makeNewDb(SignUp.this, getApplicationContext(), userEmail, "Email");

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        System.out.println("회원가입에 실패했습니다");
                                        Toast.makeText(getApplicationContext(), getString(R.string.EMAIL_EXIST),
                                                Toast.LENGTH_SHORT).show();
                                        progressBarLayout.setVisibility(View.GONE);

                                    }
                                }
                            });
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

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
                    warningPass.setVisibility(View.VISIBLE);
                    userPass = password.getText().toString();
                    condition = userPass.length() >= 6;
                    userPassOk = conditionCheck(condition, password);
                    if(userPassOk) {
                        warningPass.setVisibility(View.GONE);
                        password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.successgreen, 0);
                    } else {
                        warningPass.setVisibility(View.VISIBLE);
                        password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.successgrey, 0);
                    }
                    break;

                case R.id.passwordConfirm :
                    userPassConfirm = passwordConfirm.getText().toString();
                    condition = userPassConfirm.equals(userPass);
                    userPassConfirmOk = conditionCheck(condition, passwordConfirm);
                    if(userPassConfirmOk) {
                        passwordConfirm.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.successgreen, 0);
                    }else {
                        passwordConfirm.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.successgrey, 0);
                    }
                    break;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            System.out.println("EMAIL: "+userEmailOk);
            System.out.println("PASS: "+userPassOk);
            System.out.println("PASSCONFIRM: "+userPassConfirmOk);

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
