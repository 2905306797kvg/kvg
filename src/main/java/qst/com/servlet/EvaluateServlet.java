package qst.com.servlet;

import qst.com.bean.Evaluate;
import qst.com.bean.Page;
import qst.com.dao.EvaluateDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/EvaluateServlet")
public class EvaluateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取用户类型
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

        //3、一共多少行数据 totalRows
        Integer totalRows = 0;
        EvaluateDAO dao=new EvaluateDAO();
        String sql = "select count(*) from evaluate";
        totalRows = dao.getEvaluateCount(sql);

        //5、起始行 startRow
        Integer startRow = (currentPage - 1) * pageSize;

        StringBuffer sqlRow = new StringBuffer("SELECT\n " +
                "evaluate.evaluateId evaluateId,\n" +
                "evaluate.userId userId,\n" +
                "evaluate.userName userName,\n" +
                "evaluate.evaluateContent evaluateContent,\n" +
                "evaluate.evaluateTime evaluateTime \n" +
                "FROM \n" +
                "evaluate ");
        sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);

        //把所有交易记录查询出来
        List<Evaluate> evaluateList = dao.getEvaluateList(sqlRow.toString());
        request.setAttribute("evaluateList", evaluateList);

        Page evaluatePage = new Page(pageSize, currentPage, totalRows, evaluateList);
        request.setAttribute("evaluatePage", evaluatePage);

        if ("manager".equals(applicant)) {
            //请求转发到房间管理界面roomAdjust.jsp
            request.getRequestDispatcher("manager/evaluate.jsp").forward(request, response);
        } else if ("user".equals(applicant)) {
            //请求转发到用户查看房间界面lookRoom.jsp
            request.getRequestDispatcher("user/evaluate.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
