package qst.com.bean;

import java.util.Date;

public class Trade {
    private Integer tradeId;
    private Integer userId;
    private Integer roomId;
    private Integer roomPrice;
    private Integer liveTime;
    private Integer tradePrice;
    private Date tradeTime;

    public Trade() {
    }

    public Trade(Integer tradeId, Integer userId, Integer roomId, Integer roomPrice, Integer liveTime, Integer tradePrice, Date tradeTime) {
        this.tradeId = tradeId;
        this.userId = userId;
        this.roomId = roomId;
        this.roomPrice = roomPrice;
        this.liveTime = liveTime;
        this.tradePrice = tradePrice;
        this.tradeTime = tradeTime;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(Integer tradePrice) {
        this.tradePrice = tradePrice;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Integer getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Integer roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Integer getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(Integer liveTime) {
        this.liveTime = liveTime;
    }
}
