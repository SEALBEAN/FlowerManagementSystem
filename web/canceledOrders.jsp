<%-- 
    Document   : canceledOrders
    Created on : Jun 26, 2022, 3:01:48 PM
    Author     : xuhet
--%>

<%@page import="sample.dao.OrderDAO"%>
<%@page import="sample.dto.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
    </head>
    <body>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
           <%
                String email = (String) session.getAttribute("email");
                ArrayList<Order> list = OrderDAO.getOrders(email);
                String[] status = {"", "processing", "completed", "canceled"};
                String update = request.getParameter("update");
                if (update!=null)    
                    if (update.equals("false")){ %> 
                    <p>Update order false</p>
            <%      }
                if (list != null && !list.isEmpty()) {
                    for (Order ord : list) {%> 
            <table>
                <!--canceled order (3)-->
                <% if (ord.getStatus() == 3) {%>
                <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's status</td><td>action</td></tr>
                <tr><td><%= ord.getOrderID()%></td>
                    <td><%= ord.getOrderDate()%></td>
                    <td><%= ord.getShipDate()%></td>
                    <td><%= status[ord.getStatus()]%>
                        <% if (ord.getStatus() == 1) {%> 
                            <br/>
                            <a href="cancelOrder?orderid=<%= ord.getOrderID() %>">cancel order</a>
                            <% } %>
                        <% if (ord.getStatus() == 3) {%> 
                            <br/>
                            <a href="reOrder?orderid=<%= ord.getOrderID() %>">re-order</a>
                            <% }%>
                    </td>
                    <td> <a href="OrderDetail.jsp?orderid=<%= ord.getOrderID() %>"> detail</a></td>
                </tr>
                <% } %>
            </table>
            <%
                    }
                } else{ %> 
            <p>You don't have any order</p>
            <%
}
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
