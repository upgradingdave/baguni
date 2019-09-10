package net.awesomekorean.baguni.writing;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface WritingDao {

    @Query("SELECT * FROM WRITINGENTITY")
    LiveData<List<WritingEntity>> getAll();

    @Insert
    void insert(WritingEntity writingEntity);

    @Update
    void update(WritingEntity writingEntity);

    @Delete
    void delete(WritingEntity writingEntity);

}
