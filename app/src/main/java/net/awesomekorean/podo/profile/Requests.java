package net.awesomekorean.podo.profile;

import java.util.List;

public class Requests {

    private String requestId;
    private Long requestDate;
    private Long answerDate;
    private boolean isCorrection; // true: correction, false: recording
    private int status; // 1:검토중, 2:교정됨, 99:거부됨

    private String writing;
    private List<String> guid;
    private List<String> front;
    private List<String> back;
    private List<String> audio;

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

    public List<String> getGuid() {
        return guid;
    }

    public List<String> getFront() {
        return front;
    }

    public List<String> getBack() {
        return back;
    }

    public List<String> getAudio() {
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

    public void setGuid(List<String> guid) {
        this.guid = guid;
    }

    public void setFront(List<String> front) {
        this.front = front;
    }

    public void setBack(List<String> back) {
        this.back = back;
    }

    public void setAudio(List<String> audio) {
        this.audio = audio;
    }
}
