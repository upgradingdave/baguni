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

    @Query("SELECT * FROM WRITINGENTITY ORDER BY id DESC")
    LiveData<List<WritingEntity>> getAll();

    @Query("SELECT * FROM WRITINGENTITY WHERE id = :index")
    WritingEntity getById(int index);

    @Insert
    void insert(WritingEntity writingEntity);

    @Update
    void update(WritingEntity writingEntity);

    @Delete
    void delete(WritingEntity writingEntity);

}
