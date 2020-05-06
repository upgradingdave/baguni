package net.awesomekorean.podo.message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;

import java.util.ArrayList;
import java.util.List;

public class Message extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    MessageAdapter adapter;

    List<MessageItems> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        final String userEmail = getIntent().getStringExtra(getResources().getString(R.string.EMAIL));

        list = new ArrayList<>();

        // DB 에서 일괄 불러오기
        db.collection(getString(R.string.DB_USERS)).document(userEmail).collection(getString(R.string.DB_MESSAGES))
                .orderBy("messageDate", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                MessageItems item = doc.toObject(MessageItems.class);
                                list.add(item);
                                System.out.println("메시지를 불러왔습니다");
                                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                                layoutManager.setReverseLayout(true);
                                layoutManager.setStackFromEnd(true);
                                recyclerView.setLayoutManager(layoutManager);

                                adapter = new MessageAdapter(list);
                                recyclerView.setAdapter(adapter);

                                if(item.getIsNew()) {
                                    DocumentReference docRef = db.collection(getString(R.string.DB_USERS)).document(userEmail).collection(getString(R.string.DB_MESSAGES)).document(doc.getId());

                                    docRef.update("isNew", false)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    System.out.println("isNew를 업데이트 했습니다");
                                                }
                                            });
                                }

                            }
                        }
                    }
                });


        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
