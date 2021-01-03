package qst.com.servlet;

import qst.com.bean.User;
import qst.com.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //获取前台提交的数据
        String email=request.getParameter("email");
        String passWord=request.getParameter("passWord");
        String userType=request.getParameter("userType");

        //根据前台提交的email、password、userType查询有无此人
        UserDAO dao=new UserDAO();
        User user=dao.getUserByEmail_passWord_userType(email,passWord,userType);

        //判断user是否为空
        if (user!=null){
            //用户登录成功将当前对象及其id保存到session对象中
            request.getSession().setAttribute("USER",user);
            Integer userId=user.getUserId();
            request.getSession().setAttribute("USERID",userId);
            //该用户登陆成功，根据其用户类型判断进入哪个主界面（用户、管理员）
            if (userType.equalsIgnoreCase("用户")){
                //跳转到用户主界面
                String applicant="user";
                request.getSession().setAttribute("applicant",applicant);
                response.sendRedirect("user_index.jsp");
            }else if (userType.equalsIgnoreCase("管理员")){
                //跳转到管理员主界面
                String applicant="manager";
                request.getSession().setAttribute("applicant",applicant);
                response.sendRedirect("manage_index.jsp");
            }
        }else {
            //登陆失败，错误信息提示，并重新登录
            PrintWriter writer=response.getWriter();
            writer.write("<script>");
            writer.write("alert('用户名或密码错误！');");
            writer.write("window.location.href='login.jsp'");
            writer.write("</script>");
            writer.flush();
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
