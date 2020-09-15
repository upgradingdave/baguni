package net.awesomekorean.podo.login;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import net.awesomekorean.podo.Logo;
import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SettingStatusBar;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;

import java.util.Arrays;

public class SignIn extends AppCompatActivity implements Button.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    EditText email;
    EditText password;
    TextView wrongEmail;
    TextView wrongPassword;
    TextView forgotPassword;

    Button btnSignIn;
    TextView btnSignUp;
    Button btnSignInGoogle;
    Button btnSignInFacebook;

    LinearLayout findPassword;
    View findPasswordBg;
    EditText sendEmail;
    Button btnSend;

    LinearLayout progressBarLayout;
    ProgressBar progressBar;

    Intent intent;

    static final int RC_SIGN_IN = 100;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;

    CallbackManager callbackManager;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        firebaseAuth = FirebaseAuth.getInstance();

        // 페이스북 로그인
        callbackManager = CallbackManager.Factory.create();


        // Firebase 구글 로그인 연동
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        SettingStatusBar.setStatusBar(this);

        progressBarLayout = findViewById(R.id.progressBarLayout);
        progressBar = findViewById(R.id.progressBar);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        wrongEmail = findViewById(R.id.wrongEmail);
        wrongPassword = findViewById(R.id.wrongPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        forgotPassword = findViewById(R.id.forgotPassword);
        btnSignInGoogle = findViewById(R.id.btnSignInGoogle);
        btnSignInFacebook = findViewById(R.id.btnSignInFacebook);
        email.setOnFocusChangeListener(focusChangeListener);
        password.setOnFocusChangeListener(focusChangeListener);
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

        int color = ContextCompat.getColor(this, R.color.PURPLE);
        progressBar.setIndeterminate(true);
        progressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);

    }




    // 페이스북 로그인 결과
    // 사용자가 정상적으로 로그인한 후에 GoogleSignInAccount 개체에서 ID 토큰을 가져와서
    // Firebase 사용자 인증 정보로 교환하고 Firebase 사용자 인증 정보를 사용해 Firebase에 인증합니다.
    private void handleFacebookAccessToken(final AccessToken accessToken) {
        System.out.println("로그인 페이스북!");
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 페이스북 로그인 성공
                            progressBarLayout.setVisibility(View.VISIBLE);
                            System.out.println("로그인 페이스북 성공!");
                            final String userEmail = task.getResult().getUser().getEmail();

                            //출석부 확인 후 메인페이지로 넘어가기
                            getUserInfoAndGoToMain(userEmail, "Facebook");

                        } else {
                            // 로그인 실패
                            System.out.println("로그인 페이스북 실패!");

                            Toast.makeText(getApplicationContext(), getString(R.string.FACEBOOK_FAILED), Toast.LENGTH_LONG).show();
                            progressBarLayout.setVisibility(View.GONE);
                        }
                    }
                });
    }

    View.OnFocusChangeListener focusChangeListener = (new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            wrongEmail.setVisibility(View.GONE);
            wrongPassword.setVisibility(View.GONE);
        }
    });

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 페이스북 콜백 등록
        callbackManager.onActivityResult(requestCode, resultCode, data);

        // 구글로그인 버튼 응답
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // 구글 로그인 성공
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {

            }
        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // 구글 로그인 성공
                            progressBarLayout.setVisibility(View.VISIBLE);

                            final String userEmail = account.getEmail();

                            // 출석부 확인 후 메인페이지로 넘어가기
                            getUserInfoAndGoToMain(userEmail, "Google");

                        } else {
                            // 로그인 실패
                            Toast.makeText(getApplicationContext(), getString(R.string.GOOGLE_FAILED), Toast.LENGTH_LONG).show();
                            progressBarLayout.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void getUserInfoAndGoToMain(final String userEmail, final String method) {
        DocumentReference informationRef = db.collection(getString(R.string.DB_USERS)).document(userEmail);
        informationRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                SharedPreferencesInfo.setSignIn(getApplicationContext(), true);
                SharedPreferencesInfo.setUserEmail(getApplicationContext(), userEmail);

                if(documentSnapshot.exists()) {
                    System.out.println("신DB가 있습니다");
                    UserInformation userInformation = documentSnapshot.toObject(UserInformation.class);
                    SharedPreferencesInfo.setUserInfo(getApplicationContext(), userInformation);
                    System.out.println("앱에 유저 데이터를 저장했습니다.");

                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), getString(R.string.WELCOME), Toast.LENGTH_LONG).show();
                    finish();

                } else {
                    System.out.println("신DB가 없습니다. 구DB가 있는지 확인합니다");
                    DocumentReference reference = db.collection(getString(R.string.DB_USERS)).document(userEmail).collection(getString(R.string.DB_INFORMATION)).document(getString(R.string.DB_INFORMATION));
                    reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                System.out.println("구DB가 있습니다. 로고 페이지로 이동하여 신DB로 옮깁니다.");
                                intent = new Intent(getApplicationContext(), Logo.class);
                                startActivity(intent);
                                finish();

                            } else {
                                System.out.println("구DB도 없습니다. 유저정보를 새로 만듭니다.");
                                MakeNewDb makeNewDb = new MakeNewDb();
                                makeNewDb.makeNewDb(SignIn.this, getApplicationContext(), userEmail, method);
                            }
                        }
                    });
                }
                progressBarLayout.setVisibility(View.GONE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("유저정보 불러오기를 실패했습니다: " + e);
                progressBarLayout.setVisibility(View.GONE);
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnSignIn :
                final String userEmail = email.getText().toString();
                final String userPass = password.getText().toString();

                if(userEmail.getBytes().length > 0 && userPass.getBytes().length > 0) {

                    firebaseAuth.signInWithEmailAndPassword(userEmail, userPass)
                            .addOnSuccessListener(SignIn.this, new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    System.out.println("로그인에 성공했습니다");
                                    progressBarLayout.setVisibility(View.VISIBLE);
                                    getUserInfoAndGoToMain(userEmail, "Email");
                                }
                            })

                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    System.out.println("로그인에 실패했습니다" + e.getMessage());
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getApplicationContext(), e.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                }
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
                Toast.makeText(getApplicationContext(), R.string.CHECK_YOUR_EMAIL, Toast.LENGTH_LONG).show();
                break;

            case R.id.btnSignInGoogle :
                intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent, RC_SIGN_IN);
                break;

            case R.id.btnSignInFacebook :
                LoginManager loginManager = LoginManager.getInstance();
                loginManager.logInWithReadPermissions(SignIn.this, Arrays.asList("public_profile", "email"));
                loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        System.out.println("페이스북 로그인에 성공했습니다 : " + loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        System.out.println("페이스북 로그인을 취소했습니다");
                        progressBarLayout.setVisibility(View.GONE);
                    }


                    @Override
                    public void onError(FacebookException error) {
                        System.out.println("페이스북 로그인 중 에러가 발생했습니다");
                    }
                });
                break;

            case R.id.btnSignUp :
                intent = new Intent(this, SignUp.class);
                startActivity(intent);
                break;

        }

    }
}
