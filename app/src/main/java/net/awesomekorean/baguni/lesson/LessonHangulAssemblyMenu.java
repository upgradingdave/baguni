package net.awesomekorean.baguni.lesson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import net.awesomekorean.baguni.R;

public class LessonHangulAssemblyMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_hangul_assembly_menu);

    }


    public void cv_h(View view) {
        Intent intent = new Intent(getApplicationContext(), LessonHangulAssembly.class);
        intent.putExtra("type", "cv_h");
        startActivity(intent);
    }

    public void cv_v(View view) {
        Intent intent = new Intent(getApplicationContext(), LessonHangulAssembly.class);
        intent.putExtra("type", "cv_v");
        startActivity(intent);
    }

    public void cvc_h(View view) {
        Intent intent = new Intent(getApplicationContext(), LessonHangulAssembly.class);
        intent.putExtra("type", "cvc_h");
        startActivity(intent);
    }

    public void cvc_v(View view) {
        Intent intent = new Intent(getApplicationContext(), LessonHangulAssembly.class);
        intent.putExtra("type", "cvc_v");
        startActivity(intent);
    }


}
