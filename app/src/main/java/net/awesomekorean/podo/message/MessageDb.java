package net.awesomekorean.podo.message;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MessageItems.class}, version = 1, exportSchema = false)
public abstract class MessageDb extends RoomDatabase {
    public abstract MessageDao messageDao();
}
