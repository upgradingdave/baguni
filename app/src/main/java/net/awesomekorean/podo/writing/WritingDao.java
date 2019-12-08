package net.awesomekorean.podo.writing;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WritingDao {

    @Query("SELECT * FROM WRITINGENTITY ORDER BY writingDate DESC")
    LiveData<List<WritingEntity>> getAll();

    @Query("SELECT * FROM WRITINGENTITY WHERE guid = :guid")
    WritingEntity getByGuid(String guid);

    @Insert
    void insert(WritingEntity writingEntity);

    @Update
    void update(WritingEntity writingEntity);

    @Delete
    void delete(WritingEntity writingEntity);

}
