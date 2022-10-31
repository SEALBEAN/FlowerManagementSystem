<%-- 
    Document   : AdminIndex
    Created on : Jul 18, 2022, 1:02:47 AM
    Author     : SEAL
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            if((Integer)session.getAttribute("role") == 0)
                response.sendRedirect("personalPage.jsp");
        %>
        <c:import url="header_loginedAdmin.jsp"></c:import>
        <section class="right">
            <img src="images/background.jpg"/>
        </section>
    </body>
</html>
