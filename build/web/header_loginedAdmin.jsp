<%-- 
    Document   : header_loginedAdmin
    Created on : Jul 18, 2022, 1:04:21 AM
    Author     : SEAL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="mycss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header>
            <ul>
                <li><a href="mainController?action=manageAccounts">Manage Accounts</a></li> 
                <li><a href="mainController?action=manageOrders">Manage Orders</a></li>
                <li><a href="#">Manage Plants</a></li>
                <li><a href="#">Manage categories</a></li>
                <li>Welcome ${sessionScope.name}  |  <a href="mainController?action=logout">Logout</a></li>
            </ul>
        </header>
    </body>
</html>
