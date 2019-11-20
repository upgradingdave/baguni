package net.awesomekorean.podo.webService;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String dateSignUp;
    private String dateSignIn;
    private String msgFromServer;

    public User(String email, String password) {
        this.name = email.substring(0, email.lastIndexOf("@")); // 이메일의 @ 앞부분을 이름으로 자동 설정
        this.email = email;
        this.password = password;
        this.dateSignUp = "NOW()";
        this.dateSignIn = "NOW()";
    }

    public int getId() {
        return id;
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

    public String getDateSignUp() {
        return dateSignUp;
    }

    public String getDateSignIn() {
        return dateSignIn;
    }

    public String getMsgFromServer() {
        return msgFromServer;
    }

    public void setMsgFromServer(String msgFromServer) {
        this.msgFromServer = msgFromServer;
    }
}
