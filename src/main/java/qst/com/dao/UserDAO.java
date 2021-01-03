package qst.com.dao;

import qst.com.bean.User;
import qst.com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO {
    /**
     * email验证是否被注册过
     *
     * @param email
     * @return true未被注册  false被注册了
     */
    public boolean isExistEmail(String email) {
        Connection conn= DBUtil.getConnection();
        PreparedStatement pst=null;
        ResultSet rs=null;
        String sql="select * from user where userEmail=?";
        try {
            pst=conn.prepareStatement(sql);
            pst.setString(1,email);
            rs=pst.executeQuery();
            if (rs.next())
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.closeJDBC(rs,pst,conn);
        }
        return false;
    }

    /**
     * 保存注册用户信息
     *
     * @param email
     * @param passWord
     * @param userType
     */
    public void save(String email, String passWord, String userType, String userVip) {
        Connection conn=DBUtil.getConnection();
        PreparedStatement pst=null;
        String sql="insert into user(userEmail,userPassWord,userType,userVip) values(?,?,?,?)";
        try {
            pst=conn.prepareStatement(sql);
            pst.setString(1,email);
            pst.setString(2,passWord);
            pst.setString(3,userType);
            pst.setString(4,userVip);
            pst.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.closeJDBC(null,pst,conn);
        }
    }

    /**
     * 根据email、password、userType从数据库查询此人，并且返回该user对象
     *
     * @param email
     * @param passWord
     * @param userType
     * @return 一个user对象
     */
    public User getUserByEmail_passWord_userType(String email, String passWord, String userType) {

        String sql="select * from user where userEmail=? and userPassWord=? and userType=?";
        return DBUtil.getSingleObj(User.class,sql,email,passWord,userType);
    }

    /**
     * 根据用户ID找到该用户
     *
     * @param id
     * @return 返回一个user对象
     */
    public User getUserById(Integer id) {
        String sql="select \n"+
                "userId userId,\n"+
                "userEmail userEmail,\n"+
                "userPassWord userPassWord,\n"+
                "userType userType,\n"+
                "userSex userSex,\n"+
                "userBirthday userBirthday,\n"+
                "userVip userVip,\n"+
                "roomId roomId,\n"+
                "userName userName \n"+
                "from \n"+
                "user where userId=?";

        return DBUtil.getSingleObj(User.class,sql,id);
    }

    /**
     * 更新个人信息
     *
     * @param user
     * @return
     */
    public boolean updateUser(User user) {
        String sql="update user set \n" +
                "userEmail= ?,\n" +
                "userPassWord= ?,\n" +
                "userType= ?,\n" +
                "userSex= ?,\n" +
                "userBirthday= ?,\n" +
                "userVip= ?,\n" +
                "roomId= ?,\n" +
                "userName= ?\n" +
                "where userId= ? ";
        boolean flag=DBUtil.update(sql,user.getUserEmail(),user.getUserPassWord(),user.getUserType(),user.getUserSex(),user.getUserBirthday(),user.getUserVip(),user.getRoomId(),user.getUserName(),user.getUserId());
        return flag;
    }

    /**
     * 用户订房，更新用户所订房间ID
     * @param user
     * @param roomId
     * @return
     */
    public boolean updateRoomPurchase(User user,Integer roomId){
        String sql="update user set roomId=? where userId=?";
        boolean flag=DBUtil.update(sql,roomId,user.getUserId());
        return flag;
    }

    /**
     * 查询vip用户个数
     * @param sql
     * @param args
     * @return
     */
    public Integer getUserVipCount(String sql, Object...args) {
        Integer count=DBUtil.getCount(sql,args);
        return count;
    }

    /**
     * 返回vip用户集合
     * @param sql
     * @param args
     * @return
     */
    public List<User> getVipUserList(String sql, Object...args) {
        List<User> userList=DBUtil.getList(User.class,sql,args);
        return userList;
    }

    /**
     * 用户申请vip成功，更新数据库user表对应用户数据
     * @param userId
     * @return
     */
    public boolean updateUserVipByUserId(Integer userId) {
        String sql="update user set userVip=? where userId=?";
        String userVip="是";
        boolean flag=DBUtil.update(sql,userVip,userId);
        return flag;
    }
}
