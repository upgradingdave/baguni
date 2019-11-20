package net.awesomekorean.podo.collection;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DATE_SYNC")
public class DateSyncEntity {

    @PrimaryKey
    public int id = 0; // Primary 때문에 만듦. 의미 없음.
    public String dateSync;

    // 초기화 생성자
    public DateSyncEntity() {
        this.dateSync = "0000-00-00 00:00:00";
    }

    public String getDateSync() {
        return dateSync;
    }

    public void setDateSync(String dateSync) {
        this.dateSync = dateSync;
    }
}
