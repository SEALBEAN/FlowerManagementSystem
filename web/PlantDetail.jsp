<%-- 
    Document   : PlantDetail
    Created on : Jul 17, 2022, 3:06:18 PM
    Author     : SEAL
--%>

<%@page import="sample.dto.Plant"%>
<%@page import="sample.dao.PlantDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <%
            Plant plant = PlantDAO.getPlant(Integer.parseInt(request.getParameter("pid")));
            String[] tmp = {"out of stock", "available"};
            request.setAttribute("p", plant);
        %>
        <table class="product">
            <tr>
                <jsp:useBean id="p" class="sample.dto.Plant" scope="request"/>
                <td><img src="${p.imgpath}" class="plantimg"/></td>
                <td>Product ID: ${p.id}</td>
                <td>Product name: ${p.name}</td>
                <td>Price: ${p.getPrice}</td>
                <td>Status: ${p.status}</td>
                <td>Category: ${p.catename}</td>
                <td>Description: ${p.description}</td>
                <td><a href="mainController?action=addtocart&pid=<%= p.getId()%>">add to cart</a></td>
            </tr>
        </table>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
