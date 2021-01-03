<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>注册</title>
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
    <script type="text/javascript">
        var xhr = false;
        function createXHR() {
            try {
                xhr = new XMLHttpRequest();
            } catch (e) {
                try {
                    xhr = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (e1) {
                    xhr = false;
                }
            }
            if (!xhr)
                alert("初始化XMLHttpRequest对象失败！");
        }
        function ajaxValidate(emailObj){
            createXHR();
            var url = "RegisterServlet";
            var content = "type=emailAjaxValidate&email=" + emailObj.value;
            xhr.open("POST", url, true);
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    document.getElementById("emailValidate").innerHTML = xhr.responseText;
                }
            };
            xhr.setRequestHeader("Content-Length",content.length);
            xhr.setRequestHeader("CONTENT-TYPE","application/x-www-form-urlencoded");
            xhr.send(content);
        }

        function validate() {
            var email = document.getElementById("email");
            var passWord = document.getElementById("passWord");
            var passWordX=document.getElementById("passwordX");
            var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;

            if (email.value == "") {
                alert("邮箱不能为空！");
                email.focus();
                return false;
            } else if (!pattern.test(email.value)) {
                alert("请输入正确的邮箱格式！");
                email.focus();
                return false;
            }
            if (passWord.value == "") {
                alert("密码不能为空！");
                passWord.focus();
                return false;
            } else if (passWord.value.length<6 || passWord.value.length>12) {
                alert("密码长度不符合要求，请输入6-12位密码!");
                passWord.focus();
                return false;
            }else if(passWord.value!=passWordX.value) {
                alert("密码与验证密码不一致");
                passWordX.focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body bgcolor="#ffe4c4">
<form action="RegisterServlet" method="post" onsubmit="return validate();">
    <div class="box">
        <div id="tittle"><h2 align="center">注册</h2></div>
        <div class="input_box">
            <label>邮箱：</label> <input id="email" type="text" name="email" placeholder="请输入您的email" onblur="ajaxValidate(this)">
        </div>
        <div class="input_box">
            <label style="color: red" id="emailValidate"></label>
        </div>
        <div class="input_box">
            <label>密码：</label><input id="passWord" name="passWord" type="password" placeholder="请设置您的密码">
        </div>
        <div class="input_box">
            <label>验证：</label><input id="passWordX" name="passWordX" type="password" placeholder="请再次输入密码">
        </div>

        <div>
            <input type="radio" name="userType" value="用户" checked>用户
            <input type="radio" name="userType" value="管理员">管理员
        </div>

        <div id="error_box"><br></div>
        <div class="input_box">
            <input name="submit" type="submit" value="立即注册">
        </div>
        <h4 align="center"> <a href="login.jsp">已有账号请登录</a></h4>


    </div>
</form>
</body>
</html>
