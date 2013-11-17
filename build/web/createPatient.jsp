<%-- 
    Document   : createUser
    Created on : 16-Nov-2013, 5:31:38 PM
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
        <h1>Hello New Patient!</h1>
        <form method="post" action="newUserServlet?type=patient">
            Enter employee data:
            <p>       
                Employee ID: <input type="text" name="empID" size="20" autofocus><br/>
                Employee name: <input type="text" name="empName" size="20"><br/> 
                Job:<input type="text" name="job" size="20"><br/>
                Salary: <input type="text" name="salary" size="20"><br/>
            <p> <input type="submit" value="Submit">
        </form>
    </body>
</html>
