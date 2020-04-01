package net.awesomekorean.podo;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.SeekBar;

import java.io.IOException;

public class MediaPlayerManager {

    private MediaPlayer mediaPlayer;
    private boolean isSet = false;
    private int playingPosition = 0;
    private boolean isPlaying = false;

    private static MediaPlayerManager instance = new MediaPlayerManager();

    public static MediaPlayerManager getInstance() {
        return instance;
    }

    private MediaPlayerThread thread;


    class MediaPlayerThread extends Thread {

        private SeekBar seekBar;
        private volatile boolean running = false;

        public void terminate() {
            running = false;
        }

        public MediaPlayerThread(SeekBar seekBar) {
            super();
            this.seekBar = seekBar;
            this.running = true;
        }

        @Override
        // 쓰레드가 시작되면 콜백되는 매서드, 시크바를 조금씩 움직이게 해줌
        public void run() {
            while(running && isPlaying && mediaPlayer != null) {
                Integer currentPosition = null;
                try {
                    currentPosition = mediaPlayer.getCurrentPosition();
                } catch (IllegalStateException e) {
                    System.out.println(e);
                    mediaPlayer.reset();
                    currentPosition = mediaPlayer.getCurrentPosition();
                }
                seekBar.setProgress(currentPosition);
            }
            if(mediaPlayer == null) {
                isPlaying = false;
                isSet = false;
            }
        }
    }


    public void createMediaPlayer() {
        if(mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            isSet = false;
            isPlaying = false;
            playingPosition = 0;
        }
    }


    public void setMediaPlayer(String audioUrl) {
        createMediaPlayer();
        try {
            mediaPlayer.setDataSource(audioUrl);
            isSet = true;
            isPlaying = false;
            playingPosition = 0;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getDuration() {
        if(mediaPlayer != null) {
            int duration = mediaPlayer.getDuration();
            if(duration < 0) {
                System.out.println("getDuration must not be less than zero");
                return 0;
            } else {
                return duration;
            }
        } else {
            System.out.println("can't getDuration. mediaPlayer is null");
            return 0;
        }
    }

    public void playMediaPlayer(SeekBar seekBar) {
        if(mediaPlayer == null || isSet == false) {
            System.out.println("Should never be here!: inside playMediaPlayer but mediaPlayer is not set");
        } else {
            try {
                mediaPlayer.prepare(); // need test reset or not
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.seekTo(playingPosition);
            isPlaying = true;
            mediaPlayer.start();
            thread = new MediaPlayerThread(seekBar);
            thread.start();
        }
    }


    public void playMediaPlayer() {
        if(mediaPlayer == null || isSet == false) {
            System.out.println("Should never be here!: inside playMediaPlayer but mediaPlayer is not set");
        } else {
            try {
                mediaPlayer.prepare(); // need test reset or not
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.seekTo(playingPosition);
            isPlaying = true;
            mediaPlayer.start();
        }
    }


    public void stopMediaPlayer() {
        if(thread != null) {
            thread.terminate();
        }
        if(mediaPlayer != null && mediaPlayer.isPlaying()) {
            playingPosition = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
        } else {
            playingPosition = 0;
        }
        isPlaying = false;
    }

    public int getPlayingPosition() {
        return playingPosition;
    }

    // when we do mediaPlayer.stop() also call .release();


//    public void setMediaPlayerState(String stringNextState, String audioUrl) {
//        if(mediaPlayer == null) {
//            mediaPlayer = createAndSetMediaPlayer(mediaPlayer, audioUrl);
//
//            // this is when we want to play but the mediaPlayer is not created
//            if(stringNextState.equals("play")) {
//                mediaPlayer = playMediaPlayer(mediaPlayer);
//            }
//
//        } else {
//            // this is when we want to play and mediaPlayer is already created
//            mediaPlayer.stop();
//            if(stringNextState.equals("play")) {
//                mediaPlayer = null;
//                mediaPlayer = createAndSetMediaPlayer(mediaPlayer, audioUrl);
//                mediaPlayer = playMediaPlayer(mediaPlayer);
//            }
//        }
//    }

}
