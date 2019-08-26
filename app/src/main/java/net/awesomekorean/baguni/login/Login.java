package net.awesomekorean.baguni.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import net.awesomekorean.baguni.MainActivity;
import net.awesomekorean.baguni.R;
import net.awesomekorean.baguni.reading.ReadingFrame;

public class Login extends AppCompatActivity implements Button.OnClickListener {

    Button btnSignIn;
    Button btnSignUp;
    Button btnSignInGoogle;
    Button btnSignInFacebook;
    Button btnSelectLanguage;

    ListView listView;
    LoginListViewAdapter adapter;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        adapter = new LoginListViewAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String language = (String) adapterView.getItemAtPosition(i);
                btnSelectLanguage.setText(language);
                listView.setVisibility(View.GONE);

                switch (i) {    // 선택한 언어에 따라 메뉴 언어 변경

                    case 0 :
                        break;

                    case 1 :
                        break;

                    case 2 :
                        break;

                    case 3 :
                        break;

                    case 4 :
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnSignIn :
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.btnSingUp :
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
