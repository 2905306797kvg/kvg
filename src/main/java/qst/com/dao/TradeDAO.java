package qst.com.dao;

import qst.com.bean.Trade;
import qst.com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class TradeDAO {
    /**
     * 用户订房成功，插入一条trade记录，保存
     * @param userId
     * @param roomId
     * @param roomPrice
     * @param liveTime
     * @param money
     * @param date
     */
    public void sava(Integer userId, Integer roomId,Integer roomPrice,Integer liveTime, Integer money, Date date) {
        Connection conn= DBUtil.getConnection();
        PreparedStatement pst=null;
        String sql="insert into trade(userId,roomId,roomPrice,liveTime,tradePrice,tradeTime) values(?,?,?,?,?,?)";
        //数据类型转换
        Date dates=new java.sql.Date(date.getTime());
        try {
            pst=conn.prepareStatement(sql);
            pst.setInt(1,userId);
            pst.setInt(2,roomId);
            pst.setInt(3,roomPrice);
            pst.setInt(4,liveTime);
            pst.setInt(5,money);
            pst.setDate(6, (java.sql.Date) dates);
            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.closeJDBC(null,pst,conn);
        }
    }

    /**
     * 获取交易记录集合
     * @param sql
     * @param args
     * @return
     */
    public List<Trade> getTradeList(String sql,Object...args) {
        List<Trade> tradeList=DBUtil.getList(Trade.class,sql,args);
        return tradeList;
    }

    /**
     * 查询交易记录数量
     * @param sql
     * @param args
     * @return
     */
    public Integer getTradeCount(String sql,Object...args) {
        Integer count=DBUtil.getCount(sql,args);
        return count;
    }
}
