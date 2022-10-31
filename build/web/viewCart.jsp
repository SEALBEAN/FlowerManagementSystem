<%-- 
    Document   : viewCart
    Created on : Jul 2, 2022, 11:56:09 PM
    Author     : xuhet
--%>

<%@page import="sample.dto.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sample.dao.PlantDAO"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
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
        <section>
            <% 
                String name = (String) session.getAttribute("name");
                if (name != null){ %> 
                <h3>Welcome <%= session.getAttribute("name") %> come back</h3>
                <h3><a href="mainController?action=logout">Logout</a></h3>
                <h3><a href="personalPage.jsp">personal page</a></h3>
            <%
                }
            %>
            <font style='color:red;'><%= (request.getAttribute("WARNING") == null)?"":(String) request.getAttribute("WARNING") %> </font>
            
            <table width="100%" class="shopping">
                <tr><td>Product ID</td><td>image</td><td>quantity</td><td>price</td><td>action</td></tr>
                <%
                    int totalMoney = 0;
                    HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("cart");
                    if (cart != null) {
                        Set<String> pids = cart.keySet();
                        ArrayList<Plant> list = PlantDAO.getPlants("", "");
                        for (String pid : pids) {
                            int index = list.indexOf(new Plant(Integer.parseInt(pid)));
                            Plant plant = list.get(index);
                            request.setAttribute("p", plant);
                            int quantity = cart.get(pid);
                            totalMoney+= quantity*plant.getPrice();
                %> 
                <jsp:useBean id="p" class="sample.dto.Plant" scope="request"/>
                <form action="mainController" method="post">
                    <tr><td><input type="hidden" value="<%= pid%>" name="pid"><a href="getPlantServlet?pid=<%= pid %>"><%= pid%></a></td>
                        <td><img src="${p.imgpath}" class="plantimg"/></td>
                        <td><input type="number" value="<%= quantity%>" name="quantity"></td>
                        <td>${p.price}</td>
                        <td><input type="submit" value="update" name="action"></td>
                        <td><input type="submit" value="delete" name="action"></td>
                    </tr>
                </form>
                <%
                    }
                } else { %> 
                <tr><td>Your cart is empty</td></tr>
                <%
                    }
                %>
                <tr><td>Total money:<%= totalMoney %> </td></tr>
                <tr><td>Order Date: <%= (new Date(System.currentTimeMillis())) %> </td></tr>
                <tr><td>Ship Date: N/A </td></tr>
            </table>
        </section>
        <section>
            <form action="mainController" method="post">
                <input type="submit" value="saveOrder" name="action" class="submitorder">
            </form>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
