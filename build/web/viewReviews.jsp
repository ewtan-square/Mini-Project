<%-- 
    Document   : viewReviews
    Created on : 17-Nov-2013, 3:21:10 PM
    Author     : Francis
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="miniProject.Doctor"%>
<%@page import="miniProject.Review"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RateMyDoctor</title>
    </head>
    <%! ArrayList<Review> reviewList;%>
    <% reviewList = (ArrayList<Review>) request.getAttribute("reviewList");%>
    <%! String docUsername;%>
    <% docUsername = (String)request.getAttribute("docUsername"); %>
    <body>
        <%
        out.print("<h2>View Reviews for ");
        out.print(docUsername);
    %>
        </h2>
        <%
            if (reviewList != null) {
                out.println("<table border=1>");
                out.println("<tr><th>Patient ID</th><th>Date</th><th>Rating</th><th>Recommendation</th><th>Comment</th></tr>");
                for(Review rev : reviewList)
                {
                    out.println("<tr><td>");
                    out.print(rev.getPatientUsername());
                    out.print("</td><td>");
                    out.print(rev.getDate());
                    out.print("</td><td>");
                    out.print(rev.getRating());
                    out.print("</td><td>");
                    out.print(rev.getRecommendation());
                    out.print("</td><td>");
                    out.print(rev.getComment());
                    out.print("</td></tr>");
                }
                out.println("</table>");
            }
        %>
        
        <button type="submit" name="CreateReview" onclick="location.href='patientHome.jsp'">Return to Main Page</button>
    </body>
</html>
