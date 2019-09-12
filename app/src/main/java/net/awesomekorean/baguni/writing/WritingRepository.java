package net.awesomekorean.baguni.writing;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class WritingRepository {

    private String DB_NAME = "writing_db";
    private WritingDb db;

    public WritingRepository(Context context) {
        db = Room.databaseBuilder(context, WritingDb.class, DB_NAME).build();
    }

    public void insert (String firstDate, String letters, String article) {

        final WritingEntity entity = new WritingEntity(firstDate, "", letters, article);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.writingDao().insert(entity);
                return null;
            }
        }.execute();
    }

    public void editById(final int index, final String lastDate, final String letters, final String article) {

        new AsyncTask<Void, Void, WritingEntity>() {
            @Override
            protected WritingEntity doInBackground(Void... voids) {
                WritingEntity entity = db.writingDao().getById(index);
                entity.setLastDate(lastDate);
                entity.setLetters(letters);
                entity.setArticle(article);
                db.writingDao().update(entity);
                return null;
            }
        }.execute();
    }

    public void deleteById(final int index) {

        new AsyncTask<Void, Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.writingDao().delete(db.writingDao().getById(index));
                return null;
            }
        }.execute();
    }

    public LiveData<List<WritingEntity>> getAll() {
        return db.writingDao().getAll();
    }


}
