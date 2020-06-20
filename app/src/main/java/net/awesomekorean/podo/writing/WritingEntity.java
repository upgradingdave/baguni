package net.awesomekorean.podo.writing;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import net.awesomekorean.podo.MainActivity;
import net.awesomekorean.podo.SharedPreferencesInfo;
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
    private int letters;
    private Long writingDate;
    private int status = 0; // 0:교정요청없음, 1:검토중, 2:교정됨, 99:거부됨
    private String userToken;

    private String teacherName;
    private String teacherId;
    private Long dateRequest;

    private String correction;
    private String teacherFeedback;
    private Long dateAnswer;

    private String studentFeedback;

    @Ignore
    public WritingEntity() {}

    public WritingEntity(String contents, int letters) {
        this.guid = UUID.randomUUID().toString();
        this.contents = contents;
        this.letters = letters;
        this.writingDate = UnixTimeStamp.getTimeNow();
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE WRITINGENTITY ADD COLUMN userToken VARCHAR(100)");
        }
    };


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

    public int getLetters() {
        return letters;
    }

    public void setLetters(int letters) {
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

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
