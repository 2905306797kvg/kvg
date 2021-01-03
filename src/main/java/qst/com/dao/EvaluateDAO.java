package qst.com.dao;

import qst.com.bean.Evaluate;
import qst.com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class EvaluateDAO {
    /**
     * 查询评论个数
     * @param sql
     * @param args
     * @return
     */
    public Integer getEvaluateCount(String sql,Object...args) {
        Integer count=DBUtil.getCount(sql,args);
        return count;
    }

    /**
     * 获取数据库中所有评价集合
     * @param sql
     * @param args
     * @return
     */
    public List<Evaluate> getEvaluateList(String sql,Object...args) {
        List<Evaluate> evaluateList= DBUtil.getList(Evaluate.class,sql,args);
        return evaluateList;
    }

    /**
     * 用户添加一条评论，保存
     * @param userId
     * @param evaluateContent
     * @param date
     */
    public void addEvaluate(Integer userId, String userName, String evaluateContent, Date date) {
        Connection conn= DBUtil.getConnection();
        PreparedStatement pst=null;
        String sql="insert into evaluate(userId,userName,evaluateContent,evaluateTime) values(?,?,?,?)";
        //数据类型转换
        Date dates=new java.sql.Date(date.getTime());
        try {
            pst=conn.prepareStatement(sql);
            pst.setInt(1,userId);
            pst.setString(2,userName);
            pst.setString(3,evaluateContent);
            pst.setDate(4,(java.sql.Date) dates);
            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.closeJDBC(null,pst,conn);
        }
    }
}
