package net.awesomekorean.podo.message;

public class MessageItems {

    private int peopleImage;
    private String message;
    private String messageDate;
    private Boolean isNew;

    public int getPeopleImage() {
        return peopleImage;
    }

    public void setPeopleImage(int peopleImage) {
        this.peopleImage = peopleImage;
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

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean aNew) {
        isNew = aNew;
    }
}
