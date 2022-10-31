<%-- 
    Document   : index
    Created on : Jun 15, 2022, 4:03:38 PM
    Author     : xuhet
--%>

<%@page import="sample.dao.PlantDAO"%>
<%@page import="sample.dto.Plant"%>
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
        <section>
            <%
                String keyword = request.getParameter("txtsearch");
                String searchby = request.getParameter("txtsearchby");
                String[] tmp = {"out of stock", "available"};
                ArrayList<Plant> list;
                if (keyword == null && searchby == null) {
                    list = PlantDAO.getPlants("", "");
                } else {
                    list = PlantDAO.getPlants(keyword, searchby);
                }
                if (list != null && !list.isEmpty()) {
                    for (Plant plant : list) {
                        request.setAttribute("p", plant);%> 
            <jsp:useBean id="p" class="sample.dto.Plant" scope="request"/>
            <table class="product">
                <tr>
                    <td><img src="${p.imgpath}" class="plantimg"/></td>
                    <td>Product ID: ${p.id}</td>
                    <td>Product name: ${p.name}</td>
                    <td>Price: ${p.price}</td>
                    <td>Status: ${p.status}</td>
                    <td>Category: ${p.catename}</td>
                    <td><a href="mainController?action=addtocart&pid=<%= p.getId()%>">add to cart</a></td>
                </tr>
            </table>
            <%
                    }
                }
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
