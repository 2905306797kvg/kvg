<%@ page import="qst.com.bean.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../app.jsp" %>
<html>
<head>
    <title>修改房态及价格</title>
</head>
<body bgcolor="ffe4c4">
<%
    Room room= (Room) request.getAttribute("room");
%>
<form action="<%=appPath%>/UpdateRoomServlet?method=update" method="post" >
    <input type="hidden" name="roomUpdateId" value="<%=room.getRoomId()%>">
    <div align="center">
        <table border="1px" cellspacing="" cellpadding="10px">
            <tr align="center">
                <td><img src="image/OIP.jpg"></td>
            </tr>
            <tr align="center" bgcolor="#808080">
                <td>房间号码</td>
            </tr>
            <tr align="center">
                <td><input type="text" style="text-align: center" readonly="readonly" name="roomNumber" value="<%=room.getRoomNumber()%>"></td>
            </tr>
            <tr align="center" bgcolor="#808080">
                <td>房间大小</td>
            </tr>
            <tr align="center">
                <td><input style="text-align: center" type="text" readonly="readonly" name="roomSize" value="<%=room.getRoomSize()%>">平方米</td>
            </tr>
            <tr align="center" bgcolor="#808080">
                <td>房间价格</td>
            </tr>
            <tr align="center">
                <td><input type="text" style="text-align: center" name="roomPrice" id="roomPrice" value="<%=room.getRoomPrice()%>"/>元/晚</td>
            </tr>
            <tr align="center" bgcolor="#808080">
                <td>房间类型</td>
            </tr>
            <tr align="center">
                <td><input type="text" style="text-align: center" readonly="readonly" name="roomType" value="<%=room.getRoomType()%>"></td>
            </tr>
            <tr align="center" bgcolor="#808080">
                <td>房态（0：空闲中 1：装修中）</td>
            </tr>
            <tr align="center">
                <td><input type="text" style="text-align: center" name="roomState" id="roomState" value="<%=room.getRoomState()%>"/></td>
            </tr>
        </table>
    </div>
    <div align="center">
        <input type="submit" name="submit" value="确认">
    </div>
</form>
</body>
</html>
