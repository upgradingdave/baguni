package net.awesomekorean.baguni.writing;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class WritingEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String date;
    private int letters;
    private String article;
    private Boolean isCorrected;

    public WritingEntity(String date, int letters, String article) {
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLetters() {
        return letters;
    }

    public void setLetters(int letters) {
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
