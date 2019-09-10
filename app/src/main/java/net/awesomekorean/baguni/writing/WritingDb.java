package net.awesomekorean.baguni.writing;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {WritingEntity.class}, version = 1, exportSchema = false)
public abstract class WritingDb extends RoomDatabase {
    public abstract WritingDao writingDao();
}
