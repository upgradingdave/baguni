package net.awesomekorean.baguni.teachers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.awesomekorean.baguni.R;

import java.util.ArrayList;

public class Teachers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

        ArrayList<TeachersItems> list = new ArrayList<>();

        TeachersItems teacher1 = new TeachersItems();
        teacher1.isAvailable = "Available";
        teacher1.name = "Danny";
        teacher1.tag = "#male #KoreanTeacher";
        list.add(teacher1);

        TeachersItems teacher2 = new TeachersItems();
        teacher2.isAvailable = "Vacation";
        teacher2.name = "Lyla";
        teacher2.tag = "#female #dialect";
        list.add(teacher2);

        TeachersItems teacher3 = new TeachersItems();
        teacher3.isAvailable = "Vacation";
        teacher3.name = "Dave";
        teacher3.tag = "#female #dialect";
        list.add(teacher3);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TeachersAdapter adapter = new TeachersAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
