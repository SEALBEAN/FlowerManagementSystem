<%-- 
    Document   : OrderDetail
    Created on : Jun 25, 2022, 10:14:51 PM
    Author     : xuhet
--%>

<%@page import="sample.dao.OrderDAO"%>
<%@page import="sample.dto.OrderDetail"%>
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
            <%@include file="header.jsp" %>
        </header>
        <section> <!-- load all orders of the user at here -->
            <% String orderid = request.getParameter("orderid");
                if (orderid != null) {
                    int orderID = Integer.parseInt(orderid.trim());
                    ArrayList<OrderDetail> list = OrderDAO.getOrderDetail(orderID);
                    if (list != null && !list.isEmpty()) {
                        int money = 0;
                        for (OrderDetail de : list) {
                            request.setAttribute("detail", de);%> 
            <jsp:useBean id="detail" class="sample.dto.OrderDetail" scope="request"/>
            <table class="order">
                <tr><td>Order ID</td><td>Plant ID</td><td>Plant Name</td><td>Image</td><td>Price</td><td>quantity</td></tr>
                <tr><td>${detail.orderID}</td>
                    <td>${detail.PID}</td>
                    <td>${detail.plantName}</td>
                    <td><img src="${detail.imgPath}" class="plantimg"/></td>
                    <td>${detail.price}</td>
                    <td>${detail.quantity}</td>
                </tr>
            </table>
            <% money = money + detail.getPrice() * detail.getQuantity(); %>
            <%  }%>
            <h3> Total money: <%= money%></h3>
            <%
            } else { %> 
            <p>You don't have any order</p>
            <% }
                }
            %>            
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>