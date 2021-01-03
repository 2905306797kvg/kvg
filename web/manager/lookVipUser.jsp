<%@ page import="java.util.List" %>
<%@ page import="qst.com.bean.User" %>
<%@ page import="qst.com.bean.Page" %>
<%@ page import="qst.com.util.DataUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../app.jsp"%>
<html>
<head>
    <title>会员信息列表</title>
</head>
<body bgcolor="ffe4c4">
<%
    List<User> userList= (List<User>) request.getAttribute("userList");
    Page userPage= (Page) request.getAttribute("userPage");
%>
<div align="center">
    <table border="1px" cellpadding="10px">
        <thead align="center" bgcolor="#808080">
        <td>用户 ID</td>
        <td>用户姓名</td>
        <td>性别</td>
        <td>出生日期</td>
        <td>所预定的房间ID</td>
        <td>用户邮箱</td>
        </thead>
        <%if (userList != null) {%>
        <%for (User user : userList) {%>
        <tbody align="center">
        <tr>
            <td><%=user.getUserId()%></td>
            <td><%=user.getUserName()%></td>
            <td><%=user.getUserSex()%></td>
            <td><%=DataUtils.format(user.getUserBirthday())%></td>
            <td><%=user.getRoomId()%></td>
            <td><%=user.getUserEmail()%></td>
        </tr>
        </tbody>
        <%}%>
        <%}%>
    </table>
</div>

<div align="center">
    <a href="javascript:beforePage(<%=userPage.getBeforePage()%>)">上一页</a>
    共<%=userPage.getTotalPages()%>页 共<%=userPage.getTotalRows()%>行 第<%=userPage.getCurrentPage()%>页
    <a href="javascript:afterPage(<%=userPage.getAfterPage()%>)">下一页</a>
    <a href="<%=appPath%>/manage_index.jsp">返回主界面</a>
</div>
<script>
    function beforePage(page) {
        window.location = "<%=appPath%>/VipUserServlet?currentPage=" + page + "&pageSize=<%=userPage.getPageSize()%>";
    }

    function afterPage(page) {
        window.location = "<%=appPath%>/VipUserServlet?currentPage=" + page + "&pageSize=<%=userPage.getPageSize()%>";
    }
</script>
</body>
</html>
