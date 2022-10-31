<%-- 
    Document   : header_loginedUser
    Created on : Jun 15, 2022, 4:25:11 PM
    Author     : xuhet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
    </head>
    <body>
        <nav>
            <ul>
                <li><a href=""><img src="images/logo.jpg" id="logo"> </a> </li>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="changeProfile.jsp">change profile</a></li>
                <li><a href="completedOrders.jsp">completed orders</a></li>
                <li><a href="canceledOrders.jsp">canceled orders</a></li>
                <li><a href="processingOrders.jsp">processing orders</a></li>
                <li>
                    <form action="mainController" method="post">
                        from<input type="date" name="from"> to <input type="date" name="to">
                        <input type="hidden" value="<%= (String)session.getAttribute("email") %>" name="txtemail">
                        <input type="submit" value="Search" name="action">
                    </form>

                </li>
            </ul>
        </nav>
    </body>
</html>
