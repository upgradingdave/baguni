package net.awesomekorean.baguni.writing;

public class WritingItems {

    private int id;
    private String firstDate;
    private String lastDate;
    private String letters;
    private String article;
    private Boolean isCorrected; // 교정여부 표시 on/off, 4:off, 0:on

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

    public void setIsCorrected(Boolean b) {
        isCorrected = b;
    }
}
