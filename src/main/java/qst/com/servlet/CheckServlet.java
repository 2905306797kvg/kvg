package qst.com.servlet;

import qst.com.bean.Room;
import qst.com.bean.User;
import qst.com.dao.RoomDAO;
import qst.com.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/CheckServlet")
public class CheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String method=request.getParameter("method");
        if ("findRoomByUser".equals(method)){
            //通过user表查找其roomID是否为空，为空用户未曾订房
            User user= (User) request.getSession().getAttribute("USER");
            if (user.getRoomId()==null || user.getRoomId()==0){
                PrintWriter writer=response.getWriter();
                writer.write("<script>");
                writer.write("alert('您还没有订房，请先订房！');");
                writer.write("window.location.href='RoomServlet'");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }else {
                //通过roomID找到room
                Room room=new RoomDAO().getRoomById(user.getRoomId());
                request.setAttribute("room",room);
                request.getRequestDispatcher("user/check.jsp").forward(request,response);
            }
        }else if ("check".equals(method)){
            //用户确定退房
            User user= (User) request.getSession().getAttribute("USER");
            //根据roomID更新房间状态
            Room room=new RoomDAO().getRoomById(user.getRoomId());
            new RoomDAO().updateRoomState(room,0);
            //更新用户表的roomID值
            new UserDAO().updateRoomPurchase(user,0);
            User user1=new UserDAO().getUserById(user.getUserId());
            request.getSession().setAttribute("USER",user1);
            PrintWriter writer=response.getWriter();
            writer.write("<script>");
            writer.write("alert('退房成功！');");
            writer.write("window.location.href='user_index.jsp'");
            writer.write("</script>");
            writer.flush();
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
