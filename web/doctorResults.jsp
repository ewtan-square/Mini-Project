<%-- 
    Document   : doctorResults
    Created on : 17-Nov-2013, 12:47:30 AM
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
        <h2>Search Results</h2>
        <%
            if (doctorList != null) {
                out.println("<table border=1>");
                out.println("<tr><th>First Name</th><th>Last Name</th><th>gender</th><th>Work Address</th>"
                        + "<th>specialization</th><th>license year</th><th>Average Rating</th>");
                for (Doctor doc : doctorList) {
                    out.println("<tr><td>");
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
                        out.print(wa.getStreet());
                        out.print("</li>");
                    }
                    out.print("</ul></td><td></ul>");
                    for(String s : doc.getSpecializations())
                    {
                        out.print("<li>");
                        out.print(s);
                        out.print("</li>");
                    }
                    
                    out.print("</ul></td><td>");
                    out.print(doc.getLicenseYear());
                    out.print("</td><td>");
                    out.print(doc.getAverageStarRating());
                    out.println("</td><td></tr>");
                }
                out.println("</table>");
            }
        %>
    </body>
</html>
