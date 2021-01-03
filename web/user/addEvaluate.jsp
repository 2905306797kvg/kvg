<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../app.jsp"%>
<html>
<head>
    <title>添加评论</title>
</head>
<body bgcolor="ffe4c4">
<form action="<%=appPath%>/AddEvaluateServlet" method="post">
    <div align="center">添加评论，填写内容</div>
    <div align="center">
        <textarea name="evaluateContent" style="width: 600px;height: 400px"></textarea>
    </div>
    <div align="center">
        <input type="submit" name="submit" value="发布评论">
        <a href="<%=appPath%>/EvaluateServlet" style="font-size: 15px;font-weight: bold">返回</a>
    </div>
</form>
</body>
</html>
