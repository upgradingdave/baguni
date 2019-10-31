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

    public void insert (String front, String back) {

        final CollectionEntity entity = new CollectionEntity(front, back);

        new AsyncTask<Void, Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                // dateSync 에 날짜가 안 박혀있는 컬렉션. 즉 앱에서 생성한 컬렉션일 때
                if(entity.getDateSync()==null) {
                    System.out.println("THIS IS ITEM MADE IN APP");
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date time = new Date();
                    String dateNow = format.format(time);
                    entity.setDateNew(dateNow);
                }
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

    public void editByGuid(final String guid, final String front, final String back) {

        new AsyncTask<Void, Void, CollectionEntity>() {
            @Override
            protected CollectionEntity doInBackground(Void... voids) {
                CollectionEntity entity = db.collectionDao().getByGuid(guid);
                entity.setFront(front);
                entity.setBack(back);
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


    // 동기화 작업
    public void syncCollections(final int userId) {
        new AsyncTask<Void, Void, List<Collection>>() {
            @Override
            protected List<Collection> doInBackground(Void... voids) {
                // 마지막 동기화 날짜 가져오기
                String dateLastSync = db.collectionDao().getDateLastSync();
                if(dateLastSync==null) {
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
                            System.out.println("There are items to download");
                            List<CollectionEntity> entities = response.body();
                            for(CollectionEntity entity : entities) {
                                String front = entity.getFront();
                                String back = entity.getBack();
                                insert(front, back);
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
                List<Collection> newItems = collectionEntityToCollection(userId, db.collectionDao().getNewItems());
                System.out.println("DATESYNC : " + newItems.get(0).getDateSync());

                // DB 로 업로드하기
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
                            System.out.println("Date Last Sync Updated");

                        } else {
                            System.out.println("UPLOAD FAILED");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Collection>> call, Throwable t) {
                        System.out.println("CONNECTION FAILED");
                    }
                });





                /*
                // 기존 컬렉션 중에서 가장 마지막 itemId 가져오기
                Integer lastExistingItemId = db.collectionDao().getLastExistingItemId();
                System.out.println("Got last existing itemId : " + lastExistingItemId);

                // DB 의 컬렉션 중에서 가장 마지막 itemId 가져오기
                RetrofitConnection retrofitConnection = new RetrofitConnection();
                Call<Integer> lastItemIdInDB = retrofitConnection.service().getItem(userId, "last");
                lastItemIdInDB.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if(response.isSuccessful()) {
                            System.out.println("SUCCEED");
                        } else {
                            System.out.println("DB is empty.");
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        System.out.println("CONNECTION FAIL");
                    }
                });

                // 신규 컬렉션 업로드 하기
                List<Collection> newItems = collectionEntityToCollection(userId, db.collectionDao().getNewItems());
                Call<List<Collection>> uploadItems = retrofitConnection.service().uploadItems(newItems);
                uploadItems.enqueue(new Callback<List<Collection>>() {
                    @Override
                    public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
                        if(response.isSuccessful()) {
                            System.out.println("SUCCEED");
                        } else {
                            System.out.println("DB is empty.");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Collection>> call, Throwable t) {
                        System.out.println("CONNECTION FAIL");
                    }
                });

                //db.collectionDao().updateIsNewToFalse();
                System.out.println("Upload new items Complete");


                // 앱에 없는 컬렉션 다운로드 하기
                if (lastExistingItemId + 1 < lastItemIdInDB) {
                    System.out.println("Run Download");

                }else {
                    System.out.println("Synchronize finished");
                }

                // 수정된 컬렉션 DB 에 반영하기
                */
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
            collection.setGuid(entity.getGuid());
            collection.setFront(entity.getFront());
            collection.setBack(entity.getBack());
            collection.setAudio(entity.getAudio());
            collection.setDateNew(entity.getDateNew());
            collection.setDateEdit(entity.getDateEdit());
            collections.add(collection);
        }

        return collections;
    }

    public List<CollectionEntity> collectionToCollectionEntity(List<Collection> collections) {

        List<CollectionEntity> entities = new ArrayList<>();

        for(int i=0; i<collections.size(); i++){
            CollectionEntity entity = new CollectionEntity();
            Collection collection = collections.get(i);
            entity.setGuid(collection.getGuid());
            entity.setFront(collection.getFront());
            entity.setBack(collection.getBack());
            entity.setAudio(collection.getAudio());
            entity.setDateNew(collection.getDateNew());
            entity.setDateEdit(collection.getDateEdit());
            entity.setDateSync(collection.getDateSync());
            entities.add(entity);
        }
        return entities;
    }
}
