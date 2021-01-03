package qst.com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        urlPatterns = {"/user/*"},servletNames = {"qst.com.servlet.CheckServlet","qst.com.servlet.EvaluateServlet","qst.com.servlet.MyselfInformationServlet",
        "qst.com.servlet.RoomServlet","qst.com.servlet.TradeServlet","qst.com.servlet.ApplyVipServlet"},
        initParams = {@WebInitParam(name = "loginPage",value = "login.jsp")},
        dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD}
)

public class UserFilter implements Filter {
    private String loginPage="login.jsp";
    public UserFilter(){}
    public void destroy() {
        this.loginPage=null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse resp=(HttpServletResponse) response;
        HttpSession session=req.getSession();
        //判断被拦截的请求用户是否处于登录状态
        if (session.getAttribute("USER")==null){
            //获取被拦截的请求及参数
            String requestPath=req.getRequestURI();
            if (req.getQueryString()!=null){
                requestPath+="?"+req.getQueryString();
            }
            //将请求地址保存到request对象并请求装发到登录页面
            req.setAttribute("requestPath",requestPath);
            request.getRequestDispatcher("/"+loginPage).forward(request,response);
            return;
        }else {
            chain.doFilter(request,response);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        //获取当前请求被拦截时转向的页面
        loginPage=config.getInitParameter("loginPage");
        if (null==loginPage){
            loginPage="login.jsp";
        }
    }

}
