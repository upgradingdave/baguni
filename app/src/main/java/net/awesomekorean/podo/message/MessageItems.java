package net.awesomekorean.podo.message;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class MessageItems {

    @PrimaryKey(autoGenerate = true)
    int id;
    private String userEmail;
    private String message;
    private String messageDate;

    private int senderImage;
    private boolean isNew;

    @Ignore
    public void MessageItems(){}

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getSenderImage() {
        return senderImage;
    }

    public void setSenderImage(int senderImage) {
        this.senderImage = senderImage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean aNew) {
        isNew = aNew;
    }
}
