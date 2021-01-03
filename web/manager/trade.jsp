<%@ page import="java.util.List" %>
<%@ page import="qst.com.bean.Trade" %>
<%@ page import="qst.com.bean.Page" %>
<%@ page import="qst.com.util.DataUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../app.jsp"%>
<html>
<head>
    <title>营收记录表</title>
</head>
<body bgcolor="ffe4c4">
<%
    List<Trade> tradeList= (List<Trade>) request.getAttribute("tradeList");
    Page tradePage= (Page) request.getAttribute("tradePage");
%>
<div align="center">
    <table border="1px" cellpadding="10px">
        <thead align="center" bgcolor="#808080">
        <td>交易 ID</td>
        <td>用户 ID</td>
        <td>房间 ID</td>
        <td>房间价格（元/晚）</td>
        <td>入住时长（天）</td>
        <td>交易金额</td>
        <td>交易时间</td>
        </thead>
        <%if (tradeList != null) {%>
        <%for (Trade trade : tradeList) {%>
        <tbody align="center">
        <tr>
            <td><%=trade.getTradeId()%></td>
            <td><%=trade.getUserId()%></td>
            <td><%=trade.getRoomId()%></td>
            <td><%=trade.getRoomPrice()%></td>
            <td><%=trade.getLiveTime()%></td>
            <td><%=trade.getTradePrice()%></td>
            <td><%=DataUtils.format(trade.getTradeTime())%></td>
        </tr>
        </tbody>
        <%}%>
        <%}%>
    </table>
</div>

<div align="center">
    <a href="javascript:beforePage(<%=tradePage.getBeforePage()%>)">上一页</a>
    共<%=tradePage.getTotalPages()%>页 共<%=tradePage.getTotalRows()%>行 第<%=tradePage.getCurrentPage()%>页
    <a href="javascript:afterPage(<%=tradePage.getAfterPage()%>)">下一页</a>
    <a href="<%=appPath%>/manage_index.jsp">返回主界面</a>
</div>
<script>
    function beforePage(page) {
        window.location = "<%=appPath%>/TradeServlet?currentPage=" + page + "&pageSize=<%=tradePage.getPageSize()%>";
    }

    function afterPage(page) {
        window.location = "<%=appPath%>/TradeServlet?currentPage=" + page + "&pageSize=<%=tradePage.getPageSize()%>";
    }
</script>
</body>
</html>
