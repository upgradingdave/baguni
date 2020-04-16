package net.awesomekorean.podo;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.SeekBar;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    public static MediaPlayerManager getInstance(SeekBar seekBar) {
        instance.setSeekBar(seekBar);
        return instance;
    }


    private MediaPlayerThread thread;

    private SeekBar seekBar;


    class MediaPlayerThread extends Thread {

        private SeekBar seekBar;

        private volatile boolean running = false;

        public void terminate() {
            running = false;
        }


        public MediaPlayerThread() {
            super();
            this.running = true;
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

                if(seekBar != null) {
                    seekBar.setProgress(currentPosition);
                }
            }

            if(mediaPlayer == null) {
                isPlaying = false;
                isSet = false;
            }
        }
    }

    private void setSeekBar(SeekBar seekBar) {
        this.seekBar = seekBar;
    }

    private void initialize(boolean isSinglePlayer) {
        if(isSinglePlayer || mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            isSet = false;
            isPlaying = false;
            playingPosition = 0;
        }
    }


    public void setMediaPlayerUrl(String audioUrl) {
        initialize(false);
        try {
            mediaPlayer.setDataSource(audioUrl);
            mediaPlayer.prepare();
            isSet = true;
            isPlaying = false;
            playingPosition = 0;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setMediaPlayerByte(byte[] bytes) {

        try {

            File tempMp3 = File.createTempFile("audio", "mp3");
            tempMp3.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tempMp3);
            BufferedOutputStream bfos = new BufferedOutputStream(fos);
            bfos.write(bytes);
            fos.close();

            FileInputStream fis = new FileInputStream(tempMp3);

            initialize(true);
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(fis.getFD());
                mediaPlayer.prepare();
                isSet = true;
                isPlaying = false;
                playingPosition = 0;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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

    public void playMediaPlayer() {
        if(mediaPlayer == null || isSet == false) {
            System.out.println("Should never be here!: inside playMediaPlayer but mediaPlayer is not set");
        } else {

            seek(playingPosition);
            isPlaying = true;
            mediaPlayer.start();


            if(seekBar != null) {
                thread = new MediaPlayerThread(seekBar);
            } else {
                thread = new MediaPlayerThread();
            }

            thread.start();
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


    public void seek(int position) {
        playingPosition = position;

        if(seekBar != null) {
            seekBar.setProgress(playingPosition);
        }

        if(mediaPlayer != null) {
            mediaPlayer.seekTo(playingPosition);
        }
    }


    public void setSpeed(float speed) {
        if(mediaPlayer != null ) {
            mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(speed));
        }
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
