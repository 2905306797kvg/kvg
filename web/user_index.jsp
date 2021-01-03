<%@ page import="qst.com.bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>用户主界面</title>
    <style>
        .main{
            background: url("image/background 2.png");
        }
        .context{
            height: 500px;
            width: 1600px;
            margin-top: 100px;
        }
        .context_next{
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<%
    User user= (User) request.getSession().getAttribute("USER");
%>
<body bgcolor="ffe4c4" class="main">
<div class="context">
    <div class="context_next">
        <table cellpadding="30px">
            <tr>
                <%if (user.getUserVip().equalsIgnoreCase("否")){%>
                    <td><a href="/hotelsystem_war_exploded/ApplyVipServlet?method=apply"><img src="image/applyVIP.png" alt=""></a></td>
                <%}else if (user.getUserVip().equalsIgnoreCase("是")){%>
                    <td><span style="font-size: 20px;font-weight: bold;color: black">尊贵的会员用户：<%=user.getUserEmail()%><br>
                    很高心为您服务</span></td>
                <%}%>
                <td><a href="/hotelsystem_war_exploded/RoomServlet"><img src="image/lookRoom.png" alt=""></a></td>
                <td><a href="/hotelsystem_war_exploded/EvaluateServlet"><img src="image/lookEvaluate.png" alt=""></a></td>
                <td><a href="login.jsp" target="_parent"><span style="font-size: 36px;font-weight: bold;color: red">退出登录</span></a></td>
            </tr>
            <tr>
                <td><a href="/hotelsystem_war_exploded/CheckServlet?method=findRoomByUser"><img src="image/check.png" alt=""></a></td>
                <td><a href="/hotelsystem_war_exploded/TradeServlet"><img src="image/consumptionRecord.png" alt=""></a></td>
                <td><a href="user/myself.jsp"><img src="image/myself.png" alt=""></a></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
