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
import java.util.Map;

import static com.facebook.FacebookSdk.getCacheDir;

public class PlayAudioWithString {

    private static PlayAudioWithString instance;

    public static PlayAudioWithString getInstance() {
        if(instance == null) {
            instance = new PlayAudioWithString();
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
            mp.stop();
            mp.release();
        }
        StorageReference storageRef = storage.getReference().child(folder).child(audioFile);
        final long ONE_MEGABYTE = 1024 * 1024;
        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                try {
                    File tempMp3 = File.createTempFile("audio", "mp3", getCacheDir());
                    tempMp3.deleteOnExit();
                    FileOutputStream fos = new FileOutputStream(tempMp3);
                    fos.write(bytes);
                    fos.close();

                    mp.reset();
                    FileInputStream fis = new FileInputStream(tempMp3);
                    mp.setDataSource(fis.getFD());

                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();
                        }
                    });

                    mp.prepare();
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


    // 바이트로 오디오 재생하기
    public void playAudioInByte(byte[] b) {
        mp = new MediaPlayer();

        if (mp.isPlaying()) {
            mp.stop();
            mp.release();
        }

        try {

            File tempMp3 = File.createTempFile("audio", "mp3");
            tempMp3.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tempMp3);
            fos.write(b);
            fos.close();

            //mp.reset();
            FileInputStream fis = new FileInputStream(tempMp3);
            mp.setDataSource(fis.getFD());

            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });

            mp.prepare();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    int dialogIndex = 0;

    // 바이트로 대화 오디오 전체 재생하기
    public void playDialogAudioInByte(final Map<Integer, byte[]> dialogAudio) {

        final int dialogLength = dialogAudio.size();

        mp = new MediaPlayer();

        if (mp.isPlaying()) {
            mp.stop();
            mp.release();
        }

        try {

            File tempMp3 = File.createTempFile("audio", "mp3", getCacheDir());
            tempMp3.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tempMp3);
            fos.write(dialogAudio.get(dialogIndex));
            fos.close();

            mp.reset();
            FileInputStream fis = new FileInputStream(tempMp3);
            mp.setDataSource(fis.getFD());

            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    dialogIndex ++;
                    if(dialogIndex < dialogLength) {
                        playAudioInByte(dialogAudio.get(dialogIndex));
                    } else {
                        dialogIndex = 0;
                    }
                }
            });

            mp.prepare();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
