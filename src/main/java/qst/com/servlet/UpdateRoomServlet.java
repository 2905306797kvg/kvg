package qst.com.servlet;

import qst.com.bean.Room;
import qst.com.bean.User;
import qst.com.dao.RoomDAO;
import qst.com.dao.TradeDAO;
import qst.com.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = "/UpdateRoomServlet")
public class UpdateRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String method=request.getParameter("method");
        if ("findById".equals(method)){
            //通过roomID从数据库中找到该room
            String roomId=request.getParameter("roomId");
            RoomDAO dao=new RoomDAO();
            Integer id=Integer.valueOf(roomId);
            Room room=dao.getRoomById(id);
            request.setAttribute("room",room);
            request.getRequestDispatcher("manager/updateRoom.jsp").forward(request,response);
        }else if ("update".equals(method)){
            //更新房间信息
            String id=request.getParameter("roomUpdateId");
            Room room=requestDateObj(request);
            room.setRoomId(Integer.valueOf(id));
            RoomDAO dao=new RoomDAO();
            //更新房间
            boolean flag=dao.updateRoom(room);
            if (flag){
                //更新成功,返回房间管理界面
                request.getRequestDispatcher("RoomServlet").forward(request,response);
            }
        }else if ("purchaseRoom".equals(method)){
            //用户订房操作，首先根据房价ID找到该房间
            String roomId=request.getParameter("purchaseRoomId");
            RoomDAO dao=new RoomDAO();
            Room room=dao.getRoomById(Integer.valueOf(roomId));
            request.setAttribute("room",room);
            request.getRequestDispatcher("user/purchaseRoom.jsp").forward(request,response);
        }else if ("purchase".equals(method)){
            //用户确认订房，获取入住时长以及房间ID
            String timeLong=request.getParameter("roomTime");
            String roomId=request.getParameter("purchaseId");
            //判断用户是否已经订过房
            User user= (User) request.getSession().getAttribute("USER");
            if (user.getRoomId()!=null && user.getRoomId()!=0){
                PrintWriter writer=response.getWriter();
                writer.write("<script>");
                writer.write("alert('您已经订过房间！请先退房再进行订房！');");
                writer.write("window.location.href='RoomServlet'");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }else {
                //没有订过房，更新user表数据和room表数据、插入一条trade（交易记录）数据,并保存
                //更新user
                new UserDAO().updateRoomPurchase(user,Integer.valueOf(roomId));
                User user1=new UserDAO().getUserById(user.getUserId());
                request.getSession().setAttribute("USER",user1);
                //更新room状态(根据roomID获取room)
                Room room=new RoomDAO().getRoomById(Integer.valueOf(roomId));
                new RoomDAO().updateRoomState(room,2);

                //获取当前时间
                Date date=new Date();
                //计算交易金额
                Integer money=Integer.valueOf(timeLong)*room.getRoomPrice();
                new TradeDAO().sava(user.getUserId(),room.getRoomId(),room.getRoomPrice(),Integer.valueOf(timeLong),money,date);
                PrintWriter writer=response.getWriter();
                writer.write("<script>");
                writer.write("alert('订房成功！');");
                writer.write("window.location.href='RoomServlet'");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }
        }
    }

    /**
     *将请求修改的房间封装成一个对象
     *
     * @param request
     * @return 一个room对象
     */
    private Room requestDateObj(HttpServletRequest request){
        String roomNumber=request.getParameter("roomNumber");
        String roomSize=request.getParameter("roomSize");
        String roomPrice=request.getParameter("roomPrice");
        String roomType=request.getParameter("roomType");
        String roomState=request.getParameter("roomState");

        Room room=new Room(null,roomNumber,Integer.valueOf(roomSize),Integer.valueOf(roomPrice),Integer.valueOf(roomState),roomType);
        return room;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
