/*
-----------------------------------------------------------------------------------
 File        : LoginServlet.java
 Author(s)   : Schmidt Emmanuel, Zharkova Anastasia
 Date        : 19.10.2017
 Goal        : Servlet who is responsible for verify matching login and password
 -----------------------------------------------------------------------------------
 */
package com.mycompany.mvc.web;

import com.mycompany.mvc.services.dao.AuthenManagerLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

   @EJB
   private AuthenManagerLocal authenManager;

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
      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(request, response);
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
      // get values of data typed by user
      String username = request.getParameter("inputUsername");
      String password = request.getParameter("inputPassword");

      int idUser = authenManager.login(username, password);
      if (idUser >= 0) {
         request.getSession().setAttribute("id", idUser);

      }
      response.sendRedirect(request.getContextPath());
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
