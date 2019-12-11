package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

public class PlayAudioWithString {
    MediaPlayer mp;

    // 오디오 재생 메소드
    public void playAudio(Context context, String audioFile) {
        if(context != null) {
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
