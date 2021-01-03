<%@ page import="qst.com.bean.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../app.jsp"%>
<html>
<head>
    <title>订房</title>
</head>
<body bgcolor="ffe4c4">
<%
    Room room= (Room) request.getAttribute("room");
%>
<form action="<%=appPath%>/UpdateRoomServlet?method=purchase" method="post" >
    <input type="hidden" name="purchaseId" value="<%=room.getRoomId()%>">
    <div align="center">
        <table border="1px" cellspacing="" cellpadding="10px">
            <tr align="center">
                <td><img src="<%=appPath%>/image/OIP.jpg"></td>
            </tr>
            <tr align="center" bgcolor="#808080">
                <td>房间号码</td>
            </tr>
            <tr align="center">
                <td>${room.roomNumber}</td>
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
            <tr align="center" bgcolor="#808080">
                <td>住房时长</td>
            </tr>
            <tr align="center">
                <td><input type="text" style="text-align: center" name="roomTime" value="1"/>晚</td>
            </tr>
        </table>
    </div>
    <div align="center">
        <%if (room.getRoomState()==0){%>
            <input type="submit" name="submit" value="订房">
        <%}else {%>
            <span>该房间暂时不能入住</span>
        <%}%>
        <a href="<%=appPath%>/RoomServlet" style="font-size: 15px;font-weight: bold;color: black">返回</a>
    </div>
</form>
</body>
</html>
