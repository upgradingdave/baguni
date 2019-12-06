package net.awesomekorean.podo.collection;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CollectionDao {

    @Query("SELECT * FROM COLLECTION ORDER BY dateNew DESC")
    LiveData<List<CollectionEntity>> getAll();

    @Query("SELECT * FROM COLLECTION WHERE guid = :guid")
    CollectionEntity getByGuid(String guid);

    @Query("SELECT * FROM COLLECTION ORDER BY RANDOM() LIMIT 1")
    CollectionEntity getRandom();

    @Query("SELECT * FROM COLLECTION ORDER BY dateNew DESC LIMIT 1 OFFSET :index")
    CollectionEntity getDesc(int index);

    //@Query("SELECT * FROM COLLECTIONENTITY LIMIT people20 OFFSET 0")

    @Query("SELECT COUNT(*) FROM COLLECTION WHERE deleted != 1")
    int getCount();


    // << 동기화 부분 >>

    // 신규 추가된 아이템들 가져오기
    @Query("SELECT * FROM COLLECTION WHERE dateEdit > :dateSync")
    List<CollectionEntity> getUploadItems(String dateSync);

    // 마지막 동기화 날짜 가져오기
    @Query("SELECT dateSync FROM DATE_SYNC")
    LiveData<String> getDateSync();

    // 마지막 동기화 날짜 업데이트하기
    @Update

    void updateDateSync(DateSyncEntity dateSyncEntity);



    @Insert
    void initDateSync(DateSyncEntity dateSyncEntity);


    @Query("DELETE FROM COLLECTION")
    void deleteAll();


    @Insert
    void insert(CollectionEntity collectionEntity);

    @Update
    void update(CollectionEntity collectionEntity);

    @Delete
    void delete(CollectionEntity collectionEntity);

    // 개발용으로 만듦. 개발 끝나면 삭제할 것
    @Query("DELETE FROM DATE_SYNC")
    void deleteDateSync();
}

