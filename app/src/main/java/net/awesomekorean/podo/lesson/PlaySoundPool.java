package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import net.awesomekorean.podo.R;

public class PlaySoundPool {

    SoundPool soundPool;
    int[] soundIds;

    public PlaySoundPool(Context context) {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(2).build();

        soundIds = new int[2];
        soundIds[0] = soundPool.load(context, R.raw.correct, 1);
        soundIds[1] = soundPool.load(context, R.raw.wrong, 1);
    }

    public void playSoundPool(int sound) {
        soundPool.play(soundIds[sound], 1f, 1f, 0, 0, 1f);
    }
}
