package qst.com.servlet;

import qst.com.bean.Page;
import qst.com.bean.User;
import qst.com.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/VipUserServlet")
public class VipUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String userVip="是";
        //1、每页多少行数据 pageSize
        String pageSizeStr = request.getParameter("pageSize");
        Integer pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.valueOf(pageSizeStr);
        } else {
            pageSize = 10;
        }

        //2、当前是第几页 currentPage
        String currentPageStr = request.getParameter("currentPage");
        Integer currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.valueOf(currentPageStr);
        } else {
            currentPage = 1;
        }

        //3、一共多少行数据 totalRows
        Integer totalRows = 0;
        UserDAO dao=new UserDAO();
        String sql = "select count(*) from user where userVip=?";
        totalRows = dao.getUserVipCount(sql,userVip);

        //5、起始行 startRow
        Integer startRow = (currentPage - 1) * pageSize;

        StringBuffer sqlRow = new StringBuffer("SELECT\n " +
                "user.userId userId,\n" +
                "user.userEmail userEmail,\n" +
                "user.userPassWord userPassWord,\n" +
                "user.userType userType,\n" +
                "user.userSex userSex,\n" +
                "user.userBirthday userBirthday,\n" +
                "user.userVip userVip,\n" +
                "user.roomId roomId,\n" +
                "user.userName userName \n" +
                "FROM \n" +
                "user where userVip=? ");
        sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);

        //把所有交易记录查询出来
        List<User> userList = dao.getVipUserList(sqlRow.toString(),userVip);
        request.setAttribute("userList", userList);

        Page userPage = new Page(pageSize, currentPage, totalRows, userList);
        request.setAttribute("userPage", userPage);

        //请求转发到查看会员信息列表界面
        request.getRequestDispatcher("manager/lookVipUser.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
