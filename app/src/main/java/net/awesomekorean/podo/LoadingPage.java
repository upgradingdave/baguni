package net.awesomekorean.podo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

public class LoadingPage extends AppCompatActivity {

    public static Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);

        activity = LoadingPage.this;
    }
}
