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
        <style type="text/css" media="screen">
        input {
          border: none;
          background: none;
          color: #00f;
          text-decoration: underline;
          cursor: pointer;
          display: in-line;
          margin: 0px;
          padding: 0px;
        }
        </style>
        <form method="post" action="UpdateDoctorServlet?type=none">
            <li><input type="submit" value="Update your work address(es) or Add Specialties"/></li>
        </form>
    </body>
</html>
