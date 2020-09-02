package net.awesomekorean.podo.teachers;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UnixTimeStamp;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.collection.CollectionEntity;
import net.awesomekorean.podo.purchase.TopUp;
import net.awesomekorean.podo.writing.WritingEntity;
import net.awesomekorean.podo.writing.WritingRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Teachers extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ImageView btnBack;
    TextView requiredPoints;
    TextView holdingPoints;
    Button btnTopUp;
    Button btnSubmit;

    int pointsHave;
    int pointsNeed;

    Intent intent;
    String teacherName;
    String teacherId;
    String teacherEmail;

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

        intent = getIntent();
        WritingEntity entity = (WritingEntity) intent.getSerializableExtra(getString(R.string.EXTRA_ENTITY));
        pointsNeed = entity.getLetters();
        pointsHave = SharedPreferencesInfo.getUserInfo(getApplicationContext()).getPoints();
        requiredPoints.setText(Integer.toString(pointsNeed));
        holdingPoints.setText(String.valueOf(pointsHave));


        // DB 에서 선생님 정보들 가져와서 아래 리스트에 넣을 것
        list = new ArrayList<>();
        db.collection(getString(R.string.DB_TEACHERS)).whereGreaterThan("status", 0)
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
                        if(list.get(pos).getStatus() == 1 && pointsHave >= pointsNeed) {
                            btnSubmit.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple_30));
                            btnSubmit.setEnabled(true);
                        }
                        teacherName = list.get(pos).getName();
                        teacherId = list.get(pos).getId();
                        teacherEmail = list.get(pos).getEmail();
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

                final String userEmail = SharedPreferencesInfo.getUserEmail(this);
                final String userName = SharedPreferencesInfo.getUserName(this);

                final int newPoints = pointsHave - pointsNeed;

                final UserInformation userInformation = SharedPreferencesInfo.getUserInfo(getApplicationContext());
                userInformation.setPoints(newPoints);

                DocumentReference informationRef = db.collection(getString(R.string.DB_USERS)).document(userEmail).collection(getString(R.string.DB_INFORMATION)).document(getString(R.string.DB_INFORMATION));
                informationRef.update("points", newPoints).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        SharedPreferencesInfo.setUserInfo(getApplicationContext(), userInformation);
                        System.out.println("포인트를 업데이트 했습니다. : " + newPoints);
                    }
                });

                final WritingEntity requestWriting = (WritingEntity) intent.getSerializableExtra(getString(R.string.EXTRA_ENTITY));

                String token = SharedPreferencesInfo.getUserToken(getApplicationContext());

                // 재요청 일 때
                if(requestWriting.getStatus() == 99) {
                    requestWriting.setContents(requestWriting.getCorrection());
                    requestWriting.setCorrection("");
                    requestWriting.setTeacherFeedback("");
                }

                requestWriting.setUserEmail(userEmail);
                requestWriting.setUserName(userName);
                requestWriting.setTeacherName(teacherName);
                requestWriting.setTeacherId(teacherId);
                requestWriting.setDateRequest(UnixTimeStamp.getTimeNow());
                requestWriting.setStatus(1);
                requestWriting.setUserToken(token);

                WritingRepository repository = new WritingRepository(getApplicationContext());
                repository.update(requestWriting);

                // 교정요청 DB에 저장하기
                db.collection(getString(R.string.DB_WRITINGS)).document(requestWriting.getGuid())
                        .set(requestWriting).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("교정요청을 DB에 저장했습니다.");
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

                // analytics 로그 이벤트 얻기
                FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
                Bundle bundle = new Bundle();
                bundle.putString("type", "correction");
                bundle.putInt("points", pointsNeed);
                firebaseAnalytics.logEvent("point_use", bundle);
                break;
        }
    }
}
