package qst.com.dao;

import qst.com.bean.Apply;
import qst.com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ApplyDAO {
    /**
     * 根据用户ID查找apply
     * @param userId
     * @return
     */
    public Apply getApplyByUserId(Integer userId) {
        String sql="select * from apply where userId=?";
        return DBUtil.getSingleObj(Apply.class,sql,userId);
    }

    /**
     * 添加申请
     * @param userId
     * @param userName
     * @param date
     */
    public void addApply(Integer userId, String userName, Date date) {
        Connection conn= DBUtil.getConnection();
        PreparedStatement pst=null;
        String sql="insert into apply(userId,userName,applyTime) values(?,?,?)";
        //数据类型转换
        Date dates=new java.sql.Date(date.getTime());
        try {
            pst=conn.prepareStatement(sql);
            pst.setInt(1,userId);
            pst.setString(2,userName);
            pst.setDate(3,(java.sql.Date) dates);
            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.closeJDBC(null,pst,conn);
        }
    }

    /**
     * 查询申请用户的数量
     * @param sql
     * @param args
     * @return
     */
    public Integer getApplyCount(String sql,Object...args) {
        Integer count=DBUtil.getCount(sql,args);
        return count;
    }

    /**
     * 放回申请用户集合
     * @param sql
     * @param args
     * @return
     */
    public List<Apply> getApplyList(String sql,Object...args) {
        List<Apply> applyList=DBUtil.getList(Apply.class,sql,args);
        return applyList;
    }

    /**
     * 管理员处理用户ID为userID的VIP申请，删除该条申请
     * @param userId
     * @return
     */
    public void deleteApply(Integer userId) {
        Connection conn=DBUtil.getConnection();
        PreparedStatement pst=null;
        String sql="delete from apply where userId=?";
        try {
            pst=conn.prepareStatement(sql);
            pst.setInt(1,userId);
            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
