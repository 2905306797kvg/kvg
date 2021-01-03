package qst.com.servlet;

import qst.com.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        //获取前台提交的数据
        String email=request.getParameter("email");

        String type=request.getParameter("type");

        //判断是否使用Ajax请求进行email唯一性验证
        if ("emailAjaxValidate".equals(type)){
            UserDAO dao=new UserDAO();
            boolean flag=dao.isExistEmail(email);
            if (flag){
                out.print("邮箱已经被注册！");
            }else {
                out.print("邮箱可以使用！");
            }
        }else {
            String passWord=request.getParameter("passWord");
            String userType=request.getParameter("userType");
            String userVip="否";
            UserDAO dao=new UserDAO();
            boolean flag=dao.isExistEmail(email);
            if (flag){
                //邮箱已被注册，进行错误提示
                out.print("<script type='text/javascript'>");
                out.print("alert('邮箱已被注册，请重新输入！');");
                out.print("window.location='register.jsp';");
                out.print("</script>");
            }else {
                //邮箱未被注册，保存注册用户信息
                dao.save(email,passWord,userType,userVip);
                //注册成功，重定向到登录界面
                response.sendRedirect("login.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
