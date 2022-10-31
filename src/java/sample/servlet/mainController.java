/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author xuhet
 */
public class mainController extends HttpServlet {

    public static String path = "index.html";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //lay email,password tu loginpage.jsp
            String s = request.getParameter("action");
            if (s == null || s.equals("")) {
                s = "search";
            }
            switch (s) {
                case "search":
                    path = "index.jsp";
                    break;
                case "login":
                    path = "LoginServlet";
                    break;
                case "register":
                    path = "registerServlet";
                    break;
                case "logout":
                    path = "logoutServlet";
                    break;
                case "changeprofile":
                    path = "changeProfile";
                    break;
                case "addtocart":
                    path = "addToCartServlet";
                    break;
                case "viewcart":
                    path = "viewCart.jsp";
                    break;
                case "update":
                    path = "updateCartServlet";
                    break;
                case "delete":
                    path = "deleteCartServlet";
                    break;
                case "saveOrder":
                    path = "saveShoppingCartServlet";
                    break;
                case "Search":
                    path = "personalPage.jsp?search=order";
                    break;
                case "manageAccounts":
                    path = "manageAccountsServlet";
                    break;
                case "updateStatusAccount":
                    path = "updateStatusAccountServlet";
                    break;
                case "searchaccount":
                    path = "searchAccountsServlet";
                    break;    
                case "manageOrders":
                    path = "manageOrdersServlet";
                    break;
                case "updateStatusOrder":
                    path = "updateStatusOrderServlet";
                    break;
                case "searchorder":
                    path = "searchOrdersServlet";
                    break;
                    
            }
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
