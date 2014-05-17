<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : index
    Created on : 10.05.2014, 14:21:03
    Author     : Pete
--%>




<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
    </head>
    <body>
        <h1><center>Login page</h1>
        <div align = "center">
            <form action="usersServlet" method="POST">
                <table border="0">
                    <thead>
                        <tr>
                            <th colspan="2">Please enter your login and password.</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Login:</td>
                            <td>
                                <input type="text" name="Login" value="" size="60" />
                            </td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td>
                                    <input type="text" name="Password" value="" size="60" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2"> 
                    <center>
                        <input type="submit" value="Login" name="Submit" />
                        <input type="submit" value="Register" name="Register" />
                    </center>                           
                    </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>
