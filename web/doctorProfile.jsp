<%-- 
    Document   : doctorProfile
    Created on : Nov 17, 2013, 1:58:39 PM
    Author     : ET
--%>

<%@page import="miniProject.WorkAddress"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update your Info!</title>
    </head>
    
    <%! ArrayList<WorkAddress> workAddressList;%>
    <%! ArrayList<String> specializationList;%>
    <% workAddressList = (ArrayList<WorkAddress>) request.getAttribute("workAddressList");%>
    <% specializationList = (ArrayList<String>) request.getAttribute("specializationList");%>
    
    <body>
        <h2>Work Addresses</h2>
        <%
            if (workAddressList != null) {
        %>
        <table border=1><tr><th>Province</th><th>City</th><th>Postal Code</th><th>Street</th></tr>
            <%
                for (WorkAddress wa : workAddressList) {
            %>
            <tr>
                <td><%= wa.getProvince()%></td>
                <td><%= wa.getCity()%></td>
                <td><%= wa.getPostalCode()%></td>
                <td><%= wa.getStreet()%></td>
            </tr>
            <%
                }
            %>
        <%
            }
        %></table>
        <h2>Specialties</h2>
        <%
            if (specializationList != null) {
        %>
        <table border=1><tr><th>Specialization</th></tr>
            <%
                for (String specialization : specializationList) {
            %>
            <tr>
                <td><%= specialization%></td>
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
