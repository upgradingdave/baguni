package net.awesomekorean.podo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import net.awesomekorean.podo.collection.CollectionRepository;

public class CollectResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_result);

        Intent intent = getIntent();

        String front = intent.getExtras().getString(getResources().getString(R.string.COLLECT_FRONT));
        String back = intent.getExtras().getString(getResources().getString(R.string.COLLECT_BACK));
        String folder = intent.getExtras().getString(getResources().getString(R.string.COLLECT_FOLDER));
        String audio = intent.getExtras().getString(getResources().getString(R.string.COLLECT_AUDIO));

        DownloadAudio downloadAudio = new DownloadAudio(getApplicationContext(), folder, audio);
        downloadAudio.downloadAudio();

        CollectionRepository repository = new CollectionRepository(getApplicationContext());
        repository.insert(front, back, audio);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }
}
