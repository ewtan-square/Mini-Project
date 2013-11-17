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
        <h1>Work Address</h1>
        <h2>Add Work Address</h2>
        <form method="post" action="UpdateDoctorServlet?type=workaddress">
        Enter the following information:
        <p>   
            Province: <input type="text" name="province" size="20"><br/>
            City: <input type="text" name="city" size="20"><br/>
            Postal Code: <input type="text" name="postalCode" size="20"><br/>
            Street Address: <input type="text" name="streetAddress" size="20"><br/>
        </p> <input type="submit" value="Submit">
        </form>
        <%
            if (workAddressList != null) {
        %>
        <h2>Current Work Addresses</h2>
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
        <h1>Doctor Specialties</h1>
        <h2>Add a Specialty</h2>
        <form method="post" action="UpdateDoctorServlet?type=specialization">
        Enter the following information:
        <p>   
            Area: <input type="text" name="province" size="20"><br/>
        </p> <input type="submit" value="Submit">
        </form>
        <%
            if (specializationList != null) {
        %>
        <h2>Current Specialties</h2>
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
