package net.awesomekorean.baguni.writing;

public class WritingItems {

    private String date;
    private int letters;
    private String article;
    private int isCorrected = 4; // 교정여부 표시 on/off, 4:off, 0:on

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

    public int getIsCorrected() {
        return isCorrected;
    }

    public void setIsCorrected(int corrected) {
        isCorrected = corrected;
    }
}
