package net.awesomekorean.baguni;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//    FragmentManager fm;
//    FragmentTransaction ft;
//    Word word = new Word();
//    Lesson lesson = new Lesson();
//    Reading reading = new Reading();
//    Writing writing = new Writing();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.navigation_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.menu_vocabulary:
                Toast.makeText(getApplicationContext(),"vocabulary clicked", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_lesson:
                Toast.makeText(getApplicationContext(),"lesson clicked", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_reading:
                Toast.makeText(getApplicationContext(),"reading clicked", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_writing:
                Toast.makeText(getApplicationContext(),"writing clicked", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_profile:
                Toast.makeText(getApplicationContext(),"profile clicked", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_option:
                Toast.makeText(getApplicationContext(),"option clicked", Toast.LENGTH_LONG).show();
                break;
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
