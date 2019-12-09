package net.awesomekorean.podo.message;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM MESSAGEITEMS")
    LiveData<List<MessageItems>> getAll();

    @Insert
    void insert(MessageItems messageItems);
}
