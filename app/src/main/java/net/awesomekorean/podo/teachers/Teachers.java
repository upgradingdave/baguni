package net.awesomekorean.podo.teachers;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.MainWriting;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.purchase.TopUp;

import java.util.ArrayList;

public class Teachers extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ImageView btnBack;
    TextView requiredPoints;
    TextView holdingPoints;
    Button btnTopUp;
    Button btnSubmit;

    Intent intent;
    String teacherName;

    LinearLayout requestResult;

    ArrayList<TeachersItems> list;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

        btnBack = findViewById(R.id.btnBack);
        requiredPoints = findViewById(R.id.requiredPoints);
        holdingPoints = findViewById(R.id.holdingPoints);
        btnTopUp = findViewById(R.id.btnTopUp);
        btnSubmit = findViewById(R.id.btnSubmit);
        requestResult = findViewById(R.id.requestResult);
        btnBack.setOnClickListener(this);
        btnTopUp.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        intent = getIntent();


        // DB 에서 선생님 정보들 가져와서 아래 리스트에 넣을 것
        list = new ArrayList<>();

        TeachersItems teacher1 = new TeachersItems();
        teacher1.setPicture(getDrawable(R.drawable.back));
        teacher1.setIsAvailable(true);
        teacher1.setName("Danny");
        teacher1.setTag("#male #KoreanTeacher");
        list.add(teacher1);

        TeachersItems teacher2 = new TeachersItems();
        teacher2.setPicture(getDrawable(R.drawable.back));
        teacher2.setIsAvailable(false);
        teacher2.setName("Dave");
        teacher2.setTag("#male #KoreanTeacher");
        list.add(teacher2);

        TeachersItems teacher3 = new TeachersItems();
        teacher3.setPicture(getDrawable(R.drawable.back));
        teacher3.setIsAvailable(true);
        teacher3.setName("Lyla");
        teacher3.setTag("#male #KoreanTeacher");
        list.add(teacher3);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TeachersAdapter adapter = new TeachersAdapter(this, list);

        adapter.setOnItemClickListener(new TeachersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                btnSubmit.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple_30));
                teacherName = list.get(pos).getName();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnBack :
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.btnTopUp :
                intent = new Intent(this, TopUp.class);
                startActivity(intent);
                break;

            case R.id.btnSubmit :
                String article = intent.getExtras().getString(getString(R.string.EXTRA_ARTICLE));
                RequestWriting requestWriting = new RequestWriting(article, teacherName);

                // 교정요청 DB 에 저장하기
                MainWriting.writingOnCorrecting = requestWriting.getGuid();
                db.collection("android/podo/writing").document(requestWriting.getGuid())
                        .set(requestWriting).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        requestResult.setVisibility(View.VISIBLE);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                requestResult.setVisibility(View.GONE);
                                Intent intent = new Intent(getApplication(), MainActivity.class);
                                startActivity(intent);
                            }
                        }, 1000);
                    }
                });
                break;
        }
    }
}
