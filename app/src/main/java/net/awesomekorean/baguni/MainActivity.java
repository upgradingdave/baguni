package net.awesomekorean.baguni;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.awesomekorean.baguni.lesson.LessonFrame;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    FragmentManager fm;
    FragmentTransaction ft;
    MainLesson mainLesson = new MainLesson();
    MainReading mainReading = new MainReading();
    MainWriting mainWriting = new MainWriting();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFrag(mainLesson);

    }

    public void setFrag(Fragment page) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.mainFrame, page);
        ft.commit();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnProfile:
                setFrag(mainLesson);
                break;

            case R.id.btnMessage:
                setFrag(mainLesson);
                break;

            case R.id.btnLesson:
                setFrag(mainLesson);
                break;

            case R.id.btnReading:
                setFrag(mainLesson);
                break;

            case R.id.btnWriting:
                setFrag(mainLesson);
                break;

            case R.id.btnCollection:
                setFrag(mainLesson);
                break;
        }
    }

}
