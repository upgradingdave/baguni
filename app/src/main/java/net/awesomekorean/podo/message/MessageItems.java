package net.awesomekorean.podo.message;

import com.google.firebase.firestore.FieldValue;

import net.awesomekorean.podo.UnixTimeStamp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageItems {

    private String message;
    private Long messageDate;

    private int senderImage;
    private boolean isNew;

    public MessageItems(){
        this.messageDate = UnixTimeStamp.getTimeNow();
        this.isNew = true;
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

    public Long getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Long messageDate) {
        this.messageDate = messageDate;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean aNew) {
        isNew = aNew;
    }
}