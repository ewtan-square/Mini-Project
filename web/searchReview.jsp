<%-- 
    Document   : searchReview
    Created on : 17-Nov-2013, 10:43:35 PM
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
        <h2>Search for a review</h2>
        <form method="post" action="SearchReviewServlet">
            Date: <input type="date" name="reviewDate" size="20"><br>
            <input type="radio" name="dateRange" value="later">Selected date or later<br>
            <input type="radio" name="dateRange" value="earlier" checked="true">Selected date or earlier<br>
            Keyword: <input type="text" name="keyword" size="20"><br/>
            <input type="submit" value="Search">
    </body>
</html>
