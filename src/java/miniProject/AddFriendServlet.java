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
public class AddFriendServlet extends HttpServlet {

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
        String username = (String)request.getSession().getAttribute("username");
        String alias = "";
        int intQueryNum = Integer.parseInt(strQueryNum);
        String accType = (String)request.getSession().getAttribute("accType");
        String url="";
        
        if(!accType.equals("patient"))
        {
            url="/fancyError.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
            return;
        }
        String patientUsername = request.getParameter("pNum");
        try {
            url = "/friendResults.jsp";
            //Add Friend
            if(intQueryNum == 1){
                //ADD AND RETURN FRIENDS OF PATIENT
                //MiniProjectDBAO.addFriend(username,patientUsername);
                //RETURN PATIENTS WHO ARE NOT FRIENDS
                //ArrayList ret = MiniProjectDBAO.queryPatients(username,alias);
                
                ArrayList<Patient> friends = new ArrayList<Patient>();
                ArrayList<Patient> ret = new ArrayList<Patient>();
                Patient temp;
                temp = new Patient("harvey","","","","","","","","","");
                friends.add(temp);
                request.setAttribute("patientList",ret);
                request.setAttribute("friendList",friends);
            }
            //Remove Friend
            else if(intQueryNum == 2){
                //REMOVE AND RETURN FRIENDS OF PATIENT
                //ArrayList friends = MiniProjectDBAO.removeFriend(username,patientUsername);
                //RETURN PATIENTS WHO ARE NOT FRIENDS
                //ArrayList ret = MiniProjectDBAO.queryPatients(username,alias);
                
                ArrayList<Patient> friends = new ArrayList<Patient>();
                ArrayList<Patient> ret = new ArrayList<Patient>();
                Patient temp;
                temp = new Patient("harvey","","","","","","","","","");
                ret.add(temp);
                request.setAttribute("patientList",ret);
                request.setAttribute("friendList",friends);
            }
            /* TODO output your page here. You may use following sample code. */
        } catch (Exception e) {
            url="/fancyError.jsp";
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
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
