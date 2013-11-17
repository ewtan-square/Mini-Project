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
 * @author Francis
 */
public class DoctorSearchServlet extends HttpServlet {

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
        String url;
        try {
            /* TODO output your page here. You may use following sample code. */
            queryhelper(request, response);
            url = "/doctorResults.jsp";
        }catch (Exception e) {
            request.setAttribute("exception", e);
            url = "/fancyError.jsp";
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
    
    protected void queryhelper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("birthday");
        String specialization = request.getParameter("specialization");
        String strLicenseYear = request.getParameter("licenseYear");
        int licenseYear = 2013;
        if(!strLicenseYear.equals(""))
            licenseYear = Integer.parseInt(strLicenseYear);
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String postalCode = request.getParameter("postalCode");
        String streetAddress = request.getParameter("streetAddress");
        String strRating = request.getParameter("rating");
        int starRating = 0;
        if (!strRating.equals("")) {
            starRating = Integer.parseInt(strRating);
        }
        
        String strRecommended =  request.getParameter("recommended");
        Boolean recommendation = true;
        if(strRecommended == null)
            recommendation = false;
        
        ArrayList<Doctor> ret = new ArrayList<Doctor>();
        ArrayList<WorkAddress> tmp = new ArrayList<WorkAddress>();
        ArrayList<Patient> tmp2 = new ArrayList<Patient>();
        ArrayList<Review> tmp3 = new ArrayList<Review>();
        ArrayList<String> tmp4 = new ArrayList<String>();
        Doctor temp;temp = new Doctor(1,"rfmaducd","Ray","Maducdoc","male","1992-12-16",1998,"Ontario","PoopCity","1234","Osas",tmp, tmp2, tmp3, tmp4,1);
        ret.add(temp);
        request.setAttribute("doctorList", ret);
//        ArrayList ret = MiniProjectDBAO.queryDoctor(firstName, lastName, gender, dob, specialization,
//                licenseYear, province, city, postalCode, streetAddress, starRating, recommendation);
//                request.setAttribute("doctorList", ret);
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