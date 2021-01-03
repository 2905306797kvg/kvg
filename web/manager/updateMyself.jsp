<%@ page import="qst.com.bean.User" %>
<%@ page import="qst.com.util.DataUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../app.jsp"%>
<html>
<head>
    <title>更新个人信息</title>
</head>
<body bgcolor="ffe4c4">
<%
    User user= (User) request.getSession().getAttribute("USER");
%>
<form action="<%=appPath%>/MyselfInformationServlet?method=update" method="post">
    <input type="hidden" name="userUpdateId" value="<%=user.getUserId()%>">
    <input type="hidden" name="userPassWord" value="<%=user.getUserPassWord()%>">
    <input type="hidden" name="userType" value="<%=user.getUserType()%>">
    <input type="hidden" name="userVip" value="<%=user.getUserVip()%>">
    <input type="hidden" name="userRoomId" value="<%=user.getRoomId()%>">
    <div align="center">
        <table border="1px" cellpadding="10px">
            <tr align="center">
                <td><img src="<%=appPath%>/image/QQ.jpg" width="400px" height="400px" alt=""></td>
            </tr>
            <tr align="center" bgcolor="#808080">
                <td>姓名</td>
            </tr>
            <tr align="center">
                <%if (user.getUserName()!=null){%>
                    <td><input type="text" style="text-align: center" name="userName" value="<%=user.getUserName()%>"></td>
                <%}else {%>
                    <td><input type="text" style="text-align: center" name="userName" value="李浩义"></td>
                <%}%>
            </tr>
            <tr align="center" bgcolor="#808080">
                <td>性别</td>
            </tr>
            <tr align="center">
                <%if (user.getUserSex()!=null){%>
                    <%if (user.getUserSex().equalsIgnoreCase("男")){%>
                        <td><input type="radio" name="userSex" value="男" checked>男<input type="radio" name="userSex" value="女">女</td>
                    <%}else if (user.getUserSex().equalsIgnoreCase("女")){%>
                        <td><input type="radio" name="userSex" value="男">男<input type="radio" name="userSex" value="女" checked>女</td>
                    <%}%>
                <%}else {%>
                    <td><input type="radio" name="userSex" value="男" checked>男<input type="radio" name="userSex" value="女">女</td>
                <%}%>
            </tr>
            <tr align="center" bgcolor="#808080">
                <td>出生日期</td>
            </tr>
            <tr align="center">
                <%if (user.getUserBirthday()!=null){%>
                    <td><input type="text" style="text-align: center" name="userBirthday" value="<%=DataUtils.format(user.getUserBirthday())%>"></td>
                <%}else {%>
                    <td><input type="text" style="text-align: center" name="userBirthday" value="2020-01-01"></td>
                <%}%>
            </tr>
            <tr align="center" bgcolor="#808080">
                <td>邮箱（email）</td>
            </tr>
            <tr align="center">
                <%if (user.getUserEmail()==null){%>
                    <td><input type="text" style="text-align: center" name="userEmail" readonly="readonly" value="20202881961@qq.com"></td>
                <%}else {%>
                    <td><input type="text" style="text-align: center" name="userEmail" readonly="readonly" value="<%=user.getUserEmail()%>"></td>
                <%}%>
            </tr>
        </table>
    </div>
    <div align="center">
        <input type="submit" name="submit" value="确认修改">
    </div>
</form>
</body>
</html>
