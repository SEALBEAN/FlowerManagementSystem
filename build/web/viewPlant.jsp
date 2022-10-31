
<%@page import="sample.dto.Plant"%>
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
            <jsp:useBean id="plantObj" class="sample.dto.Plant" scope="request"/>
        <table>
            <tr><td rowspan="8"><img src="<jsp:getProperty name="plantObj" property="imgpath"></jsp:getProperty>"/></td></tr>
            <tr><td>Product ID: <jsp:getProperty name="plantObj" property="id"></jsp:getProperty></td></tr>
            <tr><td>Product name: <jsp:getProperty name="plantObj" property="name"></jsp:getProperty></td></tr>
            <tr><td>Price: <jsp:getProperty name="plantObj" property="price"></jsp:getProperty></td></tr>
            <tr><td>Status: <jsp:getProperty name="plantObj" property="status"></jsp:getProperty></td></tr>
            <tr><td>Category: <jsp:getProperty name="plantObj" property="catename"></jsp:getProperty></td></tr>
            <tr><td>Description: <jsp:getProperty name="plantObj" property="description"></jsp:getProperty></td></tr>
        </table>
        
        
        <!--su dung EL-->
        <table>
            <tr><td rowspan="8"><img src="${plantObj.imgpath}"/></td></tr>
            <tr><td>Product ID: ${plantObj.id}</td></tr>
            <tr><td>Product name: ${plantObj.name}</td></tr>
            <tr><td>Price: ${plantObj.price}</td></tr>
            <tr><td>Status: ${plantObj.status}</td></tr>
            <tr><td>Category: ${plantObj.catename}</td></tr>
            <tr><td>Description: ${plantObj.description}</td></tr>
        </table>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
