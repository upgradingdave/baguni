package net.awesomekorean.podo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        ArrayList<MessageItems> list = new ArrayList<>();

        MessageItems item = new MessageItems();
        item.setPeopleImage(R.drawable.people4);
        item.setMessage("안녕하세요? podo 에서 알려드립니다.");
        item.setMessageDate("19.11.20");
        item.setIsNew(false);
        list.add(item);

        MessageItems item1 = new MessageItems();
        item1.setPeopleImage(R.drawable.people6);
        item1.setMessage("롱~~~~~~~~~메시지 테스트ㅡㅡㅡㅡㅡㅡㅡㄴ얼ㄴ머리ㅏ넝ㅁ라ㅣㅓㄴ런멀ㄴ머리ㅏ너미라ㅓㄴㅁ러ㅣㅏㄴ머리넝라ㅣㄴㅇ머리넘라ㅣㅓㄴㅁ라ㅣㅓㄴㅁ아ㅣ런ㅇ");
        item1.setMessageDate("19.11.30");
        item1.setIsNew(true);
        list.add(item1);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MessageAdapter adapter = new MessageAdapter(list);
        recyclerView.setAdapter(adapter);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
