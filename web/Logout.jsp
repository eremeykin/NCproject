<%-- 
    Document   : Logout
    Created on : 17.05.2014, 21:06:36
    Author     : Pete
--%>
<% 
request.getSession().removeAttribute("isActive");
request.getSession().invalidate();
response.sendRedirect("index.jsp");
%>
