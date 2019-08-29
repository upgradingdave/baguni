package net.awesomekorean.baguni.collection;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class CollectionRepository {

    private String DB_NAME = "collection_db";
    private CollectionDb db;

    public CollectionRepository(Context context) {

        db = Room.databaseBuilder(context, CollectionDb.class, DB_NAME).build();
    }

    public void insert (String front, String back) {

        CollectionEntity entity = new CollectionEntity(front, back);
        insert(entity);
    }

    public void insert(final CollectionEntity entity) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                db.collectionDao().insert(entity);
                return null;
            }
        }.execute();
    }

    public void deleteAll() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.collectionDao().deleteAll();
                return null;
            }
        }.execute();
    }

    public LiveData<List<CollectionEntity>> getAll() {

        return db.collectionDao().getAll();
    }

}
