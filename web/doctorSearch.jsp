<%-- 
    Document   : doctorSearch
    Created on : 16-Nov-2013, 11:35:58 PM
    Author     : Francis
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="miniProject.DoctorDBAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RateMyDoctor</title>
    </head>
    <%! ArrayList<String> specializations;%>
    <% specializations = DoctorDBAO.getAllSpecializations();%>
    <body>
        <h1>Doctor Search</h1>
        <form method="post" action="DoctorSearchServlet">
                First name: <input type="text" name="firstName" size="20"><br/> 
                Last name: <input type="text" name="lastName" size="20"><br/> 
                Gender: 
                <select name="gender"> 
                    <option value="M">Male</option>
                    <option value="F">Female</option>
                </select><br/>
                Specialization (You can add more later): <select name="specialization">
                    <option value =""></option>
                    <%
                    if(specializations != null)
                    {
                        for(String spec : specializations){
                            %>
                            <option value="<%=spec%>"><%=spec%></option>
                            <%
                        }
                    }
                    %>
                </select><br/>
                License Year (selected year or older): <select name="licenseYear"> 
                    <option value =""></option>
                    <%
                        for (int i = 1900; i<2013; i++) {
                    %>
                    <option value="<%=i%>"><%=i%></option>
                    <%
                        }
                    %>
               </select><br/> 
               <h3>Work Address</h3>
               Province: <input type="text" name="province" size="20"><br/>
               City: <input type="text" name="city" size="20"><br/>
               Postal Code: <input type="text" name="postalCode" size="20"><br/>
               Street Address: <input type="text" name="streetAddress" size="20"><br/>
               Average Star Rating (selected rating or higher) <select name="rating"> 
                   <option value =""></option>
                    <%
                        for (int i = 0; i<6; i++) {
                    %>
                    <option value="<%=i%>"><%=i%></option>
                    <%
                        }
                    %>
               </select><br/> 
               Recommended by a friend? <input type="checkbox" name="recommended" size="20">Yes<br>
               <input type="submit" value="Search">
               </form>
    </body>
</html>
