<%-- 
    Document   : changeProfile
    Created on : Jun 26, 2022, 2:35:55 PM
    Author     : xuhet
--%>

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
                String check = request.getParameter("check");
                if (check != null){ 
                    if (check.equals("1")){ %> 
                                <p>Update profile successfully</p>
                                <a href="personalPage.jsp">Back to personal page</a>
                 <% }  else { %>
                                <p>There is an error please try again</p>
                <% }
                } else{ %>
            <form action="mainController" method="post">
                <table>
                    <tr>
                        <td>New Full Name</td><td><input type = "text" name="txtNewFullName"></td></tr>
                    <tr>
                        <td>New Phone</td><td><input type = "txt" name="txtNewPhone"></td></tr>
                    <tr><td colspan="2"><input type = "submit" value="change profile" name="action"></td></tr>
                </table>
            </form>
            <% } %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
