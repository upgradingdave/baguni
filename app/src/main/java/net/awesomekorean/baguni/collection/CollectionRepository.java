package net.awesomekorean.baguni.collection;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class CollectionRepository {

    private String DB_NAME = "collection_db";
    private CollectionDb db;

    private String front; // 플래시 카드 앞면
    private String back; // 플래시 카드 뒷면

    public CollectionRepository(Context context) {

        db = Room.databaseBuilder(context, CollectionDb.class, DB_NAME).build();
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

    // 컬렉션 스터디에서 최신 단어부터 공부할 때 전체 카드 수를 얻기 위해 사용
    public void getCount() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                int size = db.collectionDao().getCount();
                CollectionStudy.size = size;
                return null;
            }
        }.execute();
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
                // 오디오 출력 추가할 것
                CollectionStudy.studyFront.setText(front);
                CollectionStudy.studyBack.setText(back);
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
                // 오디오 출력 추가할 것
                CollectionStudy.studyFront.setText(front);
                CollectionStudy.studyBack.setText(back);
            }
        }.execute();
    }

    public LiveData<List<CollectionEntity>> getAll() {

        return db.collectionDao().getAll();
    }
}
