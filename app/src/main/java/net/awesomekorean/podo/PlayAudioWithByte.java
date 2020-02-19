package net.awesomekorean.podo;

import android.annotation.TargetApi;
import android.media.MediaDataSource;
import android.os.Build;

import java.io.IOException;

@TargetApi(Build.VERSION_CODES.M)
public class PlayAudioWithByte extends MediaDataSource {

    private byte[] data = null;

    public void PlayAudioWithByte(byte[] data) {
        this.data = data;
    }

    @Override
    public int readAt(long position, byte[] buffer, int offset, int size) throws IOException {
        System.arraycopy(data, (int)position, buffer, offset, size);
        return size;
    }

    @Override
    public long getSize() throws IOException {
        return data.length;
    }

    @Override
    public void close() throws IOException {
        // Nothing to do here
    }}
