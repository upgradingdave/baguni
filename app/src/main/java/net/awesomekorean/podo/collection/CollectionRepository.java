package net.awesomekorean.podo.collection;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    public LiveData<String> getDateSync() {
        return db.collectionDao().getDateSync();
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
                String dateNow = getDateNow();
                entity.setAudio(audio);
                entity.setDateNew(dateNow);
                entity.setDateEdit(dateNow);
                db.collectionDao().insert(entity);
                return null;
            }
        }.execute();
    }

    public void insertDownloadItem (final CollectionEntity entity) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.collectionDao().insert(entity);
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

    public void updateLastSync(final DateSyncEntity entity) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.collectionDao().updateDateSync(entity);
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

    // 개발용으로 만듦. 개발 끝나면 삭제할 것
    public void deleteDateLastSync() {

        new AsyncTask<Void, Void,Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.collectionDao().deleteDateSync();
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

    public void initDateLasySync() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                DateSyncEntity lastSync = new DateSyncEntity();
                db.collectionDao().initDateSync(lastSync);
                return null;
            }
        }.execute();
    }
/*
    public void downloadItemsFromDb(final CollectionEntity entityInDB) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                String guid = entityInDB.getGuid();

                CollectionEntity entityInRoom = db.collectionDao().getByGuid(guid);

                // DB 에서 가져온 아이템이 폰에 없으면 저장
                if(entityInRoom == null) {
                    db.collectionDao().insert(entityInDB);
                    System.out.println("Downloaded to device: "+entityInDB.getFront());

                }else {
                    try {
                        Date dateEditInRoom = stringToDate(entityInRoom.getDateEdit());
                        Date dateEditInDb = stringToDate(entityInDB.getDateEdit());

                        // Room 날짜가 빠르면 Room 업데이트
                        if(dateEditInRoom.getTime() < dateEditInDb.getTime()) {
                            db.collectionDao().update(entityInDB);
                            System.out.println("Updated to device: "+entityInRoom.getFront());
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }
        }.execute();
    }


    // 동기화 작업 시작
    public void syncCollections() {
        new AsyncTask<Void, Void, List<Collection>>() {
            @Override
            protected List<Collection> doInBackground(Void... voids) {
                // 마지막 동기화 날짜 가져오기
                String dateSync = db.collectionDao().getDateSync();
                if (dateSync == null) { // 날짜가 없으면 초기화 0000-00-00 00:00:00
                    DateSyncEntity lastSync = new DateSyncEntity();
                    db.collectionDao().initDateSync(lastSync);
                    dateSync = db.collectionDao().getDateSync();
                }

                // 업로드 할 아이템 가져오기
                List<CollectionEntity> getUploadItems = db.collectionDao().getUploadItems(dateSync);

                // 마지막으로 동기한 날짜 보내서 다운로드 아이템 받기
                RetrofitConnection retrofitConnection = new RetrofitConnection();
                Call<List<CollectionEntity>> downloadItems = retrofitConnection.service().sendDateSync(dateSync);
                downloadItems.enqueue(new Callback<List<CollectionEntity>>() {
                    @Override
                    public void onResponse(Call<List<CollectionEntity>> call, Response<List<CollectionEntity>> response) {
                        if (response.isSuccessful()) {
                            System.out.println("Found items to DOWNLOAD");
                            List<CollectionEntity> entities = response.body();

                            for (CollectionEntity entityInDB : entities) {
                                downloadItemsFromDb(entityInDB);
                            }

                        } else {
                            System.out.println("No item to DOWNLOAD");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CollectionEntity>> call, Throwable t) {
                        System.out.println("Connection Failed");
                    }
                });

                // 업로드 아이템 보내기
                if (getUploadItems.size() > 0) {
                    System.out.println("Found items to UPLOAD");
                    Call<List<CollectionEntity>> uploadItems = retrofitConnection.service().uploadItems(getUploadItems);
                    uploadItems.enqueue(new Callback<List<CollectionEntity>>() {
                        @Override
                        public void onResponse(Call<List<CollectionEntity>> call, Response<List<CollectionEntity>> response) {
                            if (response.isSuccessful()) {
                                System.out.println("UPLOAD Succeed");
                            } else {
                                System.out.println("UPLOAD Failed");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<CollectionEntity>> call, Throwable t) {
                            System.out.println("Connection Failed");
                        }
                    });
                } else {
                    System.out.println("No item to UPLOAD");
                }

                // dateLastSync 업데이트하기
                DateSyncEntity dateSyncEntity = new DateSyncEntity();
                String dateNow = getDateNow();
                dateSyncEntity.setDateSync(dateNow);
                updateLastSync(dateSyncEntity);
                System.out.println("Updated the last synced date: " + dateNow);
                return null;
            }
        }.execute();
    }

*/
    public String getDateNow() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date time = new Date();
        String dateNow = format.format(time);
        return dateNow;
    }

    public Date stringToDate(String stringDate) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stringDate);
        return date;
    }

}
