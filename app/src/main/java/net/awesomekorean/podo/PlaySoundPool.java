package net.awesomekorean.podo;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import net.awesomekorean.podo.R;

public class PlaySoundPool {

    Context context;
    SoundPool soundPool;
    int soundId;

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
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(soundId, 0.7f, 0.7f, 0, 0, 1f);
            }
        });
        soundId = soundPool.load(path, 1);
    }


    // 퀴즈 정답/오답 플레이
    public void playSoundQuiz(int sound) {
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(soundId, 0.7f, 0.7f, 0, 0, 1f);
            }
        });

        switch (sound) {
            case 0 :
                soundId = soundPool.load(context, R.raw.correct, 1);
                break;

            case 1 :
                soundId = soundPool.load(context, R.raw.wrong, 1);
                break;
        }
    }
}
