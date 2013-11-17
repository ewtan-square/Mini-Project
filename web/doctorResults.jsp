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
                        + "<th>specialization</th><th>license year</th><th>Average Rating</th><th colspan=\"2\">Reviews</th></tr>");
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
                    out.print("</td><td>");
                    out.print("<button type=\"submit\" name=\"ViewReview\" onclick=\"location.href='DoctorResultsServlet?qnum=1&docNum=");
                    out.print(doc.getUsername());
                    out.print("';\">View Reviews</button>");
                    out.print("</td><td>");
                    out.print("<button type=\"submit\" name=\"CreateReview\" onclick=\"location.href='DoctorResultsServlet?qnum=2&docNum=");
                    out.print(doc.getUsername());
                    out.print("';\">Create Review</button>");
                    out.println("</td></tr>");
                }
                out.println("</table>");
            }
        %>
    </body>
</html>
