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
        <form method="post" action="newUserServlet?type=doctor">
            Enter the following information:
            <p>       
                Username: <input type="text" name="username" size="20"><br/> 
                Password: <input type="text" name="password" size="20"><br/>      
                First name: <input type="text" name="firstName" size="20"><br/> 
                Last name: <input type="text" name="lastName" size="20"><br/> 
                Gender: 
                <select name="gender"> 
                    <option value="M">Male</option>
                    <option value="F">Female</option>
                </select><br/>
                Birthday:<input type="date" name="birthday" size="20"><br/>
                E-Mail: <input type="text" name="email" size="20"><br/>
                Home Address<br/>
                Province: <input type="text" name="province" size="20"><br/>
                City: <input type="text" name="city" size="20"><br/>
                Postal Code: <input type="text" name="postalCode" size="20"><br/>
                Street Address: <input type="text" name="streetAddress" size="20"><br/>
            <p> <input type="submit" value="Submit">
        </form>
    </body>
</html>
