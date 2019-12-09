package net.awesomekorean.podo.message;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;

import java.util.ArrayList;
import java.util.List;

public class Message extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    MessageAdapter adapter;

    List<MessageItems> oldMessages;
    List<MessageItems> newMessages;
    List<MessageItems> list;
    MessageRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        String userEmail = MainActivity.userEmail;

        list = new ArrayList<>();

        repository = new MessageRepository(this);

        // Room 에서 이전 메시지 불러오기
        repository.getAll().observe(this, new Observer<List<MessageItems>>() {
            @Override
            public void onChanged(List<MessageItems> messageItems) {

                for(MessageItems item : messageItems) {
                    oldMessages.add(item);
                }
                // 리스트를 역순으로 변경
                //Collections.reverse(oldMessages);

                if(messageItems.size() > 10) {
                    list = oldMessages.subList(0,10);
                } else {
                    list = oldMessages;
                }

            }
        });
        System.out.println("LIST: " + list);
        // 새로운 메시지 체크하기
        db.collection(getString(R.string.DB_MESSAGES))
                .whereEqualTo("userEmail", userEmail)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            System.out.println("Listen failed" + e);
                            return;
                        }

                        newMessages = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : value) {
                            MessageItems newMessage = doc.toObject(MessageItems.class);
                            if (newMessage.getIsNew()) {
                                System.out.println("새로운 메시지가 도착했습니다");
                                newMessages.add(newMessage);
                                //list.add(newMessage);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                });



        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MessageAdapter(list);
        recyclerView.setAdapter(adapter);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 새로운 메시지를 읽음처리 하기
                for(MessageItems item : newMessages) {
                    item.setIsNew(false);
                    repository = new MessageRepository(getApplicationContext());
                    repository.insert(item);
                }
                finish();
            }
        });
    }
}
