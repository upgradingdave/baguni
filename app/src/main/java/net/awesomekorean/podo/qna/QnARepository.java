package net.awesomekorean.podo.qna;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.google.firebase.analytics.FirebaseAnalytics;

import net.awesomekorean.podo.DownloadAudio;
import net.awesomekorean.podo.MediaPlayerManager;
import net.awesomekorean.podo.PlaySoundPool;
import net.awesomekorean.podo.UnixTimeStamp;
import net.awesomekorean.podo.collection.CollectionDb;
import net.awesomekorean.podo.collection.CollectionEntity;
import net.awesomekorean.podo.collection.CollectionStudy;
import net.awesomekorean.podo.collection.MainCollection;

import java.util.List;

public class QnARepository {

    private String DB_NAME = "qna_db";
    private QnADb db;

    Context context;

    public QnARepository(Context context) {

        this.context = context;
        db = Room.databaseBuilder(context, QnADb.class, DB_NAME).build();
    }

    public LiveData<List<QnAEntity>> getAll() {

        return db.qnADao().getAll();
    }


    public void deleteByGuid(final String guid) {

        new AsyncTask<Void, Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.qnADao().delete(db.qnADao().getByGuid(guid));
                return null;
            }
        }.execute();
    }


    public void insert (final QnAEntity entity) {
        new AsyncTask<Void, Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.qnADao().insert(entity);
                return null;
            }
        }.execute();
    }

    public void delete (final QnAEntity entity) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.qnADao().delete(entity);
                return null;
            }
        }.execute();
    }

    public void update(final QnAEntity entity) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.qnADao().update(entity);
                return null;
            }
        }.execute();
    }
}
