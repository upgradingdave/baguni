package net.awesomekorean.podo.lesson;

public class LessonItems {

    private String title;
    private String subTitle;
    private int lessonImage;
    private Boolean isSpecial = true;
    private Boolean isLock = true;
    private Boolean isCompleted = true;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getLessonImage() {
        return lessonImage;
    }

    public void setLessonImage(int lessonImage) {
        this.lessonImage = lessonImage;
    }

    public Boolean getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Boolean special) {
        isSpecial = special;
    }

    public Boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(Boolean lock) {
        isLock = lock;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
