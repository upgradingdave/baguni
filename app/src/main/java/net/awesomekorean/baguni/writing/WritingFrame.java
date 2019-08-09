package net.awesomekorean.baguni.writing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import net.awesomekorean.baguni.R;

public class WritingFrame extends AppCompatActivity {

    TextView textCount; // 글자 수 표시
    EditText editText; // 쓰기 입력

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_frame);

        textCount = findViewById(R.id.textCount);
        editText = findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                textCount.setText(i+i2+" 자");
                System.out.println("I : " + i+ "I1 : "+i1+ "I2 : " +i2);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }
}
