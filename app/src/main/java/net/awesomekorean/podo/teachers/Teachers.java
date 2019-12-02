package net.awesomekorean.podo.teachers;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.purchase.TopUp;

import java.util.ArrayList;

public class Teachers extends AppCompatActivity implements View.OnClickListener {

    ImageView btnBack;
    TextView requiredPoints;
    TextView holdingPoints;
    Button btnTopUp;
    Button btnSubmit;

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
        btnBack.setOnClickListener(this);
        btnTopUp.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        ArrayList<TeachersItems> list = new ArrayList<>();

        TeachersItems teacher1 = new TeachersItems();
        teacher1.setPicture(getDrawable(R.drawable.back));
        teacher1.setIsAvailable(true);
        teacher1.setName("Danny");
        teacher1.setTag("#male #KoreanTeacher");
        list.add(teacher1);

        TeachersItems teacher2 = new TeachersItems();
        teacher2.setPicture(getDrawable(R.drawable.back));
        teacher2.setIsAvailable(false);
        teacher2.setName("Danny");
        teacher2.setTag("#male #KoreanTeacher");
        list.add(teacher2);

        TeachersItems teacher3 = new TeachersItems();
        teacher3.setPicture(getDrawable(R.drawable.back));
        teacher3.setIsAvailable(true);
        teacher3.setName("Danny");
        teacher3.setTag("#male #KoreanTeacher");
        list.add(teacher3);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TeachersAdapter adapter = new TeachersAdapter(this, list);

        adapter.setOnItemClickListener(new TeachersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                btnSubmit.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple_30));
            }
        });

        recyclerView.setAdapter(adapter);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnBack :
                finish();
                break;

            case R.id.btnTopUp :
                Intent intent = new Intent(this, TopUp.class);
                startActivity(intent);
                break;

            case R.id.btnSubmit :
                break;
        }
    }
}
