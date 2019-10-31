package net.awesomekorean.baguni.collection;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "DATE_LASTSYNC")
public class DateLastSyncEntity {

    @PrimaryKey
    public int id = 0; // Primary 때문에 만듦. 의미 없음.
    public String dateLastSync;

    // 초기화 생성자
    public DateLastSyncEntity() {
        this.dateLastSync = "0000-00-00 00:00:00";
    }

    public String getDateLastSync() {
        return dateLastSync;
    }

    public void setDateLastSync(String dateLastSync) {
        this.dateLastSync = dateLastSync;
    }
}
