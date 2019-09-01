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

        final CollectionEntity entity = new CollectionEntity(front, back);

        new AsyncTask<Void, Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.collectionDao().insert(entity);

                return null;
            }
        }.execute();
    }

    public void update(final CollectionEntity entity) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.collectionDao().update(entity);
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

    public void deleteById(final int index) {

        new AsyncTask<Void, Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.collectionDao().delete(db.collectionDao().getById(index));
                return null;
            }
        }.execute();
    }

    public void editById(final int index, final String front, final String back) {

        new AsyncTask<Void, Void, CollectionEntity>() {
            @Override
            protected CollectionEntity doInBackground(Void... voids) {
                CollectionEntity entity = db.collectionDao().getById(index);
                return entity;
            }

            @Override
            protected void onPostExecute(CollectionEntity entity) {
                entity.setFront(front);
                entity.setBack(back);
                update(entity);
            }
        }.execute();
    }


    public LiveData<List<CollectionEntity>> getAll() {

        return db.collectionDao().getAll();
    }


/*
    public LiveData<CollectionEntity> getById(int index) {

        return db.collectionDao().getById(index);
    }
*/
}
