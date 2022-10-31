<%-- 
    Document   : personalPage
    Created on : Jun 15, 2022, 4:30:17 PM
    Author     : xuhet
--%>

<%@page import="java.sql.Date"%>
<%@page import="sample.dao.AccountDao"%>
<%@page import="sample.dto.Account"%>
<%@page import="sample.dto.Order"%>
<%@page import="sample.dao.OrderDAO"%>
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
        <%
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");
            Cookie[] c = request.getCookies();
            boolean login = false;
            if (name == null) {
                String token = "";
                for (Cookie aCookie : c) {
                    if (aCookie.getName().equals("selector")) {
                        token = aCookie.getValue();
                        Account acc = AccountDao.getAccount(token);
                        if (acc != null) {
                            name = acc.getFullname();
                            email = acc.getEmail();
                            login = true;
                        }
                    }
                }

            } else {
                login = true;
            }

            //show content if login = true
            if (login) {
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <h3>Welcome <%= name%> come back </h3>
            <h3><a href="mainController?action=logout">Logout</a></h3>
        </section>
        <section><!---load all orders of the user at here --->
            <%
                ArrayList<Order> list = new ArrayList<>();
                if (request.getParameter("search") != null) {
                    Date from = Date.valueOf(request.getParameter("from"));
                    Date to = Date.valueOf(request.getParameter("to"));
                    list = OrderDAO.getOrders(email,from, to);
                }else{
                    list = OrderDAO.getOrders(email);
                }
                String[] status = {"", "processing", "completed", "canceled"};
                String update = request.getParameter("update");
                if (update != null)
                    if (update.equals("false")) { %> 
            <p>Update order false</p>
            <%      }
                if (list != null && !list.isEmpty()) {
                    for (Order ord : list) {%> 
            <table>
                <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's status</td><td>action</td></tr>
                <tr><td><%= ord.getOrderID()%></td>
                    <td><%= ord.getOrderDate()%></td>
                    <td><%= ord.getShipDate()%></td>
                    <td><%= status[ord.getStatus()]%>
                        <% if (ord.getStatus() == 1) {%> 
                        <br/>
                        <a href="cancelOrder?orderid=<%= ord.getOrderID()%>">cancel order</a>
                        <% } %>
                        <% if (ord.getStatus() == 3) {%> 
                        <br/>
                        <a href="reOrder?orderid=<%= ord.getOrderID()%>">re-order</a>
                        <% }%>
                    </td>
                    <td> <a href="OrderDetail.jsp?orderid=<%= ord.getOrderID()%>"> detail</a></td>
                </tr>
            </table>
            <%
                }
            } else { %> 
            <p>You don't have any order</p>
            <%
                }
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <% }%>
    </body>
</html>