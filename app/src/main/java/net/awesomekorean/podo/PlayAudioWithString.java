package net.awesomekorean.podo;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class PlayAudioWithString {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    MediaPlayer mp;

    String folder;

    public void playAudioHangul(String audioFile, String hangul) {
        if(audioFile != null) {
            folder = "hangul/" + hangul;
            findFromStorage(folder, audioFile);
        }
    }

    public void playAudioLesson(String audioFile, String unitId) {
        if(audioFile != null) {
            folder = "lesson/" + unitId;
            findFromStorage(folder, audioFile);
        }
        System.out.println(audioFile);
        System.out.println(unitId);
    }

    public void playAudioReading(String audioFile, String unitId) {
        if(audioFile != null) {
            folder = "reading/" + unitId;
            findFromStorage(folder, audioFile);
        }
    }



    // 저장소에서 오디오 재생하기
    private void findFromStorage(String folder, String audioFile) {
        StorageReference storageRef = storage.getReference().child(folder).child(audioFile);
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                if(mp != null) {
                    mp.release();
                }
                String url = uri.toString();
                mp = new MediaPlayer();
                try {
                    mp.setDataSource(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp.start();
            }
        });
    }


    // 앱 내부 오디오 재생 메소드
    public void playAudio(Context context, String audioFile) {
        if(audioFile != null && context != null) {
            if(mp != null) {
                mp.release();
            }
            String uriPath = "android.resource://" + context.getPackageName() + "/raw/" + audioFile;
            System.out.println("URIPATH:"+uriPath);
            Uri uri = Uri.parse(uriPath);
            mp = MediaPlayer.create(context, uri);
            try {
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
            }
            catch (Exception e) {}
        }
    }
}
