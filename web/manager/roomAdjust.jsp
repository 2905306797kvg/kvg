<%@ page import="qst.com.bean.Page" %>
<%@ page import="java.util.List" %>
<%@ page import="qst.com.bean.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../app.jsp" %>
<html>
<head>
    <title>房间管理</title>
</head>
<body bgcolor="ffe4c4">
<%
    Page roomPage = (Page) request.getAttribute("roomPage");
    List<Room> roomList = (List<Room>) request.getAttribute("roomList");
%>

<div align="center">
    <table border="1px" cellpadding="10px">
        <thead align="center">
        <td>房间 ID</td>
        <td>房间号码</td>
        <td>房间大小</td>
        <td>房间价格</td>
        <td>房间状态</td>
        <td>房间类型</td>
        <td>房间管理</td>
        </thead>
        <%if (roomList != null) {%>
            <%for (Room room : roomList) {%>
                <tbody align="center">
                    <tr>
                        <td><%=room.getRoomId()%></td>
                        <td><%=room.getRoomNumber()%></td>
                        <td><%=room.getRoomSize()%></td>
                        <td><%=room.getRoomPrice()%></td>
                        <%if (room.getRoomState() == 0) {%>
                            <td bgcolor="blue">空闲中</td>
                        <%} else if (room.getRoomState() == 1) {%>
                            <td bgcolor="#8a2be2">装修中</td>
                        <%} else if (room.getRoomState() == 2) {%>
                            <td bgcolor="red">已有客人</td>
                        <%}%>
                            <td><%=room.getRoomType()%></td>
                        <%if (room.getRoomState() == 0 || room.getRoomState() == 1) {%>
                            <td><a href="<%=appPath%>/UpdateRoomServlet?method=findById&roomId=<%=room.getRoomId()%>">调整</a></td>
                        <%} else if (room.getRoomState() == 2) {%>
                            <td>不可调整</td>
                        <%}%>
                    </tr>
                </tbody>
            <%}%>
        <%}%>
    </table>
</div>

<div align="center">
    <a href="javascript:beforePage(<%=roomPage.getBeforePage()%>)">上一页</a>
    共<%=roomPage.getTotalPages()%>页 共<%=roomPage.getTotalRows()%>行 第<%=roomPage.getCurrentPage()%>页
    <a href="javascript:afterPage(<%=roomPage.getAfterPage()%>)">下一页</a>
    <a href="<%=appPath%>/manage_index.jsp">返回主界面</a>
</div>
<script>
    function beforePage(page) {
        window.location = "<%=appPath%>/RoomServlet?currentPage=" + page + "&pageSize=<%=roomPage.getPageSize()%>";
    }

    function afterPage(page) {
        window.location = "<%=appPath%>/RoomServlet?currentPage=" + page + "&pageSize=<%=roomPage.getPageSize()%>";
    }
</script>
</body>
</html>
