package net.awesomekorean.baguni.writing;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class WritingEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String firstDate;
    private String lastDate;
    private String letters;
    private String article;
    private Boolean isCorrected;

    public WritingEntity(String firstDate, String lastDate, String letters, String article) {
        this.firstDate = firstDate;
        this.lastDate = lastDate;
        this.letters = letters;
        this.article = article;
        this.isCorrected = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String date) {
        this.firstDate = date;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Boolean getIsCorrected() {
        return isCorrected;
    }

    public void setIsCorrected(Boolean corrected) {
        isCorrected = corrected;
    }
}
