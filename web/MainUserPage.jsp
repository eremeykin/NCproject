<%-- 
    Document   : MainPage
    Created on : 17.05.2014, 20:25:14
    Author     : Pete
--%>

<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
    </head>
    <body>
        <h1>Hello, 
            <%
                HttpSession hs = request.getSession(true);
                out.println(hs.getAttribute("Login"));
            %>
            !</h1>
        <form action="Logout.jsp">
            <input type="submit" value="Logout" name="Logout_button" />
        </form>
    </body>
</html>
