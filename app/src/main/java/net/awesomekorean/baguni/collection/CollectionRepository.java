package net.awesomekorean.baguni.collection;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import net.awesomekorean.baguni.MainCollection;
import net.awesomekorean.baguni.webService.Collection;
import net.awesomekorean.baguni.webService.RetrofitConnection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionRepository {

    private String DB_NAME = "collection_db";
    private CollectionDb db;

    private String front; // 플래시 카드 앞면
    private String back; // 플래시 카드 뒷면

    public CollectionRepository(Context context) {

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
    public void insert (String front, String back) {

        final CollectionEntity entity = new CollectionEntity(front, back);

        new AsyncTask<Void, Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date time = new Date();
                String dateNow = format.format(time);
                entity.setDateNew(dateNow);
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

    public void updateLastSync(final DateLastSyncEntity entity) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.collectionDao().updateLastSync(entity);
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

    public void deleteByGuid(final String guid) {

        new AsyncTask<Void, Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.collectionDao().delete(db.collectionDao().getByGuid(guid));
                return null;
            }
        }.execute();
    }

    // 개발용으로 만듦. 개발 끝나면 삭제할 것
    public void deleteDateLastSync() {

        new AsyncTask<Void, Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.collectionDao().deleteDateLastSync();
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
                entity.setIsEdit(1);
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


    // 동기화 작업 시작
    public void syncCollections(final int userId) {
        new AsyncTask<Void, Void, List<Collection>>() {
            @Override
            protected List<Collection> doInBackground(Void... voids) {
                // 마지막 동기화 날짜 가져오기
                String dateLastSync = db.collectionDao().getDateLastSync();
                if(dateLastSync==null) { // 날짜가 없으면 초기화 0000-00-00 00:00:00
                    DateLastSyncEntity lastSync = new DateLastSyncEntity();
                    db.collectionDao().initLastSync(lastSync);
                    dateLastSync = db.collectionDao().getDateLastSync();
                }

                // 마지막으로 동기한 날짜 보내서 다운로드 아이템 받기
                RetrofitConnection retrofitConnection = new RetrofitConnection();
                Call<List<CollectionEntity>> downloadItems = retrofitConnection.service().sendSyncDates(dateLastSync);
                downloadItems.enqueue(new Callback<List<CollectionEntity>>() {
                    @Override
                    public void onResponse(Call<List<CollectionEntity>> call, Response<List<CollectionEntity>> response) {
                        if(response.isSuccessful()) {
                            System.out.println("Found items to download");
                            // !!! insert 하기 전에 guid 를 먼저 확인해보고 일치하는 아이템이 있으면 update 하기!!!
                            List<CollectionEntity> entities = response.body();
                            for(CollectionEntity entity : entities) {
                                insert(entity);
                            }

                        } else {
                            System.out.println("No item to download");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CollectionEntity>> call, Throwable t) {
                        System.out.println("Connection Failed");
                    }
                });

                // 업로드 안 한(dateSync 에 날짜 없는) 컬렉션 가져오기
                uploadItems(userId);
                return null;
            }
        }.execute();
    }

    // DB 에 업로드 하기
    public void uploadItems(final int userId) {
        new AsyncTask<Void, Void, List<CollectionEntity>>() {
            @Override
            protected List<CollectionEntity> doInBackground(Void... voids) {
                // dateSync 에 날짜 없는 아이템 찾기
                List<CollectionEntity> newItems = db.collectionDao().getNewItems();
                return newItems;
            }

            @Override
            protected void onPostExecute(List<CollectionEntity> entities) {
                List<Collection> newItems = collectionEntityToCollection(userId, entities);
                if(newItems.size()!=0) {
                    // DB 로 업로드하기
                    RetrofitConnection retrofitConnection = new RetrofitConnection();
                    Call<List<Collection>> uploadItems = retrofitConnection.service().uploadItems(newItems);
                    uploadItems.enqueue(new Callback<List<Collection>>() {
                        @Override
                        public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
                            if (response.isSuccessful()) {
                                System.out.println("UPLOAD SUCCEED");

                                // 싱크날짜 도장 찍힌 컬렉션을 ROOM 에 업데이트하기
                                List<Collection> collections = response.body();
                                List<CollectionEntity> entities = collectionToCollectionEntity(collections);
                                for(CollectionEntity entity :entities) {
                                    update(entity);
                                }

                                DateLastSyncEntity dateLastSyncEntity = new DateLastSyncEntity();
                                dateLastSyncEntity.setDateLastSync(entities.get(0).getDateSync());
                                updateLastSync(dateLastSyncEntity);
                                System.out.println("Updated the last synced date: " + entities.get(0).getDateSync() );

                            } else {
                                System.out.println("UPLOAD FAILED");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Collection>> call, Throwable t) {
                            System.out.println("CONNECTION FAILED");
                        }
                    });
                } else {
                    System.out.println("No item to upload");
                }
                updateItems(userId);
            }
        }.execute();
    }

    // 수정한 아이템 DB에 업데이트 하기
    public void updateItems(final int userId) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                List<Collection> collections = collectionEntityToCollection(userId, db.collectionDao().getUpdateItems());
                if(collections.size() != 0) {
                    RetrofitConnection retrofitConnection = new RetrofitConnection();
                    Call<List<Collection>> updateItems = retrofitConnection.service().updateItems(collections);
                    updateItems.enqueue(new Callback<List<Collection>>() {
                        @Override
                        public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
                            if(response.isSuccessful()) {
                                System.out.println("UPDATE SUCCEED");

                                // 싱크날짜 도장 찍힌 컬렉션을 ROOM 에 업데이트하기
                                List<Collection> collections = response.body();
                                List<CollectionEntity> entities = collectionToCollectionEntity(collections);
                                for(CollectionEntity entity :entities) {
                                    update(entity);
                                }

                                DateLastSyncEntity dateLastSyncEntity = new DateLastSyncEntity();
                                dateLastSyncEntity.setDateLastSync(entities.get(0).getDateSync());
                                updateLastSync(dateLastSyncEntity);
                                System.out.println("Updated the last synced date: " + entities.get(0).getDateSync() );

                            }else {
                                System.out.println("UPDATE FAILED");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Collection>> call, Throwable t) {
                            System.out.println("CONNECTION FAILED");
                        }
                    });
                } else {
                    System.out.println("No item to UPDATE");
                }
                return null;
            }
        }.execute();
    }


    public List<Collection> collectionEntityToCollection(int userId, List<CollectionEntity> entities) {

        List<Collection> collections = new ArrayList<>();

        for(int i=0; i<entities.size(); i++){
            Collection collection = new Collection();
            CollectionEntity entity = entities.get(i);
            collection.setUserId(userId);
            collection.setItemId(entity.getItemId());
            collection.setGuid(entity.getGuid());
            collection.setFront(entity.getFront());
            collection.setBack(entity.getBack());
            collection.setAudio(entity.getAudio());
            collection.setDateNew(entity.getDateNew());
            collections.add(collection);
        }

        return collections;
    }

    public List<CollectionEntity> collectionToCollectionEntity(List<Collection> collections) {

        List<CollectionEntity> entities = new ArrayList<>();

        for(int i=0; i<collections.size(); i++){
            CollectionEntity entity = new CollectionEntity();
            Collection collection = collections.get(i);
            entity.setItemId(collection.getItemId());
            entity.setGuid(collection.getGuid());
            entity.setFront(collection.getFront());
            entity.setBack(collection.getBack());
            entity.setAudio(collection.getAudio());
            entity.setDateNew(collection.getDateNew());
            entity.setDateSync(collection.getDateSync());
            entities.add(entity);
        }
        return entities;
    }
}
