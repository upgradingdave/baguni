package net.awesomekorean.podo.teachers;

import net.awesomekorean.podo.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class RequestWriting {
    private String guid;
    private String userEmail;
    private String userName;
    private String article;
    private String dateRequest;
    private String teacherName;
    private Boolean isCorrected = false;

    private String correction;
    private String teacherFeedback;
    private String dateCorrection;

    private String studentFeeback;


    public RequestWriting(String article, String teacherName) {
        this.guid = UUID.randomUUID().toString();
        this.userEmail = MainActivity.userEmail;
        this.userName = MainActivity.userName;
        this.article = article;
        this.teacherName = teacherName;
        this.dateRequest = new SimpleDateFormat("yyyy.MM.dd").format(new Date());

    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getCorrection() {
        return correction;
    }

    public void setCorrection(String correction) {
        this.correction = correction;
    }

    public String getTeacherFeedback() {
        return teacherFeedback;
    }

    public void setTeacherFeedback(String teacherFeedback) {
        this.teacherFeedback = teacherFeedback;
    }

    public String getStudentFeeback() {
        return studentFeeback;
    }

    public void setStudentFeeback(String studentFeeback) {
        this.studentFeeback = studentFeeback;
    }

    public String getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(String dateRequest) {
        this.dateRequest = dateRequest;
    }

    public String getDateCorrection() {
        return dateCorrection;
    }

    public void setDateCorrection(String dateCorrection) {
        this.dateCorrection = dateCorrection;
    }

    public Boolean getCorrected() {
        return isCorrected;
    }

    public void setCorrected(Boolean corrected) {
        isCorrected = corrected;
    }
}
