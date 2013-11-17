<%-- 
    Document   : login
    Created on : Nov 16, 2013, 6:35:55 PM
    Author     : ET
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login to Rate My Doctor</title>
    </head>
    <body>
        <h1>Login Now!</h1>
        <form method="post" action="Login">
            Enter employee data:
            <p>       
                Username: <input type="text" name="username" size="20" autofocus><br/>
                Password: <input type="password" name="userPass" size="20"><br/>
            <p> <input type="submit" value="Submit">
        </form>
    </body>
</html>
