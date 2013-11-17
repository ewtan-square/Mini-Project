<%-- 
    Document   : doctorHome
    Created on : Nov 16, 2013, 7:03:48 PM
    Author     : ET
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rate My Doctor</title>
    </head>
    <body>
        <%! String username = "";%>
        <% username = ((String)request.getSession().getAttribute("username") == null) ? 
                            "" : (String) request.getSession().getAttribute("username"); %>
        <h1>Hellooooooo Doctor, <%= username%>!</h1>
        <li><a href="doctorProfile.jsp">Update your work address(es) or Add Specialties</a></li>
    </body>
</html>
