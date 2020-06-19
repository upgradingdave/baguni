package net.awesomekorean.podo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CloudMessageReceiver extends AppCompatActivity {

    TextView messageTitle;
    TextView messageContents;
    ImageView btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_message_receiver);

        messageTitle = findViewById(R.id.messageTitle);
        messageContents = findViewById(R.id.messageContents);
        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        messageContents.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");

        String contents = intent.getStringExtra("contents");

        messageTitle.setText(title);

        messageContents.setText(contents);
    }
}
