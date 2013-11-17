/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniProject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ET
 */
public class newUserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newUserType = request.getParameter("type");

        String url = "/fancyError.jsp";
        try {
            if (newUserType.equals("patient")) {
                addPatientHelper(request, response);
                url = "/success.jsp";
            }
            else if (newUserType.equals("doctor")) {
                addDoctorHelper(request, response);
                url = "/success.jsp";
            }
        } 
        catch (Exception e) {
            request.setAttribute("exception", e);
            url = "/fancyError.jsp";
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    protected void addPatientHelper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
        String username = request.getParameter("firstName");
        if (username.equals("")) {
            // Check if username exists already
            throw new RuntimeException("Username cannot be empty");
        }        
        
        String userFirstName = request.getParameter("firstName");
        String userLastName = request.getParameter("lastName");
        if (userFirstName.equals("") || userLastName.equals("")) {
            throw new RuntimeException("Name cannot be empty");
        }
        
        String gender = request.getParameter("gender");
        if (gender.equals("")) {
            throw new RuntimeException("Must Select Gender!");
        }
        
        String birthday = request.getParameter("birthday");
        if (birthday.equals("")) {
            throw new RuntimeException("Birthday must be selected");
        }
        
        String email = request.getParameter("email");
        if (email.equals("")) {
            throw new RuntimeException("Email cannot be empty");
        }
        
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String postalcode = request.getParameter("postalCode");
        String streetAddress = request.getParameter("streetAddress");
        if (province.equals("") || city.equals("") || postalcode.equals("") || streetAddress.equals("") || ) {
            throw new RuntimeException("Address is incomplete");
        }     
        
        Employee em = new Employee(empID, empName, job, deptID, salary);
        Lab3DBAO.addEmployee(em);
        ArrayList ret = Lab3DBAO.getEmployees();
        request.setAttribute("employeeList", ret);
    }
    
    protected void addDoctorHelper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
