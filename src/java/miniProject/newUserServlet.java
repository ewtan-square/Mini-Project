/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
                url = "/login.jsp";
            }
            else if (newUserType.equals("doctor")) {
                addDoctorHelper(request, response);
                url = "/login.jsp";
            }
            else {
                url = "/fancyError.jsp";
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
        String username = request.getParameter("username");
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
            throw new RuntimeException("Must Select Gender");
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
        if (province.equals("") || city.equals("") || postalcode.equals("") || streetAddress.equals("")) {
            throw new RuntimeException("Address is must be complete");
        }     
        
        ArrayList<Doctor> tmp1 = new ArrayList<Doctor>();
        ArrayList<Patient> tmp2 = new ArrayList<Patient>();
        ArrayList<Review> tmp3 = new ArrayList<Review>();
        Patient em = new Patient(
            -1, username, userFirstName, gender, userLastName, birthday,
            email, province, city, postalcode, streetAddress, 
            tmp1, tmp2, tmp3);
        // miniProject.addEmployee(em);
        // ArrayList ret = miniProject.getEmployees();
        // request.setAttribute("employeeList", ret);
    }
    
    protected void addDoctorHelper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
        String username = request.getParameter("username");
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
            throw new RuntimeException("Must Select Gender");
        }
        
        String birthday = request.getParameter("birthday");
        if (birthday.equals("")) {
            throw new RuntimeException("Birthday must be selected");
        }
        
        String licenseYear = request.getParameter("licenseYear");
        if (licenseYear.equals("")) {
            throw new RuntimeException("Email cannot be empty");
        }
        
        String specialization = request.getParameter("specialization");
        if (specialization.equals("")) {
            throw new RuntimeException("Email cannot be empty");
        }
        
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String postalcode = request.getParameter("postalCode");
        String streetAddress = request.getParameter("streetAddress");
        if (province.equals("") || city.equals("") || postalcode.equals("") || streetAddress.equals("")) {
            throw new RuntimeException("Address is must be complete");
        }     
        
        ArrayList<WorkAddress> tmp = new ArrayList<WorkAddress>();
        ArrayList<Patient> tmp2 = new ArrayList<Patient>();
        ArrayList<Review> tmp3 = new ArrayList<Review>();
        ArrayList<String> tmp4 = new ArrayList<String>();
        int ilicenseYear = Integer.parseInt(licenseYear);
        Doctor doctor = new Doctor(-1 , username, userFirstName, userLastName, 
                gender, birthday, ilicenseYear, province, city, postalcode, streetAddress, 
                tmp, tmp2, tmp3, tmp4,1);
        // miniProject.addEmployee(em);
        // ArrayList ret = miniProject.getEmployees();
        // request.setAttribute("employeeList", ret);
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
