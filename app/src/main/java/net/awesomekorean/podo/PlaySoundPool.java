package net.awesomekorean.podo;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import net.awesomekorean.podo.R;

import java.text.DecimalFormat;

public class PlaySoundPool {

    Context context;
    SoundPool soundPool;
    int soundId;
    float volume;

    public PlaySoundPool(Context context) {
        this.context = context;
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(1).build();
    }

    // 컬렉션 단어 플레이
    public void playSoundCollection(String path) {
        volume = getDeviceVolume();
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(soundId, volume, volume, 0, 0, 1f);
            }
        });
        soundId = soundPool.load(path, 1);
    }


    // 퀴즈 정답/오답 플레이
    public void playSoundLesson(int sound) {
        volume = getDeviceVolume();
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(soundId, volume, volume, 0, 0, 1f);
            }
        });

        switch (sound) {
            case 0 :
                soundId = soundPool.load(context, R.raw.correct, 1);
                break;

            case 1 :
                soundId = soundPool.load(context, R.raw.wrong, 1);
                break;

            case 2 :
                soundId = soundPool.load(context, R.raw.getpoint, 1);
                break;
        }
    }


    // 예~ 플레이
    public void playSoundYay() {
        volume = getDeviceVolume();
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(soundId, volume, volume, 0, 0, 1f);
            }
        });
        soundId = soundPool.load(context, R.raw.yay, 1);
    }


    // 딩딩~ 플레이
    public void playSoundDingDing() {
        volume = getDeviceVolume();
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(soundId, volume, volume, 0, 0, 1f);
            }
        });
        soundId = soundPool.load(context, R.raw.dingding, 1);
    }



    // 디바이스 볼륨
    public float getDeviceVolume() {
        AudioManager mgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int volumeLevel = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVolume = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float streamVolume = (float) volumeLevel / maxVolume;
        //DecimalFormat format = new DecimalFormat("#.#");

        //return Float.parseFloat(format.format(streamVolume));
        return streamVolume;
    }
}
