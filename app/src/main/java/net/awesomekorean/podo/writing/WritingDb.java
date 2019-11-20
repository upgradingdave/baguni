package net.awesomekorean.podo.writing;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {WritingEntity.class}, version = 1, exportSchema = false)
public abstract class WritingDb extends RoomDatabase {
    public abstract WritingDao writingDao();
}
