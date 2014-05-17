<%-- 
    Document   : index
    Created on : 17.05.2014, 19:44:44
    Author     : Pete
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        HttpSession hs = request.getSession(true);
        String name = (String) hs.getAttribute("Login");
        if (name != null) {
            response.sendRedirect("MainUserPage.jsp");
        } else {
            response.sendRedirect("LoginPage.jsp");
        }
    %>
</html>
