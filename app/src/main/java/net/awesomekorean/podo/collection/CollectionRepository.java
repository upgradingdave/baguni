package net.awesomekorean.podo.collection;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.google.firebase.analytics.FirebaseAnalytics;

import net.awesomekorean.podo.DownloadAudio;
import net.awesomekorean.podo.PlaySoundPool;
import net.awesomekorean.podo.UnixTimeStamp;

import java.util.List;

public class CollectionRepository {

    private String DB_NAME = "collection_db";
    private CollectionDb db;

    private String front; // 플래시 카드 앞면
    private String back; // 플래시 카드 뒷면

    PlaySoundPool playSoundPool;

    Context context;

    public CollectionRepository(Context context) {

        this.context = context;
        db = Room.databaseBuilder(context, CollectionDb.class, DB_NAME).build();
    }

    public LiveData<List<CollectionEntity>> getAll() {

        return db.collectionDao().getAll();
    }

    public void getCount() {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... voids) {
                int size = db.collectionDao().getCount();
                MainCollection.size = size;
                return size;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                MainCollection.collectionNo.setText(integer + " Collected");
            }
        }.execute();
    }

    // 서버에서 다운받은 컬렉션을 INSERT
    public void insert (final CollectionEntity entity) {
        new AsyncTask<Void, Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.collectionDao().insert(entity);
                return null;
            }
        }.execute();
    }

    // 앱에서 생성한 컬렉션을 INSERT (생성날짜 도장 찍고 넣기)
    public void insert (String front, String back, final String audio) {

        final CollectionEntity entity = new CollectionEntity(front, back);

        new AsyncTask<Void, Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                entity.setAudio(audio);
                db.collectionDao().insert(entity);

                // analytics 로그 이벤트 얻기
                FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
                Bundle bundle = new Bundle();
                firebaseAnalytics.logEvent("collection_save", bundle);

                return null;
            }
        }.execute();
    }

    public void insertDownloadItem (final Context context, final CollectionEntity entity) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.collectionDao().insert(entity);

                // 오디오가 있으면 다운로드 하기
                if(entity.getAudio() != null) {
                    String[] array = entity.getAudio().split("_");
                    String folder = "lesson/l_" + array[1];

                    DownloadAudio downloadAudio = new DownloadAudio(context, folder, entity.getAudio());
                    downloadAudio.downloadAudio();
                }

                return null;
            }
        }.execute();
    }

    public void delete (final CollectionEntity entity) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.collectionDao().delete(entity);
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

    public void setDeletedByGuid(final String guid) {

        new AsyncTask<Void, Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                CollectionEntity entity = db.collectionDao().getByGuid(guid);
                entity.setDateEdit(getDateNow());
                entity.setDeleted(1);
                db.collectionDao().update(entity);
                return null;
            }
        }.execute();
    }

    public void editByGuid(final String guid, final String front, final String back) {

        new AsyncTask<Void, Void, CollectionEntity>() {
            @Override
            protected CollectionEntity doInBackground(Void... voids) {
                CollectionEntity entity = db.collectionDao().getByGuid(guid);
                entity.setFront(front);
                entity.setBack(back);
                entity.setDateEdit(getDateNow());
                db.collectionDao().update(entity);
                return entity;
            }
        }.execute();
    }

    // 컬렉션 스터디에서 랜덤 플래시 카드 불러오기
    public void getRandomForStudy() {
        new AsyncTask<Void, Void, CollectionEntity>() {
            @Override
            protected CollectionEntity doInBackground(Void... voids) {
                CollectionEntity entity = db.collectionDao().getRandom();
                front = entity.getFront();
                back = entity.getBack();
                return entity;
            }

            @Override
            protected void onPostExecute(CollectionEntity entity) {
                setCollectionStudy(entity);
            }
        }.execute();
    }

    // 컬렉션 스터디에서 최신 컬렉션 순으로 불러오기
    public void getDescForStudy(final int index) {
        new AsyncTask<Void, Void, CollectionEntity>() {
            @Override
            protected CollectionEntity doInBackground(Void... voids) {
                CollectionEntity entity = db.collectionDao().getDesc(index);
                front = entity.getFront();
                back = entity.getBack();
                return entity;
            }

            @Override
            protected void onPostExecute(CollectionEntity entity) {
                setCollectionStudy(entity);
            }
        }.execute();
    }

    private void setCollectionStudy(CollectionEntity entity) {
        String studyAudio = entity.getAudio();
        if(studyAudio != null) {
            playSoundPool = new PlaySoundPool(context);
            playSoundPool.playSoundCollection(context.getFilesDir() + "/" + studyAudio);
            CollectionStudy.btnAudio.setVisibility(View.VISIBLE);
        } else {
            CollectionStudy.btnAudio.setVisibility(View.GONE);
        }
        CollectionStudy.studyAudio = studyAudio;
        CollectionStudy.studyFront.setText(front);
        CollectionStudy.studyBack.setText(back);
    }

    public Long getDateNow() {
        Long timeNow = UnixTimeStamp.getTimeNow();
        return timeNow;
    }
}
