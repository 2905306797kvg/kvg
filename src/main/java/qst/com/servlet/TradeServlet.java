package qst.com.servlet;

import qst.com.bean.Page;
import qst.com.bean.Trade;
import qst.com.dao.TradeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/TradeServlet")
public class TradeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取用户ID、用户类型
        Integer userId = (Integer) request.getSession().getAttribute("USERID");
        String applicant = (String) request.getSession().getAttribute("applicant");

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

        //5、起始行 startRow
        Integer startRow = (currentPage - 1) * pageSize;

        if ("manager".equals(applicant)) {
            //3、一共多少行数据 totalRows
            Integer totalRows = 0;
            TradeDAO dao = new TradeDAO();
            String sql = "select count(*) from trade";
            totalRows = dao.getTradeCount(sql);

            StringBuffer sqlRow = new StringBuffer("SELECT\n " +
                    "trade.tradeId tradeId,\n" +
                    "trade.userId userId,\n" +
                    "trade.roomId roomId,\n" +
                    "trade.roomPrice roomPrice,\n" +
                    "trade.liveTime liveTime,\n" +
                    "trade.tradePrice tradePrice,\n" +
                    "trade.tradeTime tradeTime \n" +
                    "FROM \n" +
                    "trade ");
            sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);

            //把所有交易记录查询出来
            List<Trade> tradeList = dao.getTradeList(sqlRow.toString());
            request.setAttribute("tradeList", tradeList);

            Page tradePage = new Page(pageSize, currentPage, totalRows, tradeList);
            request.setAttribute("tradePage", tradePage);
            //请求转发到房间管理界面roomAdjust.jsp
            request.getRequestDispatcher("manager/trade.jsp").forward(request, response);
        } else if ("user".equals(applicant)) {
            //3、一共多少行数据 totalRows
            Integer totalRows = 0;
            TradeDAO dao = new TradeDAO();
            String sql = "select count(*) from trade where userId=?";
            totalRows = dao.getTradeCount(sql, userId);

            StringBuffer sqlRow = new StringBuffer("SELECT\n " +
                    "trade.tradeId tradeId,\n" +
                    "trade.userId userId,\n" +
                    "trade.roomId roomId,\n" +
                    "trade.roomPrice roomPrice,\n" +
                    "trade.liveTime liveTime,\n" +
                    "trade.tradePrice tradePrice,\n" +
                    "trade.tradeTime tradeTime \n" +
                    "FROM \n" +
                    "trade where userId=? ");
            sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);

            //把所有消费记录查询出来
            List<Trade> tradeList = dao.getTradeList(sqlRow.toString(), userId);
            request.setAttribute("tradeList", tradeList);

            Page tradePage = new Page(pageSize, currentPage, totalRows, tradeList);
            request.setAttribute("tradePage", tradePage);
            //请求转发到用户查看房间界面lookRoom.jsp
            request.getRequestDispatcher("user/trade.jsp").forward(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
