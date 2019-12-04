package net.awesomekorean.podo.webService;

import com.google.firebase.Timestamp;

import java.util.Date;

public class User {

    private String name;
    private String email;
    private String password;
    private Timestamp dateSignUp;
    private Timestamp dateSignIn;

    public User() {}

    public User(String email, String password) {
        this.name = email.substring(0, email.lastIndexOf("@")); // 이메일의 @ 앞부분을 이름으로 자동 설정
        this.email = email;
        this.password = password;
        this.dateSignUp = new Timestamp(new Date());
        this.dateSignIn = new Timestamp(new Date());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getDateSignUp() {
        return dateSignUp;
    }

    public Timestamp getDateSignIn() {
        return dateSignIn;
    }
}
