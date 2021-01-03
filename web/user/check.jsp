<%@ page import="qst.com.bean.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../app.jsp"%>
<html>
<head>
    <title>用户退房</title>
</head>
<body bgcolor="ffe4c4">
<%
    Room room= (Room) request.getAttribute("room");
%>
<form action="<%=appPath%>/CheckServlet?method=check" method="post">
    <div align="center">
        <table border="1px" cellspacing="" cellpadding="10px">
            <tr align="center">
                <td><img src="<%=appPath%>/image/OIP.jpg"></td>
            </tr>
            <tr align="center" bgcolor="#808080">
                <td>您所订购的房间的信息</td>
            </tr>
            <tr align="center" bgcolor="#808080">
                <td>房间号码</td>
            </tr>
            <tr align="center">
                <td><%=room.getRoomNumber()%></td>
            </tr>
            <tr align="center" bgcolor="#808080">
                <td>房间大小</td>
            </tr>
            <tr align="center">
                <td><%=room.getRoomSize()%>平方米</td>
            </tr>
            <tr align="center" bgcolor="#808080">
                <td>房间价格</td>
            </tr>
            <tr align="center">
                <td><%=room.getRoomPrice()%>元/晚</td>
            </tr>
            <tr align="center" bgcolor="#808080">
                <td>房间类型</td>
            </tr>
            <tr align="center">
                <td><%=room.getRoomType()%></td>
            </tr>
        </table>
    </div>
    <div align="center">
        <input type="submit" name="submit" value="确定退房">
        <a href="<%=appPath%>/user_index.jsp" style="font-size: 15px;font-weight: bold;color: black">返回</a>
    </div>
</form>
</body>
</html>
