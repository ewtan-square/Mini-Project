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
        <h1>Welcome to Rate My Doctor!</h1>
        <form method="post" action="UserDataServlet">
            What's your name? <input type="text" name="name"><br>
            What's your favourite color?
            <select name="color">
            <option value="red">red</option>
            <option value="blue">blue</option>
            <option value="green">green</option>
            <option value="yellow">yellow</option>
            </select>
            <input type="submit" name="Submit Query">
        </form>
    </body>
</html>
