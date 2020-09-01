package net.awesomekorean.podo.qna;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import net.awesomekorean.podo.writing.WritingEntity;

import java.util.List;

@Dao
public interface QnADao {

    @Query("SELECT * FROM QNA ORDER BY dateQuestion DESC")
    LiveData<List<QnAEntity>> getAll();

    @Query("SELECT * FROM QNA WHERE guid = :guid")
    QnAEntity getByGuid(String guid);

    @Insert
    void insert(QnAEntity qnAEntity);

    @Update
    void update(QnAEntity qnAEntity);

    @Delete
    void delete(QnAEntity qnAEntity);

}
