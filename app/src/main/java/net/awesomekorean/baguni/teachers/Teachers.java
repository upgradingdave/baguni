package net.awesomekorean.baguni.teachers;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.awesomekorean.baguni.R;

import java.util.ArrayList;

public class Teachers extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

        ArrayList<TeachersItems> list = new ArrayList<>();

        TeachersItems teacher1 = new TeachersItems();
        teacher1.setPicture(getDrawable(R.drawable.icon_back));
        teacher1.setIsAvailable("available");
        teacher1.setName("Danny");
        teacher1.setTag("#male #KoreanTeacher");
        list.add(teacher1);

        TeachersItems teacher2 = new TeachersItems();
        teacher2.setPicture(getDrawable(R.drawable.icon_back));
        teacher2.setIsAvailable("available");
        teacher2.setName("Danny");
        teacher2.setTag("#male #KoreanTeacher");
        list.add(teacher2);

        TeachersItems teacher3 = new TeachersItems();
        teacher3.setPicture(getDrawable(R.drawable.icon_back));
        teacher3.setIsAvailable("available");
        teacher3.setName("Danny");
        teacher3.setTag("#male #KoreanTeacher");
        list.add(teacher3);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TeachersAdapter adapter = new TeachersAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
