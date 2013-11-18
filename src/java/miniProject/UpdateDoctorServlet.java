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
public class UpdateDoctorServlet extends HttpServlet {

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
        String updateRequest = request.getParameter("type");
        
        returnRequest = request;
        
        String username = (String)request.getSession().getAttribute("username");
        
        String url = "/doctorProfile.jsp";
        try {
            request.setAttribute("workAddressList", DoctorDBAO.getWorkAddresses(username));
            request.setAttribute("specializationList", DoctorDBAO.getSpecializations(username));

            if (updateRequest.equals("workaddress")) {
                addWorkAddressHelper(request, response);
                request.setAttribute("workAddressList", DoctorDBAO.getWorkAddresses(username));
            }
            else if (updateRequest.equals("specialization")) {
                addSpecializationHelper(request, response);
                request.setAttribute("specializationList", DoctorDBAO.getSpecializations(username));
            }
            else {
                url = "/doctorProfile.jsp";
            }
        } 
        catch (Exception e) {
            if (e instanceof FormIncompleteException) {
                if (updateRequest.equals("workaddress")) {
                    request = this.returnRequest;
                }
                else if (updateRequest.equals("specialization")) {
                    request = this.returnRequest;
                }
                request.setAttribute("formIncompleteException", e);
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
    
    protected void addWorkAddressHelper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException, FormIncompleteException {
        
        this.returnRequest = request;
        
        //String username = request.getAttribute("")
        
        String province = request.getParameter("province");
        this.returnRequest.setAttribute("province", (String)request.getParameter("province"));
        String city = request.getParameter("city");
        this.returnRequest.setAttribute("city", (String)request.getParameter("city"));
        String postalcode = request.getParameter("postalCode");
        this.returnRequest.setAttribute("postalCode", (String)request.getParameter("postalCode"));
        String streetAddress = request.getParameter("streetAddress");
        this.returnRequest.setAttribute("streetAddress", (String)request.getParameter("streetAddress"));
        
        if (province.equals("") || city.equals("") || postalcode.equals("") || streetAddress.equals("")) {
            throw new FormIncompleteException("Address must be complete");
        }  
        
        String username = (String)request.getSession().getAttribute("username");
        WorkAddress wa = new WorkAddress(username, province, city, postalcode, streetAddress);
        DoctorDBAO.newWorkAddress(username, wa);
        
        //ArrayList<WorkAddress> workAddressList ;// = miniProject.addWorkAddress(username);
        //returnRequest.setAttribute("workAddressList", workAddressList);
        
    }
    
    protected void addSpecializationHelper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException, FormIncompleteException {
        
        this.returnRequest = request;
        
        //String username = request.getAttribute("")
        
        String area = (String)request.getParameter("area");
        this.returnRequest.setAttribute("area", (String)request.getParameter("area"));
        
        if (area.equals("")) {
            throw new FormIncompleteException("Area field must be complete");
        }  
        
        String username = (String)request.getSession().getAttribute("username");
        DoctorDBAO.newSpecialization(username, area);
        
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
