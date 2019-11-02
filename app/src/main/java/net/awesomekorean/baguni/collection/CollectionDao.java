package net.awesomekorean.baguni.collection;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
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

    //@Query("SELECT * FROM COLLECTIONENTITY LIMIT 20 OFFSET 0")

    @Query("SELECT COUNT(*) FROM COLLECTION")
    int getCount();



    // << 동기화 부분 >>

    // 신규 추가된 아이템들 가져오기
    @Query("SELECT * FROM COLLECTION WHERE dateSync is null")
    List<CollectionEntity> getNewItems();

    // 마지막 동기화 날짜 가져오기
    @Query("SELECT dateLastSync FROM DATE_LASTSYNC")
    String getDateLastSync();

    // 수정된 아이템들 가져오기
    @Query("SELECT * FROM COLLECTION WHERE isEdit = 1")
    List<CollectionEntity> getUpdateItems();


    @Insert
    void initLastSync(DateLastSyncEntity dateLastSyncEntity);

    @Update
    void updateLastSync(DateLastSyncEntity dateLastSyncEntity);


    @Query("DELETE FROM COLLECTION")
    void deleteAll();


    @Insert
    void insert(CollectionEntity collectionEntity);

    @Update
    void update(CollectionEntity collectionEntity);

    @Delete
    void delete(CollectionEntity collectionEntity);

    // 개발용으로 만듦. 개발 끝나면 삭제할 것
   @Query("DELETE FROM DATE_LASTSYNC")
    void deleteDateLastSync();

}
