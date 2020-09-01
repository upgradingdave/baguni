package net.awesomekorean.podo.qna;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UnixTimeStamp;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = "QNA")
public class QnAEntity implements Serializable {

    @PrimaryKey
    @NonNull
    private String guid;
    private String userEmail;
    private String userName;
    private String question;
    private String answer;
    private Long dateQuestion;
    private Long dateAnswer;
    private int status; // 0:질문안함, 1:검토중, 2:답변완료, 99:거부됨
    private String userToken;


    @Ignore
    public QnAEntity() {

    }

    public QnAEntity(String question) {
        this.guid = UUID.randomUUID().toString();
        this.question = question;
        this.status = 0;
        this.dateQuestion = UnixTimeStamp.getTimeNow();
    }

    @NonNull
    public String getGuid() {
        return guid;
    }

    public void setGuid(@NonNull String guid) {
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getDateQuestion() {
        return dateQuestion;
    }

    public void setDateQuestion(Long dateQuestion) {
        this.dateQuestion = dateQuestion;
    }

    public Long getDateAnswer() {
        return dateAnswer;
    }

    public void setDateAnswer(Long dateAnswer) {
        this.dateAnswer = dateAnswer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
