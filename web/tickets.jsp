<%@ page import="by.iivanov.service.TicketService" %>
<%@ page import="by.iivanov.dto.TicketDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ taglib prefix="c" uri="http://mycompany.com" %>--%>
<%--<%@ include file="index.html" %>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Купленные билеты</h1>
<ul>
    <%
        Long flightId = Long.valueOf(request.getParameter("flightId"));
        List<TicketDto> tickets = TicketService.getInstance().findAllByFlightId(flightId);
        for (TicketDto ticket : tickets) {
            out.write(String.format("<li>%s</li>", ticket.seatNo()));
        }
    %>
</ul>
</body>
</html>


<%!
    public void jspInit() {
        System.out.println("Hello world from INIT");
    }
%>
