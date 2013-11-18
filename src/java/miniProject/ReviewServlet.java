/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Francis
 */
@WebServlet(name = "ReviewServlet", urlPatterns = {"/ReviewServlet"})
public class ReviewServlet extends HttpServlet {

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
        String strDocUsername = request.getParameter("docUsername");
        request.setAttribute("docUsername", strDocUsername);
        try {
            url = "/viewReviews.jsp";
//            if(strDocUsername == null || strDocUsername.equals("")){
//                url = "/fancyError.jsp";
//                throw new Exception("Doctor field is empty");
//            }
//            else
                queryhelper(request, response);
        }catch (Exception e) {
            request.setAttribute("exception", e);
            url = "/fancyError.jsp";
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
        protected void queryhelper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
            String strDocUsername = request.getParameter("docUsername");
            String strUsername = (String)request.getSession().getAttribute("username");
            String strRating = request.getParameter("rating");
            int rating = Integer.parseInt(strRating);
            String strRecommendation = request.getParameter("recommendation");
            Boolean recommendation = true;
            if(strRecommendation == null || strRecommendation.equals("not recommended"))
                recommendation = false;
            String comments = request.getParameter("comments");
            
            DoctorDBAO.newDoctorReview(strDocUsername,strUsername,rating,recommendation,comments);
            ArrayList ret = DoctorDBAO.getDoctorReviews(strDocUsername);
            request.setAttribute("reviewList", ret);
            
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
