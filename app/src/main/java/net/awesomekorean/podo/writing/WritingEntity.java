package net.awesomekorean.podo.writing;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.UnixTimeStamp;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Entity
public class WritingEntity implements Serializable {

    @PrimaryKey
    @NonNull
    private String guid;
    private String userEmail;
    private String userName;
    private String contents;
    private String letters;
    private Long writingDate;
    private int status = 0; // 0:교정요청없음, 1:검토중, 2:교정됨, 99:거부됨

    private String teacherName;
    private String teacherId;
    private Long dateRequest;

    private String correction;
    private String teacherFeedback;
    private Long dateAnswer;

    private String studentFeedback;

    @Ignore
    public WritingEntity() {}

    public WritingEntity(String contents, String letters) {
        this.guid = UUID.randomUUID().toString();
        this.userEmail = MainActivity.userEmail;
        this.userName = MainActivity.userName;
        this.contents = contents;
        this.letters = letters;
        this.writingDate = UnixTimeStamp.getTimeNow();
    }


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Long getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(Long dateRequest) {
        this.dateRequest = dateRequest;
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

    public Long getDateAnswer() {
        return dateAnswer;
    }

    public void setDateAnswer(Long dateAnswer) {
        this.dateAnswer = dateAnswer;
    }

    public String getStudentFeedback() {
        return studentFeedback;
    }

    public void setStudentFeedback(String studentFeedback) {
        this.studentFeedback = studentFeedback;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getWritingDate() {
        return writingDate;
    }

    public void setWritingDate(Long date) {
        this.writingDate = date;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
