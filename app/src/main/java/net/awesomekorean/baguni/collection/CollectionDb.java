package net.awesomekorean.baguni.collection;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {CollectionEntity.class, DateSyncEntity.class}, version = 1, exportSchema = false)
public abstract class CollectionDb extends RoomDatabase {

    public abstract CollectionDao collectionDao();
}
