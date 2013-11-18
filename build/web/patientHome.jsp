<%-- 
    Document   : patientHome
    Created on : Nov 16, 2013, 7:03:25 PM
    Author     : ET
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rate My Doctor</title>
    </head>
    <%! String username;%>
    <% username = (String)request.getSession().getAttribute("username"); %>
    <body>
        <%
        out.print("<h2>Hello ");
        out.print(username);
    %>
    </h2>
        <li><a href="DoctorResultsServlet?qnum=2">Review a Doctor (Page not created)</a></li>        
        <li><a href="doctorSearch.jsp">Find a Doctor (Page not created)</a></li>        
        <li><a href="friendSearch.jsp">Find a Friend (Page not created)</a></li>
    </body>
</html>
