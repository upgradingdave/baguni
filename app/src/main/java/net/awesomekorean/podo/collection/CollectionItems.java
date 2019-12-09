package net.awesomekorean.podo.collection;

public class CollectionItems {

    private String guid;

    private String front;
    private String back;
    private String audio;

    private boolean isChecked = false;
    private boolean isVisible = false;

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public void setFront(String collection) {
        this.front = collection;
    }

    public void setBack(String collection) {
        this.back = collection;
    }

    public String getFront() {
        return this.front;
    }

    public String getBack() {
        return this.back;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean getChecked() {
        return isChecked;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean getVisible() {
        return isVisible;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String id) {
        this.guid = id;
    }


    // 검색 기능을 위해 equals 재정의
    @Override
    public boolean equals(Object obj) {

        boolean result = false;

        if(this.front.contains((CharSequence) obj) || this.back.contains((CharSequence) obj)) {
            result = true;
        }

        return result;
    }
}
