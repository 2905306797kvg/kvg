package qst.com.servlet;

import qst.com.bean.User;
import qst.com.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/MyselfInformationServlet")
public class MyselfInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String method=request.getParameter("method");
        if ("findByUserId".equals(method)){
            //通过用户ID找到该用户，并跳转到更新个人信息界面进行个人信息更新
            String userId=request.getParameter("userId");
            UserDAO dao=new UserDAO();
            Integer id=Integer.valueOf(userId);
            User user=dao.getUserById(id);
            request.getSession().setAttribute("USER",user);
            //user修改个人信息界面和manager修改个人信息界面一样且代码一样，所以跳到相同界面，无需判断用户类型
            request.getRequestDispatcher("manager/updateMyself.jsp").forward(request, response);
        }else if ("update".equals(method)){
            String id=request.getParameter("userUpdateId");
            User user=requestDateObj(request);
            user.setUserId(Integer.valueOf(id));
            UserDAO dao=new UserDAO();
            //更新用户信息
            boolean flag=dao.updateUser(user);
            if (flag){
                //更新成功,返回个人信息界面
                request.getSession().setAttribute("USER",user);//manage_index.jsp
                //获取用户类型，跳到相应个人信息界面
                String applicant= (String) request.getSession().getAttribute("applicant");
                if ("manager".equals(applicant)) {
                    request.getRequestDispatcher("manager/myself.jsp").forward(request, response);
                }else if ("user".equals(applicant)){
                    request.getRequestDispatcher("user/myself.jsp").forward(request, response);
                }
            }
        }
    }

    private User requestDateObj(HttpServletRequest request){
        String userEmail=request.getParameter("userEmail");
        String userPassWord=request.getParameter("userPassWord");
        String userType=request.getParameter("userType");
        String userSex=request.getParameter("userSex");
        String userBirthday=request.getParameter("userBirthday");
        String userVip=request.getParameter("userVip");
        String roomId=request.getParameter("userRoomId");
        String userName=request.getParameter("userName");
        Integer id = null;
        //数据转换
        Date birthday=null;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            birthday=sdf.parse(userBirthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!roomId.equals("null") && roomId.length()>0){
            id=Integer.valueOf(roomId);
        }
        User user=new User(null,userEmail,userPassWord,userType, birthday,userVip,id,userName,userSex);
        return user;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
