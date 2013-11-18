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
    
    HttpServletRequest returnRequest;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newUserType = request.getParameter("type");
        
        String url = "/fancyError.jsp";
        try {
            newUserType.equals("doctor");
            if (newUserType.equals("patient")) {
                addPatientHelper(request, response);
                url = "/login.jsp";
            }
            else if (newUserType.equals("doctor")) {
                addDoctorHelper(request, response);
                url = "/login.jsp";
            }
            else {
                url = "/login.jsp";
            }
        } 
        catch (Exception e) {
            if (e instanceof FormIncompleteException) {
                if (newUserType.equals("patient")) {
                    url = "/createPatient.jsp";
                    request = this.returnRequest;
                }
                else if (newUserType.equals("doctor")) {
                    url = "/createDoctor.jsp";
                    request = this.returnRequest;
                }
            }
            else {
                request.setAttribute("exception", e);
                url = "/fancyError.jsp";
            }
        }
        finally {
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
    }
    
    protected void addPatientHelper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException, FormIncompleteException {
        
        this.returnRequest = request;
        
        String username = request.getParameter("username");
        this.returnRequest.setAttribute("username", (String)request.getParameter("username"));

        String userFirstName = request.getParameter("firstName");
        this.returnRequest.setAttribute("firstName", (String)request.getParameter("firstName"));
        String userLastName = request.getParameter("lastName");
        this.returnRequest.setAttribute("lastName", (String)request.getParameter("lastName"));
        
        String gender = request.getParameter("gender");
        this.returnRequest.setAttribute("gender", (String)request.getParameter("gender"));

        String birthday = request.getParameter("birthday");
        this.returnRequest.setAttribute("birthday", (String)request.getParameter("birthday"));

        String email = request.getParameter("email");
        this.returnRequest.setAttribute("email", (String)request.getParameter("email"));

        String province = request.getParameter("province");
        this.returnRequest.setAttribute("province", (String)request.getParameter("province"));
        String city = request.getParameter("city");
        this.returnRequest.setAttribute("city", (String)request.getParameter("city"));
        String postalcode = request.getParameter("postalCode");
        this.returnRequest.setAttribute("postalCode", (String)request.getParameter("postalCode"));
        String streetAddress = request.getParameter("streetAddress");
        this.returnRequest.setAttribute("streetAddress", (String)request.getParameter("streetAddress"));
        
        if (username.equals("")) {
            // Check if username exists already
            throw new FormIncompleteException("Username cannot be empty");
        }   
        if (userFirstName.equals("") || userLastName.equals("")) {
            throw new FormIncompleteException("Name cannot be empty");
        }
        if (gender.equals("")) {
            throw new FormIncompleteException("Must Select Gender");
        }
        if (birthday.equals("")) {
            throw new FormIncompleteException("Birthday must be selected");
        }
        if (email.equals("")) {
            throw new FormIncompleteException("Email cannot be empty");
        }
        if (province.equals("") || city.equals("") || postalcode.equals("") || streetAddress.equals("")) {
            throw new FormIncompleteException("Address is must be complete");
        }  
        
        ArrayList<Patient> tmp2 = new ArrayList<Patient>();
        Patient em = new Patient(
            username, userFirstName, gender, userLastName, birthday,
            email, province, city, postalcode, streetAddress);
        // miniProject.addEmployee(em);
        // ArrayList ret = miniProject.getEmployees();
        // request.setAttribute("employeeList", ret);
    }
    
    protected void addDoctorHelper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException, FormIncompleteException {
        this.returnRequest = request;
        
        String username = request.getParameter("username");
        this.returnRequest.setAttribute("username", (String)request.getParameter("username"));
        String userFirstName = request.getParameter("firstName");
        this.returnRequest.setAttribute("firstName", (String)request.getParameter("firstName"));
        String userLastName = request.getParameter("lastName");
        this.returnRequest.setAttribute("lastName", (String)request.getParameter("lastName"));
        String gender = request.getParameter("gender");
        this.returnRequest.setAttribute("gender", (String)request.getParameter("gender"));
        String birthday = request.getParameter("birthday");
        this.returnRequest.setAttribute("birthday", (String)request.getParameter("birthday"));
        String email = request.getParameter("email");
        this.returnRequest.setAttribute("email", (String)request.getParameter("email"));

        String province = request.getParameter("province");
        this.returnRequest.setAttribute("province", (String)request.getParameter("province"));
        String city = request.getParameter("city");
        this.returnRequest.setAttribute("city", (String)request.getParameter("city"));
        String postalcode = request.getParameter("postalCode");
        this.returnRequest.setAttribute("postalCode", (String)request.getParameter("postalCode"));
        String streetAddress = request.getParameter("streetAddress");
        this.returnRequest.setAttribute("streetAddress", (String)request.getParameter("streetAddress"));
                
        String licenseYear = request.getParameter("licenseYear");
        this.returnRequest.setAttribute("licenseYear", (String)request.getParameter("licenseYear"));
        String specialization = request.getParameter("specialization");
        this.returnRequest.setAttribute("specialization", (String)request.getParameter("specialization"));

        if (username.equals("")) {
            // Check if username exists already
            throw new FormIncompleteException("Username cannot be empty");
        }   
        if (userFirstName.equals("") || userLastName.equals("")) {
            throw new FormIncompleteException("Name cannot be empty");
        }
        if (gender.equals("")) {
            throw new FormIncompleteException("Must Select Gender");
        }
        if (birthday.equals("")) {
            throw new FormIncompleteException("Birthday must be selected");
        }
        if (licenseYear.equals("")) {
            throw new FormIncompleteException("Email cannot be empty");
        }
        if (province.equals("") || city.equals("") || postalcode.equals("") || streetAddress.equals("")) {
            throw new FormIncompleteException("Address is must be complete");
        }  
        ArrayList<WorkAddress> tmp = new ArrayList<WorkAddress>();
        ArrayList<Patient> tmp2 = new ArrayList<Patient>();
        ArrayList<Review> tmp3 = new ArrayList<Review>();
        ArrayList<String> tmp4 = new ArrayList<String>();
        int ilicenseYear = Integer.parseInt(licenseYear);

        Doctor doctor = new Doctor(username, userFirstName, userLastName, 
                gender, birthday, ilicenseYear, province, city, postalcode, streetAddress);

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
