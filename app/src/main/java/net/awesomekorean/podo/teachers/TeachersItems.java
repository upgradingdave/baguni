package net.awesomekorean.podo.teachers;


import android.graphics.drawable.Drawable;

public class TeachersItems {

    // 오디오는 어떻게 해야할까?
    private Drawable picture;
    private String isAvailable;
    private String name;
    private String tag;

    public Drawable getPicture() {
        return picture;
    }

    public void setPicture(Drawable picture) {
        this.picture = picture;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(String isAvailable) {
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
}
