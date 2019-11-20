package net.awesomekorean.podo.writing;

public class WritingItems {

    private int id;
    private String firstDate;
    private String lastDate;
    private String letters;
    private String article;
    private boolean isCorrected;

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

    public boolean getIsCorrected() {
        return isCorrected;
    }

    public void setIsCorrected(boolean b) {
        isCorrected = b;
    }
}
