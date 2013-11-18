<%-- 
    Document   : viewPatients
    Created on : 18-Nov-2013, 12:47:32 AM
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
    <body>
        <h2>Patient List</h2>
        <%
            if (patientList != null) {
                out.println("<table border=1>");
                out.println("<tr><th>Username</th><th>First Name</th><th>Last Name"
                        + "</th><th>email</th><th>Home Address</th></tr>");
                for(Patient pat : patientList){
                    out.println("<tr><td>");
                    out.print(pat.getUsername());
                    out.print("</td><td>");
                    out.print(pat.getFirstName());
                    out.print("</td><td>");
                    out.print(pat.getLastName());
                    out.print("</td><td>");
                    out.print(pat.getEmail());
                    out.print("</td><td>");
                    out.print(pat.getHomeStreet());
                    out.print(", ");
                    out.print(pat.getHomeCity());
                    out.print(", ");
                    out.print(pat.getHomeProvince());
                    out.print(", ");
                    out.print(pat.getHomePostalCode());
                    out.println("</td></tr>");
                }
                
                out.println("</table>");
            }
        %>
        <button type="submit" name="return" onclick="location.href='adminHome.jsp'">Return to Main Page</button>
    </body>
</html>
