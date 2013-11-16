<%-- 
    Document   : index
    Created on : 16-Nov-2013, 4:03:13 PM
    Author     : Francis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RateMyDoctor</title>
    </head>
    <body>
        <h2>Welcome to Rate My Doctor!</h2>
        <form method="post" action="loginPageServlet">
            Username <input type="text" name="name"><br>
            Password <input type="password" name="password"><br>
            <input type="submit" name="Submit Query">
        </form>
    </body>
</html>
