<%-- 
    Document   : reviewResults
    Created on : 17-Nov-2013, 11:21:52 PM
    Author     : Francis
--%>

<%@page import="java.util.ArrayList"%>
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
    <body>
        <h2>Review Results</h2>
        <%
            if (reviewList != null) {
                out.println("<table border=1>");
                out.println("<tr><th>Doctor Username</th><th>Patient Username</th>"
                        + "<th>Date</th><th>Rating</th><th>Recommendation</th><th>Comment</th><th></th></tr>");
                for(Review rev : reviewList)
                {
                    out.println("<tr><td>");
                    out.print(rev.getDocUsername());
                    out.print("</td><td>");
                    out.print(rev.getPatientUsername());
                    out.print("</td><td>");
                    out.print(rev.getDate());
                    out.print("</td><td>");
                    out.print(rev.getRating());
                    out.print("</td><td>");
                    out.print(rev.getRecommendation());
                    out.print("</td><td>");
                    out.print(rev.getComment());
                    out.print("</td><td>");
                    out.print("<button type=\"submit\" name=\"DeleteReview\" onclick=\"location.href='ReviewResultsServlet?qnum=1&revNum=");
                    out.print(rev.getReviewID());
                    out.print("';\">Delete Review</button>");
                    out.print("</td></tr>");
                }
                out.println("</table>");
            }
        %>
        
        <button type="submit" name="CreateReview" onclick="location.href='adminHome.jsp'">Return to Main Page</button>
        <button type="submit" name="CreateReview" onclick="location.href='searchReview.jsp'">Return to Search</button>
    </body>
</html>
