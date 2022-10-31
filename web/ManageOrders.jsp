<%-- 
    Document   : ManageAccounts
    Created on : Jul 18, 2022, 1:33:00 AM
    Author     : SEAL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="mycss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"></c:import>
            <form action="mainController" method="post">
                <input type="text" name="txtsearch">
                <input type="submit" value="searchorder" name="action">
            </form>
            <h1></h1>
            <table class="order">
                <tr>
                    <th>ID</th>
                    <th>Order Date</th>
                    <th>Ship Date</th>
                    <th>status</th>
                    <th>Order ID</th>
                    <th>action</th>
                </tr>
            <c:forEach var="ord" items="${requestScope.orderList}">
                <tr>
                    <td><c:out value="${ord.getOrderID()}"></c:out></td>
                    <td><c:out value="${ord.getOrderDate()}"></c:out></td>
                    <td><c:out value="${ord.getShipDate()}"></c:out></td>
                    <td><c:choose>
                            <c:when test="${ord.getStatus() eq 1}"> processing </c:when>
                            <c:when test="${ord.getStatus() eq 2}"> completed </c:when>
                            <c:when test="${ord.getStatus() eq 3}"> canceled </c:when>
                        </c:choose>
                    </td>
                    <td><c:out value="${ord.getAccID()}"></c:out></td>
                        <td>
                        <c:if test="${ord.getStatus() eq 1}">
                            <c:url var="mylink" value="mainController">
                                <c:param name="id" value="${ord.getOrderID()}"></c:param>
                                <c:param name="status" value="${ord.getStatus()}"></c:param>
                                <c:param name="action" value="updateStatusOrder"></c:param>
                            </c:url>
                            <a href="${mylink}">processing/canceled</a>
                        </c:if>
                        
                            <c:if test="${ord.getStatus() eq 3}">
                            <c:url var="mylink" value="mainController">
                                <c:param name="id" value="${ord.getOrderID()}"></c:param>
                                <c:param name="status" value="${ord.getStatus()}"></c:param>
                                <c:param name="action" value="updateStatusOrder"></c:param>
                            </c:url>
                            <a href="${mylink}">processing/canceled</a>
                            </c:if>
                        </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
