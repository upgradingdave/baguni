package net.awesomekorean.baguni.collection;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CollectionDao {

    @Query("SELECT * FROM CollectionTable")
    List<CollectionTable> getAllCollections();

    @Query("SELECT front FROM CollectionTable WHERE id IN (:position)")
    String getFrontById(int position);

    @Query("SELECT back FROM CollectionTable WHERE id IN (:position)")
    String getBackById(int position);

    @Query("SELECT id FROM CollectionTable ORDER BY id DESC LIMIT 1")
    int getLastId();

    @Query("DELETE FROM CollectionTable")
    public void deleteAll();

    //@Query("INSERT INTO CollectionTable VALUES (:front), (:back)")
    //public void addNewCollection(String front, String back);


    @Insert
    void insert(CollectionTable collectionTable);

    @Update
    void update(CollectionTable collectionTable);

    @Delete
    void delete(CollectionTable collectionTable);
}
