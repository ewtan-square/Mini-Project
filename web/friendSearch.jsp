<%-- 
    Document   : friendSearch
    Created on : 17-Nov-2013, 12:38:34 AM
    Author     : Francis
--%>

<%@page import="miniProject.Patient"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RateMyDoctor</title>
    </head>
    <body>
        <%! String tmp;%>
        <% tmp = (String)request.getSession().getAttribute("username"); %>
        <%= tmp%>
        <h1>Search for other users</h1>
        <form method="post" action="FriendSearchServlet?type=alias">
        Alias: <input type="text" name="alias" size="20"><br/>
        <input type="submit" value="Search">
        
        <h2>Your current friends</h2>
        <%! ArrayList<Patient> friends;%>
        <% friends = (ArrayList<Patient>) request.getAttribute("friendList");%>
        <%
            if (friends != null) {
        %>
        <table border=1><tr><th>Username</th><th>First Name</th><th>Last Name</th></tr>
            <%
                for (Patient f : friends) {
            %>
            <tr>
                <td><%= f.getUsername()%></td>
                <td><%= f.getFirstName()%></td>
                <td><%= f.getLastName()%></td>
            </tr>
            <%
                }
            %>
            </table>
            <%
            }
            %>
    </body>
</html>
