<%-- 
    Document   : viewDoctors
    Created on : 18-Nov-2013, 12:19:55 AM
    Author     : Francis
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="miniProject.Doctor"%>
<%@page import="miniProject.WorkAddress"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RateMyDoctor</title>
    </head>
    <%! ArrayList<Doctor> doctorList;%>
    <% doctorList = (ArrayList<Doctor>) request.getAttribute("doctorList");%>
    <body>
        <h2>Doctor List</h2>
        <%
            if (doctorList != null) {
                out.println("<table border=1>");
                out.println("<tr><th>Username</th><th>First Name</th><th>Last Name</th><th>gender</th><th>Work Address</th>"
                        + "<th>Home Address</th><th>specialization</th><th>license year</th></tr>");
                for (Doctor doc : doctorList) {
                    out.println("<tr><td>");
                    out.print(doc.getUsername());
                    out.print("</td><td>");
                    out.print(doc.getFirstName());
                    out.print("</td><td>");
                    out.print(doc.getLastName());
                    out.print("</td><td>");
                    out.print(doc.getGender());
                    out.print("</td><td><ul>");
                    for(WorkAddress wa : doc.getWorkAddresses())
                    {
                        out.print("<li>");
                        out.print(wa.getStreet());
                        out.print(", ");
                        out.print(wa.getCity());
                        out.print(", ");
                        out.print(wa.getProvince());
                        out.print(", ");
                        out.print(wa.getPostalCode());
                        out.print("</li>");
                    }
                    out.print("</ul></td><td>");
                    out.print(doc.getHomeStreet());
                    out.print(", ");
                    out.print(doc.getHomeCity());
                    out.print(", ");
                    out.print(doc.getHomeProvince());
                    out.print(", ");
                    out.print(doc.getHomePostalCode());
                    out.print("</td><td><ul>");
                    
                    for(String s : doc.getSpecializations())
                    {
                        out.print("<li>");
                        out.print(s);
                        out.print("</li>");
                    }
                    
                    out.print("</ul></td><td>");
                    out.print(doc.getLicenseYear());
                    out.println("</td></tr>");
                }
            }
                
                out.println("</table>");
        %>
        <button type="submit" name="return" onclick="location.href='adminHome.jsp'">Return to Main Page</button>
    </body>
</html>
