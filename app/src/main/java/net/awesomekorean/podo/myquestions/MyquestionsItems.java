package net.awesomekorean.podo.myquestions;

public class MyquestionsItems {

    private String category;
    private String summary;
    private String uploadDate;
    private Integer isConfirmed = null;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Integer getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(Integer confirmed) {
        isConfirmed = confirmed;
    }
}
