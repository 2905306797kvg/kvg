package qst.com.bean;

import java.util.Date;

public class Evaluate {
    private Integer evaluateId;
    private Integer userId;
    private String userName;
    private String evaluateContent;
    private Date evaluateTime;

    public Evaluate() {
    }

    public Evaluate(Integer evaluateId, Integer userId, String userName, String evaluateContent, Date evaluateTime) {
        this.evaluateId = evaluateId;
        this.userId = userId;
        this.userName = userName;
        this.evaluateContent = evaluateContent;
        this.evaluateTime = evaluateTime;
    }

    public Integer getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Integer evaluateId) {
        this.evaluateId = evaluateId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public Date getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
