package net.awesomekorean.podo.qna;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import net.awesomekorean.podo.collection.CollectionDao;
import net.awesomekorean.podo.collection.CollectionEntity;

@Database(entities = {QnAEntity.class}, version = 1, exportSchema = false)
public abstract class QnADb extends RoomDatabase {

    public abstract QnADao qnADao();
}
