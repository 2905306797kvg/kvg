package qst.com.servlet;

import qst.com.bean.Page;
import qst.com.bean.Room;
import qst.com.bean.Page;
import qst.com.dao.RoomDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/RoomServlet")
public class RoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //1、每页多少行数据 pageSize
        String pageSizeStr=request.getParameter("pageSize");
        Integer pageSize=0;
        if (pageSizeStr!=null && pageSizeStr.length()>0){
            pageSize=Integer.valueOf(pageSizeStr);
        }else {
            pageSize=10;
        }

        //2、当前是第几页 currentPage
        String currentPageStr=request.getParameter("currentPage");
        Integer currentPage=0;
        if (currentPageStr!=null && currentPageStr.length()>0){
            currentPage=Integer.valueOf(currentPageStr);
        }else {
            currentPage=1;
        }

        //3、一共多少行数据 totalRows
        Integer totalRows=0;
        RoomDAO dao=new RoomDAO();
        String sql="select count(*) from room";
        totalRows=dao.getRoomCount(sql);

        //5、起始行 startRow
        Integer startRow=(currentPage-1)*pageSize;

        StringBuffer sqlRow=new StringBuffer("SELECT\n " +
                "room.roomId roomId,\n" +
                "room.roomNumber roomNumber,\n" +
                "room.roomSize roomSize,\n" +
                "room.roomPrice roomPrice,\n" +
                "room.roomState roomState,\n" +
                "room.roomType roomType \n" +
                "FROM \n" +
                "room ");
        sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);

        //把所有房间信息查询出来
        List<Room> roomList=dao.getRoomByPage(sqlRow.toString());
        request.setAttribute("roomList",roomList);

        Page roomPage =new Page(pageSize,currentPage,totalRows,roomList);
        request.setAttribute("roomPage", roomPage);

        //获取申请对象类型
        String applicant= (String) request.getSession().getAttribute("applicant");
        if ("manager".equals(applicant)){
            //请求转发到房间管理界面roomAdjust.jsp
            request.getRequestDispatcher("manager/roomAdjust.jsp").forward(request,response);
        }else if ("user".equals(applicant)){
            //请求转发到用户查看房间界面lookRoom.jsp
            request.getRequestDispatcher("user/lookRoom.jsp").forward(request,response);
        }

    }
}
