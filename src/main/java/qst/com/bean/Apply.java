package qst.com.bean;

import java.util.Date;

public class Apply {
    private Integer applyId;
    private Integer userId;
    private String userName;
    private Date applyTime;

    public Apply() {
    }

    public Apply(Integer applyId, Integer userId, String userName, Date applyTime) {
        this.applyId = applyId;
        this.userId = userId;
        this.userName = userName;
        this.applyTime = applyTime;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
}
