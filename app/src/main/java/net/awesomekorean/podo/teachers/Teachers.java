package net.awesomekorean.podo.teachers;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.storage.FirebaseStorage;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.UnixTimeStamp;
import net.awesomekorean.podo.collection.CollectionEntity;
import net.awesomekorean.podo.collection.CollectionRepository;
import net.awesomekorean.podo.purchase.TopUp;
import net.awesomekorean.podo.writing.WritingEntity;
import net.awesomekorean.podo.writing.WritingRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teachers extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ImageView btnBack;
    TextView requiredPoints;
    TextView holdingPoints;
    Button btnTopUp;
    Button btnSubmit;

    Intent intent;
    String teacherName;
    String teacherId;

    LinearLayout requestResult;

    ArrayList<TeachersItems> list;

    LinearLayout progressBarLayout;
    ProgressBar progressBar;

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
        progressBarLayout = findViewById(R.id.progressBarLayout);
        progressBar = findViewById(R.id.progressBar);
        btnBack.setOnClickListener(this);
        btnTopUp.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        int color = ContextCompat.getColor(this, R.color.PURPLE);
        progressBar.setIndeterminate(true);
        progressBar.getIndeterminateDrawable().setColorFilter(color, PorterDuff.Mode.MULTIPLY);

        holdingPoints.setText(String.valueOf(MainActivity.userInformation.getPoints()));

        // DB 에서 선생님 정보들 가져와서 아래 리스트에 넣을 것
        list = new ArrayList<>();
        db.collection(getString(R.string.DB_TEACHERS_INFORMATION)).whereGreaterThan("status", 0)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                System.out.println("선생님 정보를 로드했습니다");
                                TeachersItems items = document.toObject(TeachersItems.class);
                                list.add(items);
                            }
                        } else {
                            System.out.println("선생님 정보 로드 중 오류가 발생했습니다." + task.getException());
                        }
                    }
                }).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                TeachersAdapter adapter = new TeachersAdapter(getApplicationContext(), list);

                adapter.setOnItemClickListener(new TeachersAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int pos) {
                        btnSubmit.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple_30));
                        teacherName = list.get(pos).getName();
                        teacherId = list.get(pos).getId();
                    }
                });

                recyclerView.setAdapter(adapter);

                progressBarLayout.setVisibility(View.GONE);
            }
        });

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
                btnSubmit.setEnabled(false);

                Intent intent = getIntent();
                String code = intent.getStringExtra("code");


                // 교정요청일 때
                if(code.equals("correction")) {
                    WritingEntity requestWriting = (WritingEntity) intent.getSerializableExtra(getString(R.string.EXTRA_ENTITY));

                    requestWriting.setUserEmail(MainActivity.userEmail);
                    requestWriting.setUserName(MainActivity.userName);
                    requestWriting.setTeacherName(teacherName);
                    requestWriting.setTeacherId(teacherId);
                    requestWriting.setDateRequest(UnixTimeStamp.getTimeNow());
                    requestWriting.setStatus(1);

                    WritingRepository repository = new WritingRepository(this);
                    repository.update(requestWriting);

                    // 교정요청 DB 에 저장하기
                    db.collection(getString(R.string.DB_TEACHERS_WRITINGS)).document(requestWriting.getGuid())
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
                            }, 3000);
                        }
                    });

                    // 녹음요청일 떄
                } else if(code.equals("record")) {
                    ArrayList<CollectionEntity> recordList = (ArrayList<CollectionEntity>) intent.getSerializableExtra("checkedList");

                    List<String> guid = new ArrayList();
                    List<String> front = new ArrayList();
                    List<String> back = new ArrayList();
                    List<String> audio = new ArrayList();

                    for (final CollectionEntity entity : recordList) {

                        guid.add(entity.getGuid());
                        front.add(entity.getFront());
                        back.add(entity.getBack());
                    }

                    // 녹음요청 DB 에 저장하기
                    Map<String, Object> request = new HashMap<>();
                    request.put("guid", guid);
                    request.put("front", front);
                    request.put("back", back);
                    request.put("audio", audio);
                    request.put("dateRequest", UnixTimeStamp.getTimeNow());
                    request.put("dateAnswer", "");
                    request.put("status", 1);
                    request.put("teacherId", teacherId);
                    request.put("teacherName", teacherName);
                    request.put("userEmail", MainActivity.userEmail);
                    request.put("userName", MainActivity.userName);

                    db.collection(getString(R.string.DB_TEACHERS_COLLECTIONS)).document()
                            .set(request)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
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
                                    }, 3000);
                                }
                            });
                    break;
                }
        }
    }
}
