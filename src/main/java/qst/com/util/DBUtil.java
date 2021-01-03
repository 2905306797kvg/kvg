package qst.com.util;

import org.apache.commons.beanutils.BeanUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//数据库连接及相关操作
public class DBUtil {
    static String uri="jdbc:mysql://localhost:3306/hotel";
    static String user="root";
    static String password="123456";

    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * 数据库连接
     * @return 返回一个数据库连接对象
     */
    public static Connection getConnection(){
        Connection conn=null;
        try{
            conn= DriverManager.getConnection(uri,user,password);
            System.out.println("连接成功！");
        }catch (SQLException e){
            System.out.println("连接失败！");
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param rs 结果集
     * @param stmt 数据库语句
     * @param conn 数据库连接对象
     */
    public static void closeJDBC(ResultSet rs, Statement stmt, Connection conn){
        if (rs!=null){
            try {
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (stmt!=null){
            try {
                stmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断数据库中是否有登录所请求的email，password，userType
     *
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return 返回一个user对象
     */
    public static <T> T getSingleObj(Class<T> clazz, String sql,Object...args) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        T bean=null;
        conn=DBUtil.getConnection();
        try {
            ps=conn.prepareStatement(sql);
            if (args!=null && args.length>0){
                for (int i=0;i<args.length;i++){
                    ps.setObject(i+1,args[i]);
                }
            }
            rs=ps.executeQuery();

            //获取结果集元数据
            ResultSetMetaData rsmd=rs.getMetaData();
            //获取当前结果集列数
            int column=rsmd.getColumnCount();

            while (rs.next()){
                //key存放列名，values存放列值，for循环完成之后，rowMap存放一条记录
                Map<String,Object> rowMap=new HashMap<String, Object>();
                for (int i=1;i<=column;i++){
                    String columnName=rsmd.getColumnLabel(i);
                    Object columnValue=rs.getObject(columnName);
                    //判断查询出来的类的类型，如果是java.sql.Date转成java.util.Date
                    if (columnValue instanceof java.sql.Date){
                        java.sql.Date date=(java.sql.Date)columnValue;
                        columnValue=new java.util.Date(date.getTime());
                    }
                    rowMap.put(columnName,columnValue);
                }
                //创建一个user对象，给这个user对象属性赋值
                bean=clazz.newInstance();

                //赋值
                for (Map.Entry<String,Object> entry:rowMap.entrySet()){
                    String propertyName=entry.getKey();
                    Object propertyValue=entry.getValue();
                    BeanUtils.setProperty(bean,propertyName,propertyValue);
                }
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            closeJDBC(rs,ps,conn);
        }
        return bean;
    }

    /**
     * 查询一共多少条记录
     *
     * @param sql
     * @param args
     * @return 返回记录总个数
     */
    public static Integer getCount(String sql, Object...args) {
        Connection conn=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        Integer count=null;
        try {
            conn=DBUtil.getConnection();
            pst=conn.prepareStatement(sql);
            if (args!=null && args.length>0){
                for (int i=0;i<args.length;i++){
                    pst.setObject(i+1,args[i]);
                }
            }
            rs=pst.executeQuery();
            while (rs.next()){
                count=rs.getInt(1);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            closeJDBC(rs,pst,conn);
        }
        return count;
    }

    /**
     * 查询所有房间信息
     *
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return 房间信息列表
     */
    public static <T> List<T>  getList(Class<T> clazz, String sql, Object...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<T>();
        try{
            conn = getConnection();
            ps = conn.prepareStatement(sql);

            if(args != null){
                for(int i=0; i<args.length; i++){
                    ps.setObject(i+1,args[i]);
                }
            }

            rs = ps.executeQuery();
            //获取结果集数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取当前结果集的列数
            int colnum = rsmd.getColumnCount();

            while(rs.next()){
                //key存放列名，value存放列值
                Map<String,Object> rowMap = new HashMap<String, Object>();
                for(int i=1; i<=colnum; i++){
                    String columnName = rsmd.getColumnLabel(i);
                    Object columnValue = rs.getObject(columnName);

                    if(columnValue instanceof java.sql.Date){
                        java.sql.Date date = (java.sql.Date)columnValue;
                        columnValue = new java.util.Date(date.getTime());
                    }

                    rowMap.put(columnName,columnValue);
                }

                T bean = clazz.newInstance();

                for(Map.Entry<String,Object> entry : rowMap.entrySet()){
                    String propertyName = entry.getKey();
                    Object propertyValue = entry.getValue();

                    //获取给propertyName属性赋值的set方法
                    /*String methodName = "set"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);

                    //对象获取自己类对应的字节码文件
                    Method method = clazz.getMethod(methodName,propertyValue.getClass());
                    method.invoke(bean,propertyValue);*/

                    BeanUtils.setProperty(bean,propertyName,propertyValue);
                }

                list.add(bean);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeJDBC(rs,ps,conn);
        }
        return list;
    }

    /**
     * 更新操作
     * @param sql
     * @param args
     * @return
     */
    public static boolean update(String sql, Object...args) {
        Connection conn=null;
        PreparedStatement ps=null;
        Integer count=0;

        try {
            conn=DBUtil.getConnection();
            ps=conn.prepareStatement(sql);
            if (args!=null && args.length>0){
                for (int i=0;i<args.length;i++){
                    //判断当前数据类型是不是java.util.Date,转换成java.sql.Date
                    if (args[i] instanceof java.util.Date){
                        java.util.Date date=(java.util.Date)args[i];
                        args[i]=new java.sql.Date(date.getTime());
                    }
                    ps.setObject(i+1,args[i]);
                }
            }
            count=ps.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            closeJDBC(null,ps,conn);
        }
        return count>0?true:false;
    }
}
