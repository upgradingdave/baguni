package net.awesomekorean.podo.collection;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.UnixTimeStamp;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = "COLLECTION")
public class CollectionEntity implements Serializable {

    @PrimaryKey
    @NonNull
    private String guid;
    private String userEmail;
    private String userName;
    private String front;
    private String back;
    private String audio;
    private Long dateNew;
    private Long dateEdit;
    private int deleted = 0; // 0=false, 1=true
    private boolean isChecked = false;
    private boolean isVisible = false;


    @Ignore
    public CollectionEntity() {

    }

    public CollectionEntity(String front, String back) {

        this.userEmail = MainActivity.userEmail;
        this.userName = MainActivity.userName;
        this.front = front;
        this.back = back;

        // GUID 생성하기
        this.guid = UUID.randomUUID().toString();

        // UNIX 타임스탬프 생성하기
        Long timeNow = UnixTimeStamp.getTimeNow();
        this.dateNew = timeNow;
        this.dateEdit = timeNow;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean visible) {
        isVisible = visible;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public Long getDateNew() {
        return dateNew;
    }

    public void setDateNew(Long dateNew) {
        this.dateNew = dateNew;
    }

    public Long getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(Long dateEdit) {
        this.dateEdit = dateEdit;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
