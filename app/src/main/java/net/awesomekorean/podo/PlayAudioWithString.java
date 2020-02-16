package net.awesomekorean.podo;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.facebook.FacebookSdk.getCacheDir;

public class PlayAudioWithString {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    MediaPlayer mp;

    String folder;

    byte[] bytesThis;

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
    }

    public void playAudioNumber(String audioFile, String unitId) {
        if(audioFile != null) {
            folder = "lesson/number/" + unitId;
            findFromStorage(folder, audioFile);
        }
    }

    public void playAudioReading(String audioFile, String unitId) {
        if(audioFile != null) {
            folder = "reading/" + unitId;
            findFromStorage(folder, audioFile);
        }
    }

    public void playAudioCollection(String audioFile) {
        if(audioFile != null) {
            String unitId = getUnitId(audioFile);
            String isFromLesson = unitId.substring(0,1);
            if(isFromLesson.equals("l")) {
                folder = "lesson/" + unitId;
            } else if(isFromLesson.equals("r")) {
                folder = "reading/" + unitId;
            }
            findFromStorage(folder, audioFile);
        }
    }

    private String getUnitId(String audioFile) {
        String[] audioFileSplit = audioFile.split("_");
        String split1 = audioFileSplit[0];
        String split2 = audioFileSplit[1];
        String unitId = split1 + "_" + split2;
        return unitId;
    }



    // 저장소에서 오디오 재생하기
    private void findFromStorage(String folder, String audioFile) {
        mp = new MediaPlayer();

        if (mp.isPlaying()) {
            mp.release();
        }
        StorageReference storageRef = storage.getReference().child(folder).child(audioFile);
        final long ONE_MEGABYTE = 1024 * 1024;
        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                try {
                    bytesThis = new byte[bytes.length];
                    for(int i=0; i<bytes.length; i++) {
                        bytesThis[i] = bytes[i];
                    }

                    File tempMp3 = File.createTempFile("audio", "mp3", getCacheDir());
                    tempMp3.deleteOnExit();
                    FileOutputStream fos = new FileOutputStream(tempMp3);
                    fos.write(bytes);
                    fos.close();

                    mp.reset();
                    FileInputStream fis = new FileInputStream(tempMp3);
                    mp.setDataSource(fis.getFD());

                    mp.prepare();
                    mp.start();
                    System.out.println("MP1: "+mp);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("FAIED:" + e);
            }
        });
    }

    // 오디오 다시 재생하기
    public void repeatAudio() {
        if (mp.isPlaying()) {
            mp.stop();
        }
        mp.start();
        System.out.println("MP2: "+mp);
    }
}
