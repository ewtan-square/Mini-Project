<%-- 
    Document   : friendSearch
    Created on : 17-Nov-2013, 12:38:34 AM
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
        <h1>Search for other users</h1>
        <form method="post" action="FriendSearchServlet">
        Alias: <input type="text" name="alias" size="20"><br/>
        <input type="submit" value="Search">
    </body>
</html>
