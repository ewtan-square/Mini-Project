<%-- 
    Document   : CreateReview
    Created on : 16-Nov-2013, 9:27:06 PM
    Author     : Francis
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="miniProject.Doctor"%>
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
        <h1>Create a Review</h1>
        <form method="post" action="ReviewServlet">
            Enter your review:
            <p>
                Select Doctor: <select>
                    <%
                    if (doctorList != null) {
                        for(Doctor doc : doctorList)
                        {
                            out.print("<option value=\"");
                            out.print(doc.getLastName());
                            out.print(", ");
                            out.print(doc.getFirstName());
                            out.print("\">");
                            out.print(doc.getLastName());
                            out.print(", ");
                            out.print(doc.getFirstName());
                            out.println("</option>");
                        }
                    }
                    %>
                </select><br/> 
                Rating: <select>
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select><br/>
                <input type="radio" name="recommendation" value="recommended">recommended<br>
                <input type="radio" name="recommendation" value="not recommended">not recommended<br>
                Comments: <textarea rows="4" cols="50"></textarea><br/>
            <p> <input type="submit" value="Submit">
        </form>
    </body>
</html>
