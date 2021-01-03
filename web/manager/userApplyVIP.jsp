<%@ page import="java.util.List" %>
<%@ page import="qst.com.bean.Apply" %>
<%@ page import="qst.com.bean.Page" %>
<%@ page import="qst.com.util.DataUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../app.jsp"%>
<html>
<head>
    <title>VIP申请表</title>
</head>
<body bgcolor="ffe4c4">
<%
    List<Apply> applyList= (List<Apply>) request.getAttribute("applyList");
    Page applyPage= (Page) request.getAttribute("applyPage");
%>
<div align="center">
    <table border="1px" cellpadding="10px">
        <thead align="center" bgcolor="#808080">
        <td>申请 ID</td>
        <td>用户 ID</td>
        <td>用户姓名</td>
        <td>申请时间</td>
        <td>处理申请</td>
        </thead>
        <%if (applyList != null) {%>
        <%for (Apply apply : applyList) {%>
        <tbody align="center">
        <tr>
            <td><%=apply.getApplyId()%></td>
            <td><%=apply.getUserId()%></td>
            <td><%=apply.getUserName()%></td>
            <td><%=DataUtils.format(apply.getApplyTime())%></td>
            <td>
                <a href="<%=appPath%>/ApplyVipServlet?method=agree&applyUserId=<%=apply.getUserId()%>">同意</a>
                <a href="<%=appPath%>/ApplyVipServlet?method=oppose&applyUserId=<%=apply.getUserId()%>">不同意</a>
            </td>
        </tr>
        </tbody>
        <%}%>
        <%}%>
    </table>
</div>

<div align="center">
    <a href="javascript:beforePage(<%=applyPage.getBeforePage()%>)">上一页</a>
    共<%=applyPage.getTotalPages()%>页 共<%=applyPage.getTotalRows()%>行 第<%=applyPage.getCurrentPage()%>页
    <a href="javascript:afterPage(<%=applyPage.getAfterPage()%>)">下一页</a>
    <a href="<%=appPath%>/manage_index.jsp">返回主界面</a>
</div>
<script>
    function beforePage(page) {
        window.location = "<%=appPath%>/ApplyVipServlet?method=vetting&currentPage=" + page + "&pageSize=<%=applyPage.getPageSize()%>";
    }

    function afterPage(page) {
        window.location = "<%=appPath%>/ApplyVipServlet?method=vetting&currentPage=" + page + "&pageSize=<%=applyPage.getPageSize()%>";
    }
</script>
</body>
</html>
