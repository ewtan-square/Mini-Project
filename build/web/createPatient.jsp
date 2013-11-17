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
            Enter the following information:
            <p>  
                <%! String username = ""; %>
                <% username = ((String)request.getAttribute("username") == null) ? "" : (String) request.getAttribute("username"); %>
                Username: <input type="text" name="username" size="20" value="<%= username%>"><br/> 
                Password: <input type="text" name="password" size="20"><br/>   
                <%! String firstName = ""; %>
                <% username = ((String)request.getAttribute("firstName") == null) ? "" : (String) request.getAttribute("firstName"); %>
                First name: <input type="text" name="firstName" size="20" value="<%= firstName%>"><br/>   
                <%! String lastName = ""; %>
                <% username = ((String)request.getAttribute("lastName") == null) ? "" : (String) request.getAttribute("lastName"); %>
                Last name: <input type="text" name="lastName" size="20" value="<%= username%>"><br/>   
                <%! String gender = ""; String maleSelected = ""; String femaleSelected = ""; %>
                <% 
                    if ((String)request.getAttribute("gender") != null) { gender = (String) request.getAttribute("gender"); }
                    if (gender.equals("M")) {
                        maleSelected = "selected";
                    }
                    else if (gender.equals("F")) {
                        femaleSelected = "selected";
                    }
                %>
                Gender: 
                <select name="gender"> 
                    <option value="M" selected="<%= maleSelected%>">Male</option>
                    <option value="F" selected="<%= femaleSelected%>">Female</option>
                </select><br/>
                <%! String birthday; %>
                <% birthday = ((String)request.getAttribute("birthday") == null) ? "" : (String) request.getAttribute("birthday"); %>
                Birthday:<input type="date" name="birthday" size="20" value="<%= birthday%>"><br/>
                <%! String email = ""; %>
                <% email = ((String)request.getAttribute("email") == null) ? "" : (String) request.getAttribute("email"); %>
                E-Mail: <input type="text" name="email" size="20" value="<%= email%>"><br/>
                Home Address<br/>
                <%! String province = ""; %>
                <% province = ((String)request.getAttribute("province") == null) ? "" : (String) request.getAttribute("province"); %>
                Province: <input type="text" name="province" size="20" value="<%= province%>"><br/>
                <%! String city = ""; %>
                <% city = ((String)request.getAttribute("city") == null) ? "" : (String) request.getAttribute("city"); %>
                City: <input type="text" name="city" size="20" value="<%= city%>"><br/>
                <%! String postalCode = ""; %>
                <% postalCode = ((String)request.getAttribute("postalCode") == null) ? "" : (String) request.getAttribute("postalCode"); %>
                Postal Code: <input type="text" name="postalCode" size="20" value="<%= postalCode%>"><br/>
                <%! String streetAddress = ""; %>
                <% streetAddress = ((String)request.getAttribute("streetAddress") == null) ? "" : (String) request.getAttribute("streetAddress"); %>
                Street Address: <input type="text" name="streetAddress" size="20" value="<%= streetAddress%>"><br/>
            <p> <input type="submit" value="Submit">
        </form>
    </body>
</html>
