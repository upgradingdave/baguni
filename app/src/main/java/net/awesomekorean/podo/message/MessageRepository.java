package net.awesomekorean.podo.message;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import net.awesomekorean.podo.writing.WritingEntity;

import java.util.List;

public class MessageRepository {

    private String DB_NAME = "message_db";
    private MessageDb db;

    public MessageRepository(Context context) {
        db = Room.databaseBuilder(context, MessageDb.class, DB_NAME).build();
    }

    public LiveData<List<MessageItems>> getAll() {
        return db.messageDao().getAll();
    }

    public void insert(final MessageItems entity) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.messageDao().insert(entity);
                return null;
            }
        }.execute();
    }
}
