package qst.com.bean;

import java.util.Date;

public class User {
    private Integer userId;//用户ID
    private String userEmail;//用户账号
    private String userPassWord;//用户密码
    private String userType;//类型：管理员、用户
    private Date userBirthday;//用户出生日期
    private String userVip="否";//是否vip
    private Integer roomId;//已订的房间id
    private String userName;//姓名
    private String userSex;//性别

    public User() {
    }

    public User(Integer userId, String userEmail, String userPassWord, String userType, Date userBirthday, String userVip, Integer roomId, String userName, String userSex) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPassWord = userPassWord;
        this.userType = userType;
        this.userBirthday = userBirthday;
        this.userVip = userVip;
        this.roomId = roomId;
        this.userName = userName;
        this.userSex = userSex;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserVip() {
        return userVip;
    }

    public void setUserVip(String userVip) {
        this.userVip = userVip;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer userRoomId) {
        this.roomId = userRoomId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}
