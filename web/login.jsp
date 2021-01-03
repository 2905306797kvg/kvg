<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登录</title>
    <style>
        h2 {
            font-family: 宋体;
            font-size: 28px;
            text-align: center;
            background: #cccccc;
            margin-top: auto;
            height: 30px;
            width: 500px;
        }
        .box {
            border: 1px solid #000000;
            height: 370px;
            width: 500px;
            float: left;
            margin: 50px;
            margin-left: 30%;
        }

        .input_box{
            text-align: center;
            height: 30px;
            width: 500px;

        }
        input {
            align-self: center;
            height: 30px;
            width: 300px;
        }
    </style>
</head>
<body bgcolor="#ffe4c4">
<form action="LoginServlet" method="post">
    <div class="box">
        <div id="tittle"><h2 align="center">登录</h2></div>
        <div class="input_box">
            <label>邮箱：</label><input id="email" name="email" type="text" placeholder="请输入您的email">
        </div>
        <div class="input_box">
            <label>密码：</label><input id="passWord" name="passWord" type="password" placeholder="请输入您的密码">
        </div>
        <div>
            <input type="radio" name="userType" value="用户" checked>用户
            <input type="radio" name="userType" value="管理员">管理员
        </div>

        <div id="error_box"><br></div>
        <div class="input_box">
            <input type="submit" name="submit" value="登录" >
        </div>
        <h4 align="center"> <a href="register.jsp">没有账号？点我啊</a></h4>

    </div>
</form>
</body>
</html>
