<%@ page import="java.util.List" %>
<%@ page import="qst.com.bean.Evaluate" %>
<%@ page import="qst.com.bean.Page" %>
<%@ page import="qst.com.util.DataUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../app.jsp"%>
<html>
<head>
    <title>用户评论</title>
</head>
<body bgcolor="ffe4c4">
<%
    List<Evaluate> evaluateList= (List<Evaluate>) request.getAttribute("evaluateList");
    Page evaluatePage= (Page) request.getAttribute("evaluatePage");
%>
<div align="center">
    <table border="1px" cellpadding="10px">
        <thead align="center" bgcolor="#808080">
        <td>评论 ID</td>
        <td>评论信息</td>
        </thead>
        <%if (evaluateList != null) {%>
        <%for (Evaluate evaluate : evaluateList) {%>
        <tbody align="center">
        <tr>
            <td><%=evaluate.getEvaluateId()%></td>
            <td>姓名为<%=evaluate.getUserName()%>的用户于<%=DataUtils.format(evaluate.getEvaluateTime())%>发布此评论：<br>
                <textarea readonly="readonly" style="width: 400px;height: 50px;font-size: 15px"><%=evaluate.getEvaluateContent()%></textarea>
            </td>
        </tr>
        </tbody>
        <%}%>
        <%}%>
    </table>
</div>

<div align="center">
    <a href="javascript:beforePage(<%=evaluatePage.getBeforePage()%>)">上一页</a>
    共<%=evaluatePage.getTotalPages()%>页 共<%=evaluatePage.getTotalRows()%>行 第<%=evaluatePage.getCurrentPage()%>页
    <a href="javascript:afterPage(<%=evaluatePage.getAfterPage()%>)">下一页</a>
    <a href="<%=appPath%>/manage_index.jsp">返回主界面</a>
</div>
<script>
    function beforePage(page) {
        window.location = "<%=appPath%>/EvaluateServlet?currentPage=" + page + "&pageSize=<%=evaluatePage.getPageSize()%>";
    }

    function afterPage(page) {
        window.location = "<%=appPath%>/EvaluateServlet?currentPage=" + page + "&pageSize=<%=evaluatePage.getPageSize()%>";
    }
</script>
</body>
</html>
