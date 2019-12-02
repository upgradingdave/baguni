package net.awesomekorean.podo.teachers;


import android.graphics.drawable.Drawable;

public class TeachersItems {

    // 오디오는 어떻게 해야할까?
    private Drawable picture;
    private Boolean isAvailable;
    private String name;
    private String tag;
    private Boolean isChecked = false;

    public Drawable getPicture() {
        return picture;
    }

    public void setPicture(Drawable picture) {
        this.picture = picture;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean checked) {
        isChecked = checked;
    }
}
