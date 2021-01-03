<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>管理员主界面</title>
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
<body class="main">
<div class="context">
    <div class="context_next">
        <table cellpadding="30px">
            <tr>
                <td><a href="/hotelsystem_war_exploded/ApplyVipServlet?method=vetting"><img src="image/vipApply.png" alt=""></a></td>
                <td><a href="/hotelsystem_war_exploded/RoomServlet"><img src="image/room.png" alt=""></a></td>
                <td><a href="/hotelsystem_war_exploded/EvaluateServlet"><img src="image/evaluate.png" alt=""></a></td>
                <td><a href="login.jsp" target="_parent"><span style="font-size: 36px;font-weight: bold;color: red">退出登录</span></a></td>
            </tr>
            <tr>
                <td><a href="/hotelsystem_war_exploded/VipUserServlet"><img src="image/vipInformation.png" alt=""></a></td>
                <td><a href="/hotelsystem_war_exploded/TradeServlet"><img src="image/trade.png" alt=""></a></td>
                <td><a href="manager/myself.jsp"><img src="image/myself.png" alt=""></a></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
