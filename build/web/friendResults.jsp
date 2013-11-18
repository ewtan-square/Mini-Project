<%-- 
    Document   : patientResults
    Created on : 17-Nov-2013, 4:30:44 PM
    Author     : Francis
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="miniProject.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RateMyDoctor</title>
    </head>
    
    <%! ArrayList<Patient> patientList;%>
    <% patientList = (ArrayList<Patient>) request.getAttribute("patientList");%>
    <%! ArrayList<Patient> friendList;%>
    <% friendList = (ArrayList<Patient>) request.getAttribute("friendList");%>
    
    <body>
        <h2>Search Results</h2>
        <%
            if (patientList != null || friendList != null) {
                out.println("<table border=1>");
                out.println("<tr><th>Username</th><th></th></tr>");
                if(friendList != null)
                {
                    for(Patient friend : friendList)
                    {
                        out.println("<tr><td>");
                        out.print(friend.getUsername());
                        out.print("</td><td>");
                        out.print("<button type=\"submit\" name=\"RemoveFriend\" onclick=\"location.href='AddFriendServlet?qnum=2&pNum=");
                        out.print(friend.getUsername());
                        out.print("';\">Remove Friend</button>");
                        out.println("</td></tr>");
                    }
                }
                
                if(patientList != null)
                {
                    for(Patient patient : patientList)
                    {
                        out.println("<tr><td>");
                        out.print(patient.getUsername());
                        out.print("</td><td>");
                        out.print("<button type=\"submit\" name=\"RemoveFriend\" onclick=\"location.href='AddFriendServlet?qnum=1&pNum=");
                        out.print(patient.getUsername());
                        out.print("';\">Add Friend</button>");
                        out.println("</td></tr>");
                    }
                }
            }
        %>
        
        <button type="submit" name="CreateReview" onclick="location.href='patientHome.jsp'">Return to Main Page</button>
    </body>
</html>
