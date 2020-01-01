package net.awesomekorean.podo.message;

import com.google.firebase.firestore.FieldValue;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageItems {

    private String message;
    private String messageDate;

    private int senderImage;
    private boolean isNew;

    public MessageItems(){
        getDateNow();
        this.isNew = true;
    }

    public void getDateNow() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date time = new Date();
        messageDate = format.format(time);
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