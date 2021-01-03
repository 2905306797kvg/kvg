package qst.com.servlet;

import qst.com.bean.Apply;
import qst.com.bean.Page;
import qst.com.bean.User;
import qst.com.dao.ApplyDAO;
import qst.com.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/ApplyVipServlet")
public class ApplyVipServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String method=request.getParameter("method");
        ApplyDAO dao=new ApplyDAO();
        if ("apply".equals(method)){
            //用户申请成为会员vip
            //获取当前登录用户
            User user= (User) request.getSession().getAttribute("USER");
            //根据用户ID搜索apply表，判断用户是否已经申请了（管理员未审批）
            Apply apply=dao.getApplyByUserId(user.getUserId());
            if (apply!=null){
                PrintWriter writer=response.getWriter();
                writer.write("<script>");
                writer.write("alert('您已经申请过了，正在等待管理员审批！');");
                writer.write("window.location.href='user_index.jsp'");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }else {
                //用户没有申请过会员
                //获取当前时间
                Date date=new Date();
                //插入该条申请，并保存
                dao.addApply(user.getUserId(),user.getUserName(),date);
                PrintWriter writer=response.getWriter();
                writer.write("<script>");
                writer.write("alert('申请成功，请等待管理员审批！');");
                writer.write("window.location.href='user_index.jsp'");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }
        }else if ("vetting".equals(method)){
            //管理员审批,导出vip申请用户集合
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
            String sql = "select count(*) from apply";
            totalRows = dao.getApplyCount(sql);

            //5、起始行 startRow
            Integer startRow = (currentPage - 1) * pageSize;

            StringBuffer sqlRow = new StringBuffer("SELECT\n " +
                    "apply.applyId applyId,\n" +
                    "apply.userId userId,\n" +
                    "apply.userName userName,\n" +
                    "apply.applyTime applyTime \n" +
                    "FROM \n" +
                    "apply ");
            sqlRow.append(" limit ").append(startRow).append(",").append(pageSize);

            //把所有交易记录查询出来
            List<Apply> applyList = dao.getApplyList(sqlRow.toString());
            request.setAttribute("applyList", applyList);

            Page applyPage = new Page(pageSize, currentPage, totalRows, applyList);
            request.setAttribute("applyPage", applyPage);

            //跳转到VIP申请表界面
            request.getRequestDispatcher("manager/userApplyVIP.jsp").forward(request,response);
        }else if ("agree".equals(method)){
            //管理员同意用户的vip请求
            //获取管理员审批的用户ID
            String userId=request.getParameter("applyUserId");
            //更新user表数据
            new UserDAO().updateUserVipByUserId(Integer.valueOf(userId));
            //删除apply表中该用户的申请行
            dao.deleteApply(Integer.valueOf(userId));
            //处理完成，调回vip申请表界面
            request.getRequestDispatcher("ApplyVipServlet?method=vetting").forward(request,response);
        }else if ("oppose".equals(method)){
            //管理员不批准用户的VIP请求
            //获取管理员审批的用户ID
            String userId=request.getParameter("applyUserId");
            //删除apply表中该用户的申请行
            dao.deleteApply(Integer.valueOf(userId));
            //处理完成，调回vip申请表界面
            request.getRequestDispatcher("ApplyVipServlet?method=vetting").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
