package net.awesomekorean.podo;

import android.media.MediaPlayer;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PlayAudioMediaPlayer {

    private static PlayAudioMediaPlayer instance;

    public static PlayAudioMediaPlayer getInstance() {
        if(instance == null) {
            instance = new PlayAudioMediaPlayer();
        }
        return instance;
    }

    FirebaseStorage storage = FirebaseStorage.getInstance();
    MediaPlayer mp;

    String folder;

    public void playAudioHangul(String audioFile, String hangul) {
        if(audioFile != null) {
            folder = "hangul/" + hangul;
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
        if (mp != null) {
            mp.release();
        }
        mp = new MediaPlayer();

        StorageReference storageRef = storage.getReference().child(folder).child(audioFile);
        final long ONE_MEGABYTE = 1024 * 1024;
        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                if (mp != null) {
                    mp.release();
                }
                mp = new MediaPlayer();

                try {

                    File tempMp3 = File.createTempFile("audio", "mp3");
                    tempMp3.deleteOnExit();
                    FileOutputStream fos = new FileOutputStream(tempMp3);
                    BufferedOutputStream bfos = new BufferedOutputStream(fos);
                    bfos.write(bytes);
                    fos.close();

                    FileInputStream fis = new FileInputStream(tempMp3);
                    BufferedInputStream bfis = new BufferedInputStream(fis);

                    if(bfis.read(bytes) != -1) {
                        mp.setDataSource(fis.getFD());

                        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.start();
                            }
                        });

                        mp.prepare();
                    }
                } catch (Exception ex) {
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


    // 바이트로 오디오 재생하기
    public void playAudioInByte(byte[] b) {

        if (mp != null) {
            mp.release();
        }
        mp = new MediaPlayer();

        try {

            File tempMp3 = File.createTempFile("audio", "mp3");
            tempMp3.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tempMp3);
            BufferedOutputStream bfos = new BufferedOutputStream(fos);
            bfos.write(b);
            fos.close();

            FileInputStream fis = new FileInputStream(tempMp3);
            BufferedInputStream bfis = new BufferedInputStream(fis);

            if(bfis.read(b) != -1) {
                mp.setDataSource(fis.getFD());

                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                    }
                });

                mp.prepare();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
    // Uri로 오디오 재생하기
    public void playAudioInUri(Uri uri) {
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

     */
}
