package net.awesomekorean.podo.profile;

public class Requests {

    private String requestId;
    private Long requestDate;
    private Long answerDate;
    private boolean isCorrection; // true: correction, false: recording
    private int status; // 1:검토중, 2:교정됨, 99:거부됨

    private String writing;
    private String[] front;
    private String[] back;
    private String[] audio;

    public Requests() {
        this.status = 1;
    }

    public String getRequestId() {
        return requestId;
    }

    public Long getRequestDate() {
        return requestDate;
    }

    public Long getAnswerDate() {
        return answerDate;
    }

    public boolean getIsCorrection() {
        return isCorrection;
    }

    public int getStatus() {
        return status;
    }

    public String getWriting() {
        return writing;
    }

    public String[] getFront() {
        return front;
    }

    public String[] getBack() {
        return back;
    }

    public String[] getAudio() {
        return audio;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setRequestDate(Long requestDate) {
        this.requestDate = requestDate;
    }

    public void setAnswerDate(Long answerDate) {
        this.answerDate = answerDate;
    }

    public void setIsCorrection(boolean correction) {
        isCorrection = correction;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    public void setFront(String[] front) {
        this.front = front;
    }

    public void setBack(String[] back) {
        this.back = back;
    }

    public void setAudio(String[] audio) {
        this.audio = audio;
    }
}
