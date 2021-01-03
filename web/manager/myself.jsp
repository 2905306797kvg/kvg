<%@ page import="qst.com.bean.User" %>
<%@ page import="qst.com.util.DataUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../app.jsp"%>
<html>
<head>
    <title>个人信息界面</title>
</head>
<body bgcolor="ffe4c4">
<%
    User user= (User) request.getSession().getAttribute("USER");
%>
<div align="center">
    <table border="1px" cellspacing="" cellpadding="10px">
        <tr align="center">
            <td><img src="<%=appPath%>/image/QQ.jpg" width="400" height="400" alt=""></td>
        </tr>
        <tr align="center" bgcolor="#808080">
            <td>姓名</td>
        </tr>
        <tr align="center">
            <%if (user.getUserName()==null){%>
            <td>待完善</td>
            <%}else {%>
            <td><%=user.getUserName()%></td>
            <%}%>
        </tr>
        <tr align="center" bgcolor="#808080">
            <td>性别</td>
        </tr>
        <tr align="center">
            <%if (user.getUserSex()==null){%>
            <td>待完善</td>
            <%}else {%>
            <td><%=user.getUserSex()%></td>
            <%}%>
        </tr>
        <tr align="center" bgcolor="#808080">
            <td>出生日期</td>
        </tr>
        <tr align="center">
            <%if (user.getUserBirthday()==null){%>
                <td>待完善</td>
            <%}else {%>
                <td><%=DataUtils.format(user.getUserBirthday())%></td>
            <%}%>
        </tr>
        <tr align="center" bgcolor="#808080">
            <td>邮箱（email）</td>
        </tr>
        <tr align="center">
            <%if (user.getUserEmail()==null){%>
            <td>待完善</td>
            <%}else {%>
            <td><%=user.getUserEmail()%></td>
            <%}%>
        </tr>
    </table>
</div>
<div align="center">
    <tr align="center">
        <td><a href="<%=appPath%>/MyselfInformationServlet?method=findByUserId&userId=<%=user.getUserId()%>">个人信息设置</a></td>
        <td><a href="<%=appPath%>/manage_index.jsp">返回主界面</a></td>
    </tr>
</div>
</body>
</html>
