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
public class DoctorResultsServlet extends HttpServlet {

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
        String strQueryNum = request.getParameter("qnum");
        int intQueryNum = Integer.parseInt(strQueryNum);
        
        String strDocUsername = request.getParameter("docNum");
        
        String url;
        try {
            if(intQueryNum == 1){
                //ArrayList ret = MiniProjectDBAO.showDoctorReviews(strDocUsername);
                ArrayList<Review> ret = new ArrayList<Review>();
                url = "/viewReviews.jsp";
                request.setAttribute("reviewList", ret);
                request.setAttribute("docUsername",strDocUsername);
            }
            else if(intQueryNum == 2){
                url = "/CreateReview.jsp";
                queryhelper(request, response);
            }
            else
                url="/fancyError.jsp";
        } catch (Exception e) {
            url="/fancyError.jsp";
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    protected void queryhelper(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, ClassNotFoundException {
        String strDocUsername = request.getParameter("docNum");
        if(strDocUsername == null){
            //QUERY WITH DOC INFO
//            Doctor doctor = MiniProjectDBAO.queryDoctor("","","","","",2013,"","","","",0,false);
//            request.setAttribute("doctor", ret);
        }
        else{
            //QUERY WITH USER ID
            //Doctor doctor = MiniProjectDBAO.queryDoctor(strDocUsername);
            //request.setAttribute("doctor", ret);
        }
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
