package qst.com.servlet;

import qst.com.bean.User;
import qst.com.dao.EvaluateDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = "/AddEvaluateServlet")
public class AddEvaluateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取前台提交的文本域信息
        String evaluateContent=request.getParameter("evaluateContent");
        //获取当前登录用户ID
        Integer userId= (Integer) request.getSession().getAttribute("USERID");
        //获取当前登录用户
        User user= (User) request.getSession().getAttribute("USER");
        //获取当前时间
        Date date=new Date();
        //插入该条评论，并保存
        new EvaluateDAO().addEvaluate(userId,user.getUserName(),evaluateContent,date);
        PrintWriter writer=response.getWriter();
        writer.write("<script>");
        writer.write("alert('评论成功');");
        writer.write("window.location.href='EvaluateServlet'");
        writer.write("</script>");
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
