package net.awesomekorean.podo;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.SeekBar;

import net.awesomekorean.podo.lesson.LessonDialog;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

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

    private LessonDialog lessonDialog;

    private int dialogIndex = 0;


    class MediaPlayerThread extends Thread {

        private SeekBar seekBar;

        private volatile boolean running;

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


    // 읽기 전체읽기용
    public void resetPlayPosition() {
        playingPosition = 0;
    }


    private void initialize() {
        if(mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            isSet = false;
            isPlaying = false;

        } else {
            mediaPlayer.reset();
        }
    }


    // 읽기 본문, 한글 조합 재생
    public void setMediaPlayerUrl(String audioUrl) {

        initialize();

        try {
            mediaPlayer.setDataSource(audioUrl);
            mediaPlayer.prepare();
            isSet = true;
            isPlaying = false;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 읽기 단어, 레슨 단어, 한글 레슨 재생
    public void setMediaPlayerByte(byte[] bytes) {

        try {
            File tempMp3 = File.createTempFile("audio", "mp3");
            tempMp3.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tempMp3);
            BufferedOutputStream bfos = new BufferedOutputStream(fos);
            bfos.write(bytes);
            fos.close();

            FileInputStream fis = new FileInputStream(tempMp3);

            initialize();

            try {
                mediaPlayer.setDataSource(fis.getFD());
                mediaPlayer.prepare();
                isSet = true;
                isPlaying = false;

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


    // 레슨 dialog 개별 플레이
    public void playOneDialog() {

        playMediaPlayer(false);

        if(mediaPlayer != null) {

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    lessonDialog = LessonDialog.newInstance();
                    lessonDialog.setToggleBtnUnChecked();
                }
            });
        }
    }


    // 레슨 dialog 전체 플레이
    public void setAndPlayAllDialog(final Map<Integer, byte[]> audiosDialog) {

        setMediaPlayerByte(audiosDialog.get(dialogIndex));

        playMediaPlayer(false);

        if(mediaPlayer != null) {

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    dialogIndex++;

                    mp.reset();

                    // 마지막 오디오 재생 끝났으면
                    if(dialogIndex == audiosDialog.size()) {
                        dialogIndex = 0;

                        lessonDialog = LessonDialog.newInstance();
                        lessonDialog.setPlayBtn(View.VISIBLE, View.GONE);

                    } else {
                        setAndPlayAllDialog(audiosDialog);
                    }
                }
            });
        }
    }


    public void playMediaPlayer(boolean isReading) {
        if(mediaPlayer == null || isSet == false) {
            System.out.println("Should never be here!: inside playMediaPlayer but mediaPlayer is not set");
        } else {

            isPlaying = true;
            mediaPlayer.start();

            if(isReading) {

                seek(playingPosition);

                if(seekBar != null) {
                    thread = new MediaPlayerThread(seekBar);

                } else {
                    thread = new MediaPlayerThread();
                }

                thread.start();
            }
        }
    }


    public void stopMediaPlayer() {

        dialogIndex = 0; // 레슨 dialog 용

        if(thread != null) {
            thread.terminate();
        }

        if(mediaPlayer != null && mediaPlayer.isPlaying()) {

            // 미디어플레이어 완료이벤트 리셋
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                }
            });

            mediaPlayer.reset();
        }

        isPlaying = false;
    }


    public void pauseMediaPlayer() {

        if(thread != null) {
            thread.terminate();
        }

        if(mediaPlayer != null && mediaPlayer.isPlaying()) {
            playingPosition = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
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
}
