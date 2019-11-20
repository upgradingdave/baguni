package net.awesomekorean.podo.collection;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CollectionEntity.class, DateSyncEntity.class}, version = 1, exportSchema = false)
public abstract class CollectionDb extends RoomDatabase {

    public abstract CollectionDao collectionDao();
}
